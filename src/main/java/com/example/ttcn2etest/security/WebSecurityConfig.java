package com.example.ttcn2etest.security;

import com.example.ttcn2etest.config.CorsConfigFilter;
import com.example.ttcn2etest.service.auth.JwtTokenProvider;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    private final UserDetailsService myUserDetailService;
    private final AuthEntryPointJwt unauthorizedHandler;
    private final JwtTokenProvider jwtTokenProvider;
    private final CorsConfigFilter corsConfigFilter;

    @Autowired
    public WebSecurityConfig(UserDetailsService myUserDetailService, AuthEntryPointJwt unauthorizedHandler, JwtTokenProvider jwtTokenProvider, CorsConfigFilter corsConfigFilter) {
        this.myUserDetailService = myUserDetailService;
        this.unauthorizedHandler = unauthorizedHandler;
        this.jwtTokenProvider = jwtTokenProvider;
        this.corsConfigFilter = corsConfigFilter;
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter(jwtTokenProvider);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(myUserDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .requestMatchers(HttpMethod.GET, "/api/paymentVNPAY/vn-pay-callback/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/paymentVNPAY/vn-pay").permitAll()
                .requestMatchers( "vnpay_jsp/vnpay_return.jsp/**").permitAll()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/client/**").permitAll()
                .requestMatchers("/user/auth/**").permitAll()
                .requestMatchers("/file/**").permitAll()
                .requestMatchers("/user/forgot/password").permitAll()
                .requestMatchers("/consulting/registration").permitAll()
                .requestMatchers("consulting/registration").permitAll()
                .requestMatchers("/order/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/order/{orderId}/update-status").authenticated()
                .requestMatchers("/classRoom/**").permitAll()
                .requestMatchers("/document/**").permitAll()
                .requestMatchers("/class-user/**").permitAll()
                .requestMatchers("/order/direct-payment").permitAll()
                .requestMatchers("/api/payment-online/**").permitAll() // Bỏ xác thực cho endpoint này
                .requestMatchers(request -> {
                    if (request.getMethod().equals(HttpMethod.GET.toString())) {
                        return new RegexRequestMatcher("/(document|news|slide|service|display|exam/schedule|...)/(all|\\d+)", null).matches(request);
                    }
                    return false;
                }).permitAll()
                .anyRequest().authenticated();

        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore((Filter) corsConfigFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}

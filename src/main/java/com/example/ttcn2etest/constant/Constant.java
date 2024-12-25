package com.example.ttcn2etest.constant;

public class Constant {

    /// STATUS ORDER
    public static final String WAITING_CONFIRM_ORDER_STATUS = "WAITING_CONFIRM"; // trạng thái chờ xác nhận
    public static final String DELIVERING_ORDER_STATUS = "DELIVERING"; //trạng thái đang giao
    public static final String DELIVERED_ORDER_STATUS ="DELIVERED"; //trạng thái đã giao
    public static final String CANCEL_ORDER_STATUS ="CANCEL"; //trạng thái hủy đơn
    public static final String REJECT_ORDER_STATUS ="REJECT"; //trạng thái trả hàng
    public static final String NOT_AVAIABLE_ORDER_STATUS = "NOT_AVAIABLE"; //trạng thái hàng không đủ
    public static final String ORDER_FAILED_IMAGE = "ORDER_FAILED_IMAGE";// trạng thái thanh toán không thành công (thông tin hình ảnh không chính xác admin không chấp nhận hình ảnh)
    public static final String ORDER_FAILED_VNPAY = "ORDER_FAILED_VNPAY";//trạng thái thanh toán không thành công từ vnpay (sẽ được trả lại )
    public static final String ORDER_FAILED = "ORDER_FAILED";//trạng thái thanh toán không được xác nhận từ admin


    // PAYMENT_METHOD ORDER
    public static final String DIRECT_PAYMENT = "DIRECT_PAYMENT"; //trạng thái thanh toán trực tiếp có xác nhận đơn hàng từ phía admin
    public static final String ONLINE_PAYMENT_IMAGE = "ONLINE_PAYMENT_IMAGE"; //trajng thái thanh toán online có thông qua quét mã có hình ảnh
    public static final String VNPAY_ONLINE_PAYMENT = "VNPAY_ONLINE_PAYMENT"; //trạng thái thanh toán qua vnpay chỉ cần thành công

}

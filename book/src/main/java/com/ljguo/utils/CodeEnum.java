package com.ljguo.utils;

/**
 * 状态码维护
 */
public enum CodeEnum {
    SUCCESS(200,"成功"),
    ERROR(500,"错误"),
    POWER(300,"TOKEN验证失败");

    //状态码
    public final Integer CODE;
    //消息提示
    public  final  String MSG;

    CodeEnum(Integer code, String msg) {
        this.CODE = code;
        this.MSG = msg;
    }
}

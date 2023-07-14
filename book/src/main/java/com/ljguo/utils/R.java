package com.ljguo.utils;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 统一响应类
 */
@Data
@AllArgsConstructor
public class R {

    //消息提示
    private String msg;
    //状态码
    private Integer code;
    //数据
    private Object data;


    //成功01
    public static R ok(){
        return new R(CodeEnum.SUCCESS.MSG,CodeEnum.SUCCESS.CODE,null);
    }
    //成功02
    public static R ok(String msg){
        return new R(msg,CodeEnum.SUCCESS.CODE,null);
    }
    //成功03
    public static R ok(String msg,Integer code ,Object data){
        return new R(msg,code,data);
    }
    //成功04
    public static R ok(Object data){
        return new R(CodeEnum.SUCCESS.MSG,CodeEnum.SUCCESS.CODE,data);
    }

    //错误01
    public static R error(){
        return new R(CodeEnum.ERROR.MSG,CodeEnum.ERROR.CODE,null);
    }
    //错误02
    public static R error(String msg){
        return new R(msg,CodeEnum.ERROR.CODE,null);
    }
    //错误03
    public static R error(String msg,Integer code ,Object data){
        return new R(msg,code,data);
    }


    public static R error(String msg,Integer code){
        return new R(msg,code,null);
    }
    //错误04
    public static R error(Object data){
        return new R(CodeEnum.ERROR.MSG,CodeEnum.ERROR.CODE,data);
    }


    public static R ok(String msg, Object data){
        return new R(msg,CodeEnum.SUCCESS.CODE,data);
    }


}
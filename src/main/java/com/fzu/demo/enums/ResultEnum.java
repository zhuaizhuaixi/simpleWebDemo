package com.fzu.demo.enums;

/**
 * Created by linqz on 2017/8/6.
 */
public enum ResultEnum {

    //业务相关
    SUCCESS(1, 60001, "操作成功"),
    LOGIN_SUCCESS(1, 60002, "登陆成功"),
    LOGINOUT_SUCCESS(1, 60003, "登出成功"),
    CHANGE_PWD_SUCCESS(1, 60004, "密码修改成功"),
    CHANGE_PWD_FAIL(-1, 60005, "密码错误，密码修改失败"),
    USERNAME_EXIST(-1, 60006, "该用户名已被占用"),


    UNKNOW_ERROR(-1, 90001, "操作失败"),
    SYSTEM_ERROR(-1, 90002, "系统错误，请联系管理员。"),

    LOGIN_NAME_OR_PASS_WORD_ERROR(-1, 80001, "用户名或者密码错误"),


    //系统相关
    SYS_UNSUPPORT_PROTOCAL(-1, 10001, "不支持的协议版本"),
    SYS_NO_PERMISSION(-1, 10002, "没有权限"),
    SYS_CONTENT_TYPE_ERR(-1, 10003, "Content-Type错误"),
    SYS_CONTENT_FORMAT_ERR(-1, 10004, "报文格式错误"),
    SYS_FUNC_EMPTY(-1, 10005, "功能码不能为空"),
    SYS_CZZD_EMPTY(-1, 10006, "操作站点不能为空");


    private Integer code;
    private String message;
    private Integer errcode;

    ResultEnum(Integer code, Integer errcode, String msg) {
        this.code = code;
        this.errcode = errcode;
        this.message = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }
}

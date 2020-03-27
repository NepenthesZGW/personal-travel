package com.zgw.personaltravel.entity;

public class ResultBody<E> {

    private Integer code;
    private String  msg;
    private E  body;

    public final static ResultBody error=new ResultBody().setCode(500).setMsg("失败");
    public final static ResultBody success=new ResultBody().setCode(200).setMsg("成功");

    @Override
    public String toString() {
        return "ResultBody{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    public E getBody() {
        return body;
    }

    public ResultBody setBody(E body) {
        this.body = body;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public ResultBody setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResultBody setMsg(String msg) {
        this.msg = msg;
        return this;
    }

}

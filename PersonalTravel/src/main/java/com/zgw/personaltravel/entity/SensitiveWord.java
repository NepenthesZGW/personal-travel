package com.zgw.personaltravel.entity;

import java.util.List;

public class SensitiveWord {

    private Long log_id;//请求唯一id
    private Long error_code;//错误提示码，失败才返回，成功不返回
    private String error_msg;//错误提示信息，失败才返回，成功不返回
    private String conclusion;//审核结果，可取值：合规、不合规、疑似、审核失败
    private Integer conclusionType;//审核结果类型，可取值1.合规，2.不合规，3.疑似，4.审核失败
    private List<Data> data;//不合规/疑似/命中白名单项详细信息。响应成功并且conclusion为疑似或不合规或命中白名单时才返回，响应失败或conclusion为合规且未命中白名单时不返回。

    @Override
    public String toString() {
        return "SensitiveWord{" +
                "log_id=" + log_id +
                ", error_code=" + error_code +
                ", error_msg='" + error_msg + '\'' +
                ", conclusion='" + conclusion + '\'' +
                ", conclusionType=" + conclusionType +
                ", data=" + data +
                '}';
    }

    public Long getLog_id() {
        return log_id;
    }

    public void setLog_id(Long log_id) {
        this.log_id = log_id;
    }

    public Long getError_code() {
        return error_code;
    }

    public void setError_code(Long error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public Integer getConclusionType() {
        return conclusionType;
    }

    public void setConclusionType(Integer conclusionType) {
        this.conclusionType = conclusionType;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }


}
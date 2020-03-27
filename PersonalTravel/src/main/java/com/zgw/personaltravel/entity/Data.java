package com.zgw.personaltravel.entity;

import java.util.List;

    public class Data {
        private Integer error_code;//内层错误提示码，底层服务失败才返回，成功不返回
        private String error_msg;//内层错误提示信息，底层服务失败才返回，成功不返回
        private Integer type;//审核主类型，11：百度官方违禁词库、12：文本反作弊、13:自定义文本黑名单、14:自定义文本白名单
        private Integer subType;//审核子类型，此字段需参照type主类型字段决定其含义：当type=11时subType取值含义：
        // 0:百度官方默认违禁词库
        // 当type=12时subType取值含义：
        // 0:低质灌水、1:暴恐违禁、2:文本色情、3:政治敏感、4:恶意推广、5:低俗辱骂
        //  当type=13时subType取值含义：
        //  0:自定义文本黑名单
        // 当type=14时subType取值含义：
        // 0:自定义文本白名单
        private String msg;//不合规项描述信息
        private Float probability;//不合规项置信度
        private String datasetName;//违规项目所属数据集名称
        private List<Hits> hits;//命中关键词信息


        @Override
        public String toString() {
            return "Data{" +
                    "error_code=" + error_code +
                    ", error_msg='" + error_msg + '\'' +
                    ", type=" + type +
                    ", subType=" + subType +
                    ", msg='" + msg + '\'' +
                    ", probability=" + probability +
                    ", datasetName='" + datasetName + '\'' +
                    ", hits=" + hits +
                    '}';
        }

        public Integer getError_code() {
            return error_code;
        }

        public void setError_code(Integer error_code) {
            this.error_code = error_code;
        }

        public String getError_msg() {
            return error_msg;
        }

        public void setError_msg(String error_msg) {
            this.error_msg = error_msg;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getSubType() {
            return subType;
        }

        public void setSubType(Integer subType) {
            this.subType = subType;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Float getProbability() {
            return probability;
        }

        public void setProbability(Float probability) {
            this.probability = probability;
        }

        public String getDatasetName() {
            return datasetName;
        }

        public void setDatasetName(String datasetName) {
            this.datasetName = datasetName;
        }

        public List<Hits> getHits() {
            return hits;
        }

        public void setHits(List<Hits> hits) {
            this.hits = hits;
        }

    }

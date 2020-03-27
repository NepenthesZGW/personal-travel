package com.zgw.personaltravel.entity;

import java.util.List;

    public class Hits{
        private Float probability;//人脸相似度
        private String datasetName;//人脸所属数据集名称
        private List<String> words;//违规文本关键字

        @Override
        public String toString() {
            return "Hits{" +
                    "probability=" + probability +
                    ", datasetName='" + datasetName + '\'' +
                    ", words=" + words +
                    '}';
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

        public List<String> getWords() {
            return words;
        }

        public void setWords(List<String> words) {
            this.words = words;
        }
    }


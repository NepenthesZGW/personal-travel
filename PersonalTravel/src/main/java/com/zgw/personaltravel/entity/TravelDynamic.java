package com.zgw.personaltravel.entity;

import java.util.List;

public class TravelDynamic<E> {

    private Travellog travellog;
    private Integer imageCount;
    private List<E> images;
    private String  content;

    @Override
    public String toString() {
        return "TravelDynamic{" +
                "travellog=" + travellog +
                ", imageCount=" + imageCount +
                ", images=" + images +
                ", content='" + content + '\'' +
                '}';
    }

    public Travellog getTravellog() {
        return travellog;
    }

    public void setTravellog(Travellog travellog) {
        this.travellog = travellog;
    }

    public Integer getImageCount() {
        return imageCount;
    }

    public void setImageCount(Integer imageCount) {
        this.imageCount = imageCount;
    }

    public List<E> getImages() {
        return images;
    }

    public void setImages(List<E> images) {
        this.images = images;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package com.zgw.personaltravel.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * travellog
 * @author 
 */
public class Travellog implements Serializable {
    private Integer id;

    private String title;

    private Date logdate;

    private String overt;

    private String resource;

    private String introduction;

    private Integer createid;

    private String place;

    private Integer star;

    private String other;

    private String nickname;

    private String headportrait;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getLogdate() {
        return logdate;
    }

    public void setLogdate(Date logdate) {
        this.logdate = logdate;
    }

    public String getOvert() {
        return overt;
    }

    public void setOvert(String overt) {
        this.overt = overt;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getCreateid() {
        return createid;
    }

    public void setCreateid(Integer createid) {
        this.createid = createid;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadportrait() {
        return headportrait;
    }

    public void setHeadportrait(String headportrait) {
        this.headportrait = headportrait;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Travellog other = (Travellog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getLogdate() == null ? other.getLogdate() == null : this.getLogdate().equals(other.getLogdate()))
            && (this.getOvert() == null ? other.getOvert() == null : this.getOvert().equals(other.getOvert()))
            && (this.getResource() == null ? other.getResource() == null : this.getResource().equals(other.getResource()))
            && (this.getIntroduction() == null ? other.getIntroduction() == null : this.getIntroduction().equals(other.getIntroduction()))
            && (this.getCreateid() == null ? other.getCreateid() == null : this.getCreateid().equals(other.getCreateid()))
            && (this.getPlace() == null ? other.getPlace() == null : this.getPlace().equals(other.getPlace()))
            && (this.getStar() == null ? other.getStar() == null : this.getStar().equals(other.getStar()))
            && (this.getOther() == null ? other.getOther() == null : this.getOther().equals(other.getOther()))
            && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
            && (this.getHeadportrait() == null ? other.getHeadportrait() == null : this.getHeadportrait().equals(other.getHeadportrait()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getLogdate() == null) ? 0 : getLogdate().hashCode());
        result = prime * result + ((getOvert() == null) ? 0 : getOvert().hashCode());
        result = prime * result + ((getResource() == null) ? 0 : getResource().hashCode());
        result = prime * result + ((getIntroduction() == null) ? 0 : getIntroduction().hashCode());
        result = prime * result + ((getCreateid() == null) ? 0 : getCreateid().hashCode());
        result = prime * result + ((getPlace() == null) ? 0 : getPlace().hashCode());
        result = prime * result + ((getStar() == null) ? 0 : getStar().hashCode());
        result = prime * result + ((getOther() == null) ? 0 : getOther().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getHeadportrait() == null) ? 0 : getHeadportrait().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", logdate=").append(logdate);
        sb.append(", overt=").append(overt);
        sb.append(", resource=").append(resource);
        sb.append(", introduction=").append(introduction);
        sb.append(", createid=").append(createid);
        sb.append(", place=").append(place);
        sb.append(", star=").append(star);
        sb.append(", other=").append(other);
        sb.append(", nickname=").append(nickname);
        sb.append(", headportrait=").append(headportrait);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
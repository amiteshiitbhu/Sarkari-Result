package com.nirmallabs.sarkariresult.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Result implements Serializable {

    @SerializedName("nameOfPost")
    @Expose
    private String nameOfPost;

    @SerializedName("shortInformation")
    @Expose
    private String shortInformation;

    @SerializedName("lastDate")
    @Expose
    private String lastDate;

    @SerializedName("applyLink")
    @Expose
    private String applyLink;

    public Result() {
        //
    }

    public Result(String nameOfPost, String shortInformation, String lastDate, String applyLink) {
        this.nameOfPost = nameOfPost;
        this.shortInformation = shortInformation;
        this.lastDate = lastDate;
        this.applyLink = applyLink;
    }

    public String getNameOfPost() {
        return nameOfPost;
    }

    public void setNameOfPost(String nameOfPost) {
        this.nameOfPost = nameOfPost;
    }

    public String getShortInformation() {
        return shortInformation;
    }

    public void setShortInformation(String shortInformation) {
        this.shortInformation = shortInformation;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getApplyLink() {
        return applyLink;
    }

    public void setApplyLink(String applyLink) {
        this.applyLink = applyLink;
    }
}

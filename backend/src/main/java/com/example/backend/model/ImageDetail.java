package com.example.backend.model;

public class ImageDetail {
    private int id;
    private String url;

    public ImageDetail() {}

    public ImageDetail(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ImageDetail{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}

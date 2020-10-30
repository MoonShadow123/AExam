package com.example.aexam.bean;

import com.example.aexam.R;

import java.util.ArrayList;
import java.util.List;

public class News {
    private String title;
    private String content;
    private String time;
    private int comments;
    private int imgId;

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }


    public static List<News> getList() {
        List<News> list = new ArrayList<>();
        int[] imgs = {R.drawable.dang1, R.drawable.dang2, R.drawable.dang3, R.drawable.dang4, R.drawable.dang5};

        for (int i = 0; i < imgs.length; i++) {
            News news = new News();
            news.setImgId(imgs[i]);
            list.add(news);
        }
        return list;
    }
}

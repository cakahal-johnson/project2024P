package com.example.cakahalnews;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class headNews {

    private String status;
    private String totalResults;
    private ArrayList<ModelClass> articles;

    public headNews(String status, String totalResults, ArrayList<ModelClass> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<ModelClass> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<ModelClass> articles) {
        this.articles = articles;
    }
}

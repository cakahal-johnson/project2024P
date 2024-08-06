package com.example.newsapiclass;

import java.io.Serializable;

public class Source implements Serializable {
    String id = "";
    String name = "";

    // getter and setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

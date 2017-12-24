package com.example.xfr.myapplication;

/**
 * 笔记类
 * Created by xfr on 2017/12/23.
 */

public class Note {

    private int id;

    private String time;

    private String content;

    public Note(int id, String time, String content) {
        this.id = id;
        this.time = time;
        this.content = content;
    }

    public int getId(){
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

}

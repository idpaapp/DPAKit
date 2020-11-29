package com.dpa_me.dpakit.Models;

import java.io.Serializable;

public class SimpleModel implements Serializable {
    private int id;
    private String title;
    private String detail_info;
    private String drawable;
    private String options;
    private int color;
    private boolean selected = false;

    public SimpleModel(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getColor() {
        return color;
    }

    public SimpleModel(int id, String title, String options) {
        this.id = id;
        this.title = title;
        this.options = options;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetail_info(String detail_info) {
        this.detail_info = detail_info;
    }

    public void setDrawable(String drawable) {
        this.drawable = drawable;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getOptions() {
        return options;
    }

    public boolean isSelected() {
        return selected;
    }

    public int getId() {
        return id;
    }

    public String getStringId() {
        return String.valueOf(id);
    }

    public String getTitle() {
        return title;
    }

    public String getDrawable() {
        return drawable;
    }

    public String getDetail_info() {
        return detail_info == null ? "" : detail_info;
    }
}

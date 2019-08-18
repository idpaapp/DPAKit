package com.dpa_me.dpakit.Models;

public class SimpleItem {
    private int id;
    private String Title;
    private String Cost;
    private String Description;
    private int Count;

    public SimpleItem(int _id, String _title){
        id = _id;
        Title = _title;
    }

    public SimpleItem(int _id, String _title, int _count){
        id = _id;
        Title = _title;
        Count = _count;
    }

    public SimpleItem(int id, String title, String cost, String description) {
        this.id = id;
        Title = title;
        Cost = cost;
        Description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return Title;
    }

    public String getCost() {
        return Cost;
    }

    public String getDescription() {
        return Description;
    }

    public int getCount() {
        return Count;
    }
}

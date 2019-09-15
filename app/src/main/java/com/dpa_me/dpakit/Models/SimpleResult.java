package com.dpa_me.dpakit.Models;

import java.util.ArrayList;

public class SimpleResult {
    public boolean Success;
    public String message;
    public String rValue;
    private ArrayList<SimpleModel> models;

    public ArrayList<SimpleModel> getModels() {
        return models;
    }
}

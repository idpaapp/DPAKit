package com.dpa_me.dpakit.Models;

import java.util.ArrayList;

public class ConfigModel {
    private String projectId;
    private String environmentId;
    private String type;
    private String id;
    private String createdAt;
    private String updatedAt;
    private ArrayList<ConfigValue> value;

    public String getProjectId() {
        return projectId;
    }

    public String getEnvironmentId() {
        return environmentId;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public ArrayList<ConfigValue> getValue() {
        return value;
    }
}

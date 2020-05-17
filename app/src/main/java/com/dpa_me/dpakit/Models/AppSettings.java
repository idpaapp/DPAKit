package com.dpa_me.dpakit.Models;

public class AppSettings {
    private String _id;
    private String AppPackage;
    private String ApiURL;
    private String MediaURL;
    private boolean MaintenanceBreak;
    private int MinSupportVersion;
    private int MaxSupportVersion;

    public String get_id() {
        return _id;
    }

    public String getAppPackage() {
        return AppPackage;
    }

    public String getApiURL() {
        return ApiURL;
    }

    public String getMediaURL() {
        return MediaURL;
    }

    public boolean isMaintenanceBreak() {
        return MaintenanceBreak;
    }

    public int getMinSupportVersion() {
        return MinSupportVersion;
    }

    public int getMaxSupportVersion() {
        return MaxSupportVersion;
    }
}

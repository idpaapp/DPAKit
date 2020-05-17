package com.dpa_me.dpakit.Models;

public class AppSettings {
    private String _id;
    private String AppPackage;
    private String ApiURL;
    private String MediaURL;
    private boolean MaintenanceBreak;
    private boolean SupportVersion;

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

    public boolean isSupportVersion() {
        return SupportVersion;
    }
}

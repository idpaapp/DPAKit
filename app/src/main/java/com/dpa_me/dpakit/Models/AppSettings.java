package com.dpa_me.dpakit.Models;

public class AppSettings {
    private String ServerAddress;
    private String ApiVersion;
    private boolean MaintenanceBreak;

    public String getServerAddress() {
        return ServerAddress;
    }

    public String getApiVersion() {
        return ApiVersion;
    }

    public boolean isMaintenanceBreak() {
        return MaintenanceBreak;
    }
}

package com.TaskCyberMAK.api.dto;


import lombok.Data;

@Data
public class SubscriberProfile {

    private String usageType;
    private String bearerService;
    private String teleservice;

    public String getUsageType() {
        return usageType;
    }

    public void setUsageType(String usageType) {
        this.usageType = usageType;
    }

    public String getBearerService() {
        return bearerService;
    }

    public void setBearerService(String bearerService) {
        this.bearerService = bearerService;
    }

    public String getTeleservice() {
        return teleservice;
    }

    public void setTeleservice(String teleservice) {
        this.teleservice = teleservice;
    }
}

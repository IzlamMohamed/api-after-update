package com.TaskCyberMAK.api.dto;

import lombok.Data;

@Data

public class Hotline {

    private String hotlineType;
    private String hotlineNumber;
    private String hotlineStatus;

    public String getHotlineType() {
        return hotlineType;
    }

    public void setHotlineType(String hotlineType) {
        this.hotlineType = hotlineType;
    }

    public String getHotlineNumber() {
        return hotlineNumber;
    }

    public void setHotlineNumber(String hotlineNumber) {
        this.hotlineNumber = hotlineNumber;
    }

    public String getHotlineStatus() {
        return hotlineStatus;
    }

    public void setHotlineStatus(String hotlineStatus) {
        this.hotlineStatus = hotlineStatus;
    }
}

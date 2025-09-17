package com.TaskCyberMAK.api.dto;

import lombok.Data;

@Data
public class Roaming {

    private String incomingRoamingIndicator;

    public String getIncomingRoamingIndicator() {
        return incomingRoamingIndicator;
    }

    public void setIncomingRoamingIndicator(String incomingRoamingIndicator) {
        this.incomingRoamingIndicator = incomingRoamingIndicator;
    }

    public String getOutgoingRoamingIndicator() {
        return outgoingRoamingIndicator;
    }

    public void setOutgoingRoamingIndicator(String outgoingRoamingIndicator) {
        this.outgoingRoamingIndicator = outgoingRoamingIndicator;
    }

    private String outgoingRoamingIndicator;
}

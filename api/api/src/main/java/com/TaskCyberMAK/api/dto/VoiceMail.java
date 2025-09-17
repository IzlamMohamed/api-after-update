package com.TaskCyberMAK.api.dto;

import lombok.Data;

@Data

public class VoiceMail {

    private String cfb;
    private String cfnrc;
    private String sfnry;

    public String getCfb() {
        return cfb;
    }

    public void setCfb(String cfb) {
        this.cfb = cfb;
    }

    public String getCfnrc() {
        return cfnrc;
    }

    public void setCfnrc(String cfnrc) {
        this.cfnrc = cfnrc;
    }

    public String getSfnry() {
        return sfnry;
    }

    public void setSfnry(String sfnry) {
        this.sfnry = sfnry;
    }

    public String getCfu() {
        return cfu;
    }

    public void setCfu(String cfu) {
        this.cfu = cfu;
    }

    private String cfu;
}

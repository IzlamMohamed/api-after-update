package com.TaskCyberMAK.api.dto;

import lombok.Data;


@Data
public class NormalViewResponse {


    private String mnp;
    private String registration;
    private String roaming;
    private String internationalAccess;
    private String subscriberProfile;
    private String msisdn;
    private String imsi;
    private String operatorCallWaitingActivation;
    private String userCallWaitingActivation;
    private Hotline hotline;
    private VoiceMail voiceMail;

    public String getMnp() {
        return mnp;
    }

    public void setMnp(String mnp) {
        this.mnp = mnp;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getRoaming() {
        return roaming;
    }

    public void setRoaming(String roaming) {
        this.roaming = roaming;
    }

    public String getInternationalAccess() {
        return internationalAccess;
    }

    public void setInternationalAccess(String internationalAccess) {
        this.internationalAccess = internationalAccess;
    }

    public String getSubscriberProfile() {
        return subscriberProfile;
    }

    public void setSubscriberProfile(String subscriberProfile) {
        this.subscriberProfile = subscriberProfile;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getOperatorCallWaitingActivation() {
        return operatorCallWaitingActivation;
    }

    public void setOperatorCallWaitingActivation(String operatorCallWaitingActivation) {
        this.operatorCallWaitingActivation = operatorCallWaitingActivation;
    }

    public String getUserCallWaitingActivation() {
        return userCallWaitingActivation;
    }

    public void setUserCallWaitingActivation(String userCallWaitingActivation) {
        this.userCallWaitingActivation = userCallWaitingActivation;
    }

    public Hotline getHotline() {
        return hotline;
    }

    public void setHotline(Hotline hotline) {
        this.hotline = hotline;
    }

    public VoiceMail getVoiceMail() {
        return voiceMail;
    }

    public void setVoiceMail(VoiceMail voiceMail) {
        this.voiceMail = voiceMail;
    }
}

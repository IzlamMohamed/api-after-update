package com.TaskCyberMAK.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NormalViewRequest {
    @NotBlank(message = "msisdn is mandatory")
    private String msisdn;

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }
}

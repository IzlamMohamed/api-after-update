package com.TaskCyberMAK.api.controller;

import com.TaskCyberMAK.api.dto.NormalViewRequest;
import com.TaskCyberMAK.api.dto.NormalViewResponse;
import com.TaskCyberMAK.api.service.HlrSoapClient;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hlr")
@RequiredArgsConstructor
public class HlrController {

    private final HlrSoapClient hlrSoapClient;

    @Operation(summary = "HLR Normal View")
    @PostMapping("/normal-view")
    public ResponseEntity<NormalViewResponse> normalView(@Valid @RequestBody NormalViewRequest request) {
        NormalViewResponse response = hlrSoapClient.callNormalView(request.getMsisdn());
        return ResponseEntity.ok(response);
    }
}
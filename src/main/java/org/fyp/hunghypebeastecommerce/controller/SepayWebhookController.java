package org.fyp.hunghypebeastecommerce.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fyp.hunghypebeastecommerce.dto.ResponseObject;
import org.fyp.hunghypebeastecommerce.dto.sepay.SepayWebhookPayload;
import org.fyp.hunghypebeastecommerce.service.SepayWebhookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sepay")
@RequiredArgsConstructor
@Slf4j
public class SepayWebhookController {

    private final SepayWebhookService sepayWebhookService;

    @PostMapping("/webhook")
    public ResponseEntity<ResponseObject<Void>> handleWebhook(
            @RequestBody SepayWebhookPayload payload,
            @RequestHeader(value = "X-API-Key", required = false) String apiKey
    ) {
        try {
            log.info("Received Sepay webhook: transactionId={}", payload.getId());
            
            sepayWebhookService.processWebhook(payload, apiKey);
            
            return ResponseEntity.ok(ResponseObject.success("Webhook processed successfully", null));
        } catch (Exception e) {
            log.error("Error processing Sepay webhook", e);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseObject.success("Webhook received", null));
        }
    }
}

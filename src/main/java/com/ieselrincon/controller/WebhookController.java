package com.ieselrincon.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/webhook")
public class WebhookController {
    @Value("${github.webhook.secret}")
    private String SECRET;

    @GetMapping
    public String blankGet() {
        return "POST only";
    }

    @PostMapping
    public String handleWebhook(@RequestHeader("X-Hub-Signature-256") String signature, @RequestBody String payload) {
        if (!isValidSignature(signature, payload)) {
            System.out.println("Recibido webhook de Github con signatura inválida");
            return "Recibido webhook de Github con signatura inválida";
        }

        final String SCRIPT = "/home/ses/SmartEcoSchool/scripts/autoDeploy.sh";
        final String[] COMMAND = { "/bin/bash", SCRIPT };

        System.out.println("Recibido webhook de Github válido:\n" + payload);

        try {
            System.out.println("Ejecutando script...");
            Process process = Runtime.getRuntime().exec(COMMAND);
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error en la ejecución del script";
        }
        System.out.println("Webhook procesado y script ejecutado");
        return "Webhook procesado y script ejecutado";
    }

    private boolean isValidSignature(String signature, String payload) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] hash = sha256_HMAC.doFinal(payload.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }

            String expectedSignature = "sha256=" + hexString.toString();
            System.out.println(expectedSignature);
            return expectedSignature.equals(signature);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
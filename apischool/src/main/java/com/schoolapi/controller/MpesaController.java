package com.schoolapi.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.schoolapi.model.PaymentRequest;
import com.schoolapi.model.PaymentCallback;
import okhttp3.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@RestController
@RequestMapping("/api/online-payment")
public class MpesaController {

    private final String consumerKey = "SPRE2WpFA5yzVEzIFEHMiX4XURsAH9fcypgM3HdilapXV45b";
    private final String consumerSecret = "0ELGz7HXzuhyrDxg0nHNRxwuMSBqNFen4gc1FxLIrK00PuIM7hA3rGUhf05GXWzp";
    private final String shortcode = "174379";
    private final String passKey = "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919";
    private final String accessTokenUrl = "https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials";
    private final String paymentUrl = "https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest";
    private final String callbackUrl = "https://schoolapp-2.onrender.com/api/online-payment/callback";

    private OkHttpClient client = new OkHttpClient();

    private String getAccessToken() throws IOException {
        String auth = consumerKey + ":" + consumerSecret;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        Request request = new Request.Builder()
                .url(accessTokenUrl)
                .get()
                .addHeader("Authorization", "Basic " + encodedAuth)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response.body().string());
                String accessToken = jsonNode.path("access_token").asText();
                System.out.println("Access Token: " + accessToken); // Log the access token
                return accessToken;
            } else {
                System.err.println("Failed to obtain access token. Response: " + response.body().string());
                return null;
            }
        }
    }

    @PostMapping("/pay")
    public ResponseEntity<String> initiatePayment(@org.springframework.web.bind.annotation.RequestBody PaymentRequest paymentRequest) {
        try {
            String token = getAccessToken();
            System.out.println("Token: " + token);
            if (token == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to obtain access token.");
            }

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String password = Base64.getEncoder().encodeToString((shortcode + passKey + timestamp).getBytes());
            System.out.println("Timestamp: " + timestamp);
            System.out.println("Password: " + password);

            // Remove the plus sign from the phone number
            String phoneNumber = paymentRequest.getPhoneNumber().replace("+", "");

            PaymentRequest mpesaPaymentRequest = new PaymentRequest();
            mpesaPaymentRequest.setBusinessShortCode(shortcode);
            mpesaPaymentRequest.setPassword(password);
            mpesaPaymentRequest.setTimestamp(timestamp);
            mpesaPaymentRequest.setTransactionType("CustomerPayBillOnline");
            mpesaPaymentRequest.setAmount(String.valueOf((int) Double.parseDouble(paymentRequest.getAmount()))); // Ensure amount is an integer string
            mpesaPaymentRequest.setPartyA(phoneNumber);
            mpesaPaymentRequest.setPartyB(shortcode);
            mpesaPaymentRequest.setPhoneNumber(phoneNumber);
            mpesaPaymentRequest.setCallBackURL(callbackUrl);
            mpesaPaymentRequest.setAccountReference("SchoolApp Payment");
            mpesaPaymentRequest.setTransactionDesc("Payment for selected activities");

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(mpesaPaymentRequest);
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, json);

            Request request = new Request.Builder()
                    .url(paymentUrl)
                    .post(body)
                    .addHeader("Authorization", "Bearer " + token)
                    .addHeader("Content-Type", "application/json")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                String responseBody = response.body().string();
                System.out.println("Request Body: " + json);
                System.out.println("Response: " + responseBody);
                return ResponseEntity.ok(responseBody);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/callback")
    public ResponseEntity<String> paymentCallback(@org.springframework.web.bind.annotation.RequestBody PaymentCallback paymentCallback) {
        System.out.println("Callback received: " + paymentCallback.toString());
        return ResponseEntity.ok("Callback received");
    }
}

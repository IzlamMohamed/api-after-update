package com.TaskCyberMAK.api.security;

import java.security.SecureRandom;
import java.util.Base64;

public class JwtKeyGenerator {
    public static void main(String[] args) {
        byte[] key = new byte[64]; // 512 bits
        new SecureRandom().nextBytes(key);
        String base64Key = Base64.getUrlEncoder().withoutPadding().encodeToString(key);
        System.out.println("Generated JWT Secret Key:");
        System.out.println(base64Key);
    }
}

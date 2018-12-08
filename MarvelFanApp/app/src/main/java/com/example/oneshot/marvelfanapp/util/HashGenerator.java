package com.example.oneshot.marvelfanapp.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {

    public static String generate(long timestamp, String privateKey, String publicKey) {
        try {
            String concatResult = timestamp + privateKey + publicKey;
            return md5(concatResult);
        } catch (Exception e) {
            return null;
        }
    }

    public static String md5(String s) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest
                .getInstance("MD5");
        digest.update(s.getBytes());
        byte messageDigest[] = digest.digest();
        BigInteger bigInt = new BigInteger(1, messageDigest);
        String hashText = bigInt.toString(16);
        while (hashText.length() < 32) {
            hashText = "0" + hashText;
        }
        return hashText;
    }

}

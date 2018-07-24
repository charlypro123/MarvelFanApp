package com.example.oneshot.marvelfanapp.util;

import com.example.oneshot.marvelfanapp.BuildConfig;

import static com.example.oneshot.marvelfanapp.util.HashGenerator.generate;

public class Constants {

    public static final String BASE_URL = "https://gateway.marvel.com:443";
    public static final String PUBLIC_KEY = "4c5844fc8e684e8ef3a438dd1bc9cbbf";
    public static final String PRIVATE_KEY = BuildConfig.PrivateKey;
            //"0a7b43817336c24b281dfa0953a6b14da4157fe0";
    //cambiar timestamp por fechahora
    public static final long TIMESTAMP = 9;
    public static final String HASH = generate(TIMESTAMP, PRIVATE_KEY, PUBLIC_KEY);
}

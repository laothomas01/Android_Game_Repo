package com.hackslash.game;

import android.os.Bundle;
import android.os.StrictMode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.files.FileHandle;
import com.hackslash.game.driver.hack_and_slash;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class AndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useAccelerometer = false;
        config.useCompass = false;
        initialize(new hack_and_slash(), config);


    }

}

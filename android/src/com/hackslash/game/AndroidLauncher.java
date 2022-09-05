package com.hackslash.GAME_VERSION1;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.hackslash.GAME_VERSION1.driver.GameStateManager;


public class AndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
//        config.useAccelerometer = false;
//        config.useCompass = false;
//        initialize(new GameStateManager(), config);


    }

}

package com.example.pokemonstadiumrandomizer;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.example.pokemonstadiumrandomizer.GUIUpdaters.BackgroundGUIUpdater;
import com.example.pokemonstadiumrandomizer.Loaders.AssetLoader;

import java.util.List;


public class SettingsActivity extends AppCompatActivity {

    private RadioButton defaultBack;
    private RadioButton tiltedBack;
    private RadioButton greatBack;
    private RadioButton invertedBack;
    private RadioButton masterBack;
    private RadioButton jolteon;
    private RadioButton stadiumSprites;
    private RadioButton goSprites;
    private RadioButton cyndaquil;
    private LinearLayout backImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String theme = AssetLoader.loadSetting("theme",getApplicationContext());
        switch(theme){
            case "light":
                BackgroundGUIUpdater.setLightText(this);
                break;
            case "default":
                BackgroundGUIUpdater.setDarkText(this);
                break;
        }        setContentView(R.layout.settings_activity);
        initGUI();
        checkSettings();
    }

    public void onBackPressed(){
        Intent settingsIntent = new Intent(getApplicationContext(), MainActivity.class);
        finish();
        startActivity(settingsIntent);
    }

    private void initGUI() {
        backImg = findViewById(R.id.backImg);
        BackgroundGUIUpdater.setBackground(backImg, getApplicationContext());
       defaultBack = findViewById(R.id.defaultBack);
       tiltedBack = findViewById(R.id.tiltedBack);
       greatBack = findViewById(R.id.greatBack);
       invertedBack = findViewById(R.id.invertedBack);
       masterBack = findViewById(R.id.masterBack);
       jolteon = findViewById(R.id.jolteon);
       stadiumSprites = findViewById(R.id.stadiumSprites);
       goSprites = findViewById(R.id.goSprites);
       cyndaquil = findViewById(R.id.cyndaquil);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.cyndaquil:
                            AssetLoader.changeSetting("theme", "light", getApplicationContext());
                            AssetLoader.changeSetting("background", "background6.jpg", getApplicationContext());
                            restartGUI();
                            //BackgroundGUIUpdater.setBackground(backImg, getApplicationContext());
                            break;

                        case R.id.defaultBack:
                            AssetLoader.changeSetting("theme", "default", getApplicationContext());
                            AssetLoader.changeSetting("background", "background1.png", getApplicationContext());
                            restartGUI();
                            //BackgroundGUIUpdater.setBackground(backImg, getApplicationContext());
                                break;

                        case R.id.tiltedBack:
                            AssetLoader.changeSetting("theme", "default", getApplicationContext());
                            AssetLoader.changeSetting("background", "background2.png", getApplicationContext());
                            restartGUI();
                            //BackgroundGUIUpdater.setBackground(backImg, getApplicationContext());
                            break;
                        case R.id.greatBack:
                            AssetLoader.changeSetting("theme", "default", getApplicationContext());
                            AssetLoader.changeSetting("background", "background4.jpg", getApplicationContext());
                            //BackgroundGUIUpdater.setBackground(backImg, getApplicationContext());
                            restartGUI();
                            break;

                        case R.id.invertedBack:
                            AssetLoader.changeSetting("theme", "default", getApplicationContext());
                            AssetLoader.changeSetting("background", "background0.png", getApplicationContext());
                            //BackgroundGUIUpdater.setBackground(backImg, getApplicationContext());
                            restartGUI();
                            break;

                        case R.id.masterBack:
                            AssetLoader.changeSetting("theme", "default", getApplicationContext());
                            AssetLoader.changeSetting("background", "background5.png", getApplicationContext());
                            restartGUI();
                            //BackgroundGUIUpdater.setBackground(backImg, getApplicationContext());
                            break;

                        case R.id.jolteon:
                            AssetLoader.changeSetting("theme", "default", getApplicationContext());
                            AssetLoader.changeSetting("background",  "background7.jpg", getApplicationContext());
                            restartGUI();
                            //BackgroundGUIUpdater.setBackground(backImg, getApplicationContext());
                                break;

                        case R.id.stadiumSprites:
                            AssetLoader.changeSetting("PokemonImagePathStart", "pokemonImages/Stadium_original/", getApplicationContext());
                                AssetLoader.changeSetting("PokemonImagePathEnd", ".png", getApplicationContext());
                                break;

                        case R.id.goSprites:
                                AssetLoader.changeSetting("PokemonImagePathStart", "pokemonImages/PoGo_sprites/pokemon_icon_", getApplicationContext());
                                AssetLoader.changeSetting("PokemonImagePathEnd", "_00.png", getApplicationContext());
                                break;

                    }
                }
        };
        defaultBack.setOnClickListener(clickListener);
        invertedBack.setOnClickListener(clickListener);
        tiltedBack.setOnClickListener(clickListener);
        greatBack.setOnClickListener(clickListener);
        masterBack.setOnClickListener(clickListener);
        jolteon.setOnClickListener(clickListener);
        goSprites.setOnClickListener(clickListener);
        stadiumSprites.setOnClickListener(clickListener);
        cyndaquil.setOnClickListener(clickListener);
        FloatingActionButton randomize = findViewById(R.id.randomize);
        randomize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(settingsIntent);
            }
        });
    }

    /**
     * Checks the correct RadioButton depending on the current value for different settings keys.
     */
    private void checkSettings(){
        List<String> settings = AssetLoader.loadAllSettings(getApplicationContext());
        for (String s :settings ) {
            switch(s){
            case "background6.jpg":
                cyndaquil.toggle();
                break;
            case "background1.png":
                    defaultBack.toggle();
                break;
            case "background0.png":
                    invertedBack.toggle();
                break;
            case "background2.png":
                    tiltedBack.toggle();
                break;
            case "background3.png":
                    defaultBack.toggle();
                break;
            case "background4.jpg":
                    greatBack.toggle();
                break;
            case "background5.png":
                    masterBack.toggle();
                break;
            case "background7.jpg":
                    jolteon.toggle();
                break;
            case "pokemonImages/PoGo_sprites/pokemon_icon_":
                    goSprites.toggle();
                break;
            case "pokemonImages/Stadium_original/":
                    stadiumSprites.toggle();
                break;
            default:
                    break;
            }
        }
    }

    /**
     * Restarts the GUI in order to update the theme whenever a currently visible graphical setting is changed.
     */
private void restartGUI(){
    Intent settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);
    finish();
    startActivity(settingsIntent);
}

}

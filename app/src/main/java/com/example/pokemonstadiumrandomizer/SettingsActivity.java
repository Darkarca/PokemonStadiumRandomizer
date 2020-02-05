package com.example.pokemonstadiumrandomizer;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.example.pokemonstadiumrandomizer.Loaders.AssetLoader;

import java.io.IOException;
import java.util.List;


public class SettingsActivity extends AppCompatActivity {

    RadioButton defaultBack;
    RadioButton tiltedBack;
    RadioButton greatBack;
    RadioButton invertedBack;
    RadioButton masterBack;
    RadioButton dexBack;
    RadioButton stadiumSprites;
    RadioButton goSprites;
    LinearLayout backImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        initGUI();
        checkSettings();
    }

    private void initGUI() {
        backImg = findViewById(R.id.backImg);
       defaultBack = findViewById(R.id.defaultBack);
       tiltedBack = findViewById(R.id.tiltedBack);
       greatBack = findViewById(R.id.greatBack);
       invertedBack = findViewById(R.id.invertedBack);
       masterBack = findViewById(R.id.masterBack);
       dexBack = findViewById(R.id.dexBack);
       stadiumSprites = findViewById(R.id.stadiumSprites);
       goSprites = findViewById(R.id.goSprites);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    switch (v.getId()) {
                            case R.id.defaultBack:
                                try {
                                    AssetLoader.changeSetting("Background", '"' + "background1.png" + '"', getApplicationContext());
                                    setBackground();
                                    break;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                        case R.id.tiltedBack:
                            try {
                                AssetLoader.changeSetting("Background", '"' + "background2.png" + '"', getApplicationContext());
                                setBackground();
                                break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        case R.id.greatBack:
                            try {
                                AssetLoader.changeSetting("Background", '"' + "background4.png" + '"', getApplicationContext());
                                setBackground();
                                break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        case R.id.invertedBack:
                            try {
                                AssetLoader.changeSetting("Background", '"' + "background0.png" + '"', getApplicationContext());
                                setBackground();
                                break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        case R.id.masterBack:
                            try {
                                AssetLoader.changeSetting("Background", '"' + "background5.png" + '"', getApplicationContext());
                                setBackground();
                                break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        case R.id.dexBack:
                            try {
                                AssetLoader.changeSetting("Background",  '"' + "background7.png" + '"', getApplicationContext());
                                setBackground();
                                break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        case R.id.stadiumSprites:
                            try {
                                AssetLoader.changeSetting("PokemonImagePathStart", '"' + "pokemonImages/Stadium_original/" + '"', getApplicationContext());
                                AssetLoader.changeSetting("PokemonImagePathEnd", '"' + ".png" + '"', getApplicationContext());
                                break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        case R.id.goSprites:
                            try {
                                AssetLoader.changeSetting("PokemonImagePathStart", '"' + "pokemonImages/PoGo_sprites/pokemon_icon_" + '"', getApplicationContext());
                                AssetLoader.changeSetting("PokemonImagePathEnd", '"' + "_00.png" + '"', getApplicationContext());
                                break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                    }
                }
        };
        defaultBack.setOnClickListener(clickListener);
        invertedBack.setOnClickListener(clickListener);
        tiltedBack.setOnClickListener(clickListener);
        greatBack.setOnClickListener(clickListener);
        masterBack.setOnClickListener(clickListener);
        dexBack.setOnClickListener(clickListener);
        goSprites.setOnClickListener(clickListener);
        stadiumSprites.setOnClickListener(clickListener);
    }

    private void checkSettings(){
        List<String> settings = AssetLoader.loadAllSettings(getApplicationContext());
        for (String s :settings ) {
            switch(s){
                case "Background1.png":
                    defaultBack.toggle();
                break;
            case "Background0.png":
                    invertedBack.toggle();
                break;
            case "Background2.png":
                    tiltedBack.toggle();
                break;
            case "Background3.png":
                    defaultBack.toggle();
                break;
            case "Background4.png":
                    greatBack.toggle();
                break;
            case "Background5.png":
                    greatBack.toggle();
                break;
            case "Background6.png":
                    masterBack.toggle();
                break;
            case "Background7.png":
                    dexBack.toggle();
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

    private void setBackground(){
        String s = AssetLoader.loadSetting("BackgroundImage",getApplicationContext());
        int id = this.getResources().getIdentifier(s.substring(0,s.length()-4), "drawable", this.getPackageName());
        backImg.setBackgroundResource(id);
    }

}

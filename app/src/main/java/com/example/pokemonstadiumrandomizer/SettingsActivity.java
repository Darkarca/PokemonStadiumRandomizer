package com.example.pokemonstadiumrandomizer;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.example.pokemonstadiumrandomizer.Loaders.AssetLoader;

import java.io.IOException;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        initGUI();
    }

    private void initGUI() {
     RadioButton defaultBack = findViewById(R.id.defaultBack);
        RadioButton tiltedBack = findViewById(R.id.tiltedBack);
        RadioButton greatBack = findViewById(R.id.greatBack);
        RadioButton invertedBack = findViewById(R.id.invertedBack);
        RadioButton masterBack = findViewById(R.id.masterBack);
        RadioButton dexBack = findViewById(R.id.dexBack);
        RadioButton stadiumSprites = findViewById(R.id.stadiumSprites);
        RadioButton goSprites = findViewById(R.id.goSprites);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    switch (v.getId()) {
                            case R.id.defaultBack:
                                try {
                                    AssetLoader.changeSetting("Background", "background1.png", getApplicationContext());
                                    break;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                        case R.id.tiltedBack:
                            try {
                                AssetLoader.changeSetting("Background", "background2.jpg", getApplicationContext());
                                break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        case R.id.greatBack:
                            try {
                                AssetLoader.changeSetting("Background", "background4.png", getApplicationContext());
                                break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        case R.id.invertedBack:
                            try {
                                AssetLoader.changeSetting("Background", "background0.png", getApplicationContext());
                                break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        case R.id.masterBack:
                            try {
                                AssetLoader.changeSetting("Background", "background5.png", getApplicationContext());
                                break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        case R.id.dexBack:
                            try {
                                AssetLoader.changeSetting("Background", "background7.png", getApplicationContext());
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

}

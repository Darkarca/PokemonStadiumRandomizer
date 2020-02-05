package com.example.pokemonstadiumrandomizer;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.example.pokemonstadiumrandomizer.Loaders.AssetLoader;

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
    RadioButton cyndaquil;
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
        setBackground(backImg, getApplicationContext());
       defaultBack = findViewById(R.id.defaultBack);
       tiltedBack = findViewById(R.id.tiltedBack);
       greatBack = findViewById(R.id.greatBack);
       invertedBack = findViewById(R.id.invertedBack);
       masterBack = findViewById(R.id.masterBack);
       dexBack = findViewById(R.id.dexBack);
       stadiumSprites = findViewById(R.id.stadiumSprites);
       goSprites = findViewById(R.id.goSprites);
       cyndaquil = findViewById(R.id.cyndaquil);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.cyndaquil:
                            AssetLoader.changeSetting("Background", '"' + "background6.jpg" + '"', getApplicationContext());
                            setBackground(backImg, getApplicationContext());
                            break;

                        case R.id.defaultBack:
                                AssetLoader.changeSetting("Background", '"' + "background1.png" + '"', getApplicationContext());
                                setBackground(backImg, getApplicationContext());
                                break;

                        case R.id.tiltedBack:
                                AssetLoader.changeSetting("Background", '"' + "background2.png" + '"', getApplicationContext());
                                setBackground(backImg, getApplicationContext());
                                break;
                        case R.id.greatBack:
                                AssetLoader.changeSetting("Background", '"' + "background4.jpg" + '"', getApplicationContext());
                                setBackground(backImg, getApplicationContext());
                                break;

                        case R.id.invertedBack:
                                AssetLoader.changeSetting("Background", '"' + "background0.png" + '"', getApplicationContext());
                                setBackground(backImg, getApplicationContext());
                                break;

                        case R.id.masterBack:
                                AssetLoader.changeSetting("Background", '"' + "background5.png" + '"', getApplicationContext());
                                setBackground(backImg, getApplicationContext());
                                break;

                        case R.id.dexBack:
                                AssetLoader.changeSetting("Background",  '"' + "background7.png" + '"', getApplicationContext());
                                setBackground(backImg, getApplicationContext());
                                break;

                        case R.id.stadiumSprites:
                                AssetLoader.changeSetting("PokemonImagePathStart", '"' + "pokemonImages/Stadium_original/" + '"', getApplicationContext());
                                AssetLoader.changeSetting("PokemonImagePathEnd", '"' + ".png" + '"', getApplicationContext());
                                break;

                        case R.id.goSprites:
                                AssetLoader.changeSetting("PokemonImagePathStart", '"' + "pokemonImages/PoGo_sprites/pokemon_icon_" + '"', getApplicationContext());
                                AssetLoader.changeSetting("PokemonImagePathEnd", '"' + "_00.png" + '"', getApplicationContext());
                                break;

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
        cyndaquil.setOnClickListener(clickListener);
    }

    private void checkSettings(){
        List<String> settings = AssetLoader.loadAllSettings(getApplicationContext());
        for (String s :settings ) {
            switch(s){
            case "background6.jpg":
                cyndaquil.toggle();
                break;
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
            case "Background4.jpg":
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

    public static void setBackground(LinearLayout view, Context context){
        String s = AssetLoader.loadSetting("BackgroundImage",context);
        int id = context.getResources().getIdentifier(s.substring(0,s.length()-4), "drawable", context.getPackageName());
        view.setBackgroundResource(id);
    }

}

package com.example.pokemonstadiumrandomizer;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.pokemonstadiumrandomizer.Loaders.AssetLoader;
import com.example.pokemonstadiumrandomizer.Loaders.FileReadWriter;
import com.example.pokemonstadiumrandomizer.Loaders.LoadFromTxt;
import com.example.pokemonstadiumrandomizer.Loaders.WinLossLoader;
import com.example.pokemonstadiumrandomizer.utilities.Pokemon;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int p1score = 0;
    int p2score = 0;
    List<Pokemon> player1 = new ArrayList<>();
    List<Pokemon> player2 = new ArrayList<>();
    ImageView p1p1;
    ImageView p1p2;
    ImageView p1p3;
    ImageView p1p4;
    ImageView p1p5;
    ImageView p1p6;
    ImageView p2p1;
    ImageView p2p2;
    ImageView p2p3;
    ImageView p2p4;
    ImageView p2p5;
    ImageView p2p6;
    ImageView focusedPKMN;
    TextView name;
    TextView hp;
    TextView att;
    TextView def;
    TextView spec;
    TextView spd;
    TextView att1;
    TextView att2;
    TextView att3;
    TextView att4;
    List<ImageView> imageList = new ArrayList<>();
    List<ImageView> imageList2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGUI();
    }

    private void initGUI() {
        //AssetLoader.addSetting("BackgroundImage", "Background0.png",getApplicationContext());
        FileReadWriter.write("settings.txt","PokemonImagePathStart = "+'"'+"pokemonImages/PoGo_sprites/pokemon_icon_" + '"' + "\n" + "PokemonImagePathEnd" + " = " + '"' + "00.png" + '"' + "\n" +"BackgroundImage = " + '"' + "Background0.png" + '"',getApplicationContext());
        p1p1 = findViewById(R.id.p1p1);
        p1p2 = findViewById(R.id.p1p2);
        p1p3 = findViewById(R.id.p1p3);
        p1p4 = findViewById(R.id.p1p4);
        p1p5 = findViewById(R.id.p1p5);
        p1p6 = findViewById(R.id.p1p6);
        p2p1 = findViewById(R.id.p2p1);
        p2p2 = findViewById(R.id.p2p2);
        p2p3 = findViewById(R.id.p2p3);
        p2p4 = findViewById(R.id.p2p4);
        p2p5 = findViewById(R.id.p2p5);
        p2p6 = findViewById(R.id.p2p6);
        focusedPKMN = findViewById(R.id.focusedPKMN);
        name = findViewById(R.id.pokeName);
        hp = findViewById(R.id.hp);
        att = findViewById(R.id.att);
        def = findViewById(R.id.def);
        spec = findViewById(R.id.spec);
        spd = findViewById(R.id.spd);
        att1 = findViewById(R.id.att1);
        att2 = findViewById(R.id.att2);
        att3 = findViewById(R.id.att3);
        att4 = findViewById(R.id.att4);
        final TextView p1wins = findViewById(R.id.p1wins);
        Button p1win = findViewById(R.id.p1win);
        final TextView p2wins = findViewById(R.id.p2wins);
        Button p2win = findViewById(R.id.p2win);
        p2win.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p2score++;
                p2wins.setText("Wins: " + p2score);
                    WinLossLoader.writeScores(player2, player1, getApplicationContext());
            }
        });
        p1win.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1score++;
                p1wins.setText("Wins: " + p1score);
                    WinLossLoader.writeScores(player1, player2, getApplicationContext());
            }
        });
        imageList.add(p1p1);
        imageList.add(p1p2);
        imageList.add(p1p3);
        imageList.add(p1p4);
        imageList.add(p1p5);
        imageList.add(p1p6);
        imageList2.add(p2p1);
        imageList2.add(p2p2);
        imageList2.add(p2p3);
        imageList2.add(p2p4);
        imageList2.add(p2p5);
        imageList2.add(p2p6);
        FloatingActionButton options = findViewById(R.id.options);
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);
                    startActivity(settingsIntent);
                /*try {
                    AssetLoader.clearSettings(getApplicationContext());
                    AssetLoader.addSetting("PokemonImagePathStart", "pokemonImages/PoGo_sprites/pokemon_icon_", getApplicationContext());
                    AssetLoader.addSetting("PokemonImagePathEnd", "_00.png", getApplicationContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
        });
            FloatingActionButton randomize = findViewById(R.id.randomize);
            randomize.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    List<Pokemon> totalPokes = LoadFromTxt.loadPokemon(getApplicationContext());
                    player1.clear();
                    player2.clear();
                    Random random = new Random();
                    for(int i=0; i<6; i++) {
                        int randomInt = random.nextInt(totalPokes.size());
                        player1.add(totalPokes.get(randomInt));
                        totalPokes.remove(totalPokes.get(randomInt));
                        randomInt = random.nextInt(totalPokes.size());
                        player2.add(totalPokes.get(randomInt));
                        totalPokes.remove(totalPokes.get(randomInt));
                    }
                    try {
                        renderPokemon();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
    }

    private void renderPokemon() throws IOException {

        int i = 0;
           for (final ImageView iv:imageList) {
               final int j = player1.get(i).getNumberValue();
               final Drawable d = AssetLoader.loadPokemon(player1.get(i), getApplicationContext());
               iv.setImageDrawable(d);
               iv.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       focusPokemon(d, LoadFromTxt.loadPokemon(getApplicationContext()).get(j-1));
                   }
               });
               i++;
           }
           i=0;
        for (final ImageView iv:imageList2) {
            final int j = player2.get(i).getNumberValue();
            final Drawable d = AssetLoader.loadPokemon(player2.get(i), getApplicationContext());
            iv.setImageDrawable(d);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                focusPokemon(d, LoadFromTxt.loadPokemon(getApplicationContext()).get(j-1));
                }
            });
            i++;
        }
    }

        public void focusPokemon(Drawable d, Pokemon currentPoke){
            focusedPKMN.setImageDrawable(d);
            name.setText(currentPoke.getName());
            hp.setText(currentPoke.getStats()[0]);
            att.setText(currentPoke.getStats()[1]);
            def.setText(currentPoke.getStats()[2]);
            spec.setText(currentPoke.getStats()[3]);
            spd.setText(currentPoke.getStats()[4]);
            att1.setText(currentPoke.getAttacks()[0]);
            att2.setText(currentPoke.getAttacks()[1]);
            att3.setText(currentPoke.getAttacks()[2]);
            att4.setText(currentPoke.getAttacks()[3]);

        }
}

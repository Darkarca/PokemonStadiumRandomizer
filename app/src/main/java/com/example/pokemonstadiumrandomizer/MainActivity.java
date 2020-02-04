package com.example.pokemonstadiumrandomizer;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.example.pokemonstadiumrandomizer.Loaders.LoadFromTxt;
import com.example.pokemonstadiumrandomizer.utilities.Pokemon;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
    TextView p1wins;
    TextView p2wins;
    Button p1win;
    Button p2win;
    List<ImageView> imageList = new ArrayList<>();
    List<ImageView> imageList2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGUI();
    }

    private void initGUI() {
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
        p1wins = findViewById(R.id.p1wins);
        p1win = findViewById(R.id.p1win);
        p2wins = findViewById(R.id.p2wins);
        p2win = findViewById(R.id.p2win);
        p2win.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p2score++;
                p2wins.setText("Wins: " + p2score);

                try {
                    writeScores(2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        p1win.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1score++;
                p1wins.setText("Wins: " + p1score);
                try {
                    writeScores(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
               Drawable d = Drawable.createFromStream(getAssets().open("pokemonImages/PoGo_sprites/pokemon_icon_" + player1.get(i).getNumber() + "_00.png"), null);
               //Drawable d = Drawable.createFromStream(getAssets().open("pokemonImages/" + "001" + ".png"), null);
               LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
               iv.setImageDrawable(d);
               iv.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Pokemon currentPoke = LoadFromTxt.loadPokemon(getApplicationContext()).get(j-1);
                       focusedPKMN.setImageDrawable(iv.getDrawable());
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
               });
               System.out.println("SUCCESS");
               i++;

           }
           i=0;
        for (final ImageView iv:imageList2) {
            final int j = player2.get(i).getNumberValue();
            final Drawable d = Drawable.createFromStream(getAssets().open("pokemonImages/PoGo_sprites/pokemon_icon_" + player2.get(i).getNumber() + "_00.png"), null);
            //Drawable d = Drawable.createFromStream(getAssets().open("pokemonImages/" + "001" + ".png"), null);
            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            iv.setImageDrawable(d);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pokemon currentPoke = LoadFromTxt.loadPokemon(getApplicationContext()).get(j-1);
                    focusedPKMN.setImageDrawable(iv.getDrawable());
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
            });
            System.out.println("SUCCESS");
            i++;
        }
    }

    private void writeScores(int winner) throws IOException {
        try {
          /*  if(winner == 1) {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getApplicationContext().openFileOutput("winners.txt.txt", getApplicationContext().MODE_PRIVATE));
                for (Pokemon current : player1) {
                    outputStreamWriter.write(current.getName() + "\n");
                }
                outputStreamWriter.close();
                outputStreamWriter = new OutputStreamWriter(getApplicationContext().openFileOutput("losers.txt", getApplicationContext().MODE_PRIVATE));
                for (Pokemon current : player1) {
                    outputStreamWriter.write(current.getName() + "\n");
                }
                outputStreamWriter.close();
            }else{
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getApplicationContext().openFileOutput("winners.txt.txt", getApplicationContext().MODE_PRIVATE));
                for (Pokemon current : player2) {
                    outputStreamWriter.write(current.getName() + "\n");
                }
                outputStreamWriter.close();
                outputStreamWriter = new OutputStreamWriter(getApplicationContext().openFileOutput("losers.txt", getApplicationContext().MODE_PRIVATE));
                for (Pokemon current : player1) {
                    outputStreamWriter.write(current.getName() + "\n");
                }
                outputStreamWriter.close();*/
          File path = getApplicationContext().getFilesDir();
          File file = new File(path, "winners.txt.txt");
            FileOutputStream stream = new FileOutputStream(file);
            for (Pokemon current:player1) {
                stream.write((current.getName() + "\n").getBytes());
                System.out.println(getAssets().open("winners.txt").read());
                System.out.println((current.getName() + "\n").getBytes());
            }

            stream.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

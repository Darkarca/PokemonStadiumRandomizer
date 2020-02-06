package com.example.pokemonstadiumrandomizer.Loaders;

import android.content.Context;
import com.example.pokemonstadiumrandomizer.utilities.Pokemon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LoadFromTxt implements IPokemonLoader {
    static final String PATH = "pokemon.txt";
    static List<Pokemon> loadedPokes = new ArrayList<>();


     private static void read(Context context) throws IOException {
         BufferedReader reader;
        loadedPokes.clear();
        try{
             int counter = 1;
             final InputStream file = context.getAssets().open(PATH);
             reader = new BufferedReader(new InputStreamReader(file));
             String line = reader.readLine();
             while ((line = reader.readLine()) != null) {
                 List<String> currentPoke = new ArrayList<>();
                 currentPoke.add(line);
                 for(int i = 0; i<9; i++){
                     currentPoke.add(reader.readLine());
                 }
                 loadedPokes.add(new Pokemon(currentPoke));
                 loadedPokes.get(loadedPokes.size()-1).setNumber(counter);
                 counter++;
             }
         } catch(IOException ioe){
             ioe.printStackTrace();
         }
    }

    public static List<Pokemon> loadPokemon(Context context){
        try {
            read(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedPokes;
    }

}

package com.example.pokemonstadiumrandomizer.Loaders;

import android.content.Context;
import com.example.pokemonstadiumrandomizer.utilities.Pokemon;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to load Pokemon Objects from a Textfile.
 * Uses FileReadWriter to read from the internal storage
 */
public class LoadFromTxt implements IPokemonLoader {

    /**
     * The String with the FilePath to the Textfile to be loaded from.
     */
    private static final String PATH = "pokemon.txt";
    private static List<Pokemon> loadedPokes = new ArrayList<>();


    /**
     * Reads a the PATH Textfile and uses the resulting list of Pokemon (represented as Strings)
     * in order to create a List of Pokemon which are stored in the static List loadedPokes.
     * @param context   The Context of the Activity calling the method, used to access internal storage.
     */
     private static void read(Context context){
         loadedPokes.clear();
         int counter = 1;
         List<String> readPokemonStrings = FileReadWriter.read(PATH,context);
         List<String> currentPoke = new ArrayList<>();
         for (int i=0; i<readPokemonStrings.size(); i++) {
             currentPoke.add(readPokemonStrings.get(i));
             if(currentPoke.size() == 10) {
                 if(currentPoke.get(0).contains("&#9792")){
                     currentPoke.set(0,"Nidoran (Female)");
                 }else if(currentPoke.get(0).contains("&#9794")){
                     currentPoke.set(0,"Nidoran (Male)");
                 }

                 loadedPokes.add(new Pokemon(currentPoke));
                 loadedPokes.get(loadedPokes.size() - 1).setNumber(counter);
                 currentPoke.clear();
                 counter++;
             }
         }
    }

    /**
     *  Runs read(Context) method (if List is not already populated) before returning the static list loadedPokes which read(Context) populates.
     * @param context   The Context of the Activity calling the method, used to access internal storage.
     * @return  A list of all loaded Pokemon found in the Textfile with the FilePath PATH.
     */
    public static List<Pokemon> loadPokemon(Context context){
        if(loadedPokes.isEmpty() || loadedPokes.size()<loadedPokes.get(loadedPokes.size()-1).getNumberValue()) {
            read(context);
        }
        return loadedPokes;
    }

}

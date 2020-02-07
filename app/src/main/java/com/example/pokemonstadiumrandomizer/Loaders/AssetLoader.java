package com.example.pokemonstadiumrandomizer.Loaders;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.example.pokemonstadiumrandomizer.utilities.Pokemon;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * File for loading Assets (Settings, Images etc.) using the FileReadWriter class.
 * All FilePaths assumed to be located in the internal storage of the app (Check FileReadWriter JavaDoc for more information).
 * Not a completely generic class, could be used anywhere if the loadPokemon method is removed/refactored
 */
public class AssetLoader {

    /**
     * The path to the Settings FilePath, assumed to be located in the internal storage of the app.
     */
    private static final String SETTINGS_PATH = "settings.txt";

    /**
     * Loads a specific setting from the settings File, only returns the value and not the key
     * Assumes a settingsformat of key + "= " + '"' + value + '"'
     * @param key   The key for the setting to be loaded.
     * @param context   The Context of the Activity calling the method, used to access the internal storage.
     * @return  The String representation of the setting with the key: key.
     */
    public static String loadSetting(String key, Context context){
        String res;
        for(String setting:FileReadWriter.read(SETTINGS_PATH,context)){
            if(setting.contains(key)){
                int start = setting.indexOf('"')+1;
                int stop = setting.lastIndexOf('"');
                res = setting.substring(start,stop);
                System.out.println("Loading setting in: " + context.getFilesDir() + "/" + SETTINGS_PATH + ", " + setting);

                return res;
            }
        }
            return null;
    }

    /**
     * Loads all settingsfrom the settings File, only returns the values and not the keys
     * Assumes a settingsformat of key + "= " + '"' + value + '"'
     * @param context   The Context of the Activity calling the method, used to access the internal storage.
     * @return  The List of String representations of the settings values.
     */
    public static List<String> loadAllSettings(Context context){
        List<String> settings = FileReadWriter.read(SETTINGS_PATH,context);
        for(int i=0; i<settings.size(); i++){
            String current = settings.get(i);
            settings.set(i,current.substring(current.indexOf('"')+1, current.lastIndexOf('"')));
        }
        return settings;
    }

    /**
     * Adds a specific setting to the settings File
     * Adds in the settingsformat of key + "= " + '"' + value + '"'
     * @param key   The key of the setting to be added.
     * @param value The initial value of the setting to be added.
     * @param context   The Context of the Activity calling the method, used to access the internal storage.
     */
    public static void addSetting(String key, String value, Context context)  {
        String toAdd = key + " = " + '"' + value + '"';
        FileReadWriter.appendFile(SETTINGS_PATH,toAdd,context);
    }

    /**
     * Changes a specific setting in the settings File.
     * Assumes the settingsfile to use the settingsformat of key + "= " + '"' + value + '"'
     * @param key   The key of the setting to be changed.
     * @param value The new value of the setting with the key: key.
     * @param context   The Context of the Activity calling the method, used to access the internal storage.
     */
    public static void changeSetting(String key, String value, Context context){
        List<String> settings = FileReadWriter.read("settings.txt",context);
        for (int i=0; i<settings.size(); i++) {
            if(settings.get(i).contains(key)) {
                if (settings.get(i).indexOf('"') >= 0) {
                    String temp = settings.get(i).substring(0, settings.get(i).indexOf('"'));
                    temp = temp + '"' + value + '"';
                    //String temp = key + " = " + '"' + value + '"';
                    settings.set(i, temp);
                }
            }
        }
        FileReadWriter.write(SETTINGS_PATH, settings, context);
    }

    /**
     * Loads a Pokémon Drawable corresponding to the Pokémon Object passed in.
     * Uses the settings File to get Image location and name.
     * @param pokemon   The Pokémon whose Image to convert into a Drawable.
     * @param context   The Context of the Activity calling the method, used to access the internal storage.
     * @return  The Drawable representation of the Pokémon's Image (Returns null if no Image could be loaded).
     * @throws IOException  If the requested Image does not exist, look over your settings Files paths aswell as making sure that the Pokémon Object passed in holds all the necessary data (it's nationalDex number).
     */
    public static Drawable loadPokemon(Pokemon pokemon, Context context){
        Drawable d = null;
        try {
            d = Drawable.createFromStream(context.getAssets().open(loadSetting("PokemonImagePathStart",context) + pokemon.getNumber() + loadSetting("PokemonImagePathEnd",context)), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * Overwrites the settings File with an empty File.
     * @param context   The Context of the Activity calling the method, used to access the internal storage.
     * @throws IOException  If the File did not exist originally, if that is the case then why are you calling this method? Or create a new File using FileReadWriter.
     */
    public static void clearSettings(Context context) throws IOException {
        File file = new File(context.getFilesDir() + "/" + SETTINGS_PATH);
        file.delete();
        file.createNewFile();
    }

    /**
     * Resets the settings File with default values, method currently needs to be updated whenever a new settings key is added.
     * Should be split into using a List<String> in the future rather then a large String for readability.
     * @param context   The Context of the Activity calling the method, used to access the internal storage.
     */
    public static void resetSettings(Context context){
        FileReadWriter.write("settings.txt","PokemonImagePathStart = "+'"'+"pokemonImages/PoGo_sprites/pokemon_icon_" + '"' + "\n" + "PokemonImagePathEnd" + " = " + '"' + "_00.png" + '"' + "\n" +"BackgroundImage = " + '"' + "background0.png" + '"' + "\n" + "theme = " + '"' + "default" + '"',context);
    }
}

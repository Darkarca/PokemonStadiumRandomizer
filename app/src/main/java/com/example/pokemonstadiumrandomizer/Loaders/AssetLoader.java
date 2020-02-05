package com.example.pokemonstadiumrandomizer.Loaders;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.pokemonstadiumrandomizer.R;
import com.example.pokemonstadiumrandomizer.utilities.Pokemon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class AssetLoader {

    private static String PATH = "settings.txt";

    private static String loadSetting(String key, Context context) throws IOException {
        BufferedReader reader;
        String setting;
        int start;
        int stop;
        final InputStream file = new FileInputStream(context.getFilesDir() + "/" + PATH);
        reader = new BufferedReader(new InputStreamReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains(key)) {
                start = line.indexOf('"')+1;
                stop = line.lastIndexOf('"');
                setting = line.substring(start, stop);
                System.out.println("Loading setting in: " + context.getFilesDir() + "/" + PATH + ", " + setting);
                return setting;
                }
            }
            return null;
    }

    private static List<String> loadAllSettings(Context context) throws IOException {
        BufferedReader reader;
        List<String> settings = new ArrayList<>();
        final InputStream file = new FileInputStream(context.getFilesDir() + "/" + PATH);
        reader = new BufferedReader(new InputStreamReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
                settings.add(line);
            }

        return settings;
    }

    public static Boolean addSetting(String key, String value, Context context) throws IOException {
        List<String> settings = loadAllSettings(context);
        settings.add(key + " = " + '"' + value + '"');
        File file = new File(context.getFilesDir() + "/" + PATH);
        System.out.println(file.exists());
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream fileout = new FileOutputStream(context.getFilesDir() + "/" + PATH);
        OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
        for (String s:settings) {
            outputWriter.write(s + "\n");
            System.out.println(context.getFilesDir() + "/" + PATH);
        }
        outputWriter.close();

        return false;
    }

    public static Boolean changeSetting(String key, String value, Context context) throws IOException {
        List<String> settings = loadAllSettings(context);
        for (int i=0; i<settings.size(); i++) {
            if(settings.get(i).contains(key)) {
                if (settings.get(i).indexOf('"') >= 0) {
                    String temp = settings.get(i).substring(0, settings.get(i).indexOf('"'));
                    temp = temp + value;
                    //String temp = key + " = " + '"' + value + '"';
                    settings.set(i, temp);
                }
            }
        }

        //FileOutputStream rawResource = context.getResources().openRawResource(R.raw.settings);
        File file = new File(context.getFilesDir() + "/" + PATH);
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream fileout = new FileOutputStream(context.getFilesDir() + "/" + PATH);
        OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
        for (String s:settings) {
            outputWriter.write(s + "\n");
            System.out.println("Changed setting in: " + context.getFilesDir() + "/" + PATH + " to: " + value);
        }
        outputWriter.close();

        return false;
    }
    public static Drawable loadPokemon(Pokemon pokemon, Context context) throws IOException {
        Drawable d;
        d = Drawable.createFromStream(context.getAssets().open(loadSetting("PokemonImagePathStart",context) + pokemon.getNumber() + loadSetting("PokemonImagePathEnd",context)), null);
        return d;
    }

    public static Boolean clearSettings(Context context) throws IOException {
        File file = new File(context.getFilesDir() + "/" + PATH);
        file.delete();
        file.createNewFile();
        return true;
    }
}

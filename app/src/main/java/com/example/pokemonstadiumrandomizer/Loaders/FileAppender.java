package com.example.pokemonstadiumrandomizer.Loaders;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import static com.example.pokemonstadiumrandomizer.Loaders.LoadFromTxt.PATH;

public class FileAppender {
    public static Boolean appendFile(String path, String toAdd, Context context) throws IOException {

        List<String> currentData = read(path, context);
        currentData.add(toAdd);
        File file = new File(context.getFilesDir() + "/" + path);
        if(!file.exists()){
            file.createNewFile();
        }
        System.out.println(file.exists());
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream fileout = new FileOutputStream(context.getFilesDir() + "/" + path);
        OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
        for (String s:currentData) {
            outputWriter.write(s + "\n");
        }
        outputWriter.close();

        return false;
    }

    private static List<String> read(String path, Context context) throws IOException {
        List <String> data = new ArrayList();
        BufferedReader reader;
        final InputStream file = new FileInputStream(context.getFilesDir() + "/" + path);
        reader = new BufferedReader(new InputStreamReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            data.add(line);
        }

        return data;
    }
}

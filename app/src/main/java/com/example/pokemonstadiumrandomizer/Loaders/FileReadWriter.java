package com.example.pokemonstadiumrandomizer.Loaders;

import android.content.Context;

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

public class FileReadWriter {
    public static Boolean appendFile(String path, String toAdd, Context context){

        List<String> currentData = read(path, context);
        currentData.add(toAdd);
        File file = new File(context.getFilesDir() + "/" + path);
        try {
        if(!file.exists()){
            file.createNewFile();
        }
        System.out.println(file.exists());
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream fileout = null;

            fileout = new FileOutputStream(context.getFilesDir() + "/" + path);

        OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
        for (String s:currentData) {
            outputWriter.write(s + "\n");
        }
        outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<String> read(String path, Context context){
        List <String> data = new ArrayList();
        BufferedReader reader;
        final InputStream file;
        try {
            file = new FileInputStream(context.getFilesDir() + "/" + path);
        reader = new BufferedReader(new InputStreamReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            data.add(line);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}

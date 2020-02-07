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

/**
 * Class to read Data from internal storage of an app using static methods to be invoked from anywhere
 * Generic and can be used in any app without modification
 * All calls assume the FilePath provided is located inside the internal storage of the app
 * (eg. "/data/user/0/com.example.applicationName/files/)
 * The Context is used to read the internal storage, could probaply be read some other way making method calls easier, worth looking into for future use.
 */
public class FileReadWriter {

    /**
     * Adds an extra line to a File
     * @param path  The FilePath of the File to be appended (Assuming it is inside the internal storage of the app).
     * @param toAdd The String representation of a string to add (use "\n" to add several lines in a single method call).
     * @param context   The context of the Activity calling the method, used to read the internal storage.
     */
    public static void appendFile(String path, String toAdd, Context context){

        List<String> currentData = read(path, context);
        currentData.add(toAdd);
        try {
        createFile(path, context);
        FileOutputStream fileout = new FileOutputStream(context.getFilesDir() + "/" + path);
        OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
        for (String s:currentData) {
            outputWriter.write(s + "\n");
        }
        outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes a String to a File, overwriting the entire File one already existed
     * @param path  The FilePath of the File to be written to (Assuming it is inside the internal storage of the app).
     * @param toWrite   The String representation of what to be written to the file (use "\n" to add several lines in a single method call).
     * @param context   The Context of the Activity calling the method, used to read the internal storage.
     */
    public static void write(String path, String toWrite, Context context){
        try {
            createFile(path, context);
            FileOutputStream fileout = new FileOutputStream(context.getFilesDir() + "/" + path);

            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                outputWriter.write(toWrite + "\n");
            outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes a String to a File, overwriting the entire File one already existed
     * @param path  The FilePath of the File to be written to (Assuming it is inside the internal storage of the app).
     * @param toWrite   The List of String representations of what to be written to the file.
     * @param context   The Context of the Activity calling the method, used to read the internal storage.
     */
    public static void write(String path, List<String> toWrite, Context context){
        try {
            createFile(path, context);
            FileOutputStream fileout = new FileOutputStream(context.getFilesDir() + "/" + path);

            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            for (String s:toWrite) {
                outputWriter.write(s + "\n");
            }
            outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads a File and returns the data as a List of Strings (where each new line in the file is its own String in the List).
     * @param path  The FilePath of the File to be read (Assuming it is inside the internal storage of the app).
     * @param context   The Context of the Activity calling the method, used to read the internal storage.
     * @return  The List<String> representation of the File, where each String is a line of the File.
     */
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

    /**
     * Creates a File if one was not already existing and then returns the File Object of the FilePath
     * @param path  The FilePath of the File to be created (Assuming it is inside the internal storage of the app).
     * @param context   The Context of the Activity calling the method, used to read the internal storage.
     * @return  The File Object representing the FilePath path.
     */
    private static File createFile(String path, Context context){
        File file = new File(context.getFilesDir() + "/" + path);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}

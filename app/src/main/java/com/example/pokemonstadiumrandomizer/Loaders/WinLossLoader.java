package com.example.pokemonstadiumrandomizer.Loaders;

import android.content.Context;

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

public class WinLossLoader {
    public static void writeScores(List<Pokemon> winner, List<Pokemon> loser, Context context) throws IOException {
        try {
/*
            File winnerFile = new File(context.getFilesDir() + "/" + "winners.txt");
            File loserFile = new File(context.getFilesDir() + "/" + "losers.txt");

                FileOutputStream winnerFileOut = new FileOutputStream(winnerFile);
                OutputStreamWriter outputWriter = new OutputStreamWriter(winnerFileOut);
                for (Pokemon current : winner) {
                    outputWriter.write(current.getName() + "\n");
                }
            outputWriter.close();
            FileOutputStream loserFileOut = new FileOutputStream(loserFile);
            outputWriter = new OutputStreamWriter(loserFileOut);
                for (Pokemon current : loser) {
                    outputWriter.write(current.getName() + "\n");
                }
            outputWriter.close();*/
        for(Pokemon current:winner){
            FileAppender.appendFile("winners.txt",current.getName(), context);
        }
        for (Pokemon current:loser){
            FileAppender.appendFile("losers.txt", current.getName(), context);
        }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readScores(String path, Context context) throws IOException {
        List<String> res = new ArrayList<>();
        res.add("Pokemon found in " + path);
        BufferedReader reader;
        final InputStream file = new FileInputStream(context.getFilesDir() + "/" + path);
        reader = new BufferedReader(new InputStreamReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            res.add(line);
        }
        return res;
    }
}

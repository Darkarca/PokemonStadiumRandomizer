package com.example.pokemonstadiumrandomizer.Loaders;

import android.content.Context;

import com.example.pokemonstadiumrandomizer.utilities.Pokemon;

import java.io.IOException;
import java.util.List;

public class WinLossLoader {

    public static void writeScores(List<Pokemon> winner, List<Pokemon> loser, Context context) {
        for(Pokemon current:winner){
            FileReadWriter.appendFile("winners.txt",current.getName(), context);
        }
        for (Pokemon current:loser){
            FileReadWriter.appendFile("losers.txt", current.getName(), context);
        }
    }

}

package com.example.pokemonstadiumrandomizer.Loaders;

import android.content.Context;

import com.example.pokemonstadiumrandomizer.utilities.Pokemon;

import java.util.List;

/**
 * Class used to save a team of Pokemon into the winner and loser Files using the FileReadWriter.
 */
public class WinLossLoader {

    /**
     * Saves a List (represents a team of Pokemon) of Pokemon to the winner and loser Textfiles in order to use later for data analysis.
     * @param winner    The FilePath for the winners file, more information about FilePath assumptions in FileReadWriter.
     * @param loser     The FilePath for the losers file, more information about FilePath assumptions in FileReadWriter
     * @param context   The Context of the Activity calling the method, used to access internal storage.
     */
    public static void writeScores(List<Pokemon> winner, List<Pokemon> loser, Context context) {
        for(Pokemon current:winner){
            FileReadWriter.appendFile("winners.txt",current.getName(), context);
        }
        for (Pokemon current:loser){
            FileReadWriter.appendFile("losers.txt", current.getName(), context);
        }
    }

}

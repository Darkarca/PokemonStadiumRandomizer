package com.example.pokemonstadiumrandomizer.Loaders;

import android.content.Context;
import com.example.pokemonstadiumrandomizer.utilities.Pokemon;
import java.util.List;

/**
 * Interface for loading Pokemon from a file (Or the internet etc?)
 *
 */
interface IPokemonLoader {
    static List<Pokemon> loadPokemon(Context context){
        return null;
    }
}

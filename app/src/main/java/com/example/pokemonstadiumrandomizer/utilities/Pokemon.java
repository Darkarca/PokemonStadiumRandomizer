package com.example.pokemonstadiumrandomizer.utilities;

import java.util.List;

/**
 * Class to represent a Pokemon (Currently only supports Generation 1 as there is no implementation for abilities.
 * Possible extensions would include Moves being represented by an Object
 * (found a list of all Generation 1 Moves at https://pokemondb.net/move/generation/1, seems to contain lists for several generations)
 * Constructor needs to be overloaded when abilities are implemented.
 * Possible damage calculation methods?
 */
public class Pokemon {
    private String name;
    private String[] stats;
    private String[] attacks;
    private int number;


    /**
     * Constructor creating a Pokemon Object representing a Generation 1 Pokemon (No ability),
     * currently does not support Move Objects but rather represents Moves as Strings just to be displayed in a TextView.
     * @param list  The List of Strings containing necessary information for the Pokemon to be created.
     */
    public Pokemon(List<String> list){
        this.name = list.get(0);
        this.stats = new String[5];
        this.attacks = new String[4];
        this.stats[0] = list.get(1);
        this.stats[1] = list.get(2);
        this.stats[2] = list.get(3);
        this.stats[3] = list.get(4);
        this.stats[4] = list.get(5);
        this.attacks[0] = list.get(6);
        this.attacks[1] = list.get(7);
        this.attacks[2] = list.get(8);
        this.attacks[3] = list.get(9);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getStats() {
        return stats;
    }

    public void setStats(String[] stats) {
        this.stats = stats;
    }

    public String[] getAttacks() {
        return attacks;
    }

    public void setAttacks(String[] attacks) {
        this.attacks = attacks;
    }

    /**
     * Concatenates the Pokemons number to a three digit String,
     * useful to find ImageNames since most Image resources will be named Pokemon001.png etc.
     * @return  This Pokemons NationalDex number represented by a 3-digit String.
     */
    public String getNumber() {
        String n = number + "";
        if(this.number<10){
            n = "00" + number;
        }else if(this.number<100){
            n = "0" + number;
        }
        return n;
    }

    public int getNumberValue(){
        return number;

    }

    public void setNumber(int number) {
       this.number = number;

    }
}

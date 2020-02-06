package com.example.pokemonstadiumrandomizer.GUIUpdaters;

import android.content.Context;
import android.widget.LinearLayout;

import com.example.pokemonstadiumrandomizer.Loaders.AssetLoader;

public class BackgroundGUIUpdater {
    public static void setBackground(LinearLayout view, Context context){
        String s = AssetLoader.loadSetting("BackgroundImage",context);
        int id = context.getResources().getIdentifier(s.substring(0,s.length()-4), "drawable", context.getPackageName());
        view.setBackgroundResource(id);
    }
}

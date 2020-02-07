package com.example.pokemonstadiumrandomizer.GUIUpdaters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pokemonstadiumrandomizer.Loaders.AssetLoader;
import com.example.pokemonstadiumrandomizer.R;

public class BackgroundGUIUpdater {
    public static void setBackground(LinearLayout view, Context context){
        String s = AssetLoader.loadSetting("BackgroundImage",context);
        int id = context.getResources().getIdentifier(s.substring(0,s.length()-4), "drawable", context.getPackageName());
        view.setBackgroundResource(id);
    }

    public static void setDarkText(Activity context){
        context.setTheme(R.style.AppTheme_NoActionBarDefault);
    }

    public static void setLightText(Activity context){
        context.setTheme(R.style.AppTheme_NoActionBarDarkBackground);
    }

}

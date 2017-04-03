package p0mamin.nined;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mark on 08.03.2017.
 */
public class DataBase {


    public static final String APP_PREFERENCES_LVL = "level_now"; // имя кота
    public static final String APP_PREFERENCES_HIGH_SCORE= "high_score"; // возраст кота



    public DataBase(){
    }

    public void setInt(String name, int i){
        SharedPreferences.Editor editor = MainClass.data_base.edit();
        editor.putInt(name, i);
        editor.apply();
    }


    public void setBoolean(String name, Boolean i){
        SharedPreferences.Editor editor = MainClass.data_base.edit();
        editor.putBoolean(name, i);
        editor.apply();
    }


    public void setString(String name, String i){
        SharedPreferences.Editor editor = MainClass.data_base.edit();
        editor.putString(name, i);
        editor.apply();
    }

    public boolean getBoolean(String name){
        return MainClass.data_base.getBoolean(name, false);
    }

    public int getInt(String name){
        return MainClass.data_base.getInt(name, 0);
    }

    public String getStrint(String name){
        return MainClass.data_base.getString(name, "");
    }
}

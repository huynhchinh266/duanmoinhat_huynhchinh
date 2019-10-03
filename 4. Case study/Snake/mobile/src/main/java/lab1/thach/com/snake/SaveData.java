package lab1.thach.com.snake;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by phamn on 2/12/2017.
 */

public class SaveData {

    private static SharedPreferences sharedPreferences;
    private static final String HIGHT_SCORE_NAME = "hight_score_name";
    private static final String HIGHT_SCORE_VALUE = "hight_score_value";

    public SaveData(Context context){
        sharedPreferences = context.getSharedPreferences("snake_game", Context.MODE_PRIVATE);
    }

    public void saveUser(String name, int score){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(HIGHT_SCORE_NAME, name);
        editor.putInt(HIGHT_SCORE_VALUE, score);
        editor.commit();
    }

    public String getHightScoreName(){
        String line = sharedPreferences.getString(HIGHT_SCORE_NAME, "");
        return line;
    }

    public int getHightScoreValue(){
        int value = sharedPreferences.getInt(HIGHT_SCORE_VALUE, 0);
        return value;
    }

}

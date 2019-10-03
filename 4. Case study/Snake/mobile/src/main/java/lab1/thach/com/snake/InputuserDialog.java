package lab1.thach.com.snake;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by phamn on 2/12/2017.
 */

public class InputuserDialog extends DialogFragment {

    @Nullable
    private int score;
    public void setScore(int score){
        this.score = score;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.inputuser_dialog, container, false);

        Button b = (Button) v.findViewById(R.id.button);
        final EditText editText = (EditText) v.findViewById(R.id.editText);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                if(name!=null){
                    SaveData saveData = new SaveData(getActivity());
                    saveData.saveUser(name, score);
                    GamePlay.hightScoreName = name;
                    GamePlay.hightScoreValue = score;
                    saveData = null;
                }
                dismiss();
            }
        });

        return v;
    }
}

package p0mamin.nined.Menu;

import android.content.Intent;
import android.net.Uri;
import android.view.MotionEvent;

import p0mamin.nined.Button;
import p0mamin.nined.MainClass;
import p0mamin.nined.R;
import p0mamin.nined.State;
import p0mamin.nined.Texture;

/**
 * Created by Mark on 08.03.2017.
 */
public class SettingScreen {

    Texture fon;
    Texture text;
    Button telegram;

    public SettingScreen(){
        float size =  MainClass.widht / 4f;
        fon = new Texture(R.drawable.square, 0, 0, MainClass.widht, MainClass.height / MainClass.ratio * 1.3f);
        text = new Texture(R.drawable.settingtext, 0, 0, MainClass.widht, MainClass.height / MainClass.ratio);
        telegram = new Button(R.drawable.telegram,  0, -0.4f,  size / 1.09f, size / MainClass.ratio * 9.5f / 11f / 1.09f);
    }

    public void render(float delta){
        fon.draw();
        text.draw();
        telegram.render(delta);
    }


    public State onTouch(float screenX, float screenY, MotionEvent event){
        if(telegram.onTouch(screenX, screenY)){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain=maaaaark"));
            MainClass.activity.startActivity(browserIntent);
        }
        return State.Default;
    }

}

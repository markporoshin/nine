package p0mamin.nined.Game;

import android.util.Log;

import p0mamin.nined.Figure;
import p0mamin.nined.MainClass;
import p0mamin.nined.R;
import p0mamin.nined.Texture;

/**
 * Created by Mark on 05.02.2017.
 */
public class BackgroundScreen {
    private final int MAX_FIGURE = 10;

    Texture background;
    Texture square;

    public BackgroundScreen(){

        float size = (MainClass.widht / DigitField.offset * (DigitField.MAX-0.7f) + MainClass.widht / 6) / 2;
        background = new Texture(R.drawable.square,  0, 0, 1, 1.5f / MainClass.ratio);
        square = new Texture(R.drawable.square3,  0, (((4f+0.5f) * MainClass.widht / DigitField.offset + MainClass.height / 2f) - 1f) * MainClass.ratio, size*1.00f, size*1.01f);
    }

    public void render(float delta){
        //background.setDepth((float)Math.sin(DigitField.time) / 2);
        //background.setPosition();

        background.draw();

        for(int i=0; i< MAX_FIGURE; i++){
           //figure[i].render(delta);
        }
        square.draw();

    }

}

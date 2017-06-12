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
    Texture square_help;

    public BackgroundScreen(){

        float size = (MainClass.widht / DigitField.offset * (DigitField.MAX-0.7f) + MainClass.widht / 6) / 2;
        background = new Texture(R.drawable.square2,  0, 0, 1, 1.1f / MainClass.ratio);
        square_help = new Texture(R.drawable.square_help,  0, 0, 1, 1f / MainClass.ratio);
        square = new Texture(R.drawable.square3,  0, (((4f+0.5f) * MainClass.widht / DigitField.offset + MainClass.height / 2f) - 1f) * MainClass.ratio, size*1.023f, size*1.027f);
    }

    public void render(float delta){
        background.draw();
        square_help.draw();
        for(int i=0; i< MAX_FIGURE; i++){
           //figure[i].render(delta);
        }
        square.draw();

    }

}

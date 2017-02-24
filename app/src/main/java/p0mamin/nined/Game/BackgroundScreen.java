package p0mamin.nined.Game;

import p0mamin.nined.Batch;
import p0mamin.nined.MainClass;
import p0mamin.nined.R;
import p0mamin.nined.State;
import p0mamin.nined.Texture;
import p0mamin.nined.mathematics.Vec2;

/**
 * Created by Mark on 05.02.2017.
 */
public class BackgroundScreen {
    Texture background;
    Texture square;
    public BackgroundScreen(){
        float size = (MainClass.widht / DigitField.offset * (DigitField.MAX-0.7f) + MainClass.widht / 6) / 2;
        background = new Texture(R.drawable.square,  0, 0, 1, 1.5f / MainClass.ratio);
        square = new Texture(R.drawable.square3,  0,/* MainClass.widht / 14f +*/ 1.5f * MainClass.widht / DigitField.offset, size*1.00f, size*1.00f);
    }

    public void render(float delta){
        //background.setDepth((float)Math.sin(DigitField.time) / 2);
        //background.setPosition();
        background.draw();
        square.draw();
    }

}

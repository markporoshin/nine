package p0mamin.nined.Game;

import p0mamin.nined.Game.Levels.Shape;
import p0mamin.nined.R;
import p0mamin.nined.State;
import p0mamin.nined.Texture;
import p0mamin.nined.mathematics.Vec2;

/**
 * Created by Mark on 29.01.2017.
 */
public class GameScreen {
    DigitField DF;
    BackgroundScreen BGS;
    Shape SP;
    public  GameScreen(boolean[][] field, byte[][] map ,int ZONE){
        DF = new DigitField(field);
        BGS = new BackgroundScreen();
        SP = new Shape(map, ZONE);
    }

    public int getSum(byte[][] map, byte m){
        return DF.getSum(map, m);
    }
    public void mix(){
        DF.mix();
    }

    public void render(float delta, int[] sum, int[] resum){
        BGS.render(delta);
        SP.render(delta, sum, resum);
        DF.render(delta);
    }

    public State onTouch(float x, float y){
        DF.OnTouch(x, y);
        return State.Default;
    }
}

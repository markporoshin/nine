package p0mamin.squax.Game;

import java.util.LinkedList;

import p0mamin.squax.Game.Levels.Addition;
import p0mamin.squax.Game.Levels.Shape;
import p0mamin.squax.State;

/**
 * Created by Mark on 29.01.2017.
 */
public class GameScreen {
    public LinkedList<Addition> additions = new LinkedList<Addition>();
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

    public void mix0(){
        DF.mix0();
    }

    public void render(float delta, int[] sum, int[] resum){
        BGS.render(delta);

        SP.render(delta, sum, resum);
        DF.render(delta);
        for (Addition a: additions) {
            a.render(delta);
        }
    }

    public State onTouch(float x, float y){
        DF.OnTouch(x, y);
        return State.Default;
    }

    public State onDrag(float x, float y){
        DF.OnTouch(x, y);
        return State.Default;
    }
}

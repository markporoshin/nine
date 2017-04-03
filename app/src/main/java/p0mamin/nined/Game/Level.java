package p0mamin.nined.Game;

import android.util.Log;

import java.util.LinkedList;

import p0mamin.nined.Batch;
import p0mamin.nined.Game.Levels.Addition;

import p0mamin.nined.Game.Levels.Shape;
import p0mamin.nined.Menu.ChooseScreen;
import p0mamin.nined.State;

/**
 * Created by Mark on 12.02.2017.
 */
public class Level {
    protected int lvl;
    public static final int MAX_ZONE = 5;
    protected int ZONE;
    protected int[] sum;
    protected int[] resum;
    protected byte[][] map;
    protected boolean[][] field;
    protected GameScreen GS;
    protected Shape SP;

    public Level(){

    }
    public void reshape(){
        for(int i = 0; i < ZONE; i++)
            resum[i] = GS.getSum(map, (byte)(i+1));
    }

    public void render(float delta){
        reshape();
        GS.render(delta, sum, resum);
        if(check()){
            Batch.DB.setInt("lvl" + lvl, (int)GS.DF.time);
            ChooseScreen.lvl++;
            Batch.state = State.Game;
            if(Batch.DB.getInt("lvllost") < this.lvl){
                Batch.DB.setInt("lvllost", this.lvl);
            }
        }
    }

    public State onTouch(float x, float y){
        if(GS.onTouch(x, y) != State.Default)
            return GS.onTouch(x, y);

        return State.Default;
    }

    public boolean check(){
        for(int i = 0;i < ZONE;i++){
            if(!(sum[i] == resum[i]))
                return false;
        }
        for (Addition a: GS.additions) {
            if(!a.check()){
                return false;
            }
        }
        return  true;
    }
}

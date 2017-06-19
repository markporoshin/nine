package p0mamin.squax.Game;

import android.util.Log;

import p0mamin.squax.Batch;
import p0mamin.squax.Game.Levels.Addition;

import p0mamin.squax.Game.Levels.Shape;
import p0mamin.squax.Game.Levels.WinScreen;
import p0mamin.squax.Menu.ChooseScreen;
import p0mamin.squax.State;

/**
 * Created by Mark on 12.02.2017.
 */
public class Level {
    public String TAG = "Level";

    protected WinScreen win;
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
        win = new WinScreen();
    }
    public void reshape(){
        for(int i = 0; i < ZONE; i++)
            resum[i] = GS.getSum(map, (byte)(i+1));
    }

    public void render(float delta){
        switch (Batch.state) {
            case Game:
                reshape();
                GS.render(delta, sum, resum);
                if (check()) {
                    Log.d(TAG, "WinScreen");
                    Batch.state = State.Win;
                    if (Batch.DB.getInt("lvllost") < this.lvl) {
                        Batch.DB.setInt("lvllost", this.lvl);
                    }
                }
                break;
            case Win:
                Log.d(TAG+"win", "render");
                GS.render(delta, sum, resum);
                Batch.state = win.render(delta);
                if(Batch.state != State.Win){
                    Batch.DB.setInt("lvl" + lvl, (int) GS.DF.time);
                    if (ChooseScreen.lvl < 30)
                        ChooseScreen.lvl++;

                }
                win.render(delta);
                break;
        }
    }

    public State onTouch(float x, float y){
        Log.d(TAG, "" + ChooseScreen.lvl);
        if(GS.onTouch(x, y) != State.Default)
            return GS.onTouch(x, y);
        if(ChooseScreen.lvl==30)
            return State.Menu;
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

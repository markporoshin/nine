package p0mamin.nined.Game.Levels;

import android.util.Log;

import p0mamin.nined.Game.GameScreen;
import p0mamin.nined.Game.Level;
import p0mamin.nined.State;

/**
 * Created by Mark on 12.02.2017.
 */
public class Level1 extends Level{
    public Level1(){
        super();
        map  = new byte[][]{
                {0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 0, 0, 4, 4, 0, 0},
                {0, 0, 1, 1, 1, 4, 4, 0},
                {0, 1, 1, 1, 1, 1, 4, 0},
                {0, 1, 2, 2, 1, 3, 3, 0},
                {0, 2, 2, 3, 3, 3, 3, 0},
                {0, 2, 2, 3, 3, 3, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        field = new boolean[][]{
                {true, true, true,  true, true, true, true, true},
                {true, true, true,  true, true, false,true, true},
                {true, true, true,  true, true, true, true, true},
                {true, true, false, true, true, true, true, true},
                {true, true, true,  true, true, true, true, true},
                {true, true, true,  true, true, true, true, true},
                {true, true, true,  true, true, true, true, true},
                {true, true, true,  true, true, true, true, true}};
        ZONE = 4;
        sum = new int[ZONE];
        resum = new int[ZONE];
        SP = new Shape(map ,ZONE);
        GS = new GameScreen(field, map, ZONE);

        for(int i = 0;i < ZONE;i++) {
            sum[i] = GS.getSum(map, (byte) (i+1));
        }
        GS.mix();
        for(int i=0;i<ZONE;i++) {
            resum[i] = GS.getSum(map, (byte) 1);
        }
    }

}

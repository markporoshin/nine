package p0mamin.squax.Game.Levels;

import p0mamin.squax.Game.GameScreen;
import p0mamin.squax.Game.Level;

/**
 * Created by Mark on 19.02.2017.
 */
public class Level3 extends Level {
    public Level3(){
        super();
        lvl = 3;
        map  = new byte[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 2, 2, 2, 2, 0, 0},
                {0, 0, 1, 1, 2, 2, 0, 0},
                {0, 0, 1, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };//mark check point
        field = new boolean[][]{
                {true, true, true,  true, true, true, true, true},
                {true, true, true,  true, true, true, true, true},
                {true, true, false, true, true, false,true, true},
                {true, true, true,  true, true, true, false,true},
                {true, false,true,  true, true, true, true, true},
                {true, true, true,  true, true, true, true, true},
                {true, true, false, true, true, false, true, true},
                {true, true, true,  true, true, true, true, true}};
        ZONE = 2;
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



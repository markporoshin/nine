package p0mamin.squax.Game.Levels;

import p0mamin.squax.Game.GameScreen;
import p0mamin.squax.Game.Level;

/**
 * Created by Mark on 12.03.2017.
 */
public class Level12 extends Level {
    public Level12(){
        super();
        lvl = 12;
        map  = new byte[][]{
                {4, 4, 0, 0, 0, 3, 3, 0},
                {4, 0, 0, 3, 3, 3, 3, 0},
                {0, 0, 3, 3, 3, 1, 1, 1},
                {3, 3, 3, 1, 1, 1, 1, 1},
                {3, 3, 1, 1, 1, 2, 2, 2},
                {1, 1, 1, 2, 2, 2, 2, 2},
                {1, 1, 2, 2, 2, 0, 0, 4},
                {0, 0, 2, 0, 0, 0, 4, 4}
        };//mark check point
        field = new boolean[][]{
                {true, true, true,  false, true, true, true, true},
                {true, true, true,  true, true, true, true, true},
                {true, false, true,  true, true, true, true, true},
                {true, true, true,  true, true, true, false, true},
                {true, true, true,  true, true, true, true, true},
                {true, true, true,  true, true, true, true, true},
                {true, true, true,  true, true, true, true, true},
                {true, true, true,  true, false, true, true, true}};
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

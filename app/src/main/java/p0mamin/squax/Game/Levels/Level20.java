package p0mamin.squax.Game.Levels;

import p0mamin.squax.Game.GameScreen;
import p0mamin.squax.Game.Level;

/**
 * Created by Mark on 12.03.2017.
 */
public class Level20 extends Level {
    public Level20(){
        super();
        lvl = 20;
        map  = new byte[][]{
                {0, 2, 2, 0, 0, 1, 1, 1},
                {2, 2, 2, 0, 1, 1, 1, 0},
                {2, 2, 0, 0, 1, 1, 0, 0},
                {2, 2, 0, 1, 1, 0, 0, 3},
                {2, 0, 0, 1, 1, 0, 3, 3},
                {0, 0, 1, 1, 0, 0, 3, 3},
                {0, 1, 1, 1, 0, 3, 3, 3},
                {1, 1, 1, 0, 0, 3, 3, 0}
        };//mark check point
        field = new boolean[][]{
                {true, true, true,  true, true, true, true, true},
                {true, true, true,  true, true, true, true, true},
                {true, true, true,  true, true, true, true, true},
                {true, true, true,  true, true, true, true, true},
                {true, true, true,  true, true, true, true, true},
                {true, true, true,  true, true, true, true, true},
                {true, true, true,  true, true, true, true, true},
                {true, true, true,  true, true, true, true, true}};
        ZONE = 3;
        sum = new int[ZONE];
        resum = new int[ZONE];
        SP = new Shape(map ,ZONE);
        GS = new GameScreen(field, map, ZONE);
        GS.additions.add(new Addition(6, false));
        GS.additions.add(new Addition(4, false));
        for(int i = 0;i < ZONE;i++) {
            sum[i] = GS.getSum(map, (byte) (i+1));
        }
        GS.mix();
        for(int i=0;i<ZONE;i++) {
            resum[i] = GS.getSum(map, (byte) 1);
        }
    }
}

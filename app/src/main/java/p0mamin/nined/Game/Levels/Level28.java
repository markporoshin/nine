package p0mamin.nined.Game.Levels;

import p0mamin.nined.Game.GameScreen;
import p0mamin.nined.Game.Level;

/**
 * Created by Mark on 12.03.2017.
 */
public class Level28 extends Level {
    public Level28(){
        super();
        lvl = 28;
        map  = new byte[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 3, 3, 3, 3, 3, 2, 0},
                {0, 4, 3, 3, 3, 2, 2, 0},
                {0, 4, 4, 3, 2, 2, 2, 0},
                {0, 4, 4, 4, 1, 2, 2, 0},
                {0, 4, 4, 1, 1, 1, 2, 0},
                {0, 4, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };//mark check point
        field = new boolean[][]{
                {false, true, false,  true, false, true, false, true},
                {true,  true, true,  true, true, true, true, false},
                {false, true, false,  true, true, false, true, true},
                {true,  true, true,  true, true, true, true, false},
                {false, true, true,  true, true, true, true, true},
                {true,  true, false,  true, true, false, true, false},
                {false, true, true,  true, true, true, true, true},
                {true, false,true,  false,true, false, true,false}};
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

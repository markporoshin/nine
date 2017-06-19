package p0mamin.squax.Game.Levels;

import p0mamin.squax.Game.GameScreen;
import p0mamin.squax.Game.Level;

/**
 * Created by Mark on 10.03.2017.
 */
public class Level6 extends Level {
    public Level6(){
        super();
        lvl = 6;
        map  = new byte[][]{
                {0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 0, 0, 4, 4, 0, 0},
                {0, 0, 1, 1, 1, 4, 4, 0},
                {0, 1, 1, 1, 1, 1, 4, 0},
                {0, 1, 2, 2, 1, 3, 3, 0},
                {0, 2, 2, 3, 3, 3, 3, 0},
                {0, 0, 2, 3, 3, 3, 0, 0},
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
        //GS.additions.add(new Addition(1, false));
        //GS.additions.add(new Addition(2, false));
        for(int i = 0;i < ZONE;i++) {
            sum[i] = GS.getSum(map, (byte) (i+1));
        }
        GS.mix0();
        for(int i=0;i<ZONE;i++) {
            resum[i] = GS.getSum(map, (byte) 1);
        }
    }
}

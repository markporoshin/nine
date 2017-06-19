package p0mamin.squax.Game.Levels;

import p0mamin.squax.Game.GameScreen;
import p0mamin.squax.Game.Level;

/**
 * Created by Mark on 12.03.2017.
 */
public class Level16 extends Level {
    public Level16(){
        super();
        lvl = 16;
        map  = new byte[][]{
                {4, 4, 0, 0, 0, 0, 0, 0},
                {4, 4, 0, 0, 0, 0, 0, 0},
                {1, 1, 3, 3, 0, 0, 0, 0},
                {1, 1, 3, 3, 0, 0, 0, 0},
                {2, 2, 4, 4, 1, 1, 0, 0},
                {2, 2, 4, 4, 1, 1, 0, 0},
                {1, 1, 3, 3, 2, 2, 3, 3},
                {1, 1, 3, 3, 2, 2, 3, 3}
        };
        field = new boolean[][]{
                {true, true, true,  true, true, true, true, true},
                {true, true, true,  false, true, true, true, true},
                {true, false, true,  true, true, true, true, true},
                {true, true, true,  true, true, true, true, true},
                {false, true, true,  true, true, false, true, true},
                {true, true, false,  true, true, true, true, true},
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

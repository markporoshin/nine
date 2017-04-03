package p0mamin.nined.Game.Levels;

import p0mamin.nined.Game.GameScreen;
import p0mamin.nined.Game.Level;

/**
 * Created by Mark on 12.03.2017.
 */
public class Level26 extends Level {
    public Level26(){
        super();
        lvl = 26;
        map  = new byte[][]{
                {0, 0, 4, 4, 2, 2, 0, 0},
                {0, 4, 4, 4, 2, 2, 2, 0},
                {4, 4, 4, 4, 2, 2, 2, 2},
                {4, 4, 4, 4, 2, 2, 2, 2},
                {1, 1, 1, 1, 3, 3, 3, 3},
                {1, 1, 1, 1, 3, 3, 3, 3},
                {0, 1, 1, 1, 3, 3, 3, 0},
                {0, 0, 1, 1, 3, 3, 0, 0}
        };
        field = new boolean[][]{
                {true, true, true,  false, true, true, true, true},
                {true, true, true,  true, true, true, true, true},
                {true, true, true,  false, true, true, true, true},
                {true, true, true,  true, true, true, true, true},
                {true, true, true,  true, true, true, true, true},
                {true, true, true,  true, false, true, true, true},
                {true, true, true,  true, true, true, true, true},
                {true, true, true,  true, false, true, true, true}};
        ZONE = 4;
        sum = new int[ZONE];
        resum = new int[ZONE];
        SP = new Shape(map ,ZONE);
        GS = new GameScreen(field, map, ZONE);
        GS.additions.add(new Addition(2, false));
        GS.additions.add(new Addition(7, false));
        for(int i = 0;i < ZONE;i++) {
            sum[i] = GS.getSum(map, (byte) (i+1));
        }
        GS.mix();
        for(int i=0;i<ZONE;i++) {
            resum[i] = GS.getSum(map, (byte) 1);
        }
    }
}

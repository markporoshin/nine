package p0mamin.nined.Game;

import android.util.Log;

import p0mamin.nined.R;
import p0mamin.nined.Texture;
import p0mamin.nined.mathematics.Vec2;

/**
 * Created by Mark on 01.02.2017.
 */
public class DigitSquare {
    private DigitField df;
    public int i, j;
    private Digit d_00, d_01, d_11, d_10;
    private float centerX, centerY;
    private Vec2[] buf = new Vec2[4];
    private float time;
    private Vec2 center;
    private final float speed = 0.2f;
    public boolean rot;
    DigitSquare(){
        rot = false;
    }
    DigitSquare(DigitField df, int i, int j){


        this.df = df;
        this.i = i;
        this.j = j;
        this.d_00 = df.List[i][j];
        this.d_01 = df.List[i][j+1];
        this.d_11 = df.List[i+1][j+1];
        this.d_10 = df.List[i+1][j];

        rot = true;

        buf[0] = new Vec2(d_00.pos.x, d_00.pos.y);
        buf[1] = new Vec2(d_01.pos.x, d_01.pos.y);
        buf[2] = new Vec2(d_11.pos.x, d_11.pos.y);
        buf[3] = new Vec2(d_10.pos.x, d_10.pos.y);

        time = 0;
        centerX = (d_00.pos.x + d_11.pos.x) / 2;
        centerY = (d_00.pos.y + d_11.pos.y) / 2;
        center = new Vec2(centerX, centerY);
    }
    float angle;
    public void rotate(float delta){
        angle = delta*(90/speed);

        d_00.pos.subtract(center).rotate(angle).add(center);
        d_01.pos.subtract(center).rotate(angle).add(center);
        d_11.pos.subtract(center).rotate(angle).add(center);
        d_10.pos.subtract(center).rotate(angle).add(center);
        time += delta;

        check();
    }

    private void check(){
        if(time >= speed){

            finishRot();
        }
    }

    public void finishRot(){
        rot = false;
        df.List[i][j].pos = buf[3];
        df.List[i+1][j].pos = buf[2];
        df.List[i+1][j+1].pos = buf[1];
        df.List[i][j+1].pos = buf[0];

        Digit d = df.List[i][j];
        df.List[i][j] = df.List[i][j+1];
        df.List[i][j+1] = df.List[i+1][j+1];
        df.List[i+1][j+1] = df.List[i+1][j];
        df.List[i+1][j] = d;
    }
}

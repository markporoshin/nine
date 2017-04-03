package p0mamin.nined.Game;

import android.util.Log;

import java.util.Random;

import p0mamin.nined.Button;
import p0mamin.nined.MainClass;
import p0mamin.nined.font.Font;
import p0mamin.nined.mathematics.Vec2;

/**
 * Created by Mark on 29.01.2017.
 */
public class DigitField {
    public static final int MAX = 8;
    public DigitSquare ds;
    public static final float offset  = 5f;

    private int speed = 270;
    private Random rand = new Random();
    private int r;
    public static float time;
    public static Font n;

    public static Digit[][] List = new Digit[MAX][MAX];
    public Button[][] Buttons = new Button[MAX-1][MAX-1];

    DigitField(boolean rotateble[][]){
        ds = new DigitSquare();
        n = new Font();
        time = 0;
        for (int i = 0; i < MAX; i++)
            for (int j = 0; j < MAX; j++) {
                r = Math.abs(rand.nextInt()%9) + 1;
                List[i][j] = new Digit(r, rotateble[MAX-j-1][i]);
                List[i][j].setPos(new Vec2( (i+1.5f) * MainClass.widht / offset - 1,
                        ((j+1.f) * MainClass.widht / offset + MainClass.height / 2f) - 1));
                Log.d("TAG","  " + List[i][j].pos.x + "  " + List[i][j].pos.y);
            }

        for (int i = 0; i < MAX-1; i++)
            for (int j = 0; j < MAX-1; j++) {
                Buttons[i][j] = new Button((float)MainClass.widht / 11f
                        ,(float)(i+2.f) * MainClass.widht / offset - 1f
                        ,(float)(j+1.5f) * MainClass.widht / offset + MainClass.height / 2f - 1f);
            }

    }

    public int getSum(byte map[][], byte m){
        int sum = 0;
        for (int i = 0; i < MAX; i++)
            for (int j = 0; j < MAX; j++) {
                if(map[MAX-j-1][i] == m) {
                    sum += List[i][j].number;
                }
            }
        return sum;
    }

    public void render(float delta) {

        time += delta;
        if (time >= 2) {

        }

        /*for (int i = 0; i < MAX - 1; i++)
            for (int j = 0; j < MAX - 1; j++) {
                Buttons[i][j].render(delta);
            }*/
        for (int i = 0; i < MAX; i++)
            for (int j = 0; j < MAX; j++) {
                List[i][j].render();
            }

        if (ds.rot) {
            ds.rotate(delta);
        }
        n.draw((int)time, 0 - ((int)time+"").length() * 0.02f, 0.85f, 0.1f);

    }

    public void rotate(int i, int j){
        if(!ds.rot) {
            if(List[i][j].rotatable &&
                    List[i+1][j].rotatable &&
                    List[i+1][j+1].rotatable &&
                    List[i][j+1].rotatable)
            ds = new DigitSquare(this, i, j);
        }

    }

    public void OnTouch(float x, float y){
        for (int i = 0; i < MAX-1; i++)
            for (int j = 0; j < MAX-1; j++) {
                if(Buttons[i][j].onTouch(x, y)){
                    rotate(i, j);
                }
            }
        //
    }

    public void mix(){
        DigitSquare d = new DigitSquare();
        int r1, r2;
        for (int i = 0; i < MAX*5; i++)
            for (int j = 0; j < MAX*5; j++) {
                r1 = Math.abs(rand.nextInt())%(MAX - 1);
                r2 = Math.abs(rand.nextInt())%(MAX - 1);
                if(     List[r1][r2].rotatable &&
                        List[r1+1][r2].rotatable &&
                        List[r1][r2+1].rotatable &&
                        List[r1+1][r2+1].rotatable ){

                    d = new DigitSquare(this, r1, r2);
                    d.finishRot();
                }
            }
    }

    public void mix0(){
        DigitSquare d = new DigitSquare();
        d = new DigitSquare(this, 2, 2);
        d.finishRot();
        d = new DigitSquare(this, 2, 2);
        d.finishRot();
        d = new DigitSquare(this, 2, 2);
        d.finishRot();
    }



}

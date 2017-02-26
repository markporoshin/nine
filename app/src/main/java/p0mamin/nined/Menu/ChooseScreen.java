package p0mamin.nined.Menu;

import android.util.Log;
import android.view.MotionEvent;

import p0mamin.nined.Button;
import p0mamin.nined.MainClass;
import p0mamin.nined.R;
import p0mamin.nined.State;
import p0mamin.nined.Texture;
import p0mamin.nined.font.Font;
import p0mamin.nined.mathematics.Vec2;

/**
 * Created by Mark on 13.02.2017.
 */
public class ChooseScreen {
    public static int lvl = 1;

    private final int num_of_el = 5;

    private float[] pos = {0, 1.25f, 2.5f , 3.75f, 5};
    private float total_x;
    private Button[] levels = new Button[num_of_el];
    private Texture fon;
    private float oldx, oldy;
    private float buf_time;
    private Finish finish;
    Font font;


    public ChooseScreen(){
        fon = new Texture(R.drawable.square,  0, 0, 1, 1.5f / MainClass.ratio);
        font = new Font();
        finish = new Finish();
        setLevels();
        total_x = 0;
        oldx = -2;
        oldy = -2;


    }

    public void render(float delta){
        fon.draw();
        for(int i = 0; i < num_of_el; i++){
            setPos();
            if(Math.abs(pos[i] + total_x) < 1.5) {

               levels[i].render(delta);
            }
        }
        finish.render(delta);

    }

    public State onTouch(float screenX, float screenY, MotionEvent event){
        Log.d("Choose", "tap");
        finish.start = false;
        if(event.getAction() == MotionEvent.ACTION_UP){
            Log.d("Choose", "end tap");
        }
        return State.Default;
    }

    public void onDrag(float x, float y,float start_x, float start_y, MotionEvent event){
        if(oldx != -2){
            if(Math.abs(x - oldx) > Math.abs(y - oldy))
                total_x += x - oldx;
        }

        oldx = x;
        oldy = y;

        if(event.getAction() == MotionEvent.ACTION_UP) {
            //Log.d("ChooseScreen","start");
            oldx = -2;
            finish.start = true;
        }
    }

    class Finish{
        float time;
        int buf;
        boolean start;
        boolean onChoose;

        void start(){
            start = true;
            buf = 0;
            float min = Math.abs(pos[0] + total_x);
            Log.d("ChooseScreen","base min: " + min);
            for(int i = 0; i < num_of_el; i++){
                if(Math.abs(pos[i] + total_x) < min){
                    Log.d("ChooseScreen","min: " + min);
                    min = Math.abs(pos[i] + total_x);
                    buf = i;
                }
            }


            start = false;
            onChoose = true;
        }

        void render(float delta){
            if(start){
                time += delta;
                //Log.d("ChooseScreen","time: " + time);
            } else
            time = 0;
            if(time > 0.35f) {
                start();
            }
            if(onChoose == true){
                float a =  (pos[buf] + total_x) - 0;
                total_x -= a / 7;
                if(Math.abs(pos[buf] + total_x) < 0.01f) {
                    onChoose = false;
                    float a1 =  (pos[buf] + total_x) - 0;
                    total_x -= a1;
                }
            }
        }
    }

    private void setLevels(){
        float size = MainClass.widht / 2.5f;
        levels[0] = new Button(R.drawable.lvl1, size);
        levels[1] = new Button(R.drawable.lvl2, size);
        levels[2] = new Button(R.drawable.lvl3, size);
        levels[3] = new Button(R.drawable.lvl4, size);
        levels[4] = new Button(R.drawable.lvl5, size);

        levels[0].setPos(new Vec2(pos[0], 0));
        levels[1].setPos(new Vec2(pos[1], 0));
        levels[2].setPos(new Vec2(pos[2], 0));
        levels[3].setPos(new Vec2(pos[3], 0));
        levels[4].setPos(new Vec2(pos[4], 0));
    }

    private void setPos(){
        levels[0].setPos(new Vec2(pos[0] + total_x, 0));
        levels[1].setPos(new Vec2(pos[1] + total_x, 0));
        levels[2].setPos(new Vec2(pos[2] + total_x, 0));
        levels[3].setPos(new Vec2(pos[3] + total_x, 0));
        levels[4].setPos(new Vec2(pos[4] + total_x, 0));
    }
}

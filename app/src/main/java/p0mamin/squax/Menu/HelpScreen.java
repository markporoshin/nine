package p0mamin.squax.Menu;

import android.util.Log;
import android.view.MotionEvent;

import p0mamin.squax.Button;
import p0mamin.squax.MainClass;
import p0mamin.squax.R;
import p0mamin.squax.State;
import p0mamin.squax.Texture;
import p0mamin.squax.mathematics.Vec2;

/**
 * Created by Mark on 26.02.2017.
 */
public class HelpScreen {
    private final int num_of_el = 6;

    private float[] pos = {0, 1.5f, 3f , 4.5f, 6f, 7.5f};
    public float total_x;
    private Button[] helps = new Button[num_of_el];
    private Texture fon;
    private Texture fon2;
    private float oldx, oldy;
    private float buf_time;
    private Finish finish;
    private  boolean onDraging;
    private final float size = MainClass.widht / 1.25f;


    public HelpScreen(){
        fon = new Texture(R.drawable.square4,  0, 0, 1, 1.5f / MainClass.ratio);
        fon2 = new Texture(R.drawable.fon,  0, 0.04f, size, size / 7 * 11);

        finish = new Finish();
        setHelps();
        total_x = 0;
        oldx = -2;
        oldy = -2;
    }

    /*
    set cursor from 0
    */
    public void init(){
        finish.init();
        total_x = 0;
        oldx = -2;
        oldy = -2;
    }

    public void render(float delta){
        if(total_x < -pos[num_of_el-2] ){
            fon2.setPosition(pos[num_of_el-2] + total_x, 0.04f);
        }if(total_x > -pos[1]){
            fon2.setPosition(pos[1] + total_x, 0.04f);
        }
        fon.draw();
        fon2.draw();
        for(int i = 0; i < num_of_el; i++){
            setPos();
            if(Math.abs(pos[i] + total_x) < 2) {

                helps[i].render(delta);
            }
        }
        finish.render(delta);

    }

    public State onTouch(float screenX, float screenY, MotionEvent event){
        finish.start = false;
        if(helps[num_of_el-1].onTouch(screenX, screenY)) {
            return State.Menu;
        }
        return State.Default;
    }

    public void onDown(float x, float y){
        helps[num_of_el-1].onDown(x, y);
    }

    public void onDrag(float x, float y,float start_x, float start_y, MotionEvent event){
        helps[num_of_el-1].onDrag(x, y, start_x, start_y, event);
        onDraging = true;
        if(oldx != -2){
            if(Math.abs(x - oldx) > Math.abs(y - oldy))
                total_x += (x - oldx) * 1.1f;
        }

        oldx = x;
        oldy = y;

        if(event.getAction() == MotionEvent.ACTION_UP) {
            finish.start_x = start_x;
            finish.finish_x = x;
            finish.start_y = start_y;
            finish.finish_y = y;
            onDraging = false;
            oldx = -2;
            finish.start = true;
            finish.start();
        }
    }

    private void setHelps(){
        if(MainClass.loc.startsWith("ru")) {
            helps[0] = new Button(R.drawable.swipe_ru, size, size / 7f * 11f);
            helps[0 + 1] = new Button(R.drawable.rule_ru, size, size / 7f * 11f);
            helps[1 + 1] = new Button(R.drawable.amount_ru, size, size / 7f * 11f);
            helps[2 + 1] = new Button(R.drawable.rotated_ru, size, size / 7f * 11f);
            helps[3 + 1] = new Button(R.drawable.movable_ru, size, size / 7f * 11f);
        }else {
            helps[0] = new Button(R.drawable.swipe, size, size / 7f * 11f);
            helps[0 + 1] = new Button(R.drawable.rule, size, size / 7f * 11f);
            helps[1 + 1] = new Button(R.drawable.amount, size, size / 7f * 11f);
            helps[2 + 1] = new Button(R.drawable.rotated, size, size / 7f * 11f);
            helps[3 + 1] = new Button(R.drawable.movable, size, size / 7f * 11f);

        }
        helps[4 + 1] = new Button(R.drawable.helpplay, MainClass.widht / 3.5f, MainClass.widht / 3.5f);
        helps[0].setPos(new Vec2(pos[0], 0));
        helps[0+1].setPos(new Vec2(pos[1], 0));
        helps[1+1].setPos(new Vec2(pos[2], 0));
        helps[2+1].setPos(new Vec2(pos[3], 0));
        helps[3+1].setPos(new Vec2(pos[4], 0));
        helps[4+1].setPos(new Vec2(pos[5], 0));
    }

    private void setPos(){
        helps[0].setPos(new Vec2(pos[0] + total_x, 0));
        helps[0+1].setPos(new Vec2(pos[1] + total_x, 0));
        helps[1+1].setPos(new Vec2(pos[2] + total_x, 0));
        helps[2+1].setPos(new Vec2(pos[3] + total_x, 0));
        helps[3+1].setPos(new Vec2(pos[4] + total_x, 0));
        helps[4+1].setPos(new Vec2(pos[5] + total_x, 0));
    }

    class Finish{
        float time;
        int buf;
        boolean start;
        boolean onChoose;
        float start_x, start_y;
        float finish_x, finish_y;

        Finish(){
            buf = 0;
            time = 0;
            start = false;
            onChoose = false;
        }

        public void init(){
            buf = 0;
            time = 0;
            start = false;
            onChoose = false;
        }

        void start(){
            start = true;
            if(Math.abs(finish_x - start_x) > 0.3f) {
                if(finish_x - start_x > 0){
                    if(buf > 0)
                        buf = buf - 1;
                }else{
                    if(buf < num_of_el-1)
                        buf = buf + 1;
                }
            }
            start = false;
            onChoose = true;
        }

        void render(float delta){
            if(start){
                time += delta;
            } else
                time = 0;
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
}

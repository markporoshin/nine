package p0mamin.squax.Menu;

import android.view.MotionEvent;

import p0mamin.squax.Button;
import p0mamin.squax.MainClass;
import p0mamin.squax.R;
import p0mamin.squax.State;
import p0mamin.squax.Texture;
import p0mamin.squax.mathematics.Vec2;

/**
 * Created by Mark on 12.02.2017.
 */
public class MenuPart {
    private final String TAG = "MenuPart";


    Button play;
    Button setting;
    Button feedback;
    Button help;
    Button name;
    Vec2 butp[] = new Vec2[5];
    final int num_of_el = 5;

    private final float min_y = -0.2f;
    private final float max_y = -2.4f;

    private final int num_of_state = 2;
    private float oldx = -2, oldy = -2;
    private boolean onDraging;
    private float[] loc = {max_y, min_y};
    private float total_y;
    public Finish finish;

    Vec2 r;
    Vec2 pos;
    Texture field;
    float size;


    MenuScreen screen;

    public MenuPart(MenuScreen screen){
        this.screen = screen;

        finish = new Finish();
        //r = new Vec2(MainClass.widht / 3.4f, MainClass.height / 3f);
        total_y = -min_y;
        size = MainClass.widht / 3.5f;
        pos = new Vec2(0f, -size*1.1f);



        play  = new Button(R.drawable.play, size * 2.16f);
        setting = new Button(R.drawable.setting, size);
        feedback = new Button(R.drawable.feedback, size);
        help = new Button(R.drawable.help, size);
        name = new Button(R.drawable.name, size);

        field = new Texture(R.drawable.square_0, pos.x, pos.y + 0.8f, MainClass.widht,1f / MainClass.ratio * 1.2f);
        initpos();
        setPos();
    }


    public State onDrag(float x, float y, float start_x, float start_y, MotionEvent event){
        if(play.onDrag(x, y, start_x, start_y, event)){
            return State.Choose;
        }else if(help.onDrag(x, y, start_x, start_y, event)){
            return State.Help;
        }else if(feedback.onDrag(x, y, start_x, start_y, event)) {
            return State.Feedback;
        }


            onDraging = true;                                //
            if (oldy != -2) {                                //
                if (Math.abs(x - oldx) > Math.abs(y - oldy))
                    total_y += (y - oldy);                   //
            }                                                //

            oldx = x;                                        //
            oldy = y;                                        //

            if (event.getAction() == MotionEvent.ACTION_UP) {//
                finish.start_x = start_x;                    //
                finish.finish_x = x;                         //
                finish.start_y = start_y;                    //
                finish.finish_y = y;                         //
                onDraging = false;                           //
                oldx = -2;
                oldy = -2;//
                finish.start = true;                         //
                finish.start();                              //
            }



        return State.Default;
    }

    public void render(float delta){
        finish.render(delta);
        field.draw();
        play.render(delta);
        feedback.render(delta);
        help.render(delta);



        setPos();
    }

    public State OnTouch(float x, float y, MotionEvent event){
        if(play.onTouch(x, y)){
            return State.Choose;
        }else if(help.onTouch(x, y)){
            return State.Help;
        }else if(feedback.onTouch(x, y)) {
            return State.Feedback;
        }
        return State.Default;
    }

    public void onDown(float x, float y){
        play.onDown(x, y);
        help.onDown(x, y);
        feedback.onDown(x, y);
        setting.onDown(x, y);
    }

    private void setPos(){
        pos.y = total_y;
        field.setPosition(pos.x, (pos.y+0.73f) * MainClass.ratio);


        butp[0].set(0,0.35f + pos.y);
        butp[1].set(-size * 1.15f, pos.y - size * 1.9f);
        //butp[2] = new Vec2(0,0).add(r).rotate(360f / 4 * 4).add(pos);
        butp[3].set(size * 1.15f, pos.y - size * 1.9f);
        //butp[4] = new Vec2(0,0).add(r).rotate(360f / 5 * 5.625f).add(pos).add(new Vec2(0, 0.1f));

        play.setPos(butp[0]);
        help.setPos(butp[1]);
        feedback.setPos(butp[3]);
    }

    public void up(){
        finish.start_x = 0;
        finish.finish_x = 0;
        finish.start_y = -2;
        finish.finish_y = 2;
        onDraging = false;
        oldx = -2;
        finish.start = true;
        finish.start();
    }

    public void down(){
        finish.start_x = 0;
        finish.finish_x = 0;
        finish.start_y = 2;
        finish.finish_y = -2;
        onDraging = false;
        oldx = -2;
        finish.start = true;
        finish.start();
    }

    private void initpos(){
        butp[0] = new Vec2(0,0.35f + pos.y);
        butp[1] = new Vec2(-size * 1.15f, pos.y - size * 1.9f);
        //butp[2] = new Vec2(0,0).add(r).rotate(360f / 4 * 4).add(pos);
        butp[3] = new Vec2(size * 1.15f, pos.y - size * 1.9f);
        //butp[4] = new Vec2(0,0).add(r).rotate(360f / 5 * 5.625f).add(pos).add(new Vec2(0, 0.1f));

        play.setPos(butp[0]);
        help.setPos(butp[1]);
        feedback.setPos(butp[3]);
    }




    class Finish{
        float time;
        int buf;
        boolean start;
        boolean onChoose;
        float start_x, start_y;
        float finish_x, finish_y;


        Finish(){
            buf = 1;
            time = 0;
            start = false;
            onChoose = false;
        }

        void start(){
            start = true;
            if(Math.abs(finish_y - start_y) > 0.8f){
                if(finish_y - start_y > 0){
                    if(buf > 1)
                        buf = buf - 2;
                    else if(buf > 0)
                        buf = buf - 1;
                }else{
                    if(buf < num_of_state-2)
                        buf = buf + 2;
                    else if(buf < num_of_state-1)
                        buf = buf + 1;
                }
            } else if(Math.abs(finish_y - start_y) > 0.3f) {
                if(finish_y - start_y > 0){
                    if(buf > 0)
                        buf = buf - 1;
                }else{
                    if(buf < num_of_state-1)
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
                float a =  (loc[buf] + total_y) - 0;
                total_y -= a / 7;
                if(Math.abs(loc[buf] + total_y) < 0.01f) {
                    onChoose = false;
                    float a1 =  (loc[buf] + total_y) - 0;
                    total_y -= a1;
                }
            }
        }
    }
}

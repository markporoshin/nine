package p0mamin.nined.Menu;

import android.util.Log;
import android.view.MotionEvent;

import p0mamin.nined.Button;
import p0mamin.nined.MainClass;
import p0mamin.nined.R;
import p0mamin.nined.State;
import p0mamin.nined.Texture;
import p0mamin.nined.mathematics.Vec2;

/**
 * Created by Mark on 12.02.2017.
 */
public class MenuPart {
    Button play;
    Button setting;
    Button feedback;
    Button help;
    Button name;
    Vec2 butp[] = new Vec2[5];
    final int num_of_el = 5;

    private final float maxy = 2.4f;
    private final float miny = 0.2f;

    Vec2 r;
    Vec2 pos;
    Texture field;
    float size;


    MenuScreen screen;

    public MenuPart(MenuScreen screen){
        this.screen = screen;

        r = new Vec2(MainClass.widht / 3.2f, MainClass.height / 3.2f);
        pos = new Vec2(0, miny);
        size = MainClass.widht / 3.5f;


        play  = new Button(R.drawable.play, size*1.5f);
        setting = new Button(R.drawable.setting, size);
        feedback = new Button(R.drawable.help, size);
        help = new Button(R.drawable.feedback, size);
        name = new Button(R.drawable.name, size);

        field = new Texture(R.drawable.square_0, pos.x, pos.y + 1.3f, MainClass.widht,1f / MainClass.ratio * 1.5f);
        setPos();
    }


    public State onDrag(float x, float y,float startx, float starty, MotionEvent event){
        if(play.onDrag(x, y, startx, starty, event)){
            return State.Choose;
        }else if(help.onDrag(x, y,startx, starty, event)){
            return State.Help;
        }else if(feedback.onDrag(x, y, startx, starty, event)) {
            return State.Feedback;
        }
        return State.Default;
    }

    public void render(float delta){
        field.draw();
        play.render(delta);
        feedback.render(delta);
        help.render(delta);

        if(dir != 0){
            finishSwipeRender(delta);
        }
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
        field.setPosition(pos.x, (pos.y+1.3f) * MainClass.ratio);
        initpos();
    }



    private void initpos(){
        butp[0] = new Vec2(0,0.45f + pos.y);
        butp[1] = new Vec2(0,0).add(r).rotate(360f / 4 * 2).add(pos);
        butp[2] = new Vec2(0,0).add(r).rotate(360f / 4 * 4).add(pos);
        butp[3] = new Vec2(0,0).add(r).rotate(360f / 4 * 3).add(pos);
        //butp[4] = new Vec2(0,0).add(r).rotate(360f / 5 * 5.625f).add(pos).add(new Vec2(0, 0.1f));

        play.setPos(butp[0]);
        help.setPos(butp[1]);
        feedback.setPos(butp[3]);
    }


    public static boolean position = true;
    public static boolean onSwipe = false;
    public static boolean rape = false;
    public static byte dir = 0;
    private float time = 0;
    private float old_x, old_y;

    public void startSwipe(float x, float y){
        onSwipe = true;
        old_y = y;
    }

    public void onSwipe(float x, float y){
        pos.y += y - old_y;
        old_y = y;
    }

    public void finishSwipe(float x, float y, float startx, float starty){
        time = 0;
        if(y - starty < -0.4f){
            dir = 2;
        } else if(pos.y >= (miny + maxy) / 2){
            dir = 1;
        }
        if(y - starty > 0.4f){
            dir = 1;
        } else if(pos.y < (miny + maxy) / 2){
            dir = 2;
        }
        position = !position;
    }

    public void finishSwipeRender(float delta){

        switch (dir){
            case 1:
                time += delta;
                pos.y += (maxy - pos.y) * delta * 5;
                rape = false;
                break;
            case 2:
                time += delta;
                pos.y += (miny - pos.y) * delta * 5;
                rape = false;
                break;
        }
        if(time > 0.9) {
            if ((Math.abs(maxy - pos.y) < 0.01f && dir == 1)) {
                pos.y = maxy;
                dir = 0;
                onSwipe = false;
                position = false;
            }

            if ((Math.abs(miny - pos.y) < 0.01f && dir == 2)) {
                pos.y = miny;
                dir = 0;
                onSwipe = false;
                position = true;
            }
        }


    }
}

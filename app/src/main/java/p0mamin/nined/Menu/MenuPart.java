package p0mamin.nined.Menu;

import android.util.Log;

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
    //Button exit;
    Vec2 butp[] = new Vec2[5];
    final int num_of_el = 5;



    Vec2 r;
    Vec2 pos;

    Texture field;
    Texture name;

    float size;
    boolean sw;
    boolean swFinish;
    boolean direction;
    float totaly;

    MenuScreen screen;

    public MenuPart(MenuScreen screen){
        this.screen = screen;

        r = new Vec2(MainClass.widht / 2.5f, MainClass.height / 2.5f);
        pos = new Vec2(0, -0.2f);
        size = MainClass.widht / 3.5f;


        play  = new Button(R.drawable.play, size);
        setting = new Button(R.drawable.setting, size);
        feedback = new Button(R.drawable.feedback, size);
        help = new Button(R.drawable.help, size);
        //exit = new Button(R.drawable.exit, size);
        //initpos();

        field = new Texture(R.drawable.square, pos.x, pos.y+0.6f, MainClass.widht,1f / MainClass.ratio);
        name = new Texture(R.drawable.name, pos.x, pos.y+1.1f, MainClass.widht / 1.9f, MainClass.widht / 1.5f * MainClass.ratio);
        setPos();
    }

    public void render(float delta){
        field.draw();
        name.draw();
        play.render(delta);
        setting.render(delta);
        feedback.render(delta);
        help.render(delta);
        /*if (pos.y < 0) {
            pos.y += Math.abs(pos.y);
        }
        if (pos.y > 2.7f) {
            pos.y -= Math.abs(pos.y - 2.7f) / 10 + 0.001f;
        }*/
        Log.d("render", "pos.y: " + pos.y + " swfinish: " + sw + " direction " + direction);

        if(sw && swFinish){
            if(pos.y != 0 && pos.y != 1.4f){
                if(direction){
                    if(pos.y < 1.4f)
                        pos.y += (1.4 - pos.y) / 10 + 0.01f;
                }else{
                    if(pos.y > 0)
                        pos.y += (0 - pos.y) / 10 + 0.01f;
                }
            }else{
                sw = !sw;
                swFinish = !swFinish;
            }
        }
        setPos();
    }

    public State OnTouch(float x, float y){
        if(play.touch(x, y)){
            return State.Choose;
        }
        return State.Default;
    }

    private void setPos(){
        field.setPosition(pos.x, (pos.y+0.6f) * MainClass.ratio);
        name.setPosition(pos.x, (pos.y+1.1f) * MainClass.ratio);

        initpos();
        Log.d("render","" + pos.y);
    }

    public void swipe(float y){
        if(pos.y >= 0 && pos.y <= 1.4f)
            pos.y += y;

        setPos();
    }

    public void swipeStart(float y){
        totaly = pos.y;
        sw = true;
    }

    public void finishSwipe(float y){
            swFinish = true;
            if(pos.y < totaly + 0.3f)
                direction = false;
            else
                direction = true;
        //Log.d("direction", "" + direction);
            //sw = true;
    }

    private void initpos(){

        /*for(int i = 0; i < num_of_el; i++){
            butp[i].add(r).rotate(360 / (num_of_el - i)).add(pos).subtract(r);
        }*/
        butp[0] = new Vec2(0,0).add(r).rotate(360f / 5 * 1.625f).add(pos);
        butp[1] = new Vec2(0,0).add(r).rotate(360f / 5 * 2.625f).add(pos);
        butp[2] = new Vec2(0,0).add(r).rotate(360f / 5 * 3.625f).add(pos);
        butp[3] = new Vec2(0,0).add(r).rotate(360f / 5 * 4.625f).add(pos);
        butp[4] = new Vec2(0,0).add(r).rotate(360f / 5 * 5.625f).add(pos);

        play.setPos(butp[0]);
        setting.setPos(butp[1]);
        help.setPos(butp[2]);
        feedback.setPos(butp[3]);

    }


}

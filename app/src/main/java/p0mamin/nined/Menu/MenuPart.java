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
    Button name;
    Vec2 butp[] = new Vec2[5];
    final int num_of_el = 5;

    private final float maxy = 2.4f;
    private final float miny = 0.2f;

    Vec2 r;
    Vec2 pos;

    Texture field;

    float size;
    boolean sw;
    boolean swFinish;
    boolean direction;
    float totaly;

    MenuScreen screen;

    public MenuPart(MenuScreen screen){
        this.screen = screen;

        r = new Vec2(MainClass.widht / 3.2f, MainClass.height / 3.2f);
        pos = new Vec2(0, miny);
        size = MainClass.widht / 3.5f;


        play  = new Button(R.drawable.play, size);
        setting = new Button(R.drawable.setting, size);
        feedback = new Button(R.drawable.feedback, size);
        help = new Button(R.drawable.help, size);
        name = new Button(R.drawable.name, size);
        //exit = new Button(R.drawable.exit, size);
        //initpos();

        field = new Texture(R.drawable.square, pos.x, pos.y, MainClass.widht,1f / MainClass.ratio);
        setPos();
    }

    public void render(float delta){
        field.draw();
        //name.render(delta);
        play.render(delta);
        setting.render(delta);
        feedback.render(delta);
        help.render(delta);

        if(swFinish){
            if(pos.y != miny && pos.y != maxy){
                if(direction){
                    if(pos.y < maxy){
                        Log.d("MenuPart", "Ooops<");
                        pos.y += (maxy - pos.y) / 10 + 0.01f;
                    }
                }else{
                    if(pos.y > miny) {
                        Log.d("MenuPart", "Ooops>");
                        pos.y -= (pos.y - miny) / 10 + 0.01f;
                    }
                }
            }else{
                sw = !sw;
                swFinish = !swFinish;
            }
        }
        {
            if (pos.y < miny) {
                Log.d("MenuPart", "Oooops");
                pos.y += Math.abs(pos.y - miny) / 30f + 0.01f;
                if(Math.abs(pos.y - miny) < 0.005){
                    pos.y = miny;
                }
            }
            if (pos.y > maxy) {
                pos.y -= Math.abs(pos.y - maxy) / 30f + 0.01f;
                if(Math.abs(pos.y - maxy) < 0.005){
                    pos.y = maxy;
                }
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
        field.setPosition(pos.x, (pos.y+0.3f) * MainClass.ratio);

        initpos();
    }

    public void swipe(float y){
        //if(pos.y >= miny && pos.y <= maxy) {
            Log.d("MenuPart", "Ooops=");
            pos.y += y;
        //}
        sw = true;
        setPos();
    }

    public void swipeStart(float y){
        totaly = y;
        sw = true;
    }

    public void finishSwipe(float y){
            swFinish = true;
            if(0 > y - totaly)
                direction = false;
            else
                direction = true;
    }

    private void initpos(){

        /*for(int i = 0; i < num_of_el; i++){
            butp[i].add(r).rotate(360 / (num_of_el - i)).add(pos).subtract(r);
        }*/
        butp[0] = new Vec2(0,0).add(r).rotate(360f / 4 * 1).add(pos);
        butp[1] = new Vec2(0,0).add(r).rotate(360f / 4 * 2).add(pos);
        butp[2] = new Vec2(0,0).add(r).rotate(360f / 4 * 4).add(pos);
        butp[3] = new Vec2(0,0).add(r).rotate(360f / 4 * 3).add(pos);
        //butp[4] = new Vec2(0,0).add(r).rotate(360f / 5 * 5.625f).add(pos).add(new Vec2(0, 0.1f));

        play.setPos(butp[0]);
        setting.setPos(butp[1]);
        help.setPos(butp[2]);
        feedback.setPos(butp[3]);
       // name.setPos(butp[4]);

    }


}

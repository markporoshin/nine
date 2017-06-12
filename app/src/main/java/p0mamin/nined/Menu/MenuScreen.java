package p0mamin.nined.Menu;

import android.util.Log;
import android.view.MotionEvent;

import p0mamin.nined.Batch;
import p0mamin.nined.Button;
import p0mamin.nined.MainClass;
import p0mamin.nined.R;
import p0mamin.nined.State;
import p0mamin.nined.Texture;

/**
 * Created by Mark on 08.02.2017.
 */
public class MenuScreen {
    private final String TAG = "MenuScreen";

    Texture fon;
    Texture bar;
    Button record;
    Button recordt;
    MenuPart MP;
    static boolean onScore;
    static boolean dragStart;
    private HighScoreScreen HSS;
    float oldx, oldy;
    public MenuScreen(){
        onScore = false;
        dragStart = false;
        oldx = 0;
        oldy = -2;
        HSS = new HighScoreScreen();
        MP = new MenuPart(this);
        fon = new Texture(R.drawable.square4, 0, 0, MainClass.widht, MainClass.height / MainClass.ratio);
        bar = new Texture(R.drawable.bar, 0, 0, MainClass.widht, MainClass.height / MainClass.ratio * 0.224f);
        bar.setPosition(0, MainClass.height);
        float size = MainClass.widht / 3.8f;
        record = new Button(R.drawable.record, -0.75f, -0.805f / MainClass.ratio, size, size);
        recordt = new Button(R.drawable.recordt, 0.23f, -0.8f / MainClass.ratio, size * 3, size);

    }

    public void render(float delta){
        fon.draw();

        HSS.render(delta);
        record.render(delta);
        recordt.render(delta);
        try {
            MP.render(delta);
        }catch(Exception e){
            Log.e("MenuPart", "" + e);
        }
        //bar.draw();
    }

    public State onTouch(float x, float y, MotionEvent event){
        oldy = -2;
        oldx = x;
        //Log.d(TAG, "position: ");
        HSS.onTouch(x, y, event);

        /*if(record.onTouch(x, y) || recordt.onTouch(x, y)) {
            Log.d(TAG, "onTouch");
            if (MP.finish.buf == 1) {
                MP.up();
            } else {
                MP.down();
            }
        }*/
        if(MP.OnTouch(x, y, event)== State.Default) {
            return HSS.onTouch(x, y, event);
        }
        return MP.OnTouch(x, y, event);
    }

    public void onDown(float x, float y){
        record.onDown(x, y);
        recordt.onDown(x, y);
        MP.onDown(x, y);
    }

    public void onDrag(float x, float y,float startx, float starty, MotionEvent event){
        if(record.onDrag(x, y, startx, starty, event) || recordt.onDrag(x, y, startx, starty, event)) {
            Log.d(TAG, "onDrag");
            if (MP.finish.buf == 1) {
                MP.up();
            } else {
                MP.down();
            }
        }
        Log.d(TAG, "onDrag");
        MP.onDrag(x, y, startx, starty, event);
        HSS.onDrag(x, y, startx, starty, event);
        if(MP.onDrag(x, y, startx, starty, event) !=  State.Default)
            Batch.state = MP.onDrag(x, y, startx, starty, event);
        if(Math.abs(startx - x) <= Math.abs(starty -y)) {
        }

    }



}

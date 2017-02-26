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
 * Created by Mark on 08.02.2017.
 */
public class MenuScreen {
    Texture fon;
    Texture record;
    Texture recordt;
    MenuPart MP;

    static boolean dragStart;

    float oldx, oldy;
    public MenuScreen(){
        dragStart = false;
        oldx = 0;
        oldy = 0;
        MP = new MenuPart(this);
        fon = new Texture(R.drawable.square2, 0, 0, MainClass.widht, MainClass.height / MainClass.ratio);
        float size = MainClass.widht / 3.8f;
        record = new Texture(R.drawable.record, -0.75f, -0.805f, size, size);
        recordt = new Texture(R.drawable.recordt, 0.23f, -0.8f, size * 3, size);

    }

    public void render(float delta){
        fon.draw();
        record.draw();
        recordt.draw();
        try {
            MP.render(delta);
        }catch(Exception e){
            Log.e("MenuPart", "" + e);
        }
    }

    public State onTouch(float x, float y){
        oldy = y;
        oldx = x;
        return MP.OnTouch(x, y);
    }
    public void onDrag(float x, float y,float startx, float starty, MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_UP){
            dragStart = false;
            MP.finishSwipe(y);

        }
        else{
            if(dragStart == false) {
                dragStart = true;
                MP.swipeStart(y - oldy);
            }
            MP.swipe( y - oldy);
        }
        oldy = y;
    }



}

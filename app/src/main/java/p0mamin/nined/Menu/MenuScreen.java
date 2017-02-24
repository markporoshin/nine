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
    MenuPart MP;

    static boolean dragStart;

    float oldx, oldy;
    public MenuScreen(){
        dragStart = false;
        oldx = 0;
        oldy = 0;
        MP = new MenuPart(this);
        fon = new Texture(R.drawable.square2, 0, 0, MainClass.widht, MainClass.height / MainClass.ratio);
        record = new Texture(R.drawable.record, 0, -0.6f, MainClass.widht / 1.3f, MainClass.height / MainClass.ratio / 6.5f);

    }

    public void render(float delta){
        fon.draw();
        record.draw();
        MP.render(delta);
    }

    public State onTouch(float x, float y){


        // dragStart = true;
        //}
        oldy = y;
        oldx = x;
        return MP.OnTouch(x, y);
    }
    public void onDrag(float x, float y,float startx, float starty, MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_UP){
            dragStart = false;
            MP.finishSwipe(y - oldy);

        }
        else{
            if(dragStart == false) {
                dragStart = true;
                MP.swipeStart(y - oldy);
                Log.d("MenuScreen", "" + dragStart);
            }
            MP.swipe( y - oldy);
        }
        oldy = y;
    }



}

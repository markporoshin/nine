package p0mamin.nined;

import android.util.Log;
import android.view.MotionEvent;

import p0mamin.nined.Game.Digit;
import p0mamin.nined.mathematics.Vec2;

/**
 * Created by Mark on 04.02.2017.
 */
public class Button {
    private float sizex, sizey;
    private float x;
    private float y;
    private int id;

    Texture d1;
    public Button(float size, float x, float y){

        //d1 = new Texture(R.drawable.square1, x, y, size , size);
        this.sizex = size;
        this.sizey = size;
        this.x = (x);
        this.y = (y);

    }

    public void setPos(Vec2 v){
        this.x = v.x;
        this.y = v.y;
    }

    public Button(int id, float x, float y, float size){

        d1 = new Texture(id, x, y, size , size);
        this.sizex = size;
        this.sizey = size;
        this.x = (x);
        this.y = (y);

    }

    public Button(int id, float x, float y, float sizex, float sizey){

        d1 = new Texture(id, x, y, sizex , sizey);
        this.sizex = sizex;
        this.sizey = sizey;
        this.x = (x);
        this.y = (y);
    }

    public Button(int id, float size){

        d1 = new Texture(id, x, y, size , size);
        this.sizex = size;
        this.sizey = size;
    }

    public Button(int id, float sizex, float sizey){

        d1 = new Texture(id, x, y, sizex , sizey);
        this.sizex = sizex;
        this.sizey = sizey;
    }

    public void render(float delta){

        d1.setPosition(x, y * MainClass.ratio);
        d1.draw();
    }


    public boolean onTouch(float touchX, float touchY){
        if( x - sizex < touchX && x + sizex  > touchX )
            if((y - sizey) * Render.ratio  < touchY && (y  + sizey) * Render.ratio  > touchY ) {
                if(d1 != null){
                    d1.setSize(sizex, sizey);
                }
                return true;
            }
        return false;
    }

    public void onDown(float touchX, float touchY){
        if( x - sizex < touchX && x + sizex  > touchX )
            if((y - sizey) * Render.ratio  < touchY && (y  + sizey) * Render.ratio  > touchY ){
                d1.setSize(sizex * 0.95f, sizey * 0.95f);
            }

    }
    public boolean onDrag(float touchX, float touchY, float startx, float starty, MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_UP){
            if (x - sizex < touchX && x + sizex > touchX)
                if (x - sizex < startx && x + sizex > startx)
                     if ((y - sizey) * Render.ratio < touchY && (y + sizey) * Render.ratio > touchY)
                         if ((y - sizey) * Render.ratio < starty && (y + sizey) * Render.ratio > starty) {
                             d1.setSize(sizex, sizey);
                             return true;
                        }

            if (d1 != null) {
                d1.setSize(sizex, sizey);
            }
        }


        return false;

    }
}

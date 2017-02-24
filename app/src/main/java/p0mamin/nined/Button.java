package p0mamin.nined;

import p0mamin.nined.Game.Digit;
import p0mamin.nined.mathematics.Vec2;

/**
 * Created by Mark on 04.02.2017.
 */
public class Button {
    private float size;
    private float x;
    private float y;

    Texture d1;
    public Button(float size, float x, float y){
        this.size = size;
        this.x = (x);
        this.y = (y);

    }

    public void setPos(Vec2 v){
        this.x = v.x;
        this.y = v.y;
    }

    public Button(int id, float x, float y, float size){
        d1 = new Texture(id, x, y, size , size);
        this.size = size;
        this.x = (x);
        this.y = (y);

    }
    public Button(int id, float size){
        d1 = new Texture(id, x, y, size , size);
        this.size = size;
    }

    public void render(float delta){
        d1.setPosition(x, y * MainClass.ratio);
        d1.draw();
    }


    public boolean touch(float touchX, float touchY){
        if( x - size < touchX && x + size  > touchX )
            if((y - size) * Render.ratio  < touchY && (y  + size) * Render.ratio  > touchY )
                return true;
        return false;
    }
}

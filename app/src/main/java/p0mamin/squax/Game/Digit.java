package p0mamin.squax.Game;

import p0mamin.squax.MainClass;
import p0mamin.squax.R;
import p0mamin.squax.Render;
import p0mamin.squax.Texture;
import p0mamin.squax.mathematics.Vec2;

/**
 * Created by Mark on 29.01.2017.
 */
public class Digit {
    public boolean rotatable = true;

    private Texture tex;
    private Texture rot;
    public Vec2 pos = new Vec2(0, 0);
    public int number;
    private final float size = MainClass.widht / 12.0f;



    public Digit(int num, boolean rotatable){
        ChooseTex(num);
        if(!rotatable){
            rot = new Texture(R.drawable.lock, 0, 0f, size, size);
        }
        this.number = num;
        this.rotatable = rotatable;

    }

    public void setPos(Vec2 vec){
        this.pos.set(vec);

    }

    public void render(){
        tex.setPosition(pos.x, (pos.y) * Render.ratio);
        tex.draw();
        if(!this.rotatable){
            rot.setPosition(pos.x, (pos.y) * Render.ratio);
            rot.draw();
        }
    }

    public void setDepth(float d){
        try{
            tex.setDepth(d);
            tex.setPosition();
            rot.setDepth(d);
            rot.setPosition();
        }catch (Exception e){

        }
    }

    private void ChooseTex(int num){
        switch (num){
            case 1:
                tex = new Texture(R.drawable.n1, 0, 0f, size, size);
                break;

            case 2:
                tex = new Texture(R.drawable.n2, 0, 0f, size, size);
                break;

            case 3:
                tex = new Texture(R.drawable.n3, 0, 0f, size, size);
                break;

            case 4:
                tex = new Texture(R.drawable.n4, 0, 0f, size, size);
                break;

            case 5:
                tex = new Texture(R.drawable.n5, 0, 0f, size, size);
                break;

            case 6:
                tex = new Texture(R.drawable.n6, 0, 0f, size, size);
                break;

            case 7:
                tex = new Texture(R.drawable.n7, 0, 0f, size, size);
                break;

            case 8:
                tex = new Texture(R.drawable.n8, 0, 0f, size, size);
                break;

            case 9:
                tex = new Texture(R.drawable.n9, 0, 0f, size, size);
                break;

            default:
                tex = new Texture(R.drawable.square, 0, 0f, size, size);
                break;
        }
    }
}

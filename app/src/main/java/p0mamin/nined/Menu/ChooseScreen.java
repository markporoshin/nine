package p0mamin.nined.Menu;

import p0mamin.nined.Button;
import p0mamin.nined.MainClass;
import p0mamin.nined.R;
import p0mamin.nined.State;
import p0mamin.nined.Texture;
import p0mamin.nined.font.Font;

/**
 * Created by Mark on 13.02.2017.
 */
public class ChooseScreen {
    private final int num_of_el = 8;
    public Button up, upup;
    public Button down, downdown;
    public Button select;
    public static int lvl = 1;
    private Texture fon;
    Font font;


    public ChooseScreen(){
        fon = new Texture(R.drawable.square,  0, 0, 1, 1.5f / MainClass.ratio);
        font = new Font();

        up = new Button(R.drawable.up, 0, MainClass.height / 4f                 / MainClass.ratio, MainClass.widht / 5);
        upup = new Button(R.drawable.upup, 0, MainClass.height / 2f          / MainClass.ratio, MainClass.widht / 5);
        down = new Button(R.drawable.down, 0,-MainClass.height / 4f             / MainClass.ratio, MainClass.widht / 5);
        downdown = new Button(R.drawable.downdown, 0, -MainClass.height / 2f / MainClass.ratio, MainClass.widht / 5);
        select = new Button(R.drawable.select, 0, -MainClass.height / 1.3f     / MainClass.ratio, MainClass.widht / 5);
    }

    public void render(float delta){
        fon.draw();
        up.render(delta);
        upup.render(delta);
        down.render(delta);
        downdown.render(delta);
        select.render(delta);
        font.draw(lvl, 0, 0, MainClass.widht / 12);
    }

    public State onTouch(float screenX, float screenY){
        if((lvl - 1) > 0 &&
                down.touch(screenX, screenY)){
            lvl--;
        }else if((lvl - 5) > 0 &&
                downdown.touch(screenX, screenY)){
            lvl-=5;
        }else if((lvl + 1) < 6 &&
                up.touch(screenX, screenY)){
            lvl++;
        }else if((lvl + 5) < 6 &&
                upup.touch(screenX, screenY)){
            lvl+=5;
        }else if((select.touch(screenX, screenY))){
            return State.Game;
        }
        return State.Default;
    }

}

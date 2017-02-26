package p0mamin.nined;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import p0mamin.nined.Game.GameScreen;
import p0mamin.nined.Game.Level;
import p0mamin.nined.Game.Levels.Level1;
import p0mamin.nined.Game.Levels.Level2;
import p0mamin.nined.Game.Levels.Level3;
import p0mamin.nined.Game.Levels.Level4;
import p0mamin.nined.Game.Levels.Level5;
import p0mamin.nined.Menu.ChooseScreen;
import p0mamin.nined.Menu.MenuScreen;
import p0mamin.nined.mathematics.Vec2;

/**
 * Created by Mark on 29.01.2017.
 */
public class Batch {
    Level lvl;
    MenuScreen menu;
    ChooseScreen choose;
    public static State state;
    private int oldLvl;
    private float time;


    public Batch(){
        oldLvl = 0;
        state = State.Menu;
        menu = new MenuScreen();
        choose = new ChooseScreen();
        time = 0;
    }

    public void render(float delta){

        switch (state) {
            case Menu:
                menu.render(delta);
                break;
            case Win:
                win(delta);
            case Game:
                if(oldLvl != ChooseScreen.lvl)
                switch (ChooseScreen.lvl){
                    case 1:
                        lvl = new Level1();
                        break;
                    case 2:
                        lvl = new Level2();
                        break;
                    case 3:
                        lvl = new Level3();
                        break;
                    case 4:
                        lvl = new Level4();
                        break;
                    case 5:
                        lvl = new Level5();
                        break;
                }
                oldLvl = ChooseScreen.lvl;
                if(lvl != null)
                    lvl.render(delta);
                break;
            case Choose:
                choose.render(delta);
                break;

        }
    }

    public void OnTouch(float x, float y, MotionEvent event){
        switch (state) {
            case Menu:
                if(menu.onTouch(x, y) != State.Default)
                    state = menu.onTouch(x, y);
                break;
            case Choose:
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                    if(choose.onTouch(x, y, event) != State.Default) {
                        //state = choose.onTouch(x, y);

                        state = choose.onTouch(x, y, event);
                    }
                break;
            case Game:
                try {
                    if (lvl.onTouch(x, y) != State.Default)
                        state = lvl.onTouch(x, y);
                }catch (Exception e){
                }
                break;

        }

    }

    public void OnDrag(float x, float y,float startx, float starty, MotionEvent event){
        switch (state) {
            case Menu:
                menu.onDrag(x, y, startx, starty, event);
                break;
           /* case Game:
                if(game.onTouch(x, y) != State.Default)
                    state = game.onTouch(x, y);
                break;*/
            case Choose:
                choose.onDrag(x, y, startx, starty, event);
                break;

        }
    }
    private void win(float delta){
        if(time < Vec2.PI / 2) {
            time += delta;
            Render.z = 0.3f * (float)Math.sin(time);
        }
    }
}

package p0mamin.nined;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import p0mamin.nined.Game.GameScreen;
import p0mamin.nined.Game.Level;
import p0mamin.nined.Game.Levels.Level1;
import p0mamin.nined.Game.Levels.Level10;
import p0mamin.nined.Game.Levels.Level11;
import p0mamin.nined.Game.Levels.Level12;
import p0mamin.nined.Game.Levels.Level13;
import p0mamin.nined.Game.Levels.Level14;
import p0mamin.nined.Game.Levels.Level15;
import p0mamin.nined.Game.Levels.Level16;
import p0mamin.nined.Game.Levels.Level17;
import p0mamin.nined.Game.Levels.Level18;
import p0mamin.nined.Game.Levels.Level19;
import p0mamin.nined.Game.Levels.Level2;
import p0mamin.nined.Game.Levels.Level20;
import p0mamin.nined.Game.Levels.Level21;
import p0mamin.nined.Game.Levels.Level22;
import p0mamin.nined.Game.Levels.Level23;
import p0mamin.nined.Game.Levels.Level24;
import p0mamin.nined.Game.Levels.Level25;
import p0mamin.nined.Game.Levels.Level26;
import p0mamin.nined.Game.Levels.Level27;
import p0mamin.nined.Game.Levels.Level28;
import p0mamin.nined.Game.Levels.Level29;
import p0mamin.nined.Game.Levels.Level3;
import p0mamin.nined.Game.Levels.Level30;
import p0mamin.nined.Game.Levels.Level4;
import p0mamin.nined.Game.Levels.Level5;
import p0mamin.nined.Game.Levels.Level6;
import p0mamin.nined.Game.Levels.Level7;
import p0mamin.nined.Game.Levels.Level8;
import p0mamin.nined.Game.Levels.Level9;
import p0mamin.nined.Menu.ChooseScreen;
import p0mamin.nined.Menu.FeedBackScreen;
import p0mamin.nined.Menu.HelpScreen;
import p0mamin.nined.Menu.MenuScreen;
import p0mamin.nined.Menu.SettingScreen;
import p0mamin.nined.mathematics.Vec2;

/**
 * Created by Mark on 29.01.2017.
 */
public class Batch {
    public static TextureManager TM;


    Level lvl;
    SettingScreen setting;
    FeedBackScreen feedback;
    MenuScreen menu;
    ChooseScreen choose;
    HelpScreen help;
    public static DataBase DB;
    public static State state;
    public static State oldState;
    private int oldLvl;
    private float time;


    public Batch(){
        TM = new TextureManager();
        TM.first();

        oldLvl = 0;
        if(!MainClass.vizits){
            state = State.Menu;
        }else{
            state = State.Help;
        }
        DB = new DataBase();

        setting = new SettingScreen();
        feedback = new FeedBackScreen();
        help = new HelpScreen();
        menu = new MenuScreen();
        choose = new ChooseScreen();
        time = 0;
    }

    public void render(float delta){

        switch (state) {
            case Menu:
                menu.render(delta);
                oldState = state;
                break;
            case Win:
                win(delta);
                oldState = state;
            case Game:
                TM.third();
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
                    case 6:
                        lvl = new Level6();
                        break;
                    case 7:
                        lvl = new Level7();
                        break;
                    case 8:
                        lvl = new Level8();
                        break;
                    case 9:
                        lvl = new Level9();
                        break;
                    case 10:
                        lvl = new Level10();
                        break;
                    case 11:
                        lvl = new Level11();
                        break;
                    case 12:
                        lvl = new Level12();
                        break;
                    case 13:
                        lvl = new Level13();
                        break;
                    case 14:
                        lvl = new Level14();
                        break;
                    case 15:
                        lvl = new Level15();
                        break;
                    case 16:
                        lvl = new Level16();
                        break;
                    case 17:
                        lvl = new Level17();
                        break;
                    case 18:
                        lvl = new Level18();
                        break;
                    case 19:
                        lvl = new Level19();
                        break;
                    case 20:
                        lvl = new Level20();
                        break;
                    case 21:
                        lvl = new Level21();
                        break;
                    case 22:
                        lvl = new Level22();
                        break;
                    case 23:
                        lvl = new Level23();
                        break;
                    case 24:
                        lvl = new Level24();
                        break;
                    case 25:
                        lvl = new Level25();
                        break;
                    case 26:
                        lvl = new Level26();
                        break;
                    case 27:
                        lvl = new Level27();
                        break;
                    case 28:
                        lvl = new Level28();
                        break;
                    case 29:
                        lvl = new Level29();
                        break;
                    case 30:
                        lvl = new Level30();
                        break;
                }
                oldLvl = ChooseScreen.lvl;
                if(lvl != null)
                    lvl.render(delta);
                oldState = state;
                break;
            case Choose:
                TM.second();
                choose.render(delta);
                oldState = state;
                break;
            case Help:
                TM.help();
                help.render(delta);
                oldState = state;
                break;
            case Feedback:
                feedback.render(delta);
                break;
            case Setting:
                setting.render(delta);
                break;

        }
    }

    public void onDown(float x, float y){
        switch (state) {
            case Menu:
                menu.onDown(x, y);
               break;
            case Choose:
                //choose.onDown(x, y);
                break;
            case Game:
                //lvl.onDown(x, y);
                break;
            case Help:
                help.onDown(x, y);
                break;
            case Feedback:
                feedback.onDown(x, y);
                break;
            case Setting:
                //setting.onDown(x, y);
                break;
        }
    }

    public void OnTouch(float x, float y, MotionEvent event){
        switch (state) {
            case Menu:
                if(menu.onTouch(x, y, event) != State.Default)
                {
                    state = menu.onTouch(x, y, event);
                    if(state == State.Help){
                        help.total_x = 0;
                    }
                }break;
            case Choose:
                if(choose.onTouch(x, y, event) != State.Default) {
                    oldLvl = 0;
                    Log.d("ChooseScreen","onTouch ");
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
            case Help:
                if(help.onTouch(x, y, event) != State.Default) {
                    oldLvl = 0;
                    Log.d("ChooseScreen","onTouch ");
                    state = help.onTouch(x, y, event);
                }
                break;
            case Feedback:
                if(feedback.onTouch(x, y, event) != State.Default) {
                    oldLvl = 0;
                    state = feedback.onTouch(x, y, event);
                }
                break;
            case Setting:
                if(setting.onTouch(x, y, event) != State.Default) {
                    oldLvl = 0;
                    state = setting.onTouch(x, y, event);
                }
                break;
        }

    }

    public void OnDrag(float x, float y,float startx, float starty, MotionEvent event){
        switch (state) {
            case Menu:
                menu.onDrag(x, y, startx, starty, event);
                break;
            case Game:
                try {
                    if (lvl.onTouch(x, y) != State.Default)
                        state = lvl.onTouch(x, y);
                }catch(Exception e){
                }finally {
                    break;
                }
            case Choose:
                choose.onDrag(x, y, startx, starty, event);
                break;
            case Help:
                help.onDrag(x, y, startx, starty, event);
                break;
            case Feedback:
                feedback.onDrag(x, y, startx, starty, event);
                break;

        }
    }

    public void onBackPressed(){
        switch (state) {
            case Menu:
                System.exit(1);
                break;
            case Choose:
                state = State.Menu;
                break;
            case Game:
                state = State.Choose;
                break;
            case Help:
                state = State.Menu;
                break;
            case Feedback:
                state = State.Menu;
                break;
            case Setting:
                state = State.Menu;
                break;
        }
        //state = State.Menu;
    }

    private void win(float delta){
        if(time < Vec2.PI / 2) {
            time += delta;
            //Render.z = 0.3f * (float)Math.sin(time);
        }
    }
}

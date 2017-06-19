package p0mamin.squax.Menu;

import android.util.Log;
import android.view.MotionEvent;

import p0mamin.squax.Batch;
import p0mamin.squax.Button;
import p0mamin.squax.MainClass;
import p0mamin.squax.R;
import p0mamin.squax.State;
import p0mamin.squax.Texture;
import p0mamin.squax.font.BlackFont;
import p0mamin.squax.font.Font;
import p0mamin.squax.mathematics.Vec2;

/**
 * Created by Mark on 13.02.2017.
 */
public class ChooseScreen {
    public static int lvl = 1;

    public static final int num_of_el = 30;


    private float[] pos = new float[num_of_el];
    private float total_x;
    private float total_y = -0.124f;
    private Button[] levels = new Button[num_of_el];
    private Texture fon;
    private Texture leveltext;
    private Texture lock;
    private float oldx, oldy;
    private float buf_time;
    private Finish finish;
    private  boolean onDraging;

    public static BlackFont font;


    public ChooseScreen(){
        for(int i = 0; i < num_of_el; i++){
            //{0, 1.25f, 2.5f , 3.75f, 5};
            pos[i] = i * 2f;
        }

        fon = new Texture(R.drawable.square4,  0, 0, 1, 1.1f / MainClass.ratio);
        leveltext = new Texture(R.drawable.leveltext,  0, 0, 0.45f, 0.1f / MainClass.ratio);
        lock  = new Texture(R.drawable.lock2);
        lock.setSize(1f/3, 1f/3);
        font = new BlackFont();
        finish = new Finish();
        lvl = Batch.DB.getInt("lvllost")+1;
        setLevels();
        total_x = -(Batch.DB.getInt("lvllost")) * 1.25f;
        oldx = -2;
        oldy = -2;


    }

    public void choosInit(){
        finish = new Finish();
        lvl = Batch.DB.getInt("lvllost")+1;
        setLevels();
        total_x = -(Batch.DB.getInt("lvllost")) * 1.25f;
        oldx = -2;
        oldy = -2;
    }

    public void render(float delta){
        fon.draw();
        //for(int i=0; i< HighScoreScreen.MAX_FIGURE; i++){
        //    HighScoreScreen.figure[i].render(delta, total_x);
        //
        //}
        for(int i = 0; i < num_of_el; i++){
            setPos();
            if(Math.abs(pos[i] + total_x) < 2) {
                leveltext.setPosition(pos[i] + total_x, total_y + MainClass.widht / 2.15f /*size*/);
                leveltext.draw();
                font.setSize(0.045f);
                font.draw(i+1, pos[i] + total_x + 0.45f / 2 /*size of leveltext */, total_y + MainClass.widht / 2.10f, 0.075f);
                HighScoreScreen.levels[i].render(delta);
                if(i > Batch.DB.getInt("lvllost")){
                    lock.setPosition(pos[i] + total_x, (-1f / 12));
                    lock.draw();
                }
                //HighScoreScreen.font.draw(i+1, pos[i] + total_x, 0f,0.05f);
            }
        }
        finish.render(delta);

    }

    public State onTouch(float screenX, float screenY, MotionEvent event){

        finish.start = false;
        oldx = -2;
        oldy = -2;
        lvl = finish.buf + 1;
        if(lvl - 1 <= Batch.DB.getInt("lvllost") && Math.abs(screenY) < 0.5f) {
            return State.Game;
        }
        return State.Default;
    }

    public void onDrag(float x, float y,float start_x, float start_y, MotionEvent event){
        onDraging = true;
        if(oldx != -2){
            if(Math.abs(x - oldx) > Math.abs(y - oldy))
                total_x += (x - oldx) * 1.1f;
        }

        oldx = x;
        oldy = y;

        if(event.getAction() == MotionEvent.ACTION_UP) {
            finish.start_x = start_x;
            finish.finish_x = x;
            finish.start_y = start_y;
            finish.finish_y = y;
            onDraging = false;
            oldx = -2;
            finish.start = true;
            finish.start();
        }
    }

    class Finish{
        float time;
        int buf;
        boolean start;
        boolean onChoose;
        float start_x, start_y;
        float finish_x, finish_y;

        Finish(){
            buf = Batch.DB.getInt("lvllost");
            time = 0;
            start = false;
            onChoose = false;
        }

        void start(){
            start = true;
            if(Math.abs(finish_x - start_x) > 0.8f){
                if(finish_x - start_x > 0){
                    if(buf > 1)
                        buf = buf - 2;
                    else if(buf > 0)
                        buf = buf - 1;
                }else{
                    if(buf < num_of_el-2)
                        buf = buf + 2;
                    else if(buf < num_of_el-1)
                        buf = buf + 1;
                }
            } else if(Math.abs(finish_x - start_x) > 0.3f) {
                if(finish_x - start_x > 0){
                    if(buf > 0)
                        buf = buf - 1;
                }else{
                    if(buf < num_of_el-1)
                        buf = buf + 1;
                }
            }
            start = false;
            onChoose = true;
        }

        void render(float delta){
            if(start){
                time += delta;
            } else
            time = 0;
            if(onChoose == true ){
                float a =  (pos[buf] + total_x) - 0;
                total_x -= a / 7;
                if(Math.abs(pos[buf] + total_x) < 0.01f) {
                    onChoose = false;
                    float a1 =  (pos[buf] + total_x) - 0;
                    total_x -= a1;
                }
            }
        }
    }

    private void setLevels(){
        float size = MainClass.widht / 2.5f;
        HighScoreScreen.levels[0].setPos(new Vec2(pos[0], total_y));
        HighScoreScreen.levels[1].setPos(new Vec2(pos[1], total_y));
        HighScoreScreen.levels[2].setPos(new Vec2(pos[2], total_y));
        HighScoreScreen.levels[3].setPos(new Vec2(pos[3], total_y));
        HighScoreScreen.levels[4].setPos(new Vec2(pos[4], total_y));
        HighScoreScreen.levels[5].setPos(new Vec2(pos[5], total_y));
        HighScoreScreen.levels[6].setPos(new Vec2(pos[6], total_y));
        HighScoreScreen.levels[7].setPos(new Vec2(pos[7], total_y));
        HighScoreScreen.levels[8].setPos(new Vec2(pos[8], total_y));
        HighScoreScreen.levels[9].setPos(new Vec2(pos[9], total_y));
        HighScoreScreen.levels[10].setPos(new Vec2(pos[10], total_y));
        HighScoreScreen.levels[11].setPos(new Vec2(pos[11], total_y));
        HighScoreScreen.levels[12].setPos(new Vec2(pos[12], total_y));
        HighScoreScreen.levels[13].setPos(new Vec2(pos[13], total_y));
        HighScoreScreen.levels[14].setPos(new Vec2(pos[14], total_y));
        HighScoreScreen.levels[15].setPos(new Vec2(pos[15], total_y));
        HighScoreScreen.levels[16].setPos(new Vec2(pos[16], total_y));
        HighScoreScreen.levels[17].setPos(new Vec2(pos[17], total_y));
        HighScoreScreen.levels[18].setPos(new Vec2(pos[18], total_y));
        HighScoreScreen.levels[19].setPos(new Vec2(pos[19], total_y));
        HighScoreScreen.levels[20].setPos(new Vec2(pos[20], total_y));
        HighScoreScreen.levels[21].setPos(new Vec2(pos[21], total_y));
        HighScoreScreen.levels[22].setPos(new Vec2(pos[22], total_y));
        HighScoreScreen.levels[23].setPos(new Vec2(pos[23], total_y));
        HighScoreScreen.levels[24].setPos(new Vec2(pos[24], total_y));
        HighScoreScreen.levels[25].setPos(new Vec2(pos[25], total_y));
        HighScoreScreen.levels[26].setPos(new Vec2(pos[26], total_y));
        HighScoreScreen.levels[27].setPos(new Vec2(pos[27], total_y));
        HighScoreScreen.levels[28].setPos(new Vec2(pos[28], total_y));
        HighScoreScreen.levels[29].setPos(new Vec2(pos[29], total_y));
    }

    private void setPos(){
        HighScoreScreen.levels[0].setPos(new Vec2(pos[0] + total_x, total_y));
        HighScoreScreen.levels[1].setPos(new Vec2(pos[1] + total_x, total_y));
        HighScoreScreen.levels[2].setPos(new Vec2(pos[2] + total_x, total_y));
        HighScoreScreen.levels[3].setPos(new Vec2(pos[3] + total_x, total_y));
        HighScoreScreen.levels[4].setPos(new Vec2(pos[4] + total_x, total_y));
        HighScoreScreen.levels[5].setPos(new Vec2(pos[5] + total_x, total_y));
        HighScoreScreen.levels[6].setPos(new Vec2(pos[6] + total_x, total_y));
        HighScoreScreen.levels[7].setPos(new Vec2(pos[7] + total_x, total_y));
        HighScoreScreen.levels[8].setPos(new Vec2(pos[8] + total_x, total_y));
        HighScoreScreen.levels[9].setPos(new Vec2(pos[9] + total_x, total_y));
        HighScoreScreen.levels[10].setPos(new Vec2(pos[10] + total_x, total_y));
        HighScoreScreen.levels[11].setPos(new Vec2(pos[11] + total_x, total_y));
        HighScoreScreen.levels[12].setPos(new Vec2(pos[12] + total_x, total_y));
        HighScoreScreen.levels[13].setPos(new Vec2(pos[13] + total_x, total_y));
        HighScoreScreen.levels[14].setPos(new Vec2(pos[14] + total_x, total_y));
        HighScoreScreen.levels[15].setPos(new Vec2(pos[15] + total_x, total_y));
        HighScoreScreen.levels[16].setPos(new Vec2(pos[16] + total_x, total_y));
        HighScoreScreen.levels[17].setPos(new Vec2(pos[17] + total_x, total_y));
        HighScoreScreen.levels[18].setPos(new Vec2(pos[18] + total_x, total_y));
        HighScoreScreen.levels[19].setPos(new Vec2(pos[19] + total_x, total_y));
        HighScoreScreen.levels[20].setPos(new Vec2(pos[20] + total_x, total_y));
        HighScoreScreen.levels[21].setPos(new Vec2(pos[21] + total_x, total_y));
        HighScoreScreen.levels[22].setPos(new Vec2(pos[22] + total_x, total_y));
        HighScoreScreen.levels[23].setPos(new Vec2(pos[23] + total_x, total_y));
        HighScoreScreen.levels[24].setPos(new Vec2(pos[24] + total_x, total_y));
        HighScoreScreen.levels[25].setPos(new Vec2(pos[25] + total_x, total_y));
        HighScoreScreen.levels[26].setPos(new Vec2(pos[26] + total_x, total_y));
        HighScoreScreen.levels[27].setPos(new Vec2(pos[27] + total_x, total_y));
        HighScoreScreen.levels[28].setPos(new Vec2(pos[28] + total_x, total_y));
        HighScoreScreen.levels[29].setPos(new Vec2(pos[29] + total_x, total_y));
    }
}

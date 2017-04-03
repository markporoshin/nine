package p0mamin.nined.Menu;

import android.util.Log;
import android.view.MotionEvent;

import p0mamin.nined.Batch;
import p0mamin.nined.Button;
import p0mamin.nined.DataBase;
import p0mamin.nined.Figure;
import p0mamin.nined.MainClass;
import p0mamin.nined.R;
import p0mamin.nined.State;
import p0mamin.nined.Texture;
import p0mamin.nined.font.Font;
import p0mamin.nined.mathematics.Vec2;

/**
 * Created by Mark on 13.02.2017.
 */
public class ChooseScreen {
    public static int lvl = 1;

    public static final int num_of_el = 30;


    private float[] pos = new float[num_of_el];
    private float total_x;
    private Button[] levels = new Button[num_of_el];
    private Texture fon;
    private Texture lock;
    private float oldx, oldy;
    private float buf_time;
    private Finish finish;
    private  boolean onDraging;

    Font font;


    public ChooseScreen(){
        for(int i = 0; i < num_of_el; i++){
            //{0, 1.25f, 2.5f , 3.75f, 5};
            pos[i] = i * 1.25f;
        }

        fon = new Texture(R.drawable.square,  0, 0, 1, 1.5f / MainClass.ratio);
        lock  = new Texture(R.drawable.lock2);
        lock.setSize(1f/3, 1f/3);
        font = new Font();
        finish = new Finish();
        lvl = Batch.DB.getInt("lvllost")+1;
        setLevels();
        total_x = -(Batch.DB.getInt("lvllost")) * 1.25f;
        oldx = -2;
        oldy = -2;


    }

    public void render(float delta){
        fon.draw();
        Log.d("ChooseScreen", "" + total_x);
        for(int i=0; i< HighScoreScreen.MAX_FIGURE; i++){
            HighScoreScreen.figure[i].render(delta, total_x);
        }
        for(int i = 0; i < num_of_el; i++){
            setPos();
            if(Math.abs(pos[i] + total_x) < 1.5) {
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
            //Log.d("ChooseScreen","start");
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
        HighScoreScreen.levels[0].setPos(new Vec2(pos[0], 0));
        HighScoreScreen.levels[1].setPos(new Vec2(pos[1], 0));
        HighScoreScreen.levels[2].setPos(new Vec2(pos[2], 0));
        HighScoreScreen.levels[3].setPos(new Vec2(pos[3], 0));
        HighScoreScreen.levels[4].setPos(new Vec2(pos[4], 0));
        HighScoreScreen.levels[5].setPos(new Vec2(pos[5], 0));
        HighScoreScreen.levels[6].setPos(new Vec2(pos[6], 0));
        HighScoreScreen.levels[7].setPos(new Vec2(pos[7], 0));
        HighScoreScreen.levels[8].setPos(new Vec2(pos[8], 0));
        HighScoreScreen.levels[9].setPos(new Vec2(pos[9], 0));
        HighScoreScreen.levels[10].setPos(new Vec2(pos[10], 0));
        HighScoreScreen.levels[11].setPos(new Vec2(pos[11], 0));
        HighScoreScreen.levels[12].setPos(new Vec2(pos[12], 0));
        HighScoreScreen.levels[13].setPos(new Vec2(pos[13], 0));
        HighScoreScreen.levels[14].setPos(new Vec2(pos[14], 0));
        HighScoreScreen.levels[15].setPos(new Vec2(pos[15], 0));
        HighScoreScreen.levels[16].setPos(new Vec2(pos[16], 0));
        HighScoreScreen.levels[17].setPos(new Vec2(pos[17], 0));
        HighScoreScreen.levels[18].setPos(new Vec2(pos[18], 0));
        HighScoreScreen.levels[19].setPos(new Vec2(pos[19], 0));
        HighScoreScreen.levels[20].setPos(new Vec2(pos[20], 0));
        HighScoreScreen.levels[21].setPos(new Vec2(pos[21], 0));
        HighScoreScreen.levels[22].setPos(new Vec2(pos[22], 0));
        HighScoreScreen.levels[23].setPos(new Vec2(pos[23], 0));
        HighScoreScreen.levels[24].setPos(new Vec2(pos[24], 0));
        HighScoreScreen.levels[25].setPos(new Vec2(pos[25], 0));
        HighScoreScreen.levels[26].setPos(new Vec2(pos[26], 0));
        HighScoreScreen.levels[27].setPos(new Vec2(pos[27], 0));
        HighScoreScreen.levels[28].setPos(new Vec2(pos[28], 0));
        HighScoreScreen.levels[29].setPos(new Vec2(pos[29], 0));
    }

    private void setPos(){
        HighScoreScreen.levels[0].setPos(new Vec2(pos[0] + total_x, 0));
        HighScoreScreen.levels[1].setPos(new Vec2(pos[1] + total_x, 0));
        HighScoreScreen.levels[2].setPos(new Vec2(pos[2] + total_x, 0));
        HighScoreScreen.levels[3].setPos(new Vec2(pos[3] + total_x, 0));
        HighScoreScreen.levels[4].setPos(new Vec2(pos[4] + total_x, 0));
        HighScoreScreen.levels[5].setPos(new Vec2(pos[5] + total_x, 0));
        HighScoreScreen.levels[6].setPos(new Vec2(pos[6] + total_x, 0));
        HighScoreScreen.levels[7].setPos(new Vec2(pos[7] + total_x, 0));
        HighScoreScreen.levels[8].setPos(new Vec2(pos[8] + total_x, 0));
        HighScoreScreen.levels[9].setPos(new Vec2(pos[9] + total_x, 0));
        HighScoreScreen.levels[10].setPos(new Vec2(pos[10] + total_x, 0));
        HighScoreScreen.levels[11].setPos(new Vec2(pos[11] + total_x, 0));
        HighScoreScreen.levels[12].setPos(new Vec2(pos[12] + total_x, 0));
        HighScoreScreen.levels[13].setPos(new Vec2(pos[13] + total_x, 0));
        HighScoreScreen.levels[14].setPos(new Vec2(pos[14] + total_x, 0));
        HighScoreScreen.levels[15].setPos(new Vec2(pos[15] + total_x, 0));
        HighScoreScreen.levels[16].setPos(new Vec2(pos[16] + total_x, 0));
        HighScoreScreen.levels[17].setPos(new Vec2(pos[17] + total_x, 0));
        HighScoreScreen.levels[18].setPos(new Vec2(pos[18] + total_x, 0));
        HighScoreScreen.levels[19].setPos(new Vec2(pos[19] + total_x, 0));
        HighScoreScreen.levels[20].setPos(new Vec2(pos[20] + total_x, 0));
        HighScoreScreen.levels[21].setPos(new Vec2(pos[21] + total_x, 0));
        HighScoreScreen.levels[22].setPos(new Vec2(pos[22] + total_x, 0));
        HighScoreScreen.levels[23].setPos(new Vec2(pos[23] + total_x, 0));
        HighScoreScreen.levels[24].setPos(new Vec2(pos[24] + total_x, 0));
        HighScoreScreen.levels[25].setPos(new Vec2(pos[25] + total_x, 0));
        HighScoreScreen.levels[26].setPos(new Vec2(pos[26] + total_x, 0));
        HighScoreScreen.levels[27].setPos(new Vec2(pos[27] + total_x, 0));
        HighScoreScreen.levels[28].setPos(new Vec2(pos[28] + total_x, 0));
        HighScoreScreen.levels[29].setPos(new Vec2(pos[29] + total_x, 0));
    }
}

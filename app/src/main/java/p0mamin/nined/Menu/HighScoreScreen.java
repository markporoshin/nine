package p0mamin.nined.Menu;

import android.util.Log;
import android.view.MotionEvent;

import p0mamin.nined.Batch;
import p0mamin.nined.Button;
import p0mamin.nined.Figure;
import p0mamin.nined.MainClass;
import p0mamin.nined.R;
import p0mamin.nined.State;
import p0mamin.nined.Texture;
import p0mamin.nined.font.BlackFont;
import p0mamin.nined.font.Font;
import p0mamin.nined.mathematics.Vec2;

/**
 * Created by Mark on 08.03.2017.
 */
public class HighScoreScreen {

    private static final int num_of_el = 30;

    private float[] pos = new float[num_of_el];
    private float total_x;
    private float total_y = 0.124f;
    public static Button[] levels = new Button[num_of_el];
    private float oldx, oldy;
    private float buf_time;
    private Finish finish;
    private  boolean onDraging;

    private float size;
    public static BlackFont font;
    private Texture leveltext;


    public static final int MAX_FIGURE = 150;
    public static Figure[] figure = new Figure[MAX_FIGURE];


    public HighScoreScreen(){
        for(int i=0; i< HighScoreScreen.MAX_FIGURE; i++){
            HighScoreScreen.figure[i] = new Figure();
        }

        for(int i = 0; i < num_of_el; i++){
            //{0, 1.25f, 2.5f , 3.75f, 5...};
            pos[i] = i * 2f;
        }
        font = new BlackFont(0.045f);
        finish = new Finish();
        leveltext = new Texture(R.drawable.leveltext,  0, 0, 0.45f, 0.1f / MainClass.ratio);
        setLevels();
        total_x = -(Batch.DB.getInt("lvllost")+1) * 1.25f;
        oldy = -2;
        oldy = -2;

        finish.start_x = oldx;
        finish.finish_x = 0;
        finish.start_y = oldy;
        finish.finish_y = 0;
        onDraging = false;
        oldx = -2;
        finish.start = true;
        finish.start();
    }

    public void render(float delta){
        for(int i = 0; i < num_of_el; i++){
            setPos();
            if(Math.abs(pos[i] + total_x) < 2) {
                leveltext.setPosition(pos[i] + total_x, total_y + MainClass.widht / 2.15f/*size*/ - 0.13f);
                leveltext.draw();
                HighScoreScreen.font.setSize(0.045f);
                HighScoreScreen.font.draw(i+1, pos[i] + total_x + 0.45f / 2 /*size of leveltext */, total_y + MainClass.widht / 2.10f - 0.13f, 0.075f);
                levels[i].render(delta);
                int buf = Batch.DB.getInt("lvl"+(i+1)), k = 0;
                while(buf > 1){
                    buf = buf/ 10;
                    k++;
                }if(buf == 10){
                    k = 2;
                }if(buf == 100){
                    k = 3;
                }if(k == 3){
                    k = 4;
                }
                font.draw(Batch.DB.getInt("lvl"+(i+1)), pos[i] + total_x - k / 2.f * font.s,  total_y - size * 0.9f , 0.095f);
            }
        }
        finish.render(delta);

    }

    public State onTouch(float screenX, float screenY, MotionEvent event){
        finish.start = false;
        ChooseScreen.lvl = finish.buf+1;
        if (ChooseScreen.lvl - 1 <= Batch.DB.getInt("lvllost") && Math.abs(screenY) < 0.5f) {
            return State.Game;
        }
        return State.Default;
    }

    public void onDrag(float x, float y,float start_x, float start_y, MotionEvent event){

        if( Math.abs(y) < 0.5f) {
            onDraging = true;
            if (oldx != -2) {
                if (Math.abs(x - oldx) > Math.abs(y - oldy))
                    total_x += (x - oldx) * 1.1f;
            }

            oldx = x;
            oldy = y;

            if (event.getAction() == MotionEvent.ACTION_UP) {
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
                if(onChoose == true){
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
        size = MainClass.widht / 2f;
        levels[0] = new Button(R.drawable.lvl1, size, size * 1.05f);
        levels[1] = new Button(R.drawable.lvl2, size, size * 1.05f);
        levels[2] = new Button(R.drawable.lvl3, size, size * 1.05f);
        levels[3] = new Button(R.drawable.lvl4, size, size * 1.05f);
        levels[4] = new Button(R.drawable.lvl5, size, size * 1.05f);
        levels[5] = new Button(R.drawable.lvl6, size, size * 1.05f);
        levels[6] = new Button(R.drawable.lvl7, size, size * 1.05f);
        levels[7] = new Button(R.drawable.lvl8, size, size * 1.05f);
        levels[8] = new Button(R.drawable.lvl9, size, size * 1.05f);
        levels[9] = new Button(R.drawable.lvl10, size, size * 1.05f);
        levels[10] = new Button(R.drawable.lvl11, size, size * 1.05f);
        levels[11] = new Button(R.drawable.lvl12, size, size * 1.05f);
        levels[12] = new Button(R.drawable.lvl13, size, size * 1.05f);
        levels[13] = new Button(R.drawable.lvl14, size, size * 1.05f);
        levels[14] = new Button(R.drawable.lvl15, size, size * 1.05f);
        levels[15] = new Button(R.drawable.lvl16, size, size * 1.05f);
        levels[16] = new Button(R.drawable.lvl17, size, size * 1.05f);
        levels[17] = new Button(R.drawable.lvl18, size, size * 1.05f);
        levels[18] = new Button(R.drawable.lvl19, size, size * 1.05f);
        levels[19] = new Button(R.drawable.lvl20, size, size * 1.05f);
        levels[20] = new Button(R.drawable.lvl21, size, size * 1.05f);
        levels[21] = new Button(R.drawable.lvl22, size, size * 1.05f);
        levels[22] = new Button(R.drawable.lvl23, size, size * 1.05f);
        levels[23] = new Button(R.drawable.lvl24, size, size * 1.05f);
        levels[24] = new Button(R.drawable.lvl25, size, size * 1.05f);
        levels[25] = new Button(R.drawable.lvl26, size, size * 1.05f);
        levels[26] = new Button(R.drawable.lvl27, size, size * 1.05f);
        levels[27] = new Button(R.drawable.lvl28, size, size * 1.05f);
        levels[28] = new Button(R.drawable.lvl29, size, size * 1.05f);
        levels[29] = new Button(R.drawable.lvl30, size, size * 1.05f);

        levels[0].setPos(new Vec2(pos[0], total_y));
        levels[1].setPos(new Vec2(pos[1], total_y));
        levels[2].setPos(new Vec2(pos[2], total_y));
        levels[3].setPos(new Vec2(pos[3], total_y));
        levels[4].setPos(new Vec2(pos[4], total_y));
        levels[5].setPos(new Vec2(pos[5], total_y));
        levels[6].setPos(new Vec2(pos[6], total_y));
        levels[7].setPos(new Vec2(pos[7], total_y));
        levels[8].setPos(new Vec2(pos[8], total_y));
        levels[9].setPos(new Vec2(pos[9], total_y));
        levels[10].setPos(new Vec2(pos[10], total_y));
        levels[11].setPos(new Vec2(pos[11], total_y));
        levels[12].setPos(new Vec2(pos[12], total_y));
        levels[13].setPos(new Vec2(pos[13], total_y));
        levels[14].setPos(new Vec2(pos[14], total_y));
        levels[15].setPos(new Vec2(pos[15], total_y));
        levels[16].setPos(new Vec2(pos[16], total_y));
        levels[17].setPos(new Vec2(pos[17], total_y));
        levels[18].setPos(new Vec2(pos[18], total_y));
        levels[19].setPos(new Vec2(pos[19], total_y));
        levels[20].setPos(new Vec2(pos[20], total_y));
        levels[21].setPos(new Vec2(pos[21], total_y));
        levels[22].setPos(new Vec2(pos[22], total_y));
        levels[23].setPos(new Vec2(pos[23], total_y));
        levels[24].setPos(new Vec2(pos[24], total_y));
        levels[25].setPos(new Vec2(pos[25], total_y));
        levels[26].setPos(new Vec2(pos[26], total_y));
        levels[27].setPos(new Vec2(pos[27], total_y));
        levels[28].setPos(new Vec2(pos[28], total_y));
        levels[29].setPos(new Vec2(pos[29], total_y));
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

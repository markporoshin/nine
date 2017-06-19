package p0mamin.squax.Game.Levels;

import p0mamin.squax.Game.DigitField;
import p0mamin.squax.MainClass;
import p0mamin.squax.R;
import p0mamin.squax.Texture;
import p0mamin.squax.font.Font;

/**
 * Created by Mark on 07.03.2017.
 */
public class Addition {
    private Texture fon;
    private Texture fontrue;
    int num, sum;
    float x, y, size;
    boolean horizontal;
    Font n1;
    public Addition(int num, boolean horizontal){
        this.horizontal = horizontal;
        this.num = num;
        n1 = new Font(0.035f);
        sum = getSum();

        x =  (num+1.5f) * MainClass.widht / DigitField.offset - 1;
        y = - (3.5f + 0.5f) * MainClass.height / 12; // 6 = 12 / 2
        size = MainClass.widht / 11f;

        fon = new Texture(R.drawable.square5true, x, y, size, size);
        fontrue = new Texture(R.drawable.square5, x, y, size, size);
    }

    public void render(float delta){
        if(check())
            fontrue.draw();
        else
            fon.draw();
        switch (((Integer)sum).toString().length()){
            case 1:
                n1.draw(sum, x, y, 0.05f);
                break;
            case 2:
                n1.draw(sum, x - Shape.n1.s / 1.3f, y, 0.05f);
                break;
        }

    }

    public int getSum(){
        int sum = 0;
        if(!horizontal){
            for(int i = 0; i < DigitField.MAX; i++ ){
                sum += DigitField.List[num][i].number;
                //DigitField.List[7-num][i].number;
            }

        }
        return sum;
    }

    public boolean check(){
        if(getSum() == sum){
            return true;
        }
        return false;
    }
}

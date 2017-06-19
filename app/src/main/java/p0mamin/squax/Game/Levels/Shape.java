package p0mamin.squax.Game.Levels;

import java.util.ArrayList;

import p0mamin.squax.Game.DigitField;
import p0mamin.squax.MainClass;
import p0mamin.squax.R;
import p0mamin.squax.Texture;
import p0mamin.squax.font.Font;

/**
 * Created by Mark on 12.02.2017.
 */
public class Shape {
    private final int MAX = DigitField.MAX;
    private int zone;
    private Texture[] sums;
    private Texture[] resums;
    ArrayList<Texture> figure  = new ArrayList<Texture>();
    public static Font n1;
    public Shape(byte map[][], int zone) {
        n1= new Font(0.035f);
        sums = new Texture[zone];
        resums = new Texture[zone];
        for (int i = 0; i < zone; i++) {
            switch (i) {
                case 0:
                    sums[i] = new Texture(R.drawable.square_1);
                    resums[i] = new Texture(R.drawable.square_1);
                    break;
                case 1:
                    sums[i] = new Texture(R.drawable.square_2);
                    resums[i] = new Texture(R.drawable.square_2);
                    break;
                case 2:
                    sums[i] = new Texture(R.drawable.square_3);
                    resums[i] = new Texture(R.drawable.square_3);
                    break;
                case 3:
                    sums[i] = new Texture(R.drawable.square_4);
                    resums[i] = new Texture(R.drawable.square_4);
                    break;
                default:
                    break;
            }
            sums[i].setSize(MainClass.widht / DigitField.offset / 1.5f, MainClass.widht / DigitField.offset / 1.5f);
            resums[i].setSize(MainClass.widht / DigitField.offset / 1.5f, MainClass.widht / DigitField.offset / 1.5f);
        }

        int k = 0;
        this.zone = zone;
        for (int i = 0; i < MAX; i++)
            for (int j = 0; j < MAX; j++) {
                switch (map[MAX - j - 1][i]) {
                    case 1:
                        figure.add(new Texture(R.drawable.squareblue));
                        figure.get(k).setSize(MainClass.widht / DigitField.offset / 2, MainClass.widht / DigitField.offset / 2);
                        figure.get(k).setPosition((i + 1.5f) * MainClass.widht / DigitField.offset - 1,
                                (((j + 1.f) * MainClass.widht / DigitField.offset + MainClass.height / 2f) - 1) * MainClass.ratio);
                        k++;
                        break;
                    case 2:
                        figure.add(new Texture(R.drawable.squaregreen));
                        figure.get(k).setSize(MainClass.widht / DigitField.offset / 2, MainClass.widht / DigitField.offset / 2);
                        figure.get(k).setPosition((i + 1.5f) * MainClass.widht / DigitField.offset - 1,
                                (((j + 1.f) * MainClass.widht / DigitField.offset + MainClass.height / 2f) - 1) * MainClass.ratio);
                        k++;
                        break;
                    case 3:
                        figure.add(new Texture(R.drawable.squareorange));
                        figure.get(k).setSize(MainClass.widht / DigitField.offset / 2, MainClass.widht / DigitField.offset / 2);
                        figure.get(k).setPosition((i + 1.5f) * MainClass.widht / DigitField.offset - 1,
                                (((j + 1.f) * MainClass.widht / DigitField.offset + MainClass.height / 2f) - 1) * MainClass.ratio);
                        k++;
                        break;
                    case 4:
                        figure.add(new Texture(R.drawable.squareyellow));
                        figure.get(k).setSize(MainClass.widht / DigitField.offset / 2, MainClass.widht / DigitField.offset / 2);
                        figure.get(k).setPosition((i + 1.5f) * MainClass.widht / DigitField.offset - 1,
                                (((j + 1.f) * MainClass.widht / DigitField.offset + MainClass.height / 2f) - 1) * MainClass.ratio);
                        k++;
                        break;
                    case 5:
                        figure.add(new Texture(R.drawable.square));
                        figure.get(k).setSize(MainClass.widht / DigitField.offset / 2, MainClass.widht / DigitField.offset / 2);
                        figure.get(k).setPosition((i + 1.5f) * MainClass.widht / DigitField.offset - 1,
                                (((j + 1.5f) * MainClass.widht / DigitField.offset + MainClass.height / 2f) - 1) * MainClass.ratio);
                        k++;
                        break;
                    default:
                        break;
                }
            }
    }

    public void render(float delta, int[] sum, int[] resum){

        for (Texture s:figure) {
            s.draw();
        }

        for(int i=0; i < zone;i++){
            int k = 0;
            float t = sum[i];
            while(t > 1){
                t = t / 10;
                k++;
            }if(sum[i] == 10){
                k = 2;
            }if(sum[i] == 100){
                k = 3;
            }

            int k1 = 0;
            float t1 = resum[i];
            while(t1 > 1){
                t1 = t1 / 10;
                k1++;
            }if(resum[i] == 10){
                k1 = 2;
            }if(resum[i] == 100){
                k1 = 3;
            }

            if(k == 3){
                k = 4;
            }

            if(k1 == 3){
                k1 = 4;
            }

            float h1 = 2f, h2 = 1.5f;
            sums[i].setPosition(((float)(i+1))*MainClass.widht/(zone + 1) * 2 - 1, -MainClass.height/h1*1.15f);
            sums[i].draw();

            n1.draw(sum[i], ((float)(i+1))*MainClass.widht/(zone + 1) * 2 - 1 - k / 2.f * n1.s , -MainClass.height/h1*1.15f, MainClass.height / 18);

            resums[i].setPosition(((float)(i+1))*MainClass.widht/(zone + 1) * 2 - 1,  -MainClass.height/h2*1.15f);
            resums[i].draw();

            n1.draw(resum[i], ((float)(i+1))*MainClass.widht/(zone + 1) * 2 - 1 - k1 / 2.f * n1.s , -MainClass.height/h2*1.15f, MainClass.height / 18);
        }
    }

}

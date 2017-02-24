package p0mamin.nined.Game.Levels;

import org.w3c.dom.Text;

import java.util.ArrayList;

import p0mamin.nined.Batch;
import p0mamin.nined.Game.DigitField;
import p0mamin.nined.MainClass;
import p0mamin.nined.R;
import p0mamin.nined.State;
import p0mamin.nined.Texture;
import p0mamin.nined.font.Font;
import p0mamin.nined.mathematics.Vec2;

/**
 * Created by Mark on 12.02.2017.
 */
public class Shape {
    private final int MAX = DigitField.MAX;
    private int zone;
    private Texture[] sums;
    private Texture[] resums;
    ArrayList<Texture> figure  = new ArrayList<Texture>();
    Font n1;
    public Shape(byte map[][], int zone) {
        n1= new Font(0.035f);
        sums = new Texture[zone];
        resums = new Texture[zone];
        for (int i = 0; i < zone; i++) {
            switch (i) {
                case 0:
                    sums[i] = new Texture(R.drawable.squareblue);
                    resums[i] = new Texture(R.drawable.squareblue);
                    break;
                case 1:
                    sums[i] = new Texture(R.drawable.squaregreen);
                    resums[i] = new Texture(R.drawable.squaregreen);
                    break;
                case 2:
                    sums[i] = new Texture(R.drawable.squareorange);
                    resums[i] = new Texture(R.drawable.squareorange);
                    break;
                case 3:
                    sums[i] = new Texture(R.drawable.squareyellow);
                    resums[i] = new Texture(R.drawable.squareyellow);
                    break;
                case 4:
                    sums[i] = new Texture(R.drawable.square);
                    resums[i] = new Texture(R.drawable.square);
                    break;
                default:
                    break;
            }
            sums[i].setSize(MainClass.widht / DigitField.offset / 2.5f, MainClass.widht / DigitField.offset / 2.5f);
            resums[i].setSize(MainClass.widht / DigitField.offset / 2.5f, MainClass.widht / DigitField.offset / 2.5f);
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
                                (((j + 1.5f) * MainClass.widht / DigitField.offset + MainClass.height / 2f) - 1) * MainClass.ratio);
                        k++;
                        break;
                    case 2:
                        figure.add(new Texture(R.drawable.squaregreen));
                        figure.get(k).setSize(MainClass.widht / DigitField.offset / 2, MainClass.widht / DigitField.offset / 2);
                        figure.get(k).setPosition((i + 1.5f) * MainClass.widht / DigitField.offset - 1,
                                (((j + 1.5f) * MainClass.widht / DigitField.offset + MainClass.height / 2f) - 1) * MainClass.ratio);
                        k++;
                        break;
                    case 3:
                        figure.add(new Texture(R.drawable.squareorange));
                        figure.get(k).setSize(MainClass.widht / DigitField.offset / 2, MainClass.widht / DigitField.offset / 2);
                        figure.get(k).setPosition((i + 1.5f) * MainClass.widht / DigitField.offset - 1,
                                (((j + 1.5f) * MainClass.widht / DigitField.offset + MainClass.height / 2f) - 1) * MainClass.ratio);
                        k++;
                        break;
                    case 4:
                        figure.add(new Texture(R.drawable.squareyellow));
                        figure.get(k).setSize(MainClass.widht / DigitField.offset / 2, MainClass.widht / DigitField.offset / 2);
                        figure.get(k).setPosition((i + 1.5f) * MainClass.widht / DigitField.offset - 1,
                                (((j + 1.5f) * MainClass.widht / DigitField.offset + MainClass.height / 2f) - 1) * MainClass.ratio);
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
            }

            int k1 = 0;
            float t1 = resum[i];
            while(t1 > 1){
                t1 = t1 / 10;
                k1++;
            }
            float h1 = 2f, h2 = 1.5f;
            sums[i].setPosition(((float)(i+1))*MainClass.widht/(zone + 1) * 2 - 1, -MainClass.height/h1);
            sums[i].draw();

            n1.draw(sum[i], ((float)(i+1))*MainClass.widht/(zone + 1) * 2 - 1 - k * n1.s / 2, -MainClass.height/h1, MainClass.height / 18);

            resums[i].setPosition(((float)(i+1))*MainClass.widht/(zone + 1) * 2 - 1,  -MainClass.height/h2);
            resums[i].draw();

            n1.draw(resum[i], ((float)(i+1))*MainClass.widht/(zone + 1) * 2 - 1 - k1 * n1.s / 2, -MainClass.height/h2, MainClass.height / 18);
        }
    }

}

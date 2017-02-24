package p0mamin.nined.font;

import p0mamin.nined.Game.Digit;
import p0mamin.nined.R;
import p0mamin.nined.Texture;

/**
 * Created by Mark on 08.02.2017.
 */
public class Font {
    private float hieght;
    private int num;
    public float s;
    private Texture[] digit = new Texture[10];
    public Font(){
        s = 0.06f;
        setDigit();
    }
    public Font(float s){
        this.s = s;
        setDigit();
    }
    private float[] size = new float[]{
            1f,//0
            1f,//1
            1f,//2
            1f,//3
            1f,//4
            1f,//5
            1f,//6
            1f,//7
            1f,//8
            1f,//9


    };
    private float[] offset = new float[]{
            1f,//0
            1f,//1
            1f,//2
            1f,//3
            1f,//4
            1f,//5
            1f,//6
            1f,//7
            1f,//8
            2f,//9


    };


    public void setSize(float size){
        this.s = size;
    }
    public void draw(int n, float x, float y, float hieght){
        this.hieght = hieght;
        int k = (n+"").length();
        /*int n1 = n;
        while (n1 > 0.1){
            k++;
            n1 = (n1 - n1 % 10) / 10;
        }*/
        float x1 = 0;
        for(; k > 0; k--) {
            switch ((int)(n / Math.pow(10, k-1))){
                case 0:
                    digit[0].setPosition(x + x1, y);
                    digit[0].setSize( size[0]*s, hieght);
                    digit[0].draw();
                    //new Texture(R.drawable.zero, x + x1, y, size[0]*s, hieght).draw();
                    x1+=(size[0]+offset[0])*s;
                    break;
                case 1:
                    digit[1].setPosition(x + x1, y);
                    digit[1].setSize( size[1]*s, hieght);
                    digit[1].draw();
                    //new Texture(R.drawable.one, x + x1, y, size[1]*s, hieght).draw();
                    x1+=(size[1]+offset[1])*s;
                    break;
                case 2:
                    digit[2].setPosition(x + x1, y);
                    digit[2].setSize( size[2]*s, hieght);
                    digit[2].draw();
                    //new Texture(R.drawable.two, x + x1, y, size[2]*s, hieght).draw();
                    x1+=(size[2]+offset[2])*s;
                    break;
                case 3:
                    digit[3].setPosition(x + x1, y);
                    digit[3].setSize( size[3]*s, hieght);
                    digit[3].draw();
                    //new Texture(R.drawable.tree, x + x1, y, size[3]*s, hieght).draw();
                    x1+=(size[3]+offset[3])*s;
                    break;
                case 4:
                    digit[4].setPosition(x + x1, y);
                    digit[4].setSize( size[4]*s, hieght);
                    digit[4].draw();
                    //new Texture(R.drawable.four, x + x1, y, size[4]*s, hieght).draw();
                    x1+=(size[4]+offset[4])*s;
                    break;
                case 5:
                    digit[5].setPosition(x + x1, y);
                    digit[5].setSize( size[5]*s, hieght);
                    digit[5].draw();
                    //new Texture(R.drawable.five, x + x1, y, size[5]*s, hieght).draw();
                    x1+=(size[5]+offset[5])*s;
                    break;
                case 6:
                    digit[6].setPosition(x + x1, y);
                    digit[6].setSize( size[6]*s, hieght);
                    digit[6].draw();
                    //new Texture(R.drawable.six, x + x1, y, size[6]*s, hieght).draw();
                    x1+=(size[6]+offset[6])*s;
                    break;
                case 7:
                    digit[7].setPosition(x + x1, y);
                    digit[7].setSize( size[7]*s, hieght);
                    digit[7].draw();
                    //new Texture(R.drawable.seven, x + x1, y, size[7]*s, hieght).draw();
                    x1+=(size[7]+offset[7])*s;
                    break;
                case 8:
                    digit[8].setPosition(x + x1, y);
                    digit[8].setSize( size[8]*s, hieght);
                    digit[8].draw();
                    //new Texture(R.drawable.eight, x + x1, y, size[8]*s, hieght).draw();
                    x1+=(size[8]+offset[8])*s;
                    break;
                case 9:
                    digit[9].setPosition(x + x1, y);
                    digit[9].setSize( size[9]*s, hieght);
                    digit[9].draw();
                    //new Texture(R.drawable.nine, x + x1, y, size[9]*s, hieght).draw();
                    x1+=(size[9]*s+offset[9])*s;
                    break;

            }
            n -= ((int)(n / Math.pow(10, k-1))) * Math.pow(10, k-1);
        }
    }

    private void setDigit(){
        digit[0] = new Texture(R.drawable.zero);
        digit[1] = new Texture(R.drawable.one);
        digit[2] = new Texture(R.drawable.two);
        digit[3] = new Texture(R.drawable.tree);
        digit[4] = new Texture(R.drawable.four);
        digit[5] = new Texture(R.drawable.five);
        digit[6] = new Texture(R.drawable.six);
        digit[7] = new Texture(R.drawable.seven);
        digit[8] = new Texture(R.drawable.eight);
        digit[9] = new Texture(R.drawable.nine);
    }
}

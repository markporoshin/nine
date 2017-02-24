package p0mamin.nined.Game;

import p0mamin.nined.R;
import p0mamin.nined.Texture;

/**
 * Created by Mark on 22.02.2017.
 */
public class WinScreen {

    Texture left;
    Texture right;
    Texture cross;

    public WinScreen(){

        left = new Texture(R.drawable.left);
        right = new Texture(R.drawable.right);
        cross = new Texture(R.drawable.cross);

        //fon = new Texture();
        //praise = new Texture();
    }

}

package p0mamin.nined;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ConfigurationInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.opengl.GLSurfaceView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainClass extends Activity{
    /** Hold a reference to our GLSurfaceView */
    public static  Activity activity;
    private GLSurfaceView mGLSurfaceView;
    private TextView tv;
    private Render render;
    public static boolean vizits = false;
    public static float ratio;
    public static float height = 1f;
    public static float widht = 1f;


    public static SharedPreferences data_base;
    public static final String APP_PREFERENCES = "app_data";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity = this;
        super.onCreate(savedInstanceState);
        mGLSurfaceView = new GLSurfaceView(this);

        data_base = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if(data_base.getBoolean("Vizits", true)){
            vizits = true;
            SharedPreferences.Editor editor = MainClass.data_base.edit();
            editor.putBoolean("Vizits", false);
            editor.apply();
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        final int width, hieght;
        // узнаем размеры экрана из класса Display
        final Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metricsB = new DisplayMetrics();
        display.getMetrics(metricsB);
        //height = displaymetrics.heightPixels;
        //widht = displaymetrics.widthPixels;
        ratio = (float)displaymetrics.widthPixels / displaymetrics.heightPixels;
        width = displaymetrics.widthPixels;
        hieght = displaymetrics.heightPixels;
        // Check if the system supports OpenGL ES 2.0.
        final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;
        render = new Render(this);
        if (supportsEs2)
        {
            // Request an OpenGL ES 2.0 compatible context.
            mGLSurfaceView.setEGLContextClientVersion(2);

            // Set the renderer to our demo renderer, defined below.
            mGLSurfaceView.setRenderer(render);
        }
        else
        {
            // This is where you could create an OpenGL ES 1.x compatible
            // renderer if you wanted to support both ES 1 and ES 2.
            return;
        }

        mGLSurfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                render.OnTouchListener(event.getX() / (float) width * 2 - 1, (event.getY() / (float) hieght * 2 - 1) * (-1), event);
                return true;
            }
        });
        //mGLSurfaceView.setOnBack


        /*mGLSurfaceView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                render.OnTouchListener(event.getX() / (float)width * 2 - 1, (event.getY() / (float)hieght * 2 - 1)*(-1));
                return false;
            }
        });*/
        setContentView(mGLSurfaceView);
    }

    @Override
    protected void onResume()
    {
        // The activity must call the GL surface view's onResume() on activity onResume().
        super.onResume();
        //mGLSurfaceView.onResume();
    }

    @Override
    protected void onPause()
    {
        // The activity must call the GL surface view's onPause() on activity onPause().
        super.onPause();
        //mGLSurfaceView.onPause();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        render.onBackPressed();
    }
}

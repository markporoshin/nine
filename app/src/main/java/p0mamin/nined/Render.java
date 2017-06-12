package p0mamin.nined;

import android.content.Context;
import android.content.SharedPreferences;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES10.glBlendFunc;
import static android.opengl.GLES20.GL_ALPHA;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glEnable;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniformMatrix4fv;
import static android.opengl.GLES20.glUseProgram;
import static android.opengl.GLES20.glVertexAttribPointer;
import static android.opengl.GLES20.glViewport;

/**
 * Created by Mark on 28.01.2017.
 */

public class Render implements GLSurfaceView.Renderer
{
    public final static int POSITION_COUNT = 3;
    public static final int TEXTURE_COUNT = 2;
    public static final int STRIDE = (POSITION_COUNT
            + TEXTURE_COUNT) * 4;

    public static Context context;



    public static int aPositionLocation;
    public static int aTextureLocation;
    public static int uTextureUnitLocation;
    public static int uMatrixLocation;

    public static int programId;

    public static float[] mProjectionMatrix = new float[16];
    public static float[] mViewMatrix = new float[16];
    public static float[] mMatrix = new float[16];
    public static float[] mModelMatrix = new float[16];

    private static long deltaTime;
    private static long lastTime = System.currentTimeMillis();

    private int texture;

    Batch batch;

    private static long touchTime;
    private static long upTime;
    private static long downTime;
    private float startx, starty;
    private boolean drag;
    public static float ratio = MainClass.ratio;
    public static  float z = 0;
    private Texture fon;
    private boolean flag;
    private boolean flag2;
    //private static float oldx, oldy;
    /**
     * Initialize the model data.
     */
    public Render(Context context) {
        this.context = context;
        Log.d("Create", "render create");

    }

    @Override
    public void onSurfaceCreated(GL10 arg0, EGLConfig arg1) {
        glClearColor(0, 0, 0, 1);
        //glEnable(GLES20.GL_DEPTH_TEST);
        createAndUseProgram();
        getLocations();
        createViewMatrix();
        fon = new Texture(R.drawable.downloading, 0, 0, 1, 1/ MainClass.ratio);
        touchTime = 0;
        flag = true;
        flag2 = true;
        drag = false;
    }

    @Override
    public void onSurfaceChanged(GL10 arg0, int width, int height) {
        glViewport(0, 0, width, height);
        createProjectionMatrix(width, height);
    }

    private void createAndUseProgram() {
        int vertexShaderId = ShaderUtils.createShader(context, GLES20.GL_VERTEX_SHADER, R.raw.vertex_shader);
        int fragmentShaderId = ShaderUtils.createShader(context, GLES20.GL_FRAGMENT_SHADER, R.raw.fragment_shader);
        programId = ShaderUtils.createProgram(vertexShaderId, fragmentShaderId);
        glUseProgram(programId);
    }

    private void getLocations() {
        aPositionLocation = glGetAttribLocation(programId, "a_Position");
        aTextureLocation = glGetAttribLocation(programId, "a_Texture");
        uTextureUnitLocation = glGetUniformLocation(programId, "u_TextureUnit");
        uMatrixLocation = glGetUniformLocation(programId, "u_Matrix");
    }

    private void createProjectionMatrix(int width, int height) {
        float ratio = (float)height/width;
        float left = -1f;
        float right = 1f;
        float bottom = -1f / ratio;
        float top = 1f / ratio;
        float near = 0.1f;
        float far = 12;
        if (width > height) {
            ratio = (float) width / height;
            left *= ratio;
            right *= ratio;
        } else {
            ratio = (float) height / width;
            bottom *= ratio;
            top *= ratio;
        }

        Matrix.frustumM(mProjectionMatrix, 0, left, right, bottom, top, near, far);
    }

    private void createViewMatrix() {
        //glEnable(GL_ALPHA);
        GLES20.glDisable(GLES20.GL_CULL_FACE);
        GLES20.glDisable(GLES20.GL_DEPTH_TEST);
        GLES20.glEnable(GLES20.GL_BLEND);
        glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        //GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_ONE);
        // точка полоения камеры
        float eyeX = 0f;
        float eyeY = 0f;
        float eyeZ = 0.1f + z;

        // точка направления камеры
        float centerX = 0;
        float centerY = 0;
        float centerZ = 0;

        // up-вектор
        float upX = 0;
        float upY = 1;
        float upZ = 0;

        Matrix.setLookAtM(mViewMatrix, 0, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }




    @Override
    public void onDrawFrame(GL10 arg0) {
        deltaTime =  System.currentTimeMillis()-lastTime;
        lastTime = System.currentTimeMillis();
        glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        glClearColor(0, 0, 0, 1);
        //Log.d("Time", "" + deltaTime/1000.0f);
        createViewMatrix();
        try {
            if(flag) {
                fon.draw();
                if(flag2){
                    flag2 =! flag2;
                }else {
                    this.batch = new Batch();
                    flag = !flag;
                }
            }else {
                batch.render(deltaTime / 1000.0f);
            }
        }catch (Exception e){
            Log.d("Render", "" + e);
        }
    }

    public void OnTouchListener(float x, float y, MotionEvent event){
        try {
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                Log.d("Render", "onDown");
                batch.onDown(x, y);
                downTime = System.currentTimeMillis();
                startx = x;
                starty = y;
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (Math.abs(startx - x) < 0.05f && Math.abs(starty - y) < 0.05f) {
                    batch.OnTouch(x, y, event);
                } else if (drag) {
                    batch.OnDrag(x, y, startx, starty, event);
                    drag = false;
                }

            }

            if (drag || ((System.currentTimeMillis() - touchTime) + 5) > deltaTime && ((System.currentTimeMillis() - touchTime) - 5) < deltaTime) {
                batch.OnDrag(x, y, startx, starty, event);
                //Log.d("Render", "onDrag");
                drag = true;
            }


            //Log.d("Render", "" +(System.currentTimeMillis() - touchTime));
            //Log.d("Render", "" + (((System.currentTimeMillis() - touchTime) + 5) > deltaTime && ((System.currentTimeMillis() - touchTime) - 5) < deltaTime));
            touchTime = System.currentTimeMillis();


        }catch (Exception e){
            Log.d("Render", "" + e);
        }
    }

    public void onBackPressed(){
        batch.onBackPressed();
    }


    private void setModelMatrix() {
        Matrix.translateM(mModelMatrix, 0, 0, -0.5f, 0);
    }


    static  float getHeight(){
        return MainClass.height;
    }


    static  float getWidht(){
        return MainClass.widht;
    }
}


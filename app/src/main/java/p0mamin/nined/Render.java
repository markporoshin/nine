package p0mamin.nined;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
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
    private float startx, starty;
    private boolean drag;
    public static float ratio = MainClass.ratio;
    public static  float z = 0;
    /**
     * Initialize the model data.
     */
    public Render(Context context) {
        this.context = context;
        this.
        batch = new Batch();
    }

    @Override
    public void onSurfaceCreated(GL10 arg0, EGLConfig arg1) {
        glClearColor(0f, 0f, 0f, 1f);
        //glEnable(GLES20.GL_DEPTH_TEST);
        batch = new Batch();
        createAndUseProgram();
        getLocations();
        createViewMatrix();
        touchTime = 0;
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
        //Log.d("Time", "" + deltaTime/1000.0f);
        createViewMatrix();
        batch.render(deltaTime /1000.0f);
    }

    public void OnTouchListener(float x, float y, MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            //Log.d("Render","ACTION_DOWN");
            startx = x;
            starty = y;
        }
        if(event.getAction() == MotionEvent.ACTION_UP) {
            //Log.d("Render", "ACTION_UP");
            if(drag){
                batch.OnDrag(x, y, startx, starty, event);
                drag = false;
            }
        }

        if(drag || ((System.currentTimeMillis() - touchTime) + 5) > deltaTime && ((System.currentTimeMillis() - touchTime) - 5) < deltaTime) {
            batch.OnDrag(x, y, startx, starty, event);
            //Log.d("Render", "onDrag");
            drag = true;
        }else{
            //Log.d("Render", "onTouch");
            batch.OnTouch(x, y, event);
        }
        //Log.d("Render", "" +(System.currentTimeMillis() - touchTime));
        //Log.d("Render", "" + (((System.currentTimeMillis() - touchTime) + 5) > deltaTime && ((System.currentTimeMillis() - touchTime) - 5) < deltaTime));
        touchTime = System.currentTimeMillis();

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


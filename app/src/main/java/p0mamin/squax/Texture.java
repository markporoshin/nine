package p0mamin.squax;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import p0mamin.squax.mathematics.Vec2;

import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glUniformMatrix4fv;
import static android.opengl.GLES20.glVertexAttribPointer;

/**
 * Created by Mark on 29.01.2017.
 */
public class
Texture {
    public static String TAG = "Texture";

    public FloatBuffer vertexData;
    private float[] mMVPMatrix = new float[16];
    private float[] motionMatrix = new float[16];
    private int texture;
    private float x, y,z;
    private float sizex, sizey;
    private float transx, transy, transz;

    public Texture(int id) {
        Matrix.setIdentityM(motionMatrix, 0);
        Log.d(TAG, "init");
        texture = TextureManager.find(id);
    }

    public Texture(int id, float x, float y, float sizex, float sizey){
        Matrix.setIdentityM(motionMatrix, 0);
        try{
            texture = TextureManager.find(id);
        }catch (Exception e){
            //texture =  TextureUtils.loadTexture(Render.context, id);
        }

        this.sizex = sizex;
        this.sizey = sizey;
        this.x = x;
        this.y = y;
        int z = 0;
        float[] vertices = {
                //coordinates for sky
                this.x-sizex, this.y+sizey * Render.ratio, z,  0, 0,
                this.x+sizex, this.y+sizey * Render.ratio, z,  1, 0,
                this.x+sizex, this.y-sizey * Render.ratio, z,  1, 1,

                this.x-sizex, this.y-sizey * Render.ratio, z,  0, 1,
                this.x-sizex, this.y+sizey * Render.ratio, z,  0, 0,
                this.x+sizex, this.y-sizey * Render.ratio, z,  1, 1,


        };

        vertexData = ByteBuffer
                .allocateDirect(vertices.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexData.put(vertices);
    }


    public void setSize(float sizex, float sizey){
        this.sizex = sizex;
        this.sizey = sizey;
    }


    public void translate(float x, float y, float z) {
        Matrix.translateM(motionMatrix, 0, x, y, z);
    }

    public void rotate(float angle){
        Matrix.rotateM(motionMatrix, 0, angle, 0, 0, 1);
    }

    public void rotate(float angle, float dir){
        Matrix.rotateM(motionMatrix, 0, angle, 0, 0, dir);
    }

    public void scale(float scale){
        Matrix.scaleM(motionMatrix, 0, scale, scale, 0);
    }

    public void setPosition(float x, float y){
        float[] vertices = {
                //coordinates for sky
                x-sizex, y+sizey * Render.ratio, z,  0, 0,
                x+sizex, y+sizey * Render.ratio, z,  1, 0,
                x+sizex, y-sizey * Render.ratio, z,  1, 1,

                x-sizex, y-sizey * Render.ratio, z,  0, 1,
                x-sizex, y+sizey * Render.ratio, z,  0, 0,
                x+sizex, y-sizey * Render.ratio, z,  1, 1,


        };

        vertexData = ByteBuffer
                .allocateDirect(vertices.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexData.put(vertices);
    }

    public void setPosition(Vec2 pos){
        setPosition(pos.x, pos.y);
    }
    public void setPosition(){
        float[] vertices = {
                //coordinates for sky
                x-sizex, y+sizey * Render.ratio, z,  0.00001f, 0.00001f,
                x+sizex, y+sizey * Render.ratio, z,  1 - 0.00001f, 0.00001f,
                x+sizex, y-sizey * Render.ratio, z,  1 - 0.00001f, 1 - 0.00001f,

                x-sizex, y-sizey * Render.ratio, z,  0.00001f, 1 - 0.00001f,
                x-sizex, y+sizey * Render.ratio, z,  0.00001f, 0.00001f,
                x+sizex, y-sizey * Render.ratio, z,  1 - 0.00001f, 1 - 0.00001f,


        };

        vertexData = ByteBuffer
                .allocateDirect(vertices.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexData.put(vertices);
    }

    public void setDepth(float z){
        this.z = z;
    }

    public float getHieght(){
        return sizey;
    }

    public float getWidth(){
        return sizex;
    }



    public void draw(){

        // координаты вершин
        vertexData.position(0);
        glVertexAttribPointer(Render.aPositionLocation, Render.POSITION_COUNT, GLES20.GL_FLOAT,
                false, Render.STRIDE, vertexData);
        glEnableVertexAttribArray(Render.aPositionLocation);


        // координаты текстур
        vertexData.position(Render.POSITION_COUNT);
        glVertexAttribPointer(Render.aTextureLocation, Render.TEXTURE_COUNT, GLES20.GL_FLOAT,
                false, Render.STRIDE, vertexData);
        glEnableVertexAttribArray(Render.aTextureLocation);


        Matrix.setIdentityM(Render.mModelMatrix, 0);
        Matrix.multiplyMM(Render.mModelMatrix, 0, motionMatrix, 0, Render.mModelMatrix, 0); //space conversion
        Matrix.multiplyMM(Render.mModelMatrix, 0, Render.mViewMatrix, 0, Render.mModelMatrix, 0);
        Matrix.multiplyMM(Render.mModelMatrix, 0, Render.mProjectionMatrix, 0, Render.mModelMatrix, 0);

        glUniformMatrix4fv(Render.uMatrixLocation, 1, false, Render.mModelMatrix, 0);
        glBindTexture(GLES20.GL_TEXTURE_2D, texture);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 6);
        Matrix.setIdentityM(motionMatrix, 0);
    }
}

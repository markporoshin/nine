package p0mamin.squax.mathematics;

/**
 * Created by Mark on 29.01.2017.
 */
public class Vec2 {
    public float x, y;
    public static final float PI = 3.14159265358979323846f;
    static public final float degreesToRadians = PI / 180;

    public Vec2(){}

    public Vec2(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Vec2(Vec2 v){
        this.x = v.x;
        this.y = v.y;
    }

    public Vec2 add(Vec2 v)
    {
        this.x = this.x + v.x;
        this.y = this.y + v.y;
        return this;
    }


    public Vec2 multi(float k)
    {
        this.x = this.x * k;
        this.y = this.y * k;
        return this;
    }

    @Override
    public String toString() {
        return "(" +
                 x +
                "," + y +
                ')';
    }

    public Vec2 subtract(Vec2 v){
        this.x = this.x - v.x;
        this.y = this.y - v.y;
        return this;
    }

    public Vec2 add(float x, float y){
        this.x = this.x + x;
        this.y = this.y + y;
        return this;
    }

    public Vec2 set(float x1, float y1){
        this.x = x1;
        this.y = y1;
        return  this;
    }

    public Vec2 rotate(float degree){
        float radians = degreesToRadians * degree;
        float cos = (float)Math.cos(radians);
        float sin = (float)Math.sin(radians);

        float newX = (this.x * cos - this.y * sin);//* Render.ratio;
        float newY = (this.x * sin + this.y * cos) ;

        this.x = newX;
        this.y = newY;
        return this;
    }

    public void set(Vec2 vec2) {
        this.x = vec2.x;
        this.y = vec2.y;
     //   return this;
    }
}

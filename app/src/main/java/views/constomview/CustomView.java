package views.constomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.example.huangli.mydemos.R;

/**
 * Created by huangli on 16/6/4.
 */
public class CustomView extends View{
    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        paint = new Paint();

        paint.setColor(myColor);
        paint.setStrokeWidth(5);
    }

    private int centre;
    private Paint paint;
    private int contentWidth;
    private int myColor = getResources().getColor(R.color.seekbar);
    private int myColor2 = getResources().getColor(R.color.yellow);
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        contentWidth = getWidth() - paddingLeft - paddingRight;
        centre = getWidth() / 2;
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.icon_seek_avilable);
//        canvas.drawBitmap(bitmap,centre, 0,null);
//        LinearGradient shader = new LinearGradient(centre, 0, centre+50, 50,myColor ,myColor2, Shader.TileMode.MIRROR);
//        paint.setShader(shader);
        paint.setARGB(255,60,60,60);
        RectF rect = new RectF(0,0,getWidth(),getWidth());
//        canvas.drawArc(rect,0,60,true,paint);
        canvas.drawOval(0,0,100,50,paint);

    }
}

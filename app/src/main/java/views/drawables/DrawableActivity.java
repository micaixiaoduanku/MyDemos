package views.drawables;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.huangli.mydemos.R;

/**
 * Created by huangli on 16/5/21.
 */
public class DrawableActivity extends Activity implements View.OnClickListener {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawables);

        imageView = (ImageView) findViewById(R.id.imageView);
        findViewById(R.id.btn_bitmap_drawable).setOnClickListener(this);
        findViewById(R.id.shape_bitmap_drawable).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_bitmap_drawable:
                Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.banner1);
                BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bm);
                imageView.setImageDrawable(bitmapDrawable);
                //TODO implement
                break;
            case R.id.shape_bitmap_drawable:
                ShapeDrawable shapeDrawable = new ShapeDrawable();
                //TODO implement
                break;
        }
    }
}



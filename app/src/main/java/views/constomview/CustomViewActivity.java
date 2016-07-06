package views.constomview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.huangli.mydemos.R;

/**
 * Created by huangli on 16/6/4.
 */
public class CustomViewActivity extends Activity{
    private CustomView customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constomview);

    }
}

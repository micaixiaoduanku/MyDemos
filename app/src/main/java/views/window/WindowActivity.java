package views.window;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by huangli on 16/5/27.
 */
public class WindowActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button btn = new Button(this);
        btn.setText("mybutton");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,0,0, PixelFormat.TRANSLUCENT);
        WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        wm.addView(btn, layoutParams);
    }
}

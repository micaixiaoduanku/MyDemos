package views.windowdialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.huangli.mydemos.R;

/**
 * Created by huangli on 16/3/23.
 */
public class WindowDialogActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_windowdialog);
        WindowDialogActivity.this.startService(new Intent(WindowDialogActivity.this, HUD.class));
    }
}

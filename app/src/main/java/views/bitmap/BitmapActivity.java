package views.bitmap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.huangli.mydemos.R;

import java.io.IOException;

/**
 * Created by huangli on 16/7/2.
 */
public class BitmapActivity extends Activity{

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        btn = (Button)findViewById(R.id.btn_test);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DiskLruCacheHelper diskLruCacheHelper = new DiskLruCacheHelper();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}

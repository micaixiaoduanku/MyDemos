package views.autotextview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.huangli.mydemos.R;

import java.util.ArrayList;

/**
 * Created by huangli on 16/4/13.
 */
public class AutoTextActivity extends Activity{
    private Button startBtn;
    private AutoTextView autoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autotext);
        startBtn = (Button)findViewById(R.id.btn_start);
        autoTextView = (AutoTextView)findViewById(R.id.auto_tv);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> strs = new ArrayList<>();
                strs.add("text 1 ");
                strs.add("text 2 ");
                strs.add("text 3 ");
                autoTextView.startAutoScroll(strs);
            }
        });
    }

}

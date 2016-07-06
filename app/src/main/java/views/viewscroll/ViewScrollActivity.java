package views.viewscroll;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.huangli.mydemos.R;

/**
 * Created by huangli on 16/5/10.
 */
public class ViewScrollActivity extends Activity{
    private TextView mytv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewscroll);
        mytv = (TextView)findViewById(R.id.mytext);
        mytv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mytv.scrollTo(0,-100);
            }
        });
    }
}

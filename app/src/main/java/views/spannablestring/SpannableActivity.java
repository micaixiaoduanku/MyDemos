package views.spannablestring;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.example.huangli.mydemos.R;

/**
 * Created by huangli on 16/3/8.
 */
public class SpannableActivity extends Activity{
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spannable);
        tv = (TextView) findViewById(R.id.tv);
        String s="Hello World";
        SpannableString ss=  new SpannableString(s);
        ss.setSpan(new ForegroundColorSpan(Color.GREEN), 0, 5, 0);
        tv.setText(ss);
    }
}

package logic.switchlanguage;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.huangli.mydemos.R;

import java.util.Locale;

/**
 * Created by huangli on 16/5/27.
 */
public class SwitchLanguageActivity extends Activity{
    private Button btn_en,btn_cn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switchlanguage);
        btn_en = (Button)findViewById(R.id.btn_en);
        btn_cn = (Button)findViewById(R.id.btn_cn);
        btn_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchLanguage("en");
                finish();
                Intent intent = new Intent(SwitchLanguageActivity.this,SwitchLanguageActivity.class);
                startActivity(intent);
            }
        });
        btn_cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchLanguage("cn");
                finish();
                Intent intent = new Intent(SwitchLanguageActivity.this,SwitchLanguageActivity.class);
                startActivity(intent);
            }
        });
    }

    private void switchLanguage(String language){
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        if (language.equals("en")){
            config.locale = Locale.ENGLISH;
        }else{
            config.locale = Locale.SIMPLIFIED_CHINESE;
        }
        resources.updateConfiguration(config,dm);
    }
}

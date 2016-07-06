package logic.file;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.huangli.mydemos.R;

/**
 * Created by huangli on 16/5/4.
 */
public class FileTestActivity extends Activity{
    private Button createBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filetest);
        createBtn = (Button)findViewById(R.id.create_btn);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = Environment.getDataDirectory().toString();
                Log.i("file","getDataDirectory() "+path);
                path = Environment.getDownloadCacheDirectory().toString();
                Log.i("file","getDownloadCacheDirectory() "+path);
                path = Environment.getExternalStorageDirectory().toString();
                Log.i("file","getExternalStorageDirectory() "+path);
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).toString();
                Log.i("file","getExternalStoragePublicDirectory() "+path);
                path = Environment.getRootDirectory().toString();
                Log.i("file","getRootDirectory() "+path);
                FileUtils.getPicSDPath(FileTestActivity.this);
            }
        });
    }


}

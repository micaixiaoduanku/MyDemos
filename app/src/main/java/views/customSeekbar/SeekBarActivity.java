package views.customSeekbar;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.example.huangli.mydemos.R;

/**
 * Created by huangli on 16/3/15.
 */
public class SeekBarActivity extends Activity{
    private SeekBar seekBar;
    private Button btn;
    private boolean isenable = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);
        seekBar = (SeekBar)findViewById(R.id.memory_threshold_seekBar);
        btn = (Button)findViewById(R.id.btntest);
        setSeekBarEnable(seekBar, isenable);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isenable = !isenable;
                setSeekBarEnable(seekBar,isenable);
            }
        });
//        ColorDrawable colorDrawable = new ColorDrawable(0xff00bcb2);
//        seekBar.setProgressDrawable(colorDrawable);

    }

    public void setSeekBarEnable(SeekBar seekBar,boolean enable){
        seekBar.setEnabled(enable);
        if (enable){
            seekBar.setThumb(getResources().getDrawable(R.drawable.icon_seek_avilable));
//            ColorDrawable colorDrawable = new ColorDrawable(0xff00bcb2);
//            seekBar.setProgressDrawable(colorDrawable);
        }else {
            seekBar.setThumb(getResources().getDrawable(R.drawable.icon_seek_disabled));
//            ColorDrawable colorDrawable = new ColorDrawable(0xffdddddd);
//            seekBar.setProgressDrawable(colorDrawable);
        }
    }
}

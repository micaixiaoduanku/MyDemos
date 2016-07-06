package views.refreshlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.example.huangli.mydemos.R;

/**
 * Created by huangli on 16/5/18.
 */
public class HeaderRefreshView extends RelativeLayout{
    public HeaderRefreshView(Context context) {
        super(context);
        init(context);
    }

    public HeaderRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_refresh_header, this);
    }
}

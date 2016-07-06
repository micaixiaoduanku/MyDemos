package views;

import com.example.huangli.mydemos.BaseListActivity;

import views.animation.AnimationActivity;
import views.autoscroll.AutoScrollActivity;
import views.autotextview.AutoTextActivity;
import views.bitmap.BitmapActivity;
import views.constomview.CustomViewActivity;
import views.customSeekbar.SeekBarActivity;
import views.drawer.DrawerActivity;
import views.floatview.FloatViewActivity;
import views.refreshlayout.RefreshActivity;
import views.remoteViews.RemoteViewActivity;
import views.spannablestring.SpannableActivity;
import views.surfaceview.SurFaceTestActivity;
import views.translucentbar.TranslucentActivity;
import views.viewscroll.ViewScrollActivity;
import views.waveview.WaweViewActivity;
import views.whewview.WhewviewActivity;
import views.windowdialog.WindowDialogActivity;

/**
 * Created by huangli on 16/3/8.
 */
public class EntranceActivityView extends BaseListActivity{

    @Override
    public void initItems() {
        addListviewItem("沉浸式状态栏", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(TranslucentActivity.class);
            }
        });
        addListviewItem("Cache", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(BitmapActivity.class);
            }
        });
        addListviewItem("SurfaceView", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(SurFaceTestActivity.class);
            }
        });
        addListviewItem("波纹扩散效果", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(WhewviewActivity.class);
            }
        });
        addListviewItem("自定义view", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(CustomViewActivity.class);
            }
        });
        addListviewItem("动画效果", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(AnimationActivity.class);
            }
        });
        addListviewItem("下拉刷新", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(RefreshActivity.class);
            }
        });
        addListviewItem("RemoteView", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(RemoteViewActivity.class);
            }
        });
        addListviewItem("SpannableTextView", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(SpannableActivity.class);
            }
        });
        addListviewItem("FloatView", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(FloatViewActivity.class);
            }
        });
        addListviewItem("SeekBar", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(SeekBarActivity.class);
            }
        });
        addListviewItem("WaveView", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(WaweViewActivity.class);
            }
        });
        addListviewItem("Windowdialog", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(WindowDialogActivity.class);
            }
        });
        addListviewItem("AutoScroll", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(AutoScrollActivity.class);
            }
        });
        addListviewItem("AutoText", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(AutoTextActivity.class);
            }
        });
        addListviewItem("View滑动", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(ViewScrollActivity.class);
            }
        });
        addListviewItem("抽屉", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(DrawerActivity.class);
            }
        });
    }
}

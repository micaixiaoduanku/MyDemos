package bugs.duplicateid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.huangli.mydemos.R;

/**
 * Created by huangli on 16/3/1.
 * 问题描述:fragment在布局中嵌套fragment加载，父fragment视图销毁后,再次创建父fragment视图会出现嵌套的fragment duplicateid异常导致程序挂掉。
 * 问题原因:官方不提倡fragment在布局中嵌套fragment进行加载，如果这样做会导致两个fragment生命周期不相关，父fragment销毁，嵌套的fragment不会销毁掉，再次加载父fragment视图
 * 同时加载嵌套fragment就会就会出现duplicateid异常。（内部原理不太清楚，只能够理解到这）
 * 修改方法:在MapGpFragment中onDestroyView()中销毁掉MapFragment
 * 补充:关于有时候在MapGpFragment中onDestroyView()中销毁掉MapFragment会导致crash参考以下链接
 * http://www.androiddesignpatterns.com/2013/08/fragment-transaction-commit-state-loss.html
 * http://stackoverflow.com/questions/7469082/getting-exception-illegalstateexception-can-not-perform-this-action-after-onsa/10261438#10261438
 * 网上参考:
 * google搜索: Duplicate ID, tag null, or parent id with another fragment for com.google.android.gms.maps.MapFragment
 * 相关错误链接:http://stackoverflow.com/questions/14083950/duplicate-id-tag-null-or-parent-id-with-another-fragment-for-com-google-androi
 */
public class MainActivityDuplicateid extends AppCompatActivity {
    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duplicateid);
        ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);

        bar.addTab(bar
                .newTab()
                .setText("Map")
                .setTabListener(
                        new TabListener<MapGpFragment>(this, "map",
                                MapGpFragment.class)));
        bar.addTab(bar
                .newTab()
                .setText("Settings")
                .setTabListener(
                        new TabListener<SettingsFragment>(this, "settings",
                                SettingsFragment.class)));
        bar.addTab(bar
                .newTab()
                .setText("About")
                .setTabListener(
                        new TabListener<AboutFragment>(this, "about",
                                AboutFragment.class)));
        if (savedInstanceState != null) {
            bar.setSelectedNavigationItem(savedInstanceState.getInt("tab", 0));
        }
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public static class TabListener<T extends Fragment> implements
            ActionBar.TabListener {
        private final AppCompatActivity mActivity;
        private final String mTag;
        private final Class<T> mClass;
        private final Bundle mArgs;
        private Fragment mFragment;

        public TabListener(AppCompatActivity activity, String tag, Class<T> clz) {
            this(activity, tag, clz, null);
        }

        public TabListener(AppCompatActivity activity, String tag, Class<T> clz,
                           Bundle args) {
            mActivity = activity;
            mTag = tag;
            mClass = clz;
            mArgs = args;

            // Check to see if we already have a fragment for this tab,
            // probably from a previously saved state. If so, deactivate
            // it, because our initial state is that a tab isn't shown.
            mFragment =  mActivity.getSupportFragmentManager().findFragmentByTag(mTag);
            if (mFragment != null && !mFragment.isDetached()) {
                FragmentTransaction ft = mActivity.getSupportFragmentManager()
                        .beginTransaction();
                ft.detach(mFragment);
                ft.commit();
            }
        }

        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            if (mFragment == null) {
                mFragment = Fragment.instantiate(mActivity, mClass.getName(),
                        mArgs);
                ft.add(android.R.id.content, mFragment, mTag);
            } else {
                ft.attach(mFragment);
            }
        }

        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            if (mFragment != null) {
                ft.detach(mFragment);
            }
        }

        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
            Toast.makeText(mActivity, "Reselected!", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("tag", "activity onDestroy");
    }
}

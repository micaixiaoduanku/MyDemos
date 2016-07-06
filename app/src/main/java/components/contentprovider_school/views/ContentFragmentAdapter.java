package components.contentprovider_school.views;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangli on 16/3/4.
 */
public class ContentFragmentAdapter extends FragmentPagerAdapter {
    private final Context c;
    private List<Fragment> pages;
    private ViewPager pager;

    public ContentFragmentAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        c = context;
        pages = new ArrayList<>();
        pages.add(new FragmentStudents());
        pages.add(new FragmentTeachers());
    }

    public void removepage(int position){
        pages.remove(position);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public Fragment getItem(int position) {
        return pages.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "学生";
                break;
            case 1:
                title = "老师";
                break;
        }
        return title;
    }

    public ViewPager getPager() {
        return pager;
    }

    public void setPager(ViewPager pager) {
        this.pager = pager;
    }
}

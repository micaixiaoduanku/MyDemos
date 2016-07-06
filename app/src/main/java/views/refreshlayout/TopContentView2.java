package views.refreshlayout;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.huangli.mydemos.R;

/**
 * Created by huangli on 16/5/19.
 */
public class TopContentView2 extends RelativeLayout {

    private int[] imageResids = new int[]{R.drawable.banner1,R.drawable.banner2,R.drawable.banner3,R.drawable.banner4};
    private ViewPager viewPager;
    private MyPagerAdapter myPagerAdapter;

    public TopContentView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TopContentView2(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_top_content2, this);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        myPagerAdapter = new MyPagerAdapter();
        viewPager.setAdapter(myPagerAdapter);
    }

    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //这里存在问题,空了再看
//            super.destroyItem(container, position, object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_viewpager_item, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_content);
            imageView.setBackgroundResource(imageResids[position]);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return imageResids.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}

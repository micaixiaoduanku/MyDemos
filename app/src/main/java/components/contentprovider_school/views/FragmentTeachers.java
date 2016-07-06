package components.contentprovider_school.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.huangli.mydemos.R;

/**
 * Created by huangli on 16/3/4.
 */
public class FragmentTeachers extends Fragment{
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("tag","FragmentTeachers onCreateView");
        View view = inflater.inflate(R.layout.fragment_contentprovider_teachers, container, false);
        listView = (ListView)view.findViewById(R.id.listview);
        return view;
    }

    @Override
    public void onDestroyView() {
        Log.i("tag","FragmentTeachers onDestroyView");
        super.onDestroyView();
    }
}

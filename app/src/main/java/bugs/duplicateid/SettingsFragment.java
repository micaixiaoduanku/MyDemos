package bugs.duplicateid;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huangli.mydemos.R;

/**
 * Created by huangli on 16/2/29.
 */
public class SettingsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("tag", "SettingsFragment onCreateView");
        View view =  inflater.inflate(R.layout.fragment_duplicateid_setting, container, false);
        return view;
    }

    @Override
    public void onDestroyView() {
        Log.i("tag", "SettingsFragment onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.i("tag", "SettingsFragment onDestroy");
        super.onDestroy();
    }
}

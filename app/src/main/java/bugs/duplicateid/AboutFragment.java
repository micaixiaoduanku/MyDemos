package bugs.duplicateid;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huangli.mydemos.R;

/**
 * Created by huangli on 16/2/29.
 */
public class AboutFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("tag", "AboutFragment onCreateView");
        View view =  inflater.inflate(R.layout.fragment_duplicateid_about, container, false);
//        SettingsFragment settingsFragment = new SettingsFragment();
//        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
//        fragmentTransaction.add(R.id.maplayout,settingsFragment);
//        fragmentTransaction.commit();
        return view;
    }

    @Override
    public void onDestroyView() {
        Log.i("tag", "AboutFragment onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.i("tag", "AboutFragment onDestroy");
        super.onDestroy();
    }
}

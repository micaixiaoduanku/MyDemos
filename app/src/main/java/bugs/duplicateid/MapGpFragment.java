package bugs.duplicateid;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huangli.mydemos.R;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by huangli on 16/2/29.
 */
public class MapGpFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.i("tag", "MapGpFragment onCreateView");
        View view =  inflater.inflate(R.layout.fragment_duplicateid_map, container, false);
//        com.google.android.gms.maps.MapFragment mapFragment = new com.google.android.gms.maps.MapFragment();
//        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
//        fragmentTransaction.add(R.id.maplayout,mapFragment);
//        fragmentTransaction.commit();
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
//        outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onDestroyView() {
        Log.i("tag", "MapGpFragment onDestroyView");
        //销毁的时候SupportMapFragment不会自动移除,重复创建会导致crash,需要手动移除.
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapFragment);
        if (supportMapFragment != null){
            getChildFragmentManager().beginTransaction().remove(supportMapFragment).commitAllowingStateLoss();
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.i("tag", "MapGpFragment onDestroy");
        super.onDestroy();
    }
}

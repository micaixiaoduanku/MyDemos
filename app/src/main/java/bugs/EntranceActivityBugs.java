package bugs;

import android.os.Bundle;

import com.example.huangli.mydemos.BaseListActivity;

import bugs.duplicateid.MainActivityDuplicateid;
import bugs.resolution.ResolutionActivity;

/**
 * Created by huangli on 16/3/1.
 */
public class EntranceActivityBugs extends BaseListActivity {

    @Override
    public void initItems() {
        addListviewItem("多分辨率", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(ResolutionActivity.class);
            }
        });
        addListviewItem("DuplicateidBug", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(MainActivityDuplicateid.class);
            }
        });
    }
}

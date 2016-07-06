package components;

import com.example.huangli.mydemos.BaseListActivity;

import components.broadcast.BroadcastMainActivity;
import components.contentprovider_school.views.MainActivityStudents;
import components.notifaction.NotifactionActivity;

/**
 * Created by huangli on 16/3/2.
 */
public class EntranceActivityConponent extends BaseListActivity{

    @Override
    public void initItems() {
        addListviewItem("通知栏Demo", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(NotifactionActivity.class);
            }
        });
        addListviewItem("学生管理", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(MainActivityStudents.class);
            }
        });
        addListviewItem("Boardcast", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(BroadcastMainActivity.class);
            }
        });
    }
}

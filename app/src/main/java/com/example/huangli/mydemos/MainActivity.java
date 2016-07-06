package com.example.huangli.mydemos;

import bugs.EntranceActivityBugs;
import components.EntranceActivityConponent;
import logic.EntranceActivityLogic;
import views.EntranceActivityView;

/**
 * 该demo记录我自己经历过的demo和bug
 */
public class MainActivity extends BaseListActivity {


    @Override
    public void initItems() {
        addListviewItem("View", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(EntranceActivityView.class);
            }
        });
        addListviewItem("Logic", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(EntranceActivityLogic.class);
            }
        });
        addListviewItem("Bugs", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(EntranceActivityBugs.class);
            }
        });
        addListviewItem("Components", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(EntranceActivityConponent.class);
            }
        });
    }
}



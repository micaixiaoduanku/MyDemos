package logic;

import com.example.huangli.mydemos.BaseListActivity;

import logic.backphoto.BackPhotoActivity;
import logic.file.FileTestActivity;
import logic.firewall.FireWallActivity;
import logic.ipc.IPCActivityMain;
import logic.reflect.ReflectActivity;
import logic.social.SocialIntegratingActivity;
import logic.switchlanguage.SwitchLanguageActivity;
import logic.velocity.VelocityActivity;
import logic.wifi.WifiPointsActivity;

/**
 * Created by huangli on 16/3/14.
 */
public class EntranceActivityLogic extends BaseListActivity{

    @Override
    public void initItems() {
        addListviewItem("进程间通讯", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(IPCActivityMain.class);
            }
        });
        addListviewItem("语言切换", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(SwitchLanguageActivity.class);
            }
        });
        addListviewItem("反射", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(ReflectActivity.class);
            }
        });
        addListviewItem("拍照获取图片", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(BackPhotoActivity.class);
            }
        });
        addListviewItem("WifiPoints", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(WifiPointsActivity.class);
            }
        });
        addListviewItem("流量监控", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(WifiPointsActivity.class);
            }
        });
        addListviewItem("防火墙", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(FireWallActivity.class);
            }
        });
        addListviewItem("文件测试", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(FileTestActivity.class);
            }
        });
        addListviewItem("社交交互", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(SocialIntegratingActivity.class);
            }
        });
        addListviewItem("速度监听", new ItemClickListener() {
            @Override
            public void itemClicked() {
                intentActivity(VelocityActivity.class);
            }
        });
    }
}

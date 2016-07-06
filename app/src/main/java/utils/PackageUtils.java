package utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by huangli on 16/3/11.
 */
public class PackageUtils {
    public static final boolean isAppOnTop(Context app, String packageName) {
        boolean result = false;
        if (app != null && !TextUtils.isEmpty(packageName)) {
            if (PlatformUtils.isOverVersion(PlatformUtils.VERSION_CODES.LOLLIPOP)) {
                String strCurrentPkgName = getRunningAppPkgNameNew(app);
                if(strCurrentPkgName == null || strCurrentPkgName.isEmpty())
                {
                    strCurrentPkgName = getRunningAppPkgNamebyUsageStats(app);
                }
                if(strCurrentPkgName != null && !strCurrentPkgName.isEmpty())
                {
                    if (strCurrentPkgName.contains(packageName))
                    {
                        result = true;
                    }
                }
            } else {
                result = isAppOnTopBeforeLollipop(app, packageName);
            }
        }
        return result;
    }
    private static String getRunningAppPkgNameNew(Context context) {
        String  result = "";

        Field processStateField = null;
        try {
            Class<?> c = Class.forName("android.app.ActivityManager$RunningAppProcessInfo");
            processStateField = c.getDeclaredField("processState");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        if (processStateField != null) {
            try {
                ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
                List<ActivityManager.RunningAppProcessInfo> infos = am.getRunningAppProcesses();
                if (infos != null) {
                    for (ActivityManager.RunningAppProcessInfo info : infos) {
                        int state = processStateField.getInt(info);
                        if ((info.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) && (state == 2)) {
                            result = info.processName;
                        }
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @SuppressLint("NewApi")
    private static String getRunningAppPkgNamebyUsageStats(Context context) {
        String strRunningAppName = "";
        UsageStatsManager mUsageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        long endTime = System.currentTimeMillis();
        long beginTime = endTime - 1000 * 60*30;

        // We get usage stats for the last minute
        List<UsageStats> stats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, beginTime, endTime);

        // Sort the stats by the last time used
        if (stats != null) {
            SortedMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();
            for (UsageStats usageStats : stats) {
                mySortedMap.put(usageStats.getLastTimeUsed(), usageStats);
            }
            if (mySortedMap != null && !mySortedMap.isEmpty()) {
                strRunningAppName = mySortedMap.get(mySortedMap.lastKey()).getPackageName();
            }
        }

        return strRunningAppName;
    }

    private static boolean isAppOnTopBeforeLollipop(Context app, String packageName) {
        boolean result = false;
        try {
            ActivityManager manager = (ActivityManager) app.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> tasksInfo = manager.getRunningTasks(1);
            if (tasksInfo != null && tasksInfo.size() > 0) {
                result = tasksInfo.get(0).topActivity.getPackageName().contains(packageName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

package dongting.bwei.com.dongting1503d20170509;

import android.app.Application;

import org.xutils.x;

/**
 * 作者:${董婷}
 * 日期:2017/5/9
 * 描述:
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        UMShareAPI.get(this);
        com.umeng.socialize.Config.DEBUG = true;
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
    }
}

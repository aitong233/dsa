package debug;

import com.hyphenate.easeui.echart.EChartHelper;
import com.qpyy.libcommon.base.BaseApplication;
import com.qpyy.libcommon.http.RetrofitClient;
import com.qpyy.rtc.RtcConstants;
import com.qpyy.rtc.RtcManager;

import static com.qpyy.rtc.RtcConstants.SCENARIOSTYPE_GAME;

/**
 * 项目名称 qipao-android
 * 包名：com.debug
 * 创建人 王欧
 * 创建时间 2020/7/24 9:16 AM
 * 描述 describe
 */
public class RoomApplication extends BaseApplication {
    private static RoomApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
//        RetrofitClient.getInstance().login("18473492252","123456");
        EChartHelper.getInstance().init(this);
        RtcManager.instance(this).init(RtcConstants.RtcType_ZEGO, SCENARIOSTYPE_GAME,null);
    }

    public synchronized static RoomApplication getInstance() {
        if (null == instance) {
            instance = new RoomApplication();
        }
        return instance;
    }
}

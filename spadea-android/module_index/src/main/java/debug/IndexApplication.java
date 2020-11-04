package debug;


import android.text.TextUtils;

import com.qpyy.libcommon.base.BaseApplication;
import com.qpyy.libcommon.http.RetrofitClient;
import com.qpyy.libcommon.utils.SpUtils;

public class IndexApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        if (TextUtils.isEmpty(SpUtils.getToken())) {
            RetrofitClient.getInstance().login();
        }
    }
}

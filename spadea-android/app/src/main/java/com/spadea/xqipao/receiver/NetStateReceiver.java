package com.spadea.xqipao.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hyphenate.util.NetUtils;
import com.qpyy.room.event.NetEvent;
import org.greenrobot.eventbus.EventBus;


public class NetStateReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        NetUtils.Types networkTypes = NetUtils.getNetworkTypes(context);
        EventBus.getDefault().post(new NetEvent(networkTypes));
    }
}

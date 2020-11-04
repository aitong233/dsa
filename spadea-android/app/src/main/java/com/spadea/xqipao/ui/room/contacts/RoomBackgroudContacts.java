package com.spadea.xqipao.ui.room.contacts;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.RoomBgBean;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.room.contacts
 * 创建人 王欧
 * 创建时间 2020/4/14 4:26 PM
 * 描述 describe
 */
public class RoomBackgroudContacts {
    public interface View extends IView<FragmentActivity>{
        void setSuccess(String bgPicture);
        void backgroundList(List<RoomBgBean> list);
    }

    public interface RoomBackgroudPre extends IPresenter{
        void setBg(String roomId,String bgPicture);
        void getBackgroundList();
    }
}

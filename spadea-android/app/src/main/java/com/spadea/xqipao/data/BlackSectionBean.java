package com.spadea.xqipao.data;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.spadea.yuyin.ui.fragment2.setting.blacklist.BlackListBean;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data
 * 创建人 王欧
 * 创建时间 2020/4/2 9:19 AM
 * 描述 describe
 */
public class BlackSectionBean extends SectionEntity<BlackListBean> {

    public BlackSectionBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public BlackSectionBean(BlackListBean listBean) {
        super(listBean);
    }
}

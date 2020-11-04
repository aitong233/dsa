package com.spadea.xqipao.utils;

import android.text.TextUtils;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.HousePitBean;
import com.spadea.xqipao.data.HouseUserBean;
import com.spadea.xqipao.data.RoomExtraModel;
import com.spadea.xqipao.data.WheatModel;

import java.util.List;

public class RoomPlug {


    /**
     * 是否是房主
     *
     * @param wheatModel
     * @return
     */
    public static boolean isHost(WheatModel wheatModel) {
        if (wheatModel == null) {
            return false;
        } else {
            if (wheatModel.getList().getHouse_user().getUser_id().equals(MyApplication.getInstance().getUser().getUser_id())) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 是否是管理员
     *
     * @param roomExtraModel
     * @return
     */
    public static boolean isAdmini(RoomExtraModel roomExtraModel) {
        if (roomExtraModel == null) {
            return false;
        } else if (roomExtraModel.getManager() == 1) {
            return true;
        } else {
            return false;
        }
    }



    /**
     * 主持位是否有人
     *
     * @param wheatModel
     * @return
     */
    public static boolean isHostExistence(WheatModel wheatModel) {
        if (wheatModel == null) {
            return false;
        } else {
            for (HousePitBean housePitBean : wheatModel.getList().getHouse_pit()) {
                if (housePitBean.getPit_number().equals("9")) {
                    if (TextUtils.isEmpty(housePitBean.getUser_id()) || housePitBean.getUser_id().equals("0")) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 主持位是否有人
     *
     * @param
     * @return
     */
    public static boolean isHostExistence(List<HousePitBean> data) {
        if (data == null || data.size() == 0) {
            return false;
        } else {
            for (HousePitBean housePitBean : data) {
                if (housePitBean.getPit_number().equals("9")) {
                    if (TextUtils.isEmpty(housePitBean.getUser_id()) || housePitBean.getUser_id().equals("0")) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * 自己是否在主持位
     *
     * @param wheatModel
     * @return
     */
    public static boolean isStayHost(WheatModel wheatModel) {
        if (wheatModel == null) {
            return false;
        } else {
            for (HousePitBean housePitBean : wheatModel.getList().getHouse_pit()) {
                if (housePitBean.getPit_number().equals("9")) {
                    if (TextUtils.isEmpty(housePitBean.getUser_id()) || housePitBean.getUser_id().equals("0")) {
                        return false;
                    } else {
                        if (housePitBean.getUser_id().equals(MyApplication.getInstance().getUser().getUser_id())) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }


    /**
     * 判断自己是否在主持位
     *
     * @param data
     * @return
     */
    public static boolean isStayHost(List<HousePitBean> data) {
        if (data == null || data.size() == 0) {
            return false;
        }
        for (HousePitBean housePitBean : data) {
            if (housePitBean.getPit_number().equals("9")) {
                if (TextUtils.isEmpty(housePitBean.getUser_id()) || housePitBean.getUser_id().equals("0")) {
                    return false;
                } else {
                    if (housePitBean.getUser_id().equals(MyApplication.getInstance().getUser().getUser_id())) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }


    /**
     * 获取自己所在麦位信息
     *
     * @param wheatModel
     * @return
     */
    public static HousePitBean getPersonalData(WheatModel wheatModel) {
        if (wheatModel == null) {
            return null;
        } else {
            for (HousePitBean housePitBean : wheatModel.getList().getHouse_pit()) {
                if (MyApplication.getInstance().getUser().getUser_id().equals(housePitBean.getUser_id())) {
                    return housePitBean;
                }
            }
            return null;
        }
    }



    public static HousePitBean getPersonalData(List<HousePitBean> data) {
        if (data == null || data.size() == 0) {
            return null;
        } else {
            for (HousePitBean housePitBean : data) {
                if (MyApplication.getInstance().getUser().getUser_id().equals(housePitBean.getUser_id())) {
                    return housePitBean;
                }
            }
        }
        return null;
    }


    /**
     * 获取自己坐在麦位num
     *
     * @param wheatModel
     * @return
     */
    public static String wheatPosition(WheatModel wheatModel) {
        HousePitBean personalData = getPersonalData(wheatModel);
        if (personalData == null) {
            return "0";
        } else {
            return personalData.getPit_number();
        }
    }


    public static String getEmptyPitNumber(WheatModel wheatModel) {
        if (wheatModel == null) {
            return "0";
        }
        for (HousePitBean housePitBean : wheatModel.getList().getHouse_pit()) {
            if (housePitBean.getState().equals("2") && TextUtils.isEmpty(housePitBean.getUser_id()) && !housePitBean.getPit_number().equals("9")) {
                return housePitBean.getPit_number();
            }
        }
        return "0";
    }


    /**
     * 1.自由上麦 2.申请上麦
     *
     * @param wheatModel
     * @return
     */
    public static String isRowWheat(WheatModel wheatModel) {
        if (wheatModel == null) {
            return "0";
        }

        return wheatModel.getList().getHouse_user().getWheat();
    }


    /**
     * 判断自己是否在麦位上
     *
     * @param data
     * @return
     */
    public static boolean isStayWheat(List<HousePitBean> data) {
        if (data == null || data.size() == 0) {
            return false;
        }
        for (HousePitBean housePitBean : data) {
            if (!TextUtils.isEmpty(housePitBean.getUser_id()) && housePitBean.getUser_id().equals(MyApplication.getInstance().getUser().getUser_id())) {
                return true;
            }
        }
        return false;
    }


    /**
     * 是否是房主
     *
     * @param data
     * @return
     */
    public static boolean isHouseOwner(HouseUserBean data) {
        if (data == null) {
            return false;
        }
        if (data.getUser_id().equals(getUserId())) {
            return true;
        } else {
            return false;
        }
    }


    public static String getUserId() {
        return MyApplication.getInstance().getUser().getUser_id();
    }


}

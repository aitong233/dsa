package com.spadea.xqipao.data;

import java.io.Serializable;
import java.util.List;

public class ListBean implements Serializable {

    private HouseUserBean house_user;
    private List<HousePitBean> house_pit;

    public HouseUserBean getHouse_user() {
        return house_user;
    }

    public void setHouse_user(HouseUserBean house_user) {
        this.house_user = house_user;
    }

    public List<HousePitBean> getHouse_pit() {
        return house_pit;
    }

    public void setHouse_pit(List<HousePitBean> house_pit) {
        this.house_pit = house_pit;
    }
}

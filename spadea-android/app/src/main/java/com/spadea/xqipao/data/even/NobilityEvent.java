package com.spadea.xqipao.data.even;

import com.spadea.xqipao.data.ApproachBean;

public class NobilityEvent {

    private ApproachBean approachBean;

    public NobilityEvent(ApproachBean approachBean) {
        this.approachBean = approachBean;
    }

    public ApproachBean getApproachBean() {
        return approachBean;
    }

    public void setApproachBean(ApproachBean approachBean) {
        this.approachBean = approachBean;
    }
}

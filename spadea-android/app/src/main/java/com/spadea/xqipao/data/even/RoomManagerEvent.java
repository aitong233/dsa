package com.spadea.xqipao.data.even;

public class RoomManagerEvent {


    private boolean isManager;

    public RoomManagerEvent(boolean isManager) {
        this.isManager = isManager;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }
}

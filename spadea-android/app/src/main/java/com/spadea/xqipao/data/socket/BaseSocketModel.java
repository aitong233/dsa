package com.spadea.xqipao.data.socket;

public class BaseSocketModel<T> {


    /**
     * type : 0
     * time : 1583746352196
     * message : 7f00000108fd00000020
     */

    private int type;
    private long time;
    private T message;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}

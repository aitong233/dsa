package com.spadea.yuyin.base;

import java.io.Serializable;

/**
 * Copyright (c) 1
 *
 * @Description
 * @Author 1
 * @Copyright Copyright (c) 1
 * @Date $date$ $time$
 */

public class BaseBean<T> implements Serializable {
    public int status;
    public String info;
    public T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
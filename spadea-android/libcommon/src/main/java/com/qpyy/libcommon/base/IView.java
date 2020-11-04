package com.qpyy.libcommon.base;

public interface IView<T> {
    T getSelfActivity();

    void showLoadings();

    void showLoadings(String content);

    void disLoadings();
}
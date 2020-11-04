package com.spadea.xqipao.ui.base.view;


public interface IView<T> {
    T getSelfActivity();
    void showLoadings();
    void disLoadings();
}

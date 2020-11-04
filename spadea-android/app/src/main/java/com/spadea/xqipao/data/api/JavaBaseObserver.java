package com.spadea.xqipao.data.api;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data.api
 * 创建人 王欧
 * 创建时间 2020/5/20 8:02 PM
 * 描述 describe
 */
public abstract class JavaBaseObserver<T> extends BaseObserver<T> {
    public JavaBaseObserver() {
    }

    public JavaBaseObserver(int... a) {
        super(a);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ApiNullException) {
            onNext(null);
            e.printStackTrace();
            onComplete();
            return;
        }
        super.onError(e);
    }
}

package com.qpyy.libcommon.http.transform;


import com.qpyy.libcommon.http.BaseModel;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;


public class BaseTransformer<T extends BaseModel>
        implements ObservableTransformer<T, T> {

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.compose(SchedulerTransformer.<T>create())
                .compose(new BaseErrorCheckerTransformer<T>());
    }
}
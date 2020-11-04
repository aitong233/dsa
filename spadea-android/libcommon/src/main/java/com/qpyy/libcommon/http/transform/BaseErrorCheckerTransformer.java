package com.qpyy.libcommon.http.transform;


import com.qpyy.libcommon.http.APIException;
import com.qpyy.libcommon.http.BaseModel;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

public class BaseErrorCheckerTransformer<T extends BaseModel>
        implements ObservableTransformer<T, T> {

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.map(new Function<T, T>() {
            @Override
            public T apply(T t) throws Exception {
                if (t != null) {
                    int code = t.getStatus();
                    String msg = t.getInfo();
                    if (code == 1) {
                        return t;
                    } else if (code == 0) {
                        throw new APIException(code, msg);
                    } else {
                        String message = ErrorMessage.get(code);
                        if (message != null) {
                            throw new APIException(code, message);
                        } else {
                            throw new APIException(code, t.getInfo());
                        }
                    }
                } else {
                    return null;
                }
            }
        });
    }
}

package com.spadea.xqipao.data.api.transform;


import com.spadea.xqipao.data.BaseModel;
import com.spadea.xqipao.data.api.APIException;
import com.spadea.xqipao.data.api.ErrorMessage;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;


public class ErrorCheckerTransformer<T extends BaseModel<R>, R>
        implements ObservableTransformer<T, R> {

    @Override
    public ObservableSource<R> apply(Observable<T> upstream) {
        return upstream.map(new Function<T, R>() {
            @Override
            public R apply(T t) throws Exception {
                if (t != null) {
                    int code = t.getStatus();
                    String msg = t.getInfo();
                    if (code == 1 || (t.isJava() && t.getCode() == 0)) {//请求成功
                        return t.getData();
                    } else if (code == 2014) {
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

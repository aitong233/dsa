package com.spadea.xqipao.data.api.transform;




import com.spadea.xqipao.data.BaseModel;
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
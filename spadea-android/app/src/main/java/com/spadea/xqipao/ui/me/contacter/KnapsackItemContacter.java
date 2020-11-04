package com.spadea.xqipao.ui.me.contacter;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.MyProductsModel;
import com.spadea.xqipao.data.UsingProductsModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class KnapsackItemContacter {


    public interface View extends IView<FragmentActivity> {
        void myProductsSuccess(List<MyProductsModel> data);

        void myUsingProductsSuccess(UsingProductsModel data);

        void useProductSuccess();

        void downProductSuccess();
    }

    public interface IKnapsackItemPre extends IPresenter {
        void myProducts(String categoryId);

        void myUsingProducts(String categoryId);

        void useProduct(String id);

        void downProduct(String id);
    }

}

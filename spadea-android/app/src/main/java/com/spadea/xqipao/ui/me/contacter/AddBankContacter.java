package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class AddBankContacter {


    public interface View extends IView<Activity> {
        void addBankSuccess();

        void getPhoneCodeSuccess();

        void editBankSuccess();
    }

    public interface IAddBankPre extends IPresenter {
        void addBank(String bankNum, String cardholder, String bankName, String mobile, String bankZhi, String cardNumber, String code);

        void editBank(String cardholder, String bank_name, String mobile, String card_number, String id, String bank_num, String bank_zhi, String code);

        void addZFB(String name, String id, String code);

        void getPhoneCode(String phoneNumber);
    }

}

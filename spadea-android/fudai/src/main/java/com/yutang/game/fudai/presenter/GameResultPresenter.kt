package com.yutang.game.fudai.presenter

import android.content.Context
import com.blankj.utilcode.util.ToastUtils
import com.qpyy.libcommon.base.BasePresenter
import com.qpyy.libcommon.http.BaseObserver
import com.qpyy.libcommon.utils.SpUtils
import com.yutang.game.fudai.bean.LuckGiftBean
import com.yutang.game.fudai.contacts.GameResultContacts
import com.yutang.game.fudai.net.ApiClient
import io.reactivex.disposables.Disposable

class GameResultPresenter(view: GameResultContacts.View, context: Context) : BasePresenter<GameResultContacts.View>(view, context), GameResultContacts.Presenter {
}
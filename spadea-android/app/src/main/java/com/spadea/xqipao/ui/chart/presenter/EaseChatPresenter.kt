package com.spadea.xqipao.ui.chart.presenter

import com.hjq.toast.ToastUtils
import com.hyphenate.chat.EMMessage
import com.qpyy.libcommon.http.BaseObserver
import com.qpyy.libcommon.http.RetrofitClient
import com.spadea.xqipao.ui.chart.contacts.EaseChatContacts
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class EaseChatPresenter(var view: EaseChatContacts.View?) : EaseChatContacts.Presenter {
    private val compositeDisposable = CompositeDisposable()

    fun sendTxtMessage(userId: String?, s: String?, content: String?, room_id: String?) {
        RetrofitClient.getInstance().sendTxtMessage(userId, s, content, room_id, object : BaseObserver<String?>() {
            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(s: String) {
                view?.sendTextMessage(content)
            }

            override fun onComplete() {}
        })
    }

    fun checkImage(userId: String?, imageUrl: String, imagePath: String) {
        RetrofitClient.getInstance().sendImageMessage(userId, imageUrl, object : BaseObserver<String?>() {
            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(s: String) {
                view?.sendCheckedImageMesage(imagePath)
            }

            override fun onComplete() {}

            override fun onError(e: Throwable) {
                super.onError(e)
                ToastUtils.show("发送失败")
            }
        })
    }

     fun checkVoice(userId: String?, voiceUrl: String, filePath: String, length: Int, toChatUsername: String) {
        RetrofitClient.getInstance().sendAudioMessage(userId, voiceUrl, object : BaseObserver<String?>() {
            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(s: String) {
                val message = EMMessage.createVoiceSendMessage(filePath, length, toChatUsername)
                view?.sendMessage(message)
            }

            override fun onComplete() {}
            override fun onError(e: Throwable) {
                super.onError(e)
                ToastUtils.show("发送失败")
            }
        })
    }

    fun clearCompositeDisposable(){
        compositeDisposable.clear()
    }
}
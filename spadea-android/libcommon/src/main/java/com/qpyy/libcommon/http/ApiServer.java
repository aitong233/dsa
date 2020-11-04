package com.qpyy.libcommon.http;

import com.qpyy.libcommon.bean.CheckImageResp;
import com.qpyy.libcommon.bean.CheckTxtResp;
import com.qpyy.libcommon.bean.EmChatUserInfo;
import com.qpyy.libcommon.bean.SignSwitchModel;
import com.qpyy.libcommon.bean.UserBean;
import com.qpyy.libcommon.constant.URLConstants;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServer {
    @FormUrlEncoded
    @POST(URLConstants.LOGIN)
    Observable<BaseModel<UserBean>> login(@Field("mobile") String mobile, @Field("password") String password, @Field("code") String code, @Field("type") int type);

    @FormUrlEncoded
    @POST(URLConstants.LOGIN)
    Observable<BaseModel<UserBean>> oauthLogin(@Field("netease_token") String netease_token, @Field("access_token") String access_token, @Field("type") int type);

    @FormUrlEncoded
    @POST(URLConstants.THIRD_PARTY_LOGIN)
    Observable<BaseModel<UserBean>> thirdPartyLogin(@Field("openid") String openid, @Field("three_party") int three_party, @Field("nickname") String nickname, @Field("head_pic") String head_pic);

    @FormUrlEncoded
    @POST(URLConstants.SEND_CODE)
    Observable<BaseModel<String>> sendCode(@Field("mobile") String mobile, @Field("type") int type);

    @POST(URLConstants.BALANCE)
    Observable<BaseModel<String>> getBalance();

    @FormUrlEncoded
    @POST(URLConstants.CHECKTXT)
    Observable<BaseModel<CheckTxtResp>> checkTxt(@Field("content") String content, @Field("type") String type);

    @FormUrlEncoded
    @POST(URLConstants.CHECKIMAGE)
    Observable<BaseModel<CheckImageResp>> checkImage(@Field("image") String image, @Field("type") String type);

    @POST(URLConstants.SIGN_SWITCH)
    Observable<BaseModel<SignSwitchModel>> signSwitch();

    @FormUrlEncoded
    @POST(URLConstants.FOLLOW)
    Observable<BaseModel<String>> follow(@Field("user_id") String userId, @Field("type") int type);

    @FormUrlEncoded
    @POST(URLConstants.GET_INFO_BY_EM_CHAT)
    Observable<BaseModel<EmChatUserInfo>> getInfoByEmChat(@Field("emchat_username") String userName);

    @FormUrlEncoded
    @POST(URLConstants.SENDCHATMSG)
    Observable<BaseModel<String>> sendTxtMessage(@Field("user_id") String user_id, @Field("type") String type, @Field("content") String content, @Field("room_id") String room_id);

    @FormUrlEncoded
    @POST(URLConstants.SEND_CHAT_PIC)
    Observable<BaseModel<String>> sendImgMessage(@Field("user_id") String user_id, @Field("image") String audio);

    @FormUrlEncoded
    @POST(URLConstants.SEND_CHAT_AUDIO)
    Observable<BaseModel<String>> sendAudioMessage(@Field("user_id") String user_id, @Field("audio") String image);

}

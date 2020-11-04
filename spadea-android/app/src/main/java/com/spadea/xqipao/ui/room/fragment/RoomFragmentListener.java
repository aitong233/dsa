package com.spadea.xqipao.ui.room.fragment;

public interface RoomFragmentListener {
    void sendMesg();

    void onEmoj();

    void applyWheat(String pitNumber);

    void applyWheatWait(String pitNumber);

    void downWheat();

    void muteAllRemoteAudioStreams(boolean b);

    void closedWheat();

    void showRoomWheat(String pitNumber);

    void showRoomMore();

    void getRoomUserInfo(String userId);

    void guardListClick();

    void hostListClick();

    void playingClick();




    void givingGifts(String userId);

    String getUserId();

    void sendGiftMessage(String userName, String giftId, String giftPic, String giftName, String giftPrice, String giftSpectial, String giftMum, String pits);

    void rowWheat();


//    void downUserWheat(String userName, String pitNumber);

    void shutUp(String type, String userName, String userId);

    void addRoomBanned(String userId, String userName);

    void cancelRoomBanned(String userId, String userName);




    void getOnWheat(String userId);


    void sendStartGame(String text);

    void sendOverGame(String text);

    void sendGameData(String content, String qiu1, String qiu2, String qiu3);


    void startQiuGame();


    void operationWheat();

    void addWheat(boolean isHostWheat, String pitNumber);

    void pitCountDown(String pitNumber,String time);

}

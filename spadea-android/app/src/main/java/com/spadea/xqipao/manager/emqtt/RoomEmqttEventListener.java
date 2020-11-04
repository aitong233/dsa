package com.spadea.xqipao.manager.emqtt;

import com.spadea.xqipao.data.ApproachBean;
import com.spadea.xqipao.data.FaceBean;
import com.spadea.xqipao.data.RoomPitBean;
import com.spadea.xqipao.data.socket.RoomGiftModel;
import com.spadea.xqipao.data.socket.WeekStarInModel;
import com.spadea.xqipao.utils.view.room.animation.ItemRoomGiftBean;

import java.util.List;

public interface RoomEmqttEventListener {

    void reconnectSuccess();


    void roomPopularity(String popularity);


    void roomApplyWheat(List<RoomPitBean> data);

    void roomDeleteCardiac(String pitNumber);

    void roomDeleteAllCardiac();

    void roomUserPit(String pitNumber);

    void roomBanned(String action);

    void roomShutup(String action,String pitNumber);

    void roomClosePit(String pitNumber, String action);

    void roomAddManager();

    void roomDeleteManager();

    void roomPassword(String action);

    void roomCardiacSwitch(String action);

    void roomMaiXu(String action);

    void roomUpWheat(String pit_number);

    void roomDownWheat(String pit_number);

    void roomApplyCount(String count);

    void roomKickOut(int action);

    void roomGetUserOnWheat();

    void roomFace(FaceBean message);

    void roomJueWeiIn(ApproachBean approachBean);

    void roomGofis(String msg);

    void roomGift(RoomGiftModel roomGiftModel);

    void roomGiftGive(ItemRoomGiftBean data);

    void roomGiftShow(String special);

    void roomContribution(String contribution);

    void roomCardiac(String pit_number, String rough_number);

    void roomName(String roomName);

    void roomPublicScreen();

    void roomUserShutup(String pit_number, String action);

    void roomCountDown(String pitNumber,String time);

    void setRoomBackground(String picture);

    void setRoomPlaying(String playing);

    void weekStarIn(WeekStarInModel weekStarInModel);

}

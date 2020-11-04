package com.yutang.game.tangguobao.bean

class GetRoomListEvent()

class GetGameRulerEvent()

class GetRoomConfigEvent()

class GetGameHistoryEvent()

data class SubscribeRoomTopicEvent(val room_id: Int)

data class UnSubscribeRoomTopicEvent(val room_id: Int)

data class SubscribeBigRoomTopicEvent(val subscribe: Boolean)

data class SubscribeBigRoomTopicStatusEvent(val isSuccess: Boolean)

data class CreateRoomEvent(val sugar_id: Int, val people_num: Int, val round_id: Int, val rule: Int)

data class JoinRoomEvent(val room_id: Int)

data class ReconnectEvent(val room_id: Int)

class EmqttServiceReconnectEvent()

data class InRoomEvent(val inRoom: Boolean)

data class OperateRoomEvent(val room_id: Int, val operate_type: Int)  //operate_type 1准备 2 取消 3 离开

data class OpenRedParperEvent(val room_id: Int)  //开红包

data class GetRoomGameLogEvent(val room_id: Int)  //获取房间游戏日志




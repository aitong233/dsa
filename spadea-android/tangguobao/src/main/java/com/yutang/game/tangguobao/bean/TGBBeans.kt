package com.yutang.game.tangguobao.bean

import java.io.Serializable

data class BaseReq(val user_id: Int, val token: String)

data class RspStatus(val code: Int, val msg: String)

//获取房间列表
data class GetRoomListReq(val base: BaseReq, val outside_room_id: Int)  //获取房间列表

data class GetRoomListRsp(val status: RspStatus, val room_info: List<RoomInfo>, val room_id: Int)  //获取房间列表

data class RoomInfo(val players: List<PlayerInfo>, val room_id: Int, val user_id: Int, val nickname: String,
                    val head_picture: String, val sugar_num: Int, val people_num: Int, val people_had_num: Int,
                    val round_num: Int, val rule: Int, var status: Int, val end_time: Int, val out_user_id: Int) : Serializable  //获取房间列表

data class PlayerInfo(val user_id: Int, val nickname: String, val head_picture: String, val user_code: Int, var status: Int, val cancel_time: Int, var sugar_num: Int, var is_open: Boolean, var send_sugar: Int)

//获取房间配置信息
data class RoomConfReq(val base: BaseReq)

data class RoomConfRsp(val status: RspStatus, val rounds: List<Round>, val sugars: List<Sugar>, val max_people: Int, val min_people: Int)

data class Round(val round_id: Int, val round_num: Int)

data class Sugar(val sugar_id: Int, val sugar_num: Int)

//创建房间
data class CreateRoomReq(val base: BaseReq, val outside_room_id: Int, val sugar_id: Int, val people_num: Int, val round_id: Int, val rule: Int)

data class CreateRoomRsp(val status: RspStatus, val room_id: Int)

//加入房间
data class JoinRoomReq(val base: BaseReq, val outside_room_id: Int, val room_id: Int)

data class JoinRoomRsp(val status: RspStatus, val room_id: Int, val room_if: RoomInfo)

//重连房间
data class ReconnRoomReq(val base: BaseReq, val room_id: Int)

data class ReconnRoomRsp(val status: RspStatus, val current_round: Int, val round_games: List<PlayerGameIf>, val room_if: RoomInfo, val end_time: Int)

data class PlayerGameIf(val user_id: Int, val sugar_num: Int, val is_open: Boolean, val send_sugar: Int)

//大房间广播
data class BroadcastRoomRsp(val status: RspStatus, val room_if: RoomInfo)

//小房间广播
data class BroadcastGameRsp(val status: RspStatus, val current_round: Int, val round_games: List<PlayerGameIf>, val user_results: ArrayList<UserResult>, val end_time: Int, val room_if: RoomInfo, val user_id: Int)

data class UserResult(val user_id: Int, val head_picture: String, val get_suger_num: Int, val max_count: Int, val is_best: Boolean, val send_count: Int) : Serializable

//获取游戏规则
data class GameRuleReq(val base: BaseReq)

data class GameRuleRsp(val title: String, val context: String)

//操作
data class OperateRoomReq(val base: BaseReq, val outside_room_id: Int, val room_id: Int, val operate_type: Int)

data class OperateRoomRsp(val status: RspStatus)

data class Test(var showDetail: Boolean = false)

//开红包
data class OpenRedParperReq(val base: BaseReq, val room_id: Int)

data class OpenRedParperRsp(val status: RspStatus)

data class GetGameHistoryReq(val base: BaseReq)

data class GetGameHistoryRsp(val status: RspStatus, val game_logs: List<GameLog>)

data class GetRoomGameLogReq(val base: BaseReq, val room_id: Int)

data class GetRoomGameLogRsp(val status: RspStatus, val room_log: RoomGameLog?)

data class GameLog(val round_num: Int, val money: Int, val send_sugar_num: Int, val get_sugar_num: Int, val create_at: Long, val room_log: RoomGameLog, var showDetail: Boolean = false)

data class RoomGameLog(val room_id: Int, val room_game_round: List<RoomGameRound>)

data class RoomGameRound(val round_id: Int, val player_ifs: List<PlayerRedPaper>)

data class PlayerRedPaper(val user_id: Int, val nickname: String, val head_picture: String, val is_send: Boolean, val sugar_num: Int, val is_best_luckly: Boolean)

data class UserResultList(val user_results: ArrayList<UserResult>) : Serializable
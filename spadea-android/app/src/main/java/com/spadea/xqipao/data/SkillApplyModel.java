package com.spadea.xqipao.data;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data
 * 创建人 王欧
 * 创建时间 2020/5/24 2:48 PM
 * 描述 describe
 */
public class SkillApplyModel {

    /**
     * userId : 1
     * skillId : 3
     * skillGroup : 2
     * gameNickname : 娜美
     * gameLevel : 最强王者
     * voiceExample : 语音实例地址
     * applyPicture:图片
     * remark : 接单说明
     */

    private String userId;
    private int skillId;
    private int skillGroup;
    private String gameNickname;
    private String gameLevel;
    private String voiceExample;
    private String remark;
    private String applyPicture;
    private String reason;
    private int status;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApplyPicture() {
        return applyPicture;
    }

    public void setApplyPicture(String applyPicture) {
        this.applyPicture = applyPicture;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public int getSkillGroup() {
        return skillGroup;
    }

    public void setSkillGroup(int skillGroup) {
        this.skillGroup = skillGroup;
    }

    public String getGameNickname() {
        return gameNickname;
    }

    public void setGameNickname(String gameNickname) {
        this.gameNickname = gameNickname;
    }

    public String getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(String gameLevel) {
        this.gameLevel = gameLevel;
    }

    public String getVoiceExample() {
        return voiceExample;
    }

    public void setVoiceExample(String voiceExample) {
        this.voiceExample = voiceExample;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

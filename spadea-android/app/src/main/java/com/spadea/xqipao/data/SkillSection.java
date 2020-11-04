package com.spadea.xqipao.data;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data
 * 创建人 王欧
 * 创建时间 2020/5/22 3:33 PM
 * 描述 describe
 */
public class SkillSection {
    private int groupId;
    private String groupPicture;
    private String groupName;
    private List<Item> skillResultVOs;


    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupPicture() {
        return groupPicture;
    }

    public void setGroupPicture(String groupPicture) {
        this.groupPicture = groupPicture;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Item> getSkillResultVOs() {
        return skillResultVOs;
    }

    public void setSkillResultVOs(List<Item> skillResultVOs) {
        this.skillResultVOs = skillResultVOs;
    }

    public static class Item {
        private int id;
        private String skillName;
        private String skillPicture;
        private int groupId;
        private int status; //0未通过 1通过 2审核中  3未申请
        private String examPicture;
        private String skillRemark;
        private String skilledNotice;
        private String voiceRemark;
        private int isMust;

        public int getIsMust() {
            return isMust;
        }

        public void setIsMust(int isMust) {
            this.isMust = isMust;
        }

        public String getSkillRemark() {
            return skillRemark;
        }

        public void setSkillRemark(String skillRemark) {
            this.skillRemark = skillRemark;
        }

        public String getSkilledNotice() {
            return skilledNotice;
        }

        public void setSkilledNotice(String skilledNotice) {
            this.skilledNotice = skilledNotice;
        }

        public String getVoiceRemark() {
            return voiceRemark;
        }

        public void setVoiceRemark(String voiceRemark) {
            this.voiceRemark = voiceRemark;
        }

        public String getExamPicture() {
            return examPicture;
        }

        public void setExamPicture(String examPicture) {
            this.examPicture = examPicture;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSkillName() {
            return skillName;
        }

        public void setSkillName(String skillName) {
            this.skillName = skillName;
        }

        public String getSkillPicture() {
            return skillPicture;
        }

        public void setSkillPicture(String skillPicture) {
            this.skillPicture = skillPicture;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }
    }
}

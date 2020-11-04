package com.spadea.xqipao.data;

public class AppUpdateModel {
    /**
     * {
     * "VersionCode":112,
     * "VersionName":"1.5.0",
     * "ModifyContent":"1、修复已知问题",
     * "DownloadUrl":"https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/app/yutangyy.apk"
     * }
     */

    private int VersionCode;
    private String VersionName;
    private String ModifyContent;
    private String DownloadUrl;
    private int ForceUpdate;


    public int getForceUpdate() {
        return ForceUpdate;
    }

    public void setForceUpdate(int forceUpdate) {
        ForceUpdate = forceUpdate;
    }

    public int getVersionCode() {
        return VersionCode;
    }

    public void setVersionCode(int versionCode) {
        VersionCode = versionCode;
    }

    public String getVersionName() {
        return VersionName;
    }

    public void setVersionName(String versionName) {
        VersionName = versionName;
    }

    public String getModifyContent() {
        return ModifyContent;
    }

    public void setModifyContent(String modifyContent) {
        ModifyContent = modifyContent;
    }

    public String getDownloadUrl() {
        return DownloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        DownloadUrl = downloadUrl;
    }
}

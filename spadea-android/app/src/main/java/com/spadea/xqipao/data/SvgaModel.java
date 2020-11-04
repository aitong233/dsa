package com.spadea.xqipao.data;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data
 * 创建人 王欧
 * 创建时间 2020/5/11 5:27 PM
 * 描述 describe
 */
public class SvgaModel {
    public static final int TYPE_GIFT = 0;
    public static final int TYPE_JUE = 1;
    public static final int TYPE_JUE_AND_APPROACH = 2;
    public static final int TYPE_APPROACH = 3;
    public String url;
    public int type;
    public String approachUrl;
    public String userName;
    public String nobilityName;

    public SvgaModel() {
    }

    public SvgaModel(String url) {
        this.type=TYPE_GIFT;
        this.url = url;
    }

    public SvgaModel(String url, int type) {
        this.url = url;
        this.type = type;
    }

    public SvgaModel(String url, int type, String approachUrl, String userName,String nobilityName) {
        this.url = url;
        this.type = type;
        this.approachUrl = approachUrl;
        this.userName = userName;
        this.nobilityName=nobilityName;
    }
}

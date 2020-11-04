package com.spadea.xqipao.ui.room.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.spadea.xqipao.data.GameInfo;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.room.contacts.GameContactrs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GamePresenter extends BasePresenter<GameContactrs.View> implements GameContactrs.IGamePre {

    private final static String data = "[\n" +
            "{\n" +
            "\"color\":\"a\",\n" +
            "\"num\":1\n" +
            "},{\n" +
            "\"color\":\"b\",\n" +
            "\"num\":1\n" +
            "},{\n" +
            "\"color\":\"c\",\n" +
            "\"num\":1\n" +
            "},\n" +
            "{\n" +
            "\"color\":\"a\",\n" +
            "\"num\":2\n" +
            "},{\n" +
            "\"color\":\"b\",\n" +
            "\"num\":2\n" +
            "},{\n" +
            "\"color\":\"c\",\n" +
            "\"num\":2\n" +
            "},{\n" +
            "\"color\":\"a\",\n" +
            "\"num\":3\n" +
            "},{\n" +
            "\"color\":\"b\",\n" +
            "\"num\":3\n" +
            "},{\n" +
            "\"color\":\"c\",\n" +
            "\"num\":3\n" +
            "},{\n" +
            "\"color\":\"a\",\n" +
            "\"num\":4\n" +
            "},{\n" +
            "\"color\":\"b\",\n" +
            "\"num\":4\n" +
            "},{\n" +
            "\"color\":\"c\",\n" +
            "\"num\":4\n" +
            "},{\n" +
            "\"color\":\"a\",\n" +
            "\"num\":5\n" +
            "},{\n" +
            "\"color\":\"b\",\n" +
            "\"num\":5\n" +
            "},{\n" +
            "\"color\":\"c\",\n" +
            "\"num\":5\n" +
            "},{\n" +
            "\"color\":\"a\",\n" +
            "\"num\":6\n" +
            "},{\n" +
            "\"color\":\"b\",\n" +
            "\"num\":6\n" +
            "},{\n" +
            "\"color\":\"c\",\n" +
            "\"num\":6\n" +
            "},{\n" +
            "\"color\":\"a\",\n" +
            "\"num\":7\n" +
            "},{\n" +
            "\"color\":\"b\",\n" +
            "\"num\":7\n" +
            "},{\n" +
            "\"color\":\"c\",\n" +
            "\"num\":7\n" +
            "},{\n" +
            "\"color\":\"a\",\n" +
            "\"num\":8\n" +
            "},{\n" +
            "\"color\":\"b\",\n" +
            "\"num\":8\n" +
            "},{\n" +
            "\"color\":\"c\",\n" +
            "\"num\":8\n" +
            "},{\n" +
            "\"color\":\"a\",\n" +
            "\"num\":9\n" +
            "},{\n" +
            "\"color\":\"b\",\n" +
            "\"num\":9\n" +
            "},{\n" +
            "\"color\":\"c\",\n" +
            "\"num\":9\n" +
            "}\n" +
            "]";


    private GameInfo gameInfo1;
    private GameInfo gameInfo2;
    private GameInfo gameInfo3;
    private List<GameInfo> dataList = new ArrayList<>();

    public GamePresenter(GameContactrs.View view, Context context) {
        super(view, context);
        dataList = JSON.parseArray(data, GameInfo.class);
    }


    @Override
    public void start() {
        Collections.shuffle(dataList);
        List<GameInfo> data = new ArrayList<>();
        data.addAll(dataList);
        int i1 = new Random().nextInt(data.size());
        gameInfo1 = data.get(i1);
        data.remove(i1);
        int i2 = new Random().nextInt(data.size());
        gameInfo2 = data.get(i2);
        data.remove(i2);
        int i3 = new Random().nextInt(data.size());
        gameInfo3 = data.get(i3);
        data.remove(i3);
        MvpRef.get().setData(gameInfo1, gameInfo2, gameInfo3);
    }
}

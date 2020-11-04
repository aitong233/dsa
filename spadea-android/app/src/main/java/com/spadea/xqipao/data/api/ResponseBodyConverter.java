package com.spadea.xqipao.data.api;


import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.spadea.xqipao.utils.GsonUtils;
import com.spadea.xqipao.data.BaseModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.annotations.NonNull;
import okhttp3.ResponseBody;
import retrofit2.Converter;

public class ResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    ResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(@NonNull ResponseBody value) throws IOException {
        String json = value.string();
        boolean hasCode = false;
        try {
            JSONObject jsonObject = new JSONObject(json);
            hasCode = jsonObject.has("code");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        BaseModel obj = GsonUtils.GsonToBean(json, BaseModel.class);
        obj.setJava(hasCode);
        if (hasCode && obj.getCode() != 0) {
            throw new APIException(obj.getCode(), true, obj.getMsg());
        }
        if (!hasCode && obj.getStatus() != 1) {
            throw new APIException(obj.getStatus(), obj.getInfo());
        }
        if (hasCode && obj.getData() == null) {
            throw new ApiNullException();
        }
        value.close();
        T t = adapter.fromJson(json);
        if (t instanceof BaseModel) {
            ((BaseModel) t).setJava(hasCode);
        }
        return t;
    }

}

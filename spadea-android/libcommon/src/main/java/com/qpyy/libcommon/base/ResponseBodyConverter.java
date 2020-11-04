package com.qpyy.libcommon.base;


import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.qpyy.libcommon.http.APIException;
import com.qpyy.libcommon.http.BaseModel;
import com.qpyy.libcommon.utils.GsonUtils;

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
        BaseModel obj = GsonUtils.GsonToBean(json, BaseModel.class);
        if (obj.getStatus() != 1) {
            throw new APIException(obj.getStatus(), obj.getInfo());
        }
        value.close();
        return adapter.fromJson(json);
    }

}

package com.qpyy.room.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExclusiveEmojiResp    {

    private String auth;
    private String nobility_id;
    private String id;
    private String name;
    private String picture;
    private String special;
    private String sort;
}

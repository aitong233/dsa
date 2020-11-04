package com.qpyy.libcommon.event;

import lombok.Data;

@Data
public class PublicScreenEvent {
    private String room_id;
    private int status;
}

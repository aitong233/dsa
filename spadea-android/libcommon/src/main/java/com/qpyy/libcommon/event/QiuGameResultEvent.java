package com.qpyy.libcommon.event;

import lombok.Data;

@Data
public class QiuGameResultEvent {
    private String room_id;
    private String pit_number;
    private String first;
    private String second;
    private String third;
}

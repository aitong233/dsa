package com.qpyy.libcommon.event;

import lombok.Data;

@Data
public class QiuGameStartEvent {
    private String room_id;
    private String pit_number;
}

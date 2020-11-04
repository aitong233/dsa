package com.qpyy.libcommon.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RoomFaceEvent {
    private String room_id;
    private String pit_number;
    private String special;
}

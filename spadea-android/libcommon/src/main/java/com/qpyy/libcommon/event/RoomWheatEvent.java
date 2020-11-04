package com.qpyy.libcommon.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomWheatEvent {

    private String roomId;
    private boolean isFree; //true 自由  false 排麦

}

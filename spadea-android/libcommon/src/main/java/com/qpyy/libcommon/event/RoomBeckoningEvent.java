package com.qpyy.libcommon.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomBeckoningEvent {

    private String roomId;
    private boolean isOpen;

}

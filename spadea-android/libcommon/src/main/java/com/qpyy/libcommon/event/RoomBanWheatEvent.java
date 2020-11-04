package com.qpyy.libcommon.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomBanWheatEvent {

    private String roomId;
    private String pit_number;
    private boolean isBanWheat;

}

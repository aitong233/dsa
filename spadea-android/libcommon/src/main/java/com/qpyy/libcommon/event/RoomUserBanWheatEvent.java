package com.qpyy.libcommon.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomUserBanWheatEvent {

    private String roomId;
    private String userId;
    private boolean isBanWheat;

}

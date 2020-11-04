package com.qpyy.libcommon.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomChatEvent {
    private String userId;
    private String nickName;
    private String avatar;
}

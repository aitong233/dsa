package com.qpyy.libcommon.event;

import com.qpyy.libcommon.bean.RoomGiftModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomGiftEvent {

    private String roomId;
    private RoomGiftModel.ListBean gift;

}

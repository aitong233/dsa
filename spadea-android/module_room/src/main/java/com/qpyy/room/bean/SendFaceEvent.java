package com.qpyy.room.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendFaceEvent {
    private String face_pic;
    private String face_special;
    private String room_id;
    private String face_id;
    private String pitNumber;
    private int type;
}

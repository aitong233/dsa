package com.spadea.xqipao.widget.wheat;

public interface BaseWheatOnClickListener {
    void wheatAdd(boolean isHostWheat,String pitNumber);

    void wheatLock(boolean isHostWheat,String pitNumber);

    void wheatHeadPicture(boolean isHostWheat,String userId);
}

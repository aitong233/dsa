package com.qpyy.module.index.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

public class RecordSection extends SectionEntity<String> {

    public RecordSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public RecordSection(String name) {
        super(name);
    }
}

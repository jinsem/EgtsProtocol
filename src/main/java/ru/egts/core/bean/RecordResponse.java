package ru.egts.core.bean;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RecordResponse implements ParsedObject {
    private int confirmedRecordNumber;
    private byte recordStatus;
}

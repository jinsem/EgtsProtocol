package ru.egts.core.bean;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SubRecord implements ParsedObject {
    private int subrecordType;
    private int subrecordLength;

    private SubRecordData subRecordData;
}

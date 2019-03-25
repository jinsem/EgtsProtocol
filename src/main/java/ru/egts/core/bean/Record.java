package ru.egts.core.bean;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class Record implements ParsedObject {

    private boolean persisted;

    private int recordLength;
    private int recordNumber;
    private boolean ssod;
    private boolean rsod;
    private byte[] rpp;
    private boolean tmfe;
    private boolean evfe;
    private boolean obfe;

    private long objectId;
    private long eventId;
    private LocalDateTime time;
    private int sourceServiceType;
    private int recipientServiceType;
    private SubRecord subrecord;
    private int size;

}

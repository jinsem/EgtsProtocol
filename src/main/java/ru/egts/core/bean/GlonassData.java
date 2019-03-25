package ru.egts.core.bean;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.Collections;

@Builder
@Data
public class GlonassData implements ParsedObject {
    private HeaderInfo headerInfo;

    private boolean prefix;
    private boolean rte;
    private boolean ena;
    private boolean cmp;
    private boolean pr;

    private int peerAddress;
    private int recipientAddress;
    private int timeToLive;
    private int headerCheckSum;
    @Builder.Default
    private Collection<Record> records = Collections.emptyList();
    private short servicesFrameDataCheckSum;
}

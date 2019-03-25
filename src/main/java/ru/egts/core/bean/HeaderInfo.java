package ru.egts.core.bean;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HeaderInfo implements ParsedObject {
    private int protocolVersion;
    private int securityKeyId;
    private int headerLength;
    private int dataLength;

    private int headerEncoding;

    private int packetIdentifier;

    private int packetType;
}

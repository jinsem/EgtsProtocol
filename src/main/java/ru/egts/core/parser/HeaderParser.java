package ru.egts.core.parser;

import ru.egts.core.bean.HeaderInfo;

public class HeaderParser implements Parser<HeaderInfo, byte[]> {

    @Override
    public HeaderInfo parse(int start, byte[] data) {

        return HeaderInfo.builder()
                .protocolVersion(unsignedByte(data[0]))
                .securityKeyId(unsignedByte(data[1]))
                .headerLength(unsignedByte(data[3]))
                .headerEncoding(unsignedByte(data[4]))
                .dataLength(makeIntFromShort(5, data))
                .packetIdentifier(makeIntFromShort(7, data))
                .packetType(unsignedByte(data[9]))
                .build();
    }
}

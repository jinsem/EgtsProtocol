package ru.egts.core.parser;


import ru.egts.core.bean.InitialHeader;

import java.nio.ByteBuffer;

public class InitialHeaderParser implements Parser<InitialHeader, byte[]> {
    @Override
    public InitialHeader parse(int start, byte[] data) {
        int messageLength;

        int glonassDataSize = makeIntFromShort(5, data);
        int glonassHeaderSize = unsignedByte(ByteBuffer.wrap(data, 3, 1).get());
        messageLength = glonassHeaderSize + glonassDataSize + (glonassDataSize > 0 ? 2 : 0);
        if (messageLength < 0) {
            throw new IllegalArgumentException("Length message:"
                    + messageLength
                    + " is negative");
        }
        if (glonassHeaderSize != 11 && glonassHeaderSize != 16) {
            throw new IllegalArgumentException("Length header:"
                    + glonassHeaderSize
                    + " is incorrect");
        }
        return InitialHeader.builder()
                .length(messageLength)
                .build();
    }
}

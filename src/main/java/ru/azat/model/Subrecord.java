package ru.azat.model;

import io.netty.buffer.ByteBuf;

public class Subrecord {
    private int SRT;
    private int SRL;
    private ByteBuf SRD;

    public Subrecord(ByteBuf byteBuf){
        SRT = byteBuf.readUnsignedByte();
        SRL = byteBuf.readUnsignedShortLE();
        SRD = byteBuf.readBytes(SRL);
    }
}

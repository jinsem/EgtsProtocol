package ru.azat.model.subrecord;

import io.netty.buffer.ByteBuf;

public interface Subrecord {
    ByteBuf toByteBuf();
    void fromByteBuf(ByteBuf byteBuf);
    String toHex();
    void fromHex(String hex);
}

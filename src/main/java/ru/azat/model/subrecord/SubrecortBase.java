package ru.azat.model.subrecord;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import ru.azat.model.DataConverter;

public abstract class SubrecortBase implements Subrecord{

    @Override
    public String toHex() {
        ByteBuf byteBuf = toByteBuf();
        byte[] bytes = new byte[byteBuf.readableBytes()];
        int readerIndex = byteBuf.readerIndex();
        byteBuf.getBytes(readerIndex, bytes);
        return DataConverter.printHex(bytes);
    }

    @Override
    public void fromHex(String hex) {
        fromByteBuf(Unpooled.wrappedBuffer(DataConverter.parseHex(hex)));
    }
}

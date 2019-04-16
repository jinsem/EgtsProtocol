package ru.azat.model.subrecord.teledata;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.azat.model.subrecord.SubrecortBase;

/**
 * Подзапись применяется телематической платформой
 * для информирования АС о результатах процедуры аутентификации АС
 */
@Getter
@Setter
@Builder
public class SrResultCode extends SubrecortBase {
    private byte RCD;

    @Override
    public ByteBuf toByteBuf() {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeByte(RCD);
        return byteBuf;
    }

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        RCD = byteBuf.readByte();
    }
}

package ru.azat.model.subrecord.teledata;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.*;
import ru.azat.model.subrecord.SubrecortBase;

/**
 * Применяется абонентским терминалом для передачи на
 * аппаратно-программный комплекс данных о состоянии одного аналогового входа
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SrAbsAnSensData extends SubrecortBase {

    private byte ASN;
    private ByteBuf ASV;

    @Override
    public ByteBuf toByteBuf() {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeByte(ASN);
        byteBuf.writeBytes(ASV);
        return byteBuf;
    }

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        ASN = byteBuf.readByte();
        ASV = byteBuf.readBytes(3);
    }
}

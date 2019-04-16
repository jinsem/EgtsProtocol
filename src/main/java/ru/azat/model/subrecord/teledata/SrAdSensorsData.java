package ru.azat.model.subrecord.teledata;

import io.netty.buffer.ByteBuf;
import lombok.*;
import ru.azat.model.subrecord.SubrecortBase;
import ru.azat.utils.EgtsDecoderUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SrAdSensorsData extends SubrecortBase {
    private byte DIOE;
    private byte DOUT;
    private byte ASFE;
    private byte ADIO1;
    private byte ADIO2;
    private byte ADIO3;
    private byte ADIO4;
    private byte ADIO5;
    private byte ADIO6;
    private byte ADIO7;
    private byte ADIO8;
    private ByteBuf ANS1;
    private ByteBuf ANS2;
    private ByteBuf ANS3;
    private ByteBuf ANS4;
    private ByteBuf ANS5;
    private ByteBuf ANS6;
    private ByteBuf ANS7;
    private ByteBuf ANS8;

    @Override
    public ByteBuf toByteBuf() {
        throw new NotImplementedException();
    }

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        DIOE = byteBuf.readByte();
        boolean[] DIOE_FLAGS = EgtsDecoderUtils.byteToBooleanList(DIOE);
        DOUT = byteBuf.readByte();
        ASFE = byteBuf.readByte();
        boolean[] ASFE_FLAGS = EgtsDecoderUtils.byteToBooleanList(ASFE);
        if (DIOE_FLAGS[0]) ADIO1 = byteBuf.readByte();
        if (DIOE_FLAGS[1]) ADIO2 = byteBuf.readByte();
        if (DIOE_FLAGS[2]) ADIO3 = byteBuf.readByte();
        if (DIOE_FLAGS[3]) ADIO4 = byteBuf.readByte();
        if (DIOE_FLAGS[4]) ADIO5 = byteBuf.readByte();
        if (DIOE_FLAGS[5]) ADIO6 = byteBuf.readByte();
        if (DIOE_FLAGS[6]) ADIO7 = byteBuf.readByte();
        if (DIOE_FLAGS[7]) ADIO8 = byteBuf.readByte();

        if (ASFE_FLAGS[0]) ANS1 = byteBuf.readBytes(3);
        if (ASFE_FLAGS[1]) ANS2 = byteBuf.readBytes(3);
        if (ASFE_FLAGS[2]) ANS3 = byteBuf.readBytes(3);
        if (ASFE_FLAGS[3]) ANS4 = byteBuf.readBytes(3);
        if (ASFE_FLAGS[4]) ANS5 = byteBuf.readBytes(3);
        if (ASFE_FLAGS[5]) ANS6 = byteBuf.readBytes(3);
        if (ASFE_FLAGS[6]) ANS7 = byteBuf.readBytes(3);
        if (ASFE_FLAGS[7]) ANS8 = byteBuf.readBytes(3);
    }
}

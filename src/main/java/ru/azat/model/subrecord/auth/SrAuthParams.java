package ru.azat.model.subrecord.auth;

import io.netty.buffer.ByteBuf;
import lombok.*;
import ru.azat.model.subrecord.SubrecortBase;
import ru.azat.utils.EgtsDecoderUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SrAuthParams extends SubrecortBase {
    private int PKL;
    private ByteBuf PBK;
    private int ISL;
    private int MSZ;
    private ArrayList<Byte> SS;
    private ArrayList<Byte> EXP;

    @Override
    public ByteBuf toByteBuf() {
        throw new NotImplementedException();
    }

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        boolean[] flags = EgtsDecoderUtils.byteToBooleanList(byteBuf.readByte());
        if (flags[2]) {
            PKL = byteBuf.readUnsignedShortLE();
            PBK = byteBuf.readBytes(PKL);
        }
        if (flags[3]) {
            ISL  = byteBuf.readUnsignedShortLE();
        }
        if (flags[4]) {
            MSZ = byteBuf.readUnsignedShortLE();
        }
        if (flags[5]) {
            SS = new ArrayList<>();
            byte b;
            while ((b = byteBuf.readByte()) != 0) {
                SS.add(b);
            }
        }
        if (flags[6]) {
            EXP = new ArrayList<>();
            byte b;
            while ((b = byteBuf.readByte()) != 0) {
                EXP.add(b);
            }
        }
    }
}

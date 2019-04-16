package ru.azat.model.subrecord.auth;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.*;
import ru.azat.model.subrecord.SubrecortBase;
import ru.azat.utils.EgtsDecoderUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SrTermIdentity extends SubrecortBase {

    private long TID;
    private Integer HDID;
    private ByteBuf IMEI;
    private ByteBuf IMSI;
    private ByteBuf LNGC;
    private ByteBuf NID;
    private Integer BS;
    private ByteBuf MSISDN;
    private boolean SSRA;

    @Override
    public ByteBuf toByteBuf() {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeIntLE((int)TID);
        boolean[] flags = new boolean[]{
                HDID != null,
                IMEI != null,
                IMSI != null,
                LNGC != null,
                SSRA,
                NID != null,
                BS != null,
                MSISDN != null
        };
        if (flags[0]) {
            byteBuf.writeShortLE(HDID);
        }
        if (flags[1]) {
            byteBuf.writeBytes(IMEI);
        }
        if (flags[2]) {
            byteBuf.writeBytes(IMSI);
        }
        if (flags[3]) {
            byteBuf.writeBytes(LNGC);
        }

        if (flags[5]) {
            byteBuf.writeBytes(NID);
        }
        if (flags[6]) {
            byteBuf.writeShortLE(BS);
        }
        if (flags[7]) {
            byteBuf.writeBytes(MSISDN);
        }
        return byteBuf;
    }

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        TID = byteBuf.readUnsignedIntLE();
        boolean[] flags = EgtsDecoderUtils.byteToBooleanList(byteBuf.readByte());
        SSRA = flags[4];
        if (flags[0]) {
            HDID = byteBuf.readUnsignedShortLE();
        }
        if (flags[1]) {
            IMEI = byteBuf.readBytes(15);
        }
        if (flags[2]) {
            IMSI = byteBuf.readBytes(16);
        }
        if (flags[3]) {
            LNGC = byteBuf.readBytes(3);
        }

        if (flags[5]) {
            NID = byteBuf.readBytes(3);
        }
        if (flags[6]) {
            BS = byteBuf.readUnsignedShortLE();
        }
        if (flags[7]) {
            MSISDN = byteBuf.readBytes(15);
        }
    }
}

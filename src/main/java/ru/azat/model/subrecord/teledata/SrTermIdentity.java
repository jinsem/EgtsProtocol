package ru.azat.model.subrecord.teledata;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.azat.model.subrecord.SubrecortBase;
import ru.azat.utils.EgtsDecoderUtils;

/**
 * Подзапись используется АС при запросе авторизации на
 * телематическую платформу и содержит учетные данные АС
 */
@Getter
@Setter
@Builder
public class SrTermIdentity extends SubrecortBase {
    private long TID;
    @Setter(AccessLevel.NONE)
    private boolean[] flags;
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
        flags = new boolean[]{
                HDID != null,
                IMEI != null,
                IMSI != null,
                LNGC != null,
                SSRA,
                NID != null,
                BS != null,
                MSISDN != null};
        byteBuf.writeByte(EgtsDecoderUtils.booleanListToByte(flags));

        if(HDID != null) {
            byteBuf.writeShortLE(HDID);
        }
        if(IMEI != null) {
            byteBuf.writeBytes(IMEI);
        }
        if(IMSI != null){
            byteBuf.writeBytes(IMSI);
        }
        if(LNGC != null){
            byteBuf.writeBytes(LNGC);
        }
        if(NID != null){
            byteBuf.writeBytes(NID);
        }
        if(BS != null){
            byteBuf.writeShortLE(BS);
        }
        if(MSISDN != null) {
            byteBuf.writeBytes(MSISDN);
        }
        return byteBuf;
    }

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        TID = byteBuf.readUnsignedIntLE();

        flags = EgtsDecoderUtils.byteToBooleanList(byteBuf.readByte());

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
        SSRA = flags[4];
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

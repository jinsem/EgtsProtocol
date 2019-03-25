package ru.azat.model;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Getter;
import ru.azat.utils.EgtsDecoderUtils;

@Getter
public class Package {

    private String hexString;
    private ByteBuf byteBuf;
    private Frame frame;
    //Нужна ли обратная машрутизация и есть ли PRA, RCA, TTL
    private boolean RTE = false;
    //Длина Frame
    private int FDL = 0;
    //Длина заголовка
    private int HL = 0;
    //Тип пакета
    private int PT = 0;

    public Package(String hexString) {
        byteBuf = Unpooled.wrappedBuffer(DataConverter.parseHex(hexString));
    }

    public void decode() {
        ByteBuf buf = byteBuf;
        byte PRV = buf.readByte();
        byte SKID = buf.readByte();
        byte PRF_RTE_ENA_CMP_PR = buf.readByte();
        byte HL = buf.readByte();
        byte HE = buf.readByte();
        int FDL = buf.readUnsignedShortLE();
        int PID = buf.readUnsignedShortLE();
        int PT = buf.readByte();

        if((EgtsDecoderUtils.byteToBooleanList(PRF_RTE_ENA_CMP_PR)[5])){
            int PRA = buf.readUnsignedShortLE();
            int RCA = buf.readUnsignedShortLE();
            byte TTL = buf.readByte();
        }

        byte HCS = buf.readByte();

        frame = new Frame(buf.readBytes(FDL), PT);

        int SFRCS = buf.readUnsignedShortLE();

    }


}

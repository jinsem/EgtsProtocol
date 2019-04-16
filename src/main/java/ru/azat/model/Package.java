package ru.azat.model;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Getter;
import ru.azat.utils.EgtsDecoderUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Package {

    private String hexString;
    private ByteBuf byteBuf;
    private List<Record> recordList = new ArrayList<>();

    //Нужна ли обратная машрутизация и есть ли PRA, RCA, TTL
    private boolean RTE = false;
    //Длина Frame
    private int FDL = 0;
    //Длина заголовка
    private int HL = 0;
    //Тип пакета
    private int PT = 0;
    private byte PRV;
    private byte SKID;
    private byte HE;
    private int PID;
    private int PRA;
    private int RCA;
    private byte TTL;
    private byte HCS;
    private int SFRCS;

    public Package(String hexString) {
        byteBuf = Unpooled.wrappedBuffer(DataConverter.parseHex(hexString));
    }

    public void decode() {
        ByteBuf buf = byteBuf;
        PRV = buf.readByte();
        SKID = buf.readByte();
        byte PRF_RTE_ENA_CMP_PR = buf.readByte();
        HL = buf.readByte();
        HE = buf.readByte();
        FDL = buf.readUnsignedShortLE();
        PID = buf.readUnsignedShortLE();
        PT = buf.readByte();
        boolean[] flags = EgtsDecoderUtils.byteToBooleanList(PRF_RTE_ENA_CMP_PR);
        if(flags[5]){
            PRA = buf.readUnsignedShortLE();
            RCA = buf.readUnsignedShortLE();
            TTL = buf.readByte();
        }

        HCS = buf.readByte();

        ByteBuf frame = buf.readBytes(FDL);

        if(PT == EgtsEnum.PACKAGES.EGTS_РТ_APPDATA.getValue()){
            while (frame.readableBytes() > 0){
                Record record = new Record();
                record.decode(frame);
                recordList.add(record);
            }
        }else {
            throw new NotImplementedException();
        }

        SFRCS = buf.readUnsignedShortLE();
    }

    public ByteBuf encode(){
        List<ByteBuf> records = recordList.stream().map(Record::encode).collect(Collectors.toList());
        int length = records.stream().mapToInt(ByteBuf::readableBytes).sum();

        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeByte(PRV);
        byteBuf.writeByte(SKID);
        boolean[] flasgs = new boolean[]{true, false, false, false, false, RTE, false, false};
        byteBuf.writeByte(EgtsDecoderUtils.booleanListToByte(flasgs));
        byteBuf.writeByte(HL);
        byteBuf.writeByte(HE);
        byteBuf.writeShortLE(length);
        byteBuf.writeShortLE(PID);
        byteBuf.writeByte(PT);
        if(RTE){
            byteBuf.writeShortLE(PRA);
            byteBuf.writeShortLE(RCA);
            byteBuf.writeByte(TTL);
        }
        byteBuf.writeByte(HCS);
        records.forEach(byteBuf::writeBytes);
        byteBuf.writeShortLE(SFRCS);

        return byteBuf;
    }


}

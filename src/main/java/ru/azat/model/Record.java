package ru.azat.model;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Getter;
import lombok.Setter;
import ru.azat.model.subrecord.Subrecord;
import ru.azat.utils.EgtsDecoderUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Record {
    private List<Subrecord> subrecordList = new ArrayList<>();
    private int RL;
    //Тип
    private int SST;
    private int RN;
    private byte RFL;
    private short RST;
    private Long OID;
    private Long EVID;
    private Long TM;
    private boolean RSOD;
    private boolean SSOD;

    public void decode(ByteBuf byteBuf){
        RL = byteBuf.readUnsignedShortLE();
        RN = byteBuf.readUnsignedShortLE();

        RFL = byteBuf.readByte();
        boolean[] RFLBooleans = EgtsDecoderUtils.byteToBooleanList(RFL);
        SSOD = RFLBooleans[7];
        RSOD = RFLBooleans[6];
        int RPP = RFL & 0x00111000;//TODO доделать
        boolean TMFE = RFLBooleans[2];
        boolean EVFE = RFLBooleans[1];
        boolean OBFE = RFLBooleans[0];

        if (OBFE) {
            OID = byteBuf.readUnsignedIntLE();
        }
        if (EVFE) {
            EVID = byteBuf.readUnsignedIntLE();
        }
        if (TMFE) {
            TM = byteBuf.readUnsignedIntLE();
        }

        SST = byteBuf.readByte();
        RST = byteBuf.readByte();

        ByteBuf RD = byteBuf.readBytes(RL);

        while (RD.readableBytes() > 0){
            Subrecord subrecord = SubrecordParser.parse(RD, SST);
            subrecordList.add(subrecord);
        }
    }

    public ByteBuf encode(){
        List<ByteBuf> subrecords = subrecordList.stream()
                .map(Subrecord::toByteBuf)
                .collect(Collectors.toList());
        int subrecordsLength = subrecords.stream().mapToInt(ByteBuf::readableBytes).sum();


        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeShortLE(subrecordsLength);
        byteBuf.writeShortLE(RN);
        boolean[] RFLBooleans = new boolean[]{OID != null, EVID != null, TM != null, true, false, false, RSOD, SSOD };
        if(OID != null){
            byteBuf.writeIntLE(OID.intValue());
        }
        if(EVID != null){
            byteBuf.writeIntLE(EVID.intValue());
        }
        if(TM != null){
            byteBuf.writeIntLE(TM.intValue());
        }
        byteBuf.writeByte(SST);
        byteBuf.writeByte(RST);


        subrecords.forEach(byteBuf::writeBytes);
        return byteBuf;
    }

}

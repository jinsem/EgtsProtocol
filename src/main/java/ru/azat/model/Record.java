package ru.azat.model;

import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;
import ru.azat.utils.EgtsDecoderUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Record {
    private List<Subrecord> subrecordList = new ArrayList<>();
    private int RL;

    public Record(ByteBuf byteBuf) {

        RL = byteBuf.readUnsignedShortLE();
        int RN = byteBuf.readUnsignedShortLE();

        byte RFL = byteBuf.readByte();
        boolean[] RFLBooleans = EgtsDecoderUtils.byteToBooleanList(RFL);
        boolean SSOD = RFLBooleans[7];
        boolean RSOD = RFLBooleans[6];
        int RPP = RFL & 0x00111000;//TODO доделать
        boolean TMFE = RFLBooleans[2];
        boolean EVFE = RFLBooleans[1];
        boolean OBFE = RFLBooleans[0];

        if (OBFE) {
            long OID = byteBuf.readUnsignedIntLE();
        }
        if (EVFE) {
            long EVID = byteBuf.readUnsignedIntLE();
        }
        if (TMFE) {
            long TM = byteBuf.readUnsignedIntLE();
        }

        int SST = byteBuf.readUnsignedByte();
        int RST = byteBuf.readUnsignedByte();

        ByteBuf RD = byteBuf.readBytes(RL);

        while (RD.readableBytes() > 0){
            Subrecord subrecord = new Subrecord(RD);
            subrecordList.add(subrecord);
        }
    }

}

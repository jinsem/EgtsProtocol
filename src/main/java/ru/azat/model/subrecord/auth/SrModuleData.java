package ru.azat.model.subrecord.auth;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
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
public class SrModuleData extends SubrecortBase {

    private int MT;
    private long VID;
    private int FWV;
    private int SWV;
    private byte MD;
    private byte ST;

    @Override
    public ByteBuf toByteBuf() {
       throw new NotImplementedException();
    }

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        MT = byteBuf.readUnsignedShortLE();
        VID = byteBuf.readUnsignedIntLE();
        FWV = byteBuf.readUnsignedShortLE();
        SWV = byteBuf.readUnsignedShortLE();
        MD = byteBuf.readByte();
        ST = byteBuf.readByte();
        //TODO проверить
        List<Byte> SRN = new ArrayList<>();
        byte b;
        while ((b = byteBuf.readByte()) != 0) {
            SRN.add(b);
        }

        //TODO проверить
        List<Byte> DSCR = new ArrayList<>();
        while ((b = byteBuf.readByte()) != 0) {
            DSCR.add(b);
        }
    }

}

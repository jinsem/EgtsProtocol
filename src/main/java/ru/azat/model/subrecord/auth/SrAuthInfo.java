package ru.azat.model.subrecord.auth;

import io.netty.buffer.ByteBuf;
import lombok.*;
import ru.azat.model.subrecord.SubrecortBase;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class SrAuthInfo extends SubrecortBase {

    @Override
    public ByteBuf toByteBuf() {
        throw new NotImplementedException();
    }

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        List<Byte> UNM = new ArrayList<>();
        byte b;
        while ((b = byteBuf.readByte()) != 0) {
            UNM.add(b);
        }

        List<Byte> UPSW = new ArrayList<>();
        while ((b = byteBuf.readByte()) != 0) {
            UPSW.add(b);
        }

        if (byteBuf.readableBytes() > 0) {
            List<Byte> SS = new ArrayList<>();
            while ((b = byteBuf.readByte()) != 0) {
                UPSW.add(b);
            }
        }
    }
}

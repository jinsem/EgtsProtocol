package ru.azat.model.subrecord.auth;

import io.netty.buffer.ByteBuf;
import lombok.*;
import ru.azat.model.subrecord.SubrecortBase;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SrServiceInfo extends SubrecortBase {

    @Override
    public ByteBuf toByteBuf() {
        throw new NotImplementedException();
    }

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        byte ST = byteBuf.readByte();
        byte SST = byteBuf.readByte();
        byte SRVP = byteBuf.readByte();
    }
}

package ru.azat.model.subrecord.auth;

import io.netty.buffer.ByteBuf;
import lombok.*;
import ru.azat.model.subrecord.SubrecortBase;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SrVehicleData extends SubrecortBase {

    private ByteBuf VIN;
    private long VHT;
    private long VPST;

    @Override
    public ByteBuf toByteBuf() {
        throw new NotImplementedException();
    }

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        VIN = byteBuf.readBytes(17);
        VHT = byteBuf.readUnsignedIntLE();
        VPST = byteBuf.readUnsignedIntLE();
    }
}

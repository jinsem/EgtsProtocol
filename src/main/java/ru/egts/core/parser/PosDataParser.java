package ru.egts.core.parser;


import ru.egts.core.bean.service.teledata.PosData;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

public class PosDataParser implements Parser<PosData, byte[]> {

    @Override
    public PosData parse(int start, byte[] data) {
        PosData.PosDataBuilder posDataBuilder = PosData.builder();
        posDataBuilder.navigationTime(
                START_DATE.plus(makeLongFromInt(start, data), ChronoUnit.SECONDS)
        );

        posDataBuilder.lat(BigDecimal.valueOf(makeLongFromInt(start + 4, data) * 90.0 / 4294967295L));
        posDataBuilder.lon(BigDecimal.valueOf(makeLongFromInt(start + 8, data) * 180.0 / 4294967295L));

        // FLG
        final byte flg = data[start + 12];
        posDataBuilder.vld((flg & 0x01) == (byte) 0x01);
        posDataBuilder.fix(((flg >>> 1) & 0x01) == (byte) 0x01);
        posDataBuilder.cs(((flg >>> 2) & 0x01) == (byte) 0x01);
        posDataBuilder.bb(((flg >>> 3) & 0x01) == (byte) 0x01);
        posDataBuilder.mv(((flg >>> 4) & 0x01) == (byte) 0x01);
        posDataBuilder.lahs(((flg >>> 5) & 0x01) == (byte) 0x01);
        posDataBuilder.lohs(((flg >>> 6) & 0x01) == (byte) 0x01);
        posDataBuilder.alth(((flg >>> 7) & 0x01) == (byte) 0x01);

        // only eight last Bits are a speed value
        int speed = makeIntFromShort(start + 13, data);
        /**
         * byte[] lat = new byte[] {(byte)data[start+13], (byte) (data[start + 14] & 0x3F), 0, 0};
         *  ByteBuffer.wrap(lat).order(ByteOrder.LITTLE_ENDIAN).getInt() ;
         */
        speed <<= 2;
        speed >>>= 2;
        posDataBuilder.speed(speed);

        posDataBuilder.direction(unsignedByte(data[start + 15]));

        return posDataBuilder.build();
    }

}

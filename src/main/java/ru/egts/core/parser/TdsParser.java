package ru.egts.core.parser;


import ru.egts.core.bean.service.teledata.Tds;

public class TdsParser implements Parser<Tds, byte[]> {
    @Override
    public Tds parse(int start, byte[] data) {
        final boolean tnde = ((byte) (data[start] >>> 7) & 0x01) > 0;

        final Tds.TdsBuilder tdsBuilder = Tds.builder()
                .tnde(tnde)
                .lohs(((byte) (data[start] >>> 6) & 0x01) > 0)
                .lahs(((byte) (data[start] >>> 5) & 0x01) > 0);
        if (tnde) {
            byte[] speed = new byte[]{data[start + 9], (byte) (data[start + 10] & (byte) 0x7F)};

            tdsBuilder.size(12)
                    .lat(makeLongFromInt(start + 1, data))
                    .lng(makeLongFromInt(start + 5, data))
                    .speed(makeIntFromShort(0, speed))
                    .direction(unsignedByte(data[start + 11]));
        } else {
            tdsBuilder.size(1);
        }
        return tdsBuilder.build();
    }
}

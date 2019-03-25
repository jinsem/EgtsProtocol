package ru.egts.core.parser;

import ru.egts.core.bean.Record;
import ru.egts.core.bean.SubRecord;

import java.time.temporal.ChronoUnit;

public class RecordParser implements Parser<Record, byte[]> {

    private final static SubRecordParser SUB_RECORD_PARSER = new SubRecordParser();

    @Override
    public Record parse(int start, byte[] data) {
        Record.RecordBuilder recordBuilder = Record.builder();
        final int recordLength = makeIntFromShort(start, data);
        recordBuilder.recordLength(recordLength);
        recordBuilder.recordNumber(makeIntFromShort(start + 2, data));

        final byte rfl = data[start + 4];
        boolean obfe = (rfl & 0x01) == (byte) 0x01;
        recordBuilder.obfe(obfe);
        boolean evfe = ((rfl >>> 1) & 0x01) == (byte) 0x01;
        recordBuilder.evfe(evfe);
        boolean tmfe = ((rfl >>> 2) & 0x01) == (byte) 0x01;
        recordBuilder.tmfe(tmfe);

        byte[] rpp = new byte[] {
                (byte) ((rfl >>> 5) & 0x01),
                (byte) ((rfl >>> 4) & 0x01),
                (byte) ((rfl >>> 3) & 0x01)
        };
        recordBuilder.rpp(rpp);

        boolean rsod = ((rfl >>> 6) & 0x01) == (byte) 0x01;
        recordBuilder.rsod(rsod);
        boolean ssod = ((rfl >>> 7) & 0x01) == (byte) 0x01;
        recordBuilder.ssod(ssod);

        int index = 5;
        if (obfe) {
            recordBuilder.objectId(makeLongFromInt(start + index, data));
            index += 4;
        }
        if (evfe) {
            recordBuilder.eventId(makeLongFromInt(start + index, data));
            index += 4;
        }
        if (tmfe) {
            recordBuilder.time(START_DATE.plus(makeLongFromInt(start + index, data), ChronoUnit.SECONDS));
            index += 4;
        }
        final int sourceServiceType = unsignedByte(data[start + index]);
        recordBuilder.sourceServiceType(sourceServiceType);
        recordBuilder.recipientServiceType(unsignedByte(data[start + index + 1]));

        final SubRecord subRecord = SUB_RECORD_PARSER.parse(start + index + 2, data);
        recordBuilder.subrecord(subRecord);
        recordBuilder.size(index + 2 + recordLength);
        return recordBuilder.build();
    }
}

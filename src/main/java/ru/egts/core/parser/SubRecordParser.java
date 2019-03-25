package ru.egts.core.parser;

import ru.egts.core.bean.SubRecord;
import ru.egts.core.strategy.SubrecordParsingStrategy;

public class SubRecordParser implements Parser<SubRecord, byte[]> {
    @Override
    public SubRecord parse(int start, byte[] data) {
        final SubRecord.SubRecordBuilder subRecordSubRecord = SubRecord.builder();
        final int subrecordType = unsignedByte(data[start]);
        subRecordSubRecord.subrecordType(subrecordType);
        subRecordSubRecord.subrecordLength(makeIntFromShort(start + 1, data));

        SubrecordParsingStrategy strategy = new SubrecordParsingStrategy(subrecordType);
        subRecordSubRecord.subRecordData(strategy.parse(start + 3, data));
        return subRecordSubRecord.build();
    }
}

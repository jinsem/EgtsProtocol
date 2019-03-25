package ru.egts.core.parser;

import lombok.extern.slf4j.Slf4j;
import ru.egts.core.bean.GlonassData;
import ru.egts.core.bean.HeaderInfo;
import ru.egts.core.bean.Record;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class GlonassDataParser implements Parser<GlonassData, byte[]> {
    private final static HeaderParser HEADER_PARSER = new HeaderParser();
    private final static RecordParser RECORD_PARSER = new RecordParser();

    @Override
    public GlonassData parse(int start, byte[] data) {
        GlonassData.GlonassDataBuilder builder = GlonassData.builder();

        final HeaderInfo headerInfo = HEADER_PARSER.parse(0, data);

        builder.headerInfo(headerInfo);

        if (headerInfo.getHeaderLength() == 16) {
            //in case the header length is 16 bytes
            builder.peerAddress(makeIntFromShort(10, data));
            builder.recipientAddress(makeIntFromShort(12, data));
            builder.timeToLive(unsignedByte(data[14]));
            builder.headerCheckSum(unsignedByte(data[15]));
        } else if (headerInfo.getHeaderLength() == (byte) 0x0B) {
            //in case the header length is 11 bytes
            builder.headerCheckSum(unsignedByte(data[10]));
        } else {
            String message = String.format("Header length is expected 11 or 16 bytes length but it is %d", headerInfo.getHeaderLength());
            log.error(message);
            throw new IllegalStateException(message);
        }

        final List<Record> records = new LinkedList<>();
        builder.records(records);

        final int dataLength = headerInfo.getDataLength();
        int recordsSize = 0;
        while (dataLength - recordsSize > 0) {
            final Record record = RECORD_PARSER.parse(headerInfo.getHeaderLength() + recordsSize, data);
            records.add(record);
            recordsSize += record.getSize();
        }

        return builder.build();
    }
}

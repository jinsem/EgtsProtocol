package ru.egts.core.parser;


import ru.egts.core.bean.service.teledata.Tds;
import ru.egts.core.bean.service.teledata.TrackData;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class TrackDataParser implements Parser<TrackData, byte[]> {

    private static final TdsParser TDS_PARSER = new TdsParser();

    @Override
    public TrackData parse(int start, byte[] data) {
        final int structureAmount = unsignedByte(data[start]);
        TrackData.TrackDataBuilder trackDataBuilder = TrackData.builder()
                .structureAmount(structureAmount)
                .absoluteTime(makeLongFromInt(start + 1, data));

        AtomicInteger index = new AtomicInteger(start + 5);

        List<Tds> tdsList = IntStream.range(0, structureAmount).mapToObj(i -> {
            final Tds tds = TDS_PARSER.parse(index.get(), data);
            index.addAndGet(tds.getSize());
            return tds;
        }).collect(toList());

        trackDataBuilder.tdss(tdsList);
        return trackDataBuilder.build();
    }
}

package ru.egts.core.strategy;


import lombok.extern.slf4j.Slf4j;
import ru.egts.core.bean.SubRecordData;
import ru.egts.core.parser.*;

import java.util.Base64;

@Slf4j
public class SubrecordParsingStrategy {
    private final Parser<? extends SubRecordData, byte[]> parser;

    public SubrecordParsingStrategy(int type) {
        switch (type) {
            case 1: this.parser = new PosDataParser(); break;

            case 16: this.parser = new PosDataParser(); break;
            case 17: this.parser = new ExtPosDataParser(); break;
            case 18: this.parser = new AdSensorsDataParser(); break;
            case 62: this.parser = new TrackDataParser(); break;
            default: this.parser = null;
        }
    }

    public SubRecordData parse(int start, byte[] data) {
        if (this.parser != null) {
            return this.parser.parse(start, data);
        } else {
            log.debug("Can't parse data array duo to a subrecord type is undefined, {}", Base64.getEncoder().encodeToString(data));
            return null;
        }
    }
}

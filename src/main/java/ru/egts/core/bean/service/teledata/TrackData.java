package ru.egts.core.bean.service.teledata;

import lombok.Builder;
import lombok.Data;
import ru.egts.core.bean.SubRecordData;

import java.util.Collection;
import java.util.Collections;

@Builder
@Data
public class TrackData implements SubRecordData {
    private int structureAmount;
    private long absoluteTime;

    @Builder.Default
    private Collection<Tds> tdss = Collections.emptyList();
}

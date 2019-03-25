package ru.egts.core.bean;

import lombok.Builder;
import lombok.Getter;

import java.util.Collection;
import java.util.Collections;

@Builder
@Getter
public class Response {
    private int responsePacketId;
    private byte processingResult;
    @Builder.Default
    private Collection<RecordResponse> records = Collections.emptyList();
}

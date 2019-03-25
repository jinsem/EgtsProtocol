package ru.egts.core.bean;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InitialHeader implements ParsedObject {
    private int length;
}

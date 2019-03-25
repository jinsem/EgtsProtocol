package ru.egts.core.bean.service.teledata;

import lombok.Builder;
import lombok.Data;
import ru.egts.core.bean.SubRecordData;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class PosData implements SubRecordData {
    private LocalDateTime navigationTime;
    private BigDecimal lat;
    private BigDecimal lon;

    private boolean alth;
    private boolean lohs;
    private boolean lahs;
    private boolean mv;
    private boolean bb;
    private boolean cs;
    private boolean fix;
    private boolean vld;

    private double speed;
    private int direction;
    private byte[] odometr;
    private byte digitalInputs;
    private int source;
    private byte[] altitude;
    private short sourceData;
}

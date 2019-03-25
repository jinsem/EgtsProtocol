package ru.egts.core.bean.service.teledata;

import lombok.Builder;
import lombok.Data;
import ru.egts.core.bean.ParsedObject;

@Builder
@Data
public class Tds implements ParsedObject {
    private boolean tnde;
    private boolean lohs;
    private boolean lahs;

    private long lat;
    private long lng;
    private int speed;
    private int direction;

    private int size;
}

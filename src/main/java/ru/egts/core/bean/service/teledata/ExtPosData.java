package ru.egts.core.bean.service.teledata;


import ru.egts.core.bean.SubRecordData;

public class ExtPosData implements SubRecordData {
    private boolean nsfe;
    private boolean sfe;
    private boolean pfe;
    private boolean hfe;
    private boolean vfe;
    private short verticalDilutionPrecision;
    private short horizontalDilutionPrecision;
    private short positionDilutionPrecision;
    private byte satellites;
    private short navigationSystem;
}

package ru.egts.core.bean.service.teledata;
import ru.egts.core.bean.SubRecordData;

public class AccelData  implements SubRecordData {
    private byte structureAmount;
    private int absoluteTime;
    private byte[] accelerometerDataStructure1;
    private byte[] accelerometerDataStructure2;
    private byte[] accelerometerDataStructure3;
}

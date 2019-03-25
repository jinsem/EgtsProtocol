package ru.egts.core.bean.service.teledata;
import ru.egts.core.bean.SubRecordData;

public class StateData implements SubRecordData {
    private byte state;
    private byte mainPowerSourceVoltage;
    private byte backUpBatteryVoltage;
    private byte internalBatteryVoltage;
    private boolean nms;
    private boolean ibu;
    private boolean bbu;
}

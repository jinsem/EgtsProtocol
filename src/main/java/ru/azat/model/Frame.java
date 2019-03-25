package ru.azat.model;

import io.netty.buffer.ByteBuf;
import lombok.Getter;
import ru.azat.utils.EgtsDecoderUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Frame {

    private ByteBuf egtsFrame;
    private int type;
    private List<Record> recordList = new ArrayList<>();

    public Frame(ByteBuf buf, int type){
        this.egtsFrame = buf;
        this.type = type;

        if(type == EgtsEnum.EGTS_РТ_APPDATA.getValue()){
            while (buf.readableBytes() > 0){
                Record record = new Record(buf);
                recordList.add(record);
            }
        }
    }
}

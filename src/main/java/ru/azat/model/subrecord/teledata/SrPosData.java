package ru.azat.model.subrecord.teledata;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.*;
import ru.azat.model.subrecord.SubrecortBase;
import ru.azat.utils.EgtsDecoderUtils;


/**
 * Используется абонентским терминалом при передаче основных данных определения местоположения
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SrPosData extends SubrecortBase {
    /**
     * время навигации (количество секунд с 00:00:00 01.01.2010 UTC)
     */
    private long NTM;
    /**
     * широта по модулю, градусы/90·0xFFFFFFFF и взята целая часть
     */
    private long LAT;
    /**
     * долгота по модулю, градусы / 180·0xFFFFFFFF и взята целая часть
     */
    private long LONG;
    /**
     * скорость в км/ч с дискретностью 0,1 км/ч  (используется 14 младших бит);
     */
    private int SPD;
    /**
     * направление движения. Определяется как угол в градусах, который отсчитывается по часовой стрелке
     * между северным направлением географического меридиана и направлением движения в точке измерения
     */
    private int DIR;
    /**
     * пройденное расстояние (пробег) в км, с дискретностью 0,1 км
     */
    private ByteBuf ODM;
    /**
     * битовые флаги, определяют состояние основных дискретных
     * входов 1 … 8 (если бит равен 1, то соответствующий вход активен, если 0, то неактивен).
     */
    private byte DIN;
    /**
     * определяет источник (событие), инициировавший посылку данной навигационной информации
     */
    private byte SRC;
    //В документации они являются наобязтельными и не описаны условия их использования
    //В другом документе их вовсе нет
//    /**
//     * количество видимых спутников
//     */
//    private byte SAT;
//    /**
//     *снижение точности в горизонтальной плоскости
//     */
//    private int HDOP;
//    /**
//     * значение количества топлива на ТС в литрах
//     */
//    private ByteBuf FLS;
//    /**
//     * значение расхода топлива на ТС в литрах
//     */
//    private ByteBuf FCS;
    /**
     * высота над уровнем моря, м
     */
    private ByteBuf ALT;
    //Не описывается в документации
//    /**
//     * данные, характеризующие источник (событие) из поля SRC
//     */
//    private int SRCD;
    /**
     * битовый флаг определяет наличие поля ALT в подзаписи(1 - поле ALT передается;0 - не передается;)
     */
    private boolean ALTE;
    /**
     * битовый флаг определяет полушарие долготы(1 - западная)
     */
    private boolean LOHS;
    /**
     * битовый флаг определяет полушарие долготы(1 - южная)
     */
    private boolean LAHS;
    /**
     * битовый флаг, признак движения(1 - движение)
     */
    private boolean MV;
    /**
     * битовый флаг, признак отправки данных из памяти(1 - данные из «черного ящика»)
     */
    private boolean BB;
    /**
     * битовое поле, тип используемой системы
     * 0 – система координат WGS-84;
     * 1 – система координат ПЗ-90.02;
     */
    private boolean CS;
    /**
     * битовое поле, тип определения координат(0 - 2D fix; 1 - 3D fix;)
     */
    private boolean FIX;
    /**
     * битовый флаг, признак «валидности» координатных данных
     * 1 - данные «валидны»;
     * 0 - «невалидные» данные;)
     */
    private boolean VLD;

    @Override
    public ByteBuf toByteBuf() {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeIntLE((int) NTM);
        byteBuf.writeIntLE((int) LAT);
        byteBuf.writeIntLE((int) LONG);
        boolean[] flags = new boolean[]{VLD, FIX, CS, BB, MV, LAHS, LOHS, ALT != null};
        byteBuf.writeByte(EgtsDecoderUtils.booleanListToInt(flags));
        byteBuf.writeBytes(EgtsDecoderUtils.getSpdAltsDir(SPD, ALTE, DIR));
        byteBuf.writeBytes(ODM);
        byteBuf.writeByte(DIN);
        byteBuf.writeByte(SRC);
        if(ALTE){
            byteBuf.writeBytes(ALT);
        }
        return byteBuf;
    }

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        NTM = byteBuf.readUnsignedIntLE();
        LAT = byteBuf.readUnsignedIntLE();
        LONG = byteBuf.readUnsignedIntLE();
        boolean[] flags = EgtsDecoderUtils.byteToBooleanList(byteBuf.readByte());
        ALTE = flags[7];
        LOHS = flags[6];
        LAHS = flags[5];
        MV = flags[4];
        BB = flags[3];
        CS = flags[2];
        FIX = flags[1];
        VLD = flags[0];
        byte SPD_first = byteBuf.readByte();
        byte SPD_second = byteBuf.readByte();

        boolean[] SPD_second_bytes = EgtsDecoderUtils.byteToBooleanList(SPD_second);

        SPD = EgtsDecoderUtils.getTeledataSpeed(SPD_first, SPD_second);
        DIR = EgtsDecoderUtils.getTeledataDIR(SPD_second, byteBuf.readByte());
        ODM = byteBuf.readBytes(3);
        DIN = byteBuf.readByte();
        SRC = byteBuf.readByte();

        if (ALTE) {
            ALT = byteBuf.readBytes(3);
        }
    }
}

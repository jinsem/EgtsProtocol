package ru.azat.model.subrecord.common;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.*;
import ru.azat.model.subrecord.SubrecortBase;

/**
 * Подзапись применяется для осуществления подтверждения процесса обработки записи протокола уровня поддержки услуг.
 * Данный тип подзаписи должен поддерживаться всеми сервисами
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SrRecordResponse extends SubrecortBase {

    /**
     * номер подтверждаемой записи (значение поля RN из обрабатываемой записи)
     */
    private int CRN;
    /**
     * статус обработки записи. Коды результатов обработки приведены в приложении В.
     */
    private byte RST;

    @Override
    public ByteBuf toByteBuf() {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeShortLE(CRN);
        byteBuf.writeByte(RST);
        return byteBuf;
    }

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        CRN = byteBuf.readUnsignedShortLE();
        RST = byteBuf.readByte();
    }
}
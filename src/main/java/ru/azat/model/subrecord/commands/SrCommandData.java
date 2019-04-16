package ru.azat.model.subrecord.commands;

import io.netty.buffer.ByteBuf;
import lombok.*;
import ru.azat.model.subrecord.SubrecortBase;
import ru.azat.utils.EgtsDecoderUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;

/**
 * Подзапись используется АС и телематической платформой для передачи команд, информационных сообщений,
 * подтверждений доставки, подтверждений выполнения команд, подтверждений прочтения сообщений
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SrCommandData extends SubrecortBase {
    private byte CT_CCT;
    /**
     * идентификатор команды, сообщения. Значение из данного поля должно быть использовано стороной,
     * обрабатывающей/выполняющей команду или сообщение, для создания подтверждения. Подтверждение должно
     * содержать в поле CID то же значение, что содержалось в самой команде или сообщении при отправке;
     */
    private long CID;
    /**
     * идентификатор отправителя данной команды или подтверждения. В случае передачи от АС на ТП подтверждения
     * на команду или результат выполнения команды (тип команды CT_COMCONF, CT_MSGCONF, CT_DELIV) необходимо
     * копировать значение данного поля из ранее пришедшей на АС команды. При инициации отправки подзаписи
     * EGTS_SR_COMMAND_DATA на стороне АС данное поле имеет значение 0;
     */
    private long SID;
    private byte ACFE_CHSFE;
    /**
     *  длина в байтах поля АС, содержащего код авторизации на стороне получателя
     */
    private byte ACL;
    /**
     * код авторизации, использующийся на принимающей стороне
     */
    private ByteBuf AC;
    /**
     * тело команды, параметры, данные возвращаемые на команду-запрос, использующие кодировку из поля CHS или значение по умолчанию.
     */
    private ByteBuf CD;
    /**
     * кодировка символов, используемая в поле CD, содержащем тело команды.
     */
    private CommandDataCHS CHS;
    /**
     * тип подтверждения (имеет смысл для типов команд CT_COMCONF, CT_MSGCONF, CT_DELIV)
     */
    private CommandDataCCT CCT;
    /**
     * тип команды
     */
    private CommandDataCT CT;

    @Override
    public ByteBuf toByteBuf() {
        throw new NotImplementedException();
    }

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        CT_CCT = byteBuf.readByte();
        boolean[] CT_CCT_booleans = EgtsDecoderUtils.byteToBooleanList(CT_CCT);
        boolean[] CCT_booleans = Arrays.copyOfRange(CT_CCT_booleans, 0, 4);
        boolean[] CT_booleans = Arrays.copyOfRange(CT_CCT_booleans, 4, 8);
        CCT = CommandDataCCT.from(CCT_booleans);
        CT = CommandDataCT.from(CT_booleans);

        CID = byteBuf.readUnsignedIntLE();
        SID = byteBuf.readUnsignedIntLE();
        ACFE_CHSFE = byteBuf.readByte();
        boolean[] flags = EgtsDecoderUtils.byteToBooleanList(ACFE_CHSFE);
        if (flags[0]) {
            CHS = CommandDataCHS.from(byteBuf.readByte());
        }
        if (flags[1]) {
            ACL = byteBuf.readByte();
            AC = byteBuf.readBytes(ACL);
            CD = byteBuf.readBytes(byteBuf.readableBytes());
        }
    }

}

package ru.azat.model.subrecord.commands;

import ru.azat.utils.EgtsDecoderUtils;

public enum CommandDataCT {

    /**
     * подтверждение о приеме, обработке или результат выполнения команды
     */
    CT_COMCONF("0001"),
    /**
     * подтверждение о приеме, отображении и/или обработке информационного сообщения
     */
    CT_MSGCONF("0010"),

    /**
     * информационное сообщение от АС
     */
    CT_MSGFROM("0011"),
    /**
     * информационное сообщение для вывода на устройство отображения транспортного средства
     */
    CT_MSGTO("0100"),
    /**
     * команда для выполнения на транспортном средстве
     */
    СТ_СОМ("0101"),
    /**
     * удаление из очереди на выполнение переданной ранее команды
     */
    CT_DELCOM("0110"),
    /**
     * дополнительный подзапрос для выполнения (к переданной ранее команде)
     */
    CT_SUBREQ("0111"),
    /**
     *  подтверждение о доставке команды или информационного сообщения
     */
    CT_DELIV("1000");


    CommandDataCT(String value){
        this.value = value;
    }

    private final String value;

    public String getValue(){
        return this.value;
    }

    public static CommandDataCT from(boolean[] booleans){
        String command = EgtsDecoderUtils.booleanListToString(booleans);
        for(CommandDataCT commandDataCT : CommandDataCT.values()){
            if(command.equals(commandDataCT.getValue())){
                return commandDataCT;
            }
        }
        return null;
    }
}

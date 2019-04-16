package ru.azat.model.subrecord.commands;

import ru.azat.utils.EgtsDecoderUtils;

public enum CommandDataCCT {

    /**
     * успешное выполнение, положительный ответ
     */
    СС_ОК("0000"),
    /**
     * обработка завершилась ошибкой
     */
    CC_ERROR("0001"),
    /**
     * команда не может быть выполнена по причине отсутствия в списке разрешенных
     * (определенных протоколом) команд или отсутствия разрешения на выполнение данной команды
     */
    CC_ILL("0010"),
    /**
     * команда успешно удалена
     */
    CC_DEL ("0011"),
    /**
     * команда для удаления не найден
     */
    CC_NFOUND("0100"),
    /**
     * успешное выполнение, отрицательный ответ;
     */
    CC_NCONF ("0101"),
    /**
     * команда передана на обработку, но для ее выполнения требуется длительное время
     * (результат выполнения еще не известен)
     */
    CC_INPROG ("0110");


    CommandDataCCT(String value){
        this.value = value;
    }

    private final String value;

    public String getValue(){
        return this.value;
    }

    public static CommandDataCCT from(boolean[] booleans){
        String command = EgtsDecoderUtils.booleanListToString(booleans);
        for(CommandDataCCT commandDataCCT : CommandDataCCT.values()){
            if(command.equals(commandDataCCT.getValue())){
                return commandDataCCT;
            }
        }
        return null;
    }
}

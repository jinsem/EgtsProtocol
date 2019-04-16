package ru.azat.model.subrecord.commands;

public enum CommandDataCHS {
    CP_1251((byte) 0),
    ASCII((byte)1),
    BINARY((byte)2),
    LATIN_ONE((byte)3),
    BINARY_TWO((byte)4),
    JIS((byte)5),
    CYRILLIC((byte)6),
    HEBREW((byte) 7),
    UCS2((byte)8);
    CommandDataCHS(byte value){
        this.value = value;
    }

    private final byte value;

    public byte getValue(){
        return this.value;
    }

    public static CommandDataCHS from(byte b){
        for(CommandDataCHS commandDataCHS : CommandDataCHS.values()){
            if(commandDataCHS.getValue() == b){
                return commandDataCHS;
            }
        }
        return CommandDataCHS.CP_1251;
    }
}

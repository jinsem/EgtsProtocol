package ru.azat.model;

public class EgtsEnum {

    public enum PACKAGES {
        // (подтверждение на протокол транспортного уровня);
        EGTS_PT_RESPONSE(0),
        //(пакет, содержащий данные протокола уровня поддержки услуг);
        EGTS_РТ_APPDATA(1),
        // (пакет, содержащий данные протокола уровня поддержки услуг с цифровой подписью)
        EGTS_РТ_SIGNED_APPDATA(2);

        PACKAGES(int value){
            this.value = value;
        }

        private final int value;

        public int getValue(){
            return this.value;
        }
    }

    public enum SERVICES {
        EGTS_AUTH_SERVICE(1),
        EGTS_TELEDATA_SERVICE(2),
        EGTS_COMMANDS_SERVICE(4),
        EGTS_FIRMWARE_SERVICE(9),
        EGTS_ECALL_SERVICE(10);

        SERVICES(int value){
            this.value = value;
        }

        private final int value;

        public int getValue(){
            return this.value;
        }
    }

    public enum COMMANDS_SERVICE_SUBRECORDS {
        EGTS_SR_RECORD_RESPONSE(0),
        EGTS_SR_COMMAND_DATA(51);

        COMMANDS_SERVICE_SUBRECORDS(int value){
            this.value = value;
        }

        private final int value;

        public int getValue(){
            return this.value;
        }
    }

    public enum AUTH_SERVICE_SUBRECORDS {
        EGTS_SR_RECORD_RESPONSE(0),
        EGTS_SR_TERM_IDENTITY(1),
        EGTS_SR_MODULE_DATA(2),
        EGTS_SR_VEHICLE_DATA(3),
        EGTS_SR_AUTH_PARAMS(6),
        EGTS_SR_AUTH_INFO(7),
        EGTS_SR_SERVICE_INFO(8),
        EGTS_SR_RESULT_CODE(9);

        AUTH_SERVICE_SUBRECORDS(int value){
            this.value = value;
        }

        private final int value;

        public int getValue(){
            return this.value;
        }
    }

}

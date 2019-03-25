package ru.azat.model;

public enum EgtsEnum {

    // (подтверждение на протокол транспортного уровня);
    EGTS_PT_RESPONSE {
        @Override
        public int getValue() {
            return 0;
        }
    },

    //(пакет, содержащий данные протокола уровня поддержки услуг);
    EGTS_РТ_APPDATA {
        @Override
        public int getValue() {
            return 1;
        }
    },

    // (пакет, содержащий данные протокола уровня поддержки услуг с цифровой подписью)
    EGTS_РТ_SIGNED_APPDATA {
        @Override
        public int getValue() {
            return 2;
        }
    };

    public abstract int getValue();

}

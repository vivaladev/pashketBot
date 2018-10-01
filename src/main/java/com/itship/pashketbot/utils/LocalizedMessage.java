package com.itship.pashketbot.utils;

public enum LocalizedMessage {

    DEFAULT_MESSAGE("Привет", "Hello"),

    BABUSHKA_MES("Бабушкин лучший программист","Andrew Babushkin is professional JavaScript developer!"),

    RODRIGO_MES("Родриго красавчик", "RODRIGO IS GOOD GUY!"),

    SWORD_MES("Твой меч: ", "Your sword: "),

    REVERSE_NAME("Перевернем оволс: ", "Let's reverse drow: ");

    private final String rusMes;
    private final String engMes;

    LocalizedMessage(String rusMes, String engMes) {
        this.rusMes = rusMes;
        this.engMes = engMes;
    }

    public String getRusMes() {
        return rusMes;
    }

    public String getEngMes() {
        return engMes;
    }
}

package steps;


public enum DropDownEnum {
    ДОРОЖЕ("Дороже"),
    CHEAPER("Дешевле"),
    DEFAULT("По умолчанию"),
    DATE("По дате");

    public String option;

    public String getOption() {
        return option;
    }

    DropDownEnum(String option) {

        this.option = option;
    }
}







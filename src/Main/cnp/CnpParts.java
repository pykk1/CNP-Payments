package Main.cnp;

public interface CnpParts {
    Sex sex();
    Boolean foreigner();
    CalDate birthDate();
    Judet judet();
    Integer orderNumber();

    void setSex(Sex sex);
    void setForeigner(Boolean foreigner);
    void setBirthDate(CalDate birthDate);
    void setJudet(Judet judet);
    void setOrderNumber(Integer orderNumber);
}

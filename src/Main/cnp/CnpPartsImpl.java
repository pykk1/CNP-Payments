package Main.cnp;

public class CnpPartsImpl implements CnpParts{
    private Sex sex;
    private Boolean foreigner;
    private CalDate birthDate;
    private Judet judet;
    private Integer orderNumber;

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setForeigner(Boolean foreigner) {
        this.foreigner = foreigner;
    }

    public void setBirthDate(CalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setJudet(Judet judet) {
        this.judet = judet;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public Sex sex() {
        return sex;
    }

    @Override
    public Boolean foreigner() {
        return foreigner;
    }

    @Override
    public CalDate birthDate() {
        return birthDate;
    }

    @Override
    public Judet judet() {
        return judet;
    }

    @Override
    public Integer orderNumber() {
        return orderNumber;
    }

    @Override
    public String toString() {
        return "CnpPartsImpl{" +
                "sex=" + sex +
                ", foreigner=" + foreigner +
                ", birthDate=" + birthDate +
                ", judet=" + judet +
                ", orderNumber=" + orderNumber +
                '}';
    }
}

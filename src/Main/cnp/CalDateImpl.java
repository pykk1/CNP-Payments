package Main.cnp;

public class CalDateImpl implements CalDate {
    private Short year;
    private Byte month;
    private Byte day;

    public void setYear(Short year) {
        this.year = year;
    }

    public void setMonth(Byte month) {
        this.month = month;
    }

    public void setDay(Byte day) {
        this.day = day;
    }

    @Override
    public Short year() {
        return year;
    }

    @Override
    public Byte month() {
        return month;
    }

    @Override
    public Byte day() {
        return day;
    }

    @Override
    public String toString() {
        return "CalDateImpl{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}

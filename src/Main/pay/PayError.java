package Main.pay;

public interface PayError {
    Integer line();
    Integer type();

    void setLine(Integer line);
    void setType(Integer type);
}

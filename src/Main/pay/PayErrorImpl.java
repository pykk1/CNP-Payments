package Main.pay;

import java.io.Serializable;

public class PayErrorImpl implements PayError, Serializable {
    private Integer line;
    private Integer type;

    @Override
    public Integer line() {
        return line;
    }

    @Override
    public Integer type() {
        return type;
    }

    @Override
    public void setLine(Integer line) {
        this.line = line;
    }

    @Override
    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PayErrorImpl{" +
                "line=" + line +
                ", type=" + type +
                '}';
    }
}

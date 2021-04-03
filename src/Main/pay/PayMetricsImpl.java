package Main.pay;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

public class PayMetricsImpl implements PayMetrics, Serializable {
    private BigDecimal averagePaymentAmount;
    private Integer bigPayments;
    private Integer smallPayments;
    private BigDecimal totalAmountCapitalCity;
    private Integer foreigners;
    private Set<PayError> errors;
    private Integer paymentsByMinors;

    @Override
    public BigDecimal averagePaymentAmount() {
        return averagePaymentAmount;
    }

    @Override
    public Integer bigPayments() {
        return bigPayments;
    }

    @Override
    public Integer smallPayments() {
        return smallPayments;
    }

    @Override
    public BigDecimal totalAmountCapitalCity() {
        return totalAmountCapitalCity;
    }

    @Override
    public Integer foreigners() {
        return foreigners;
    }

    @Override
    public  Set<PayError> errors() {
        return errors;
    }

    @Override
    public Integer paymentsByMinors() {
        return paymentsByMinors;
    }

    @Override
    public void setAveragePaymentAmount(BigDecimal averagePaymentAmount) {
        this.averagePaymentAmount = averagePaymentAmount;
    }

    @Override
    public void setBigPayments(Integer bigPayments) {
        this.bigPayments = bigPayments;
    }

    @Override
    public void setSmallPayments(Integer smallPayments) {
        this.smallPayments = smallPayments;
    }

    @Override
    public void setTotalAmountCapitalCity(BigDecimal totalAmountCapitalCity) {
        this.totalAmountCapitalCity = totalAmountCapitalCity;
    }

    @Override
    public void setForeigners(Integer foreigners) {
        this.foreigners = foreigners;
    }

    public void setErrors( Set<PayError> errors) {
        this.errors = errors;
    }

    @Override
    public void setPaymentsByMinors(Integer paymentsByMinors) {
        this.paymentsByMinors = paymentsByMinors;
    }

    @Override
    public String toString() {
        return "PayMetricsImpl{" +
                "averagePaymentAmount=" + averagePaymentAmount +
                ", bigPayments=" + bigPayments +
                ", smallPayments=" + smallPayments +
                ", totalAmountCapitalCity=" + totalAmountCapitalCity +
                ", foreigners=" + foreigners +
                ", errors=" + errors +
                '}';
    }
}

package Main.pay;

import java.math.BigDecimal;
import java.util.Set;

public interface PayMetrics {
    BigDecimal averagePaymentAmount();
    Integer bigPayments();
    Integer smallPayments();
    BigDecimal totalAmountCapitalCity();
    Integer foreigners();
    Set<PayError> errors();
    Integer paymentsByMinors();

    void setAveragePaymentAmount(BigDecimal averagePaymentAmount);
    void setBigPayments(Integer bigPayments);
    void setSmallPayments(Integer smallPayments);
    void setTotalAmountCapitalCity(BigDecimal totalAmountCapitalCity);
    void setForeigners(Integer foreigners);
    void setErrors(Set<PayError> errors);
    void setPaymentsByMinors(Integer paymentsByMinors);
}

package Main.pay;

import Main.cnp.*;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PayMetricsProcessorImpl implements PayMetricsProcessor {
    private CnpValidator cnpValidator;
    private CnpParts cnpParts = new CnpPartsImpl();
    private PayMetrics payMetrics = new PayMetricsImpl();
    private Map<String, Double> inputData = new HashMap<>();
    private Set<PayError> payErrorsSet = new HashSet<>();

    /**
     * Se citesc datele din input.csv, iar cele care inceplinesc conditiile sunt pastrate intr-un Map, inputData.
     * Liniile goale sunt ignorate, iar cele gresite sunt salvate intr-un set, payErrorsSet.
     * Se serializeaza rezultatele obtinute din metodele de mai jos in fisierul de iesire output.csv.
     */
    @Override
    public void process(InputStream paymentsInputStream, OutputStream metricsOutputStream) throws java.io.IOException {
        int countLines = 0;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(paymentsInputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().length() == 0) { //Se sare peste liniile goale.
                    continue;
                }
                String[] fields = line.split(";"); //Se desparte CNP-ul de plata.
                if (fields.length == 2) {   //Se verifica daca formatul liniei este corespunzator.
                    try {
                        cnpValidator = new CnpValidatorImpl();
                        cnpParts = cnpValidator.validateCnp(fields[0]); //Se valideaza CNP-ul.
                        if (Double.parseDouble(fields[1]) > 0) { //Se verifica prezenta platilor negative.
                            inputData.put(fields[0], Double.parseDouble(fields[1])); //Se populeaza Map-ul.
                        } else {
                            addError(countLines, 2);
                        }
                    } catch (CnpException e) {
                        addError(countLines, 1);
                    } catch (java.lang.NumberFormatException e) {
                        addError(countLines, 2);
                    }
                } else addError(countLines, 0);
                countLines++; //Creste indexul liniei(pentru erori).
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        paymentsInputStream.close();

        averagePaymentAmount();
        smallPayments();
        bigPayments();
        paymentsByMinors();
        totalAmountCapitalCity();
        foreigners();

        ObjectOutputStream out = new ObjectOutputStream(metricsOutputStream);
        out.writeObject(payMetrics);

        out.close();
    }

    /**
     * @param line
     *              Linia la care se produce eroarea
     * @param type
     *              Tipul erorii:
     *              <ul>
     *              <li>0 linie invalida</li>
     *              <li>1 pentru CNP invalid</li>
     *              <li>2 pentru suma plata invalida</li>
     *              </ul>
     */
    private void addError(Integer line, Integer type) {
        PayError payError = new PayErrorImpl();
        payError.setLine(line);
        payError.setType(type);
        payErrorsSet.add(payError);
        payMetrics.setErrors(payErrorsSet);
    }

    /**
     * Se parcurge Map-ul, calculandu-se media valorilor platilor.
     */
    private void averagePaymentAmount() {
        double sum = 0;
        for (Map.Entry<String, Double> entry : inputData.entrySet()) {
            sum += entry.getValue();
        }
        payMetrics.setAveragePaymentAmount(new BigDecimal(String.format("%.2f", sum / inputData.size())));
    }

    /**
     * Se parcurge Map-ul, calculandu-se numarul de plati cu valoare pana in 5000RON(inclusiv).
     */
    private void smallPayments() {
        int sum = 0;
        for (Map.Entry<String, Double> entry : inputData.entrySet()) {
            if (entry.getValue() <= 5000)
                sum++;
        }
        payMetrics.setSmallPayments(sum);
    }

    /**
     * Se parcurge Map-ul, calculandu-se numarul de plati cu valoare mai mare de 5000RON.
     */
    private void bigPayments() {
        int sum = 0;
        for (Map.Entry<String, Double> entry : inputData.entrySet()) {
            if (entry.getValue() > 5000)
                sum++;
        }
        payMetrics.setSmallPayments(sum);
    }

    /**
     * Se parcurge Map-ul, calculandu-se numarul de plati efectuate de persoane care nu au implinit varsta majoratului.
     * Se foloseste un obiect de tip Period care calculeaza diferenta de timp intre data de nastere, si data de la
     * momentul executiei programului.
     */
    private void paymentsByMinors() {
        Period period;
        int sum = 0;
        for (Map.Entry<String, Double> entry : inputData.entrySet()) {
            cnpValidator = new CnpValidatorImpl();
            try {
                cnpParts = cnpValidator.validateCnp(entry.getKey());
            } catch (CnpException e) {
                e.printStackTrace();
            }
            period = Period.between(LocalDate.of(cnpParts.birthDate().year(), cnpParts.birthDate().month(), cnpParts.birthDate().day()), LocalDate.now());
            if (period.getYears() < 18)
                sum++;
        }
        payMetrics.setPaymentsByMinors(sum);
    }

    /**
     * Se parcurge Map-ul, calculandu-se suma totala a platilor efectuate de cetateni romani nascuti in Bucuresti.
     */
    private void totalAmountCapitalCity() {
        double sum = 0;
        for (Map.Entry<String, Double> entry : inputData.entrySet()) {
            cnpValidator = new CnpValidatorImpl();
            try {
                cnpParts = cnpValidator.validateCnp(entry.getKey());
            } catch (CnpException e) {
                e.printStackTrace();
            }
            if (!cnpParts.foreigner() && cnpParts.judet() == Judet.BU) {
                sum += entry.getValue();
            }
        }
        payMetrics.setTotalAmountCapitalCity(new BigDecimal(String.format("%.2f", sum)));
    }

    /**
     * Se parcurge Map-ul, calculandu-se numarul cetateni straini ce au efectuat plati.
     */
    private void foreigners() {
        int sum = 0;
        for (Map.Entry<String, Double> entry : inputData.entrySet()) {
            cnpValidator = new CnpValidatorImpl();
            try {
                cnpParts = cnpValidator.validateCnp(entry.getKey());
            } catch (CnpException e) {
                e.printStackTrace();
            }
            if (cnpParts.foreigner()) {
                sum++;
            }
        }
        payMetrics.setForeigners(sum);
    }
}

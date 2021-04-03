package Main.pay;

import java.io.InputStream;
import java.io.OutputStream;

public interface PayMetricsProcessor {
    public void process(InputStream paymentsInputStream, OutputStream metricsOutputStream) throws IOException, java.io.IOException;
}

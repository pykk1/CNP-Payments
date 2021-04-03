package Main;

import Main.pay.PayMetricsProcessorImpl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


public class Main {

    public static void main(String[] args) {
        PayMetricsProcessorImpl payMetricsProcessor = new PayMetricsProcessorImpl();
        try {
            String fileIn = "src/Main/input.csv";
            String fileOut = "src/Main/output.csv";
            OutputStream outputStream = new FileOutputStream(fileOut);
            InputStream inputStream = new FileInputStream(fileIn);
            payMetricsProcessor.process(inputStream, outputStream);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        
    }
}
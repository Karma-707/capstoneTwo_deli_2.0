package com.ps;

import com.ps.core.Order;
import com.ps.ui.UserInterface;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileManager {
    public static void writeReceipt(Order order) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss");
        String formattedDateTime = String.format("%s.txt", currentDateTime.format(formatter));

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(formattedDateTime));

            bufferedWriter.write(order.generateReceipt());

            bufferedWriter.close();
        } catch (IOException e) {
            UserInterface.writeErrorsToLogsFile(e);
        }

    }
}

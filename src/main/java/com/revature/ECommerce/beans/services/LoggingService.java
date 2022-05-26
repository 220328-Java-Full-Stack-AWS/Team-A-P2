package com.revature.ECommerce.beans.services;

import com.revature.ECommerce.beans.enums.LoggingLevel;
import com.revature.ECommerce.beans.enums.LoggingMode;
import com.revature.ECommerce.beans.repositories.LogMessageRepository;
import com.revature.ECommerce.entities.LogMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class LoggingService {
    private LoggingLevel loggingThreshold; //not doing anything with this yet
    private LoggingMode loggingMode;

    private LogMessageRepository logRepo;

    @Autowired
    public LoggingService(LogMessageRepository logRepo) {
        this.logRepo = logRepo;
    }


    public void log(LoggingLevel level, String message) throws IOException {
        System.out.println("preparing log message...");
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(String.valueOf(LocalDateTime.now()));
        sb.append("](Level: ");
        sb.append(level);
        sb.append(")  ");
        sb.append(message);
        sb.append("\n");
        doLog(sb.toString());
    }

    public void log(String message, Exception e) throws IOException {
        doLog(message, e);
    }

    private void doLog(String logMessage, Exception ...e) throws IOException {
        switch (loggingMode) {
            case FILE: //file logging
                System.out.println("preparing log file");
                String logDate = String.valueOf(LocalDate.now());
                File file = new File("./logs/log-" + logDate +  ".log");
                FileWriter fw = new FileWriter(file, true);
                fw.write(logMessage);
                fw.close();
                break;
            case DATASOURCE: //db logging
                LogMessage logObject = new LogMessage();
                if(e.length >= 1) {
                    StringBuilder sb = new StringBuilder();
                    for (StackTraceElement element : e[0].getStackTrace()) {
                        sb.append(element);
                        sb.append("\n");
                    }
                    logObject.setTrace(sb.toString());
                }
                logObject.setMessage(logMessage);
                logObject.setTimestamp(String.valueOf(LocalDate.now()));
                logRepo.save(logObject);
                break;
        }
    }

    public LoggingLevel getLoggingThreshold() {
        return loggingThreshold;
    }

    public void setLoggingThreshold(LoggingLevel loggingThreshold) {
        this.loggingThreshold = loggingThreshold;
    }

    public LoggingMode getLoggingMode() {
        return loggingMode;
    }

    public void setLoggingMode(LoggingMode loggingMode) {
        this.loggingMode = loggingMode;
    }

    public LogMessageRepository getLogRepo() {
        return logRepo;
    }

    public void setLogRepo(LogMessageRepository logRepo) {
        this.logRepo = logRepo;
    }
}

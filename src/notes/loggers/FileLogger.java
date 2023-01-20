package notes.loggers;

import notes.model.FileOperation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileLogger implements Loggable {
    FileLoggerOperation fileLoggerOperation;
    public FileLogger(FileLoggerOperation fileLoggerOperation){
        this.fileLoggerOperation = fileLoggerOperation;
    }

    @Override
    public void saveLog(String message) {
        fileLoggerOperation.saveLogToFile(new Date() + ": " + message);
    }
}

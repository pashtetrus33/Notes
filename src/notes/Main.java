package notes;

import notes.controllers.NoteController;
import notes.loggers.ConsoleLogger;
import notes.loggers.FileLogger;
import notes.loggers.LogRepositoryFileDecorator;
import notes.model.FileAppendOperationImpl;
import notes.model.FileOperationImpl;
import notes.model.JsonRepositoryFile;
import notes.model.Repository;
import notes.views.ViewNote;

public class Main {
    public static void main(String[] args) {

        FileOperationImpl fileOperation =
                new FileOperationImpl("notes.json");
        Repository repository =
                new LogRepositoryFileDecorator(
                new JsonRepositoryFile(fileOperation),
                new FileLogger(new FileAppendOperationImpl("log")));
        //Repository repository = new LogRepositoryFileDecorator(new JsonRepositoryFile(fileOperation), new ConsoleLogger());
        NoteController noteController = new NoteController(repository);
        new ViewNote(noteController).run();
    }
}

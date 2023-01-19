package notes;

import notes.controllers.NoteController;
import notes.model.FileOperationImpl;
import notes.model.JsonRepositoryFile;
import notes.views.ViewNote;

public class Main {
    public static void main(String[] args) {

        new ViewNote(new NoteController(new JsonRepositoryFile(new FileOperationImpl("notes.json")))).run();
    }
}

package notes.model;

import java.util.List;

public interface Repository {
    List<Note> getAllNotes();
    void noteCreate(Note note);
    void noteUpdate(Note note);
    void noteDelete(Note note);
    Note noteRead(String id);
    void exit();
}

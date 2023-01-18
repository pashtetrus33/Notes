package notes.model;

import java.util.List;

public interface Repository {
    List<Note> getAllNotes();
    String noteCreate(Note note);
    void noteUpdate(Note note);
    void noteDelete(Note note);
}

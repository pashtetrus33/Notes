package notes.controllers;

import notes.model.Note;
import notes.model.Repository;

import java.util.List;


public class NoteController {
    private final Repository repository;

    public NoteController(Repository repository) {
        this.repository = repository;
    }

    public void saveNote(Note note) {
        repository.noteCreate(note);
    }

    public Note noteRead(String id)  {
       return repository.noteRead(id);
    }

    public void exit(){
        repository.exit();
    }

    public List<Note> readAll() {
        return repository.getAllNotes();
    }

    public void noteUpdate(String id, Note updatedNote) {
        updatedNote.setId(id);
        repository.noteUpdate(updatedNote);
    }

    public void noteDelete(String id) {
        Note note = repository.getAllNotes().stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        repository.noteDelete(note);
    }


    public boolean idExists(String id) throws Exception {
        List<Note> notes = repository.getAllNotes();
        Note note = notes.stream().filter(p -> p.getId().equals(id)).findAny().orElse(null);
        if (note == null)
            throw new Exception("Заметка с идентификатром: " + id + " не найдена.");
        return true;
    }

    public boolean recordsExist() {
        List<Note> notes = repository.getAllNotes();
        boolean result = ((notes != null) && (notes.size() != 0));
        if (!result)
            System.out.println("\nСписок заметок пустой.\n");
        return result;
    }
}

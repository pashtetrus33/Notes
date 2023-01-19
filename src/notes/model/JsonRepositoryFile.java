package notes.model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class JsonRepositoryFile implements Repository {
    private final JsonNoteMapper mapper = new JsonNoteMapper();
    private final FileOperation fileOperation;

    public JsonRepositoryFile(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    @Override
    public List<Note> getAllNotes() {
        List<String> lines = fileOperation.readAllLines();
        if (lines.size() != 0){

            return mapper.remap(lines.get(0));
        }
        return null;
    }


    @Override
    public void noteCreate(Note note) {
        List<Note> notes;
        if ((getAllNotes() != null) && (getAllNotes().get(0).getId() != null)){
            notes = getAllNotes();
        }
        else {
            notes = new ArrayList<>();
        }

        int max = 0;
        for (Note item : notes) {
            int id = Integer.parseInt(item.getId());
            if (max < id) {
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        note.setId(id);
        notes.add(note);
        writeToFile(notes);
    }

    @Override
    public void noteUpdate(Note updatedNote) {
        List<Note> notes = getAllNotes();
        Note noteToBeUpdated = notes.stream().filter(p -> p.getId().equals(updatedNote.getId())).findFirst().orElse(null);
        if (noteToBeUpdated != null) {
            noteToBeUpdated.setTitle(updatedNote.getTitle());
            noteToBeUpdated.setText(updatedNote.getText());
            noteToBeUpdated.setDate(updatedNote.getDate());
            writeToFile(notes);
        } else System.out.println("Заметка с идентификатором: " + updatedNote.getId() + " не найдена");

    }

    @Override
    public void noteDelete(Note note) {
        List<Note> notes = getAllNotes();
        notes.removeIf(p -> p.getId().equals(note.getId()));
        writeToFile(notes);
    }


    private void writeToFile(List<Note> notes) {
        List<String> lines = new ArrayList<>();
        lines.add(mapper.map(notes));
        fileOperation.saveAllLines(lines);
    }
}

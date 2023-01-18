package notes.model;

import java.util.ArrayList;
import java.util.List;

public class JsonRepositoryFile implements Repository {
    private JsonUserMapper mapper = new JsonUserMapper();
    private FileOperation fileOperation;

    public JsonRepositoryFile(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    @Override
    public List<Note> getAllNotes() {
        List<String> lines = fileOperation.readAllLines();
        lines.removeIf(p-> p.equals(""));
        List<Note> notes = new ArrayList<>();
        for (String line : lines) {
            notes.add(mapper.map(line));
        }
        return notes;
    }


    @Override
    public String noteCreate(Note note) {

        List<Note> notes = getAllNotes();
        int max = 0;
        for (Note item : notes) {
            int id = Integer.parseInt(item.getId());
            if (max < id){
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        note.setId(id);
        notes.add(note);
        writeToFile(notes);
        return id;
    }

    @Override
    public void noteUpdate(Note updatedNote) {
        List<Note> notes = getAllNotes();
        Note noteToBeUpdated = notes.stream().filter(p -> p.getId().equals(updatedNote.getId())).findFirst().orElse(null);
        if (noteToBeUpdated != null){
            noteToBeUpdated.setTitle(updatedNote.getTitle());
            noteToBeUpdated.setText(updatedNote.getText());
            noteToBeUpdated.setDate(updatedNote.getDate());
           writeToFile(notes);
        }
        else System.out.println("Note with id" + updatedNote.getId() + "not found");

    }

    @Override
    public void noteDelete(Note note) {
        List<Note> notes = getAllNotes();
        notes.removeIf(p -> p.getId().equals(note.getId()));
        writeToFile(notes);
    }


    private void writeToFile(List<Note> notes){
        List<String> lines = new ArrayList<>();
        for (Note item: notes) {
            lines.add(mapper.map(item));
        }
        fileOperation.saveAllLines(lines);
    }
}

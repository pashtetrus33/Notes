package notes.loggers;

import notes.model.Note;
import notes.model.Repository;

import java.util.List;

public class LogRepositoryFileDecorator implements Repository {

    Repository repository;
    Loggable log;

    public LogRepositoryFileDecorator(Repository repository, Loggable log){
        this.repository = repository;
        this.log = log;
    }
    @Override
    public List<Note> getAllNotes() {
        log.saveLog("вывод всех заметок");
        return repository.getAllNotes();
    }

    @Override
    public void noteCreate(Note note) {
        log.saveLog("Создание заметки");
        repository.noteCreate(note);
    }

    @Override
    public void noteUpdate(Note note) {
        log.saveLog("Редактирование заметки: " + note.getId());
        repository.noteUpdate(note);
    }

    @Override
    public void noteDelete(Note note) {
        log.saveLog("Удаление заметки: " + note.getId());
        repository.noteDelete(note);
    }

    @Override
    public Note noteRead(String id) {
        log.saveLog("Чтение заметки: " + id);
        return repository.noteRead(id);
    }

    @Override
    public void exit() {
        log.saveLog("Выход из приложения");
    }
}

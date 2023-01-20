package notes.views;

import notes.controllers.NoteController;
import notes.model.*;

import java.util.Scanner;

public class ViewNote {

    private final NoteController noteController;

    public ViewNote(NoteController noteController) {
        this.noteController = noteController;
    }

    public void run() {

        String id;
        String command;

        while (true) {

            command = prompt("1 - создать заметку\n2 - прочитать заметку\n3 - обновить заметку\n4 - удалить заметку\n5 - прочитать все заметки\n6 - выход\n" +
                    "Сделайте Ваш выбор: ");
            //exit
            if (command.equals("6")) {
                noteController.exit();
                return;
            }
            try {
                switch (command) {
                    //create
                    case "1":
                        noteController.saveNote(noteCreate());

                        break;
                    //read
                    case "2":
                        if (noteController.recordsExist()) {
                            id = prompt("Введите id заметки: ");
                            if (noteController.idExists(id)) {
                                Note note = noteController.noteRead(id);
                                System.out.println("Найденная заметка:");
                                System.out.println(note);
                            }
                        }
                        break;

                    //update
                    case "3":
                        if (noteController.recordsExist()) {
                            id = prompt("Введите id для редактирования: ");
                            noteController.idExists(id);
                            noteController.noteUpdate(id, noteCreate());
                        }

                        break;
                    //delete
                    case "4":
                        if (noteController.recordsExist()) {
                            id = prompt("Введите id для удаления: ");
                            noteController.idExists(id);
                            noteController.noteDelete(id);
                        }
                        break;
                    //list
                    case "5":
                        if (noteController.recordsExist()) {
                            System.out.println("Список всех заметок:");
                            for (Note item : noteController.readAll()) {
                                System.out.println(item);
                            }
                        }
                        break;

                    default:
                        System.out.println("\n Команда не найдена");

                }
            } catch (Exception e) {
                System.out.println("Ошибка. " + e.getMessage());
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private Note noteCreate() {
        String title = prompt("Заголовок: ");
        String text = prompt("Содержание: ");
        return (new Note(title, text));
    }
}

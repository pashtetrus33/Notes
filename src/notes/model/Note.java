package notes.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Date;

@JsonAutoDetect
public class Note {
    private String id;
    private String title;
    private String text;
    private Date date;


    public Note(String title, String text) {
        date = new Date();
        this.title = title;
        this.text = text;

    }

    public Note() {
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "id: " + id + "\n" + "Дата: " + date + "\n" + "Заголовок: " + title + "\n" + "Содержание: " + text + "\n";
    }
}

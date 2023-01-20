package notes.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class JsonNoteMapper {

    public String map(List<Note> notes) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        return gson.toJson(notes);

    }

    public List<Note> remap(List<String> lines) {
        StringBuilder result = new StringBuilder();

        for (String item : lines) {
            result.append(item);
        }
        Type listOfMyClassObject = new TypeToken<ArrayList<Note>>() {
        }.getType();
        Gson gson = new Gson();

        return gson.fromJson(result.toString(), listOfMyClassObject);
    }
}
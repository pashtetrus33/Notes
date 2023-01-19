package notes.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonNoteMapper {

    public String map(List<Note> notes) {

        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(writer, notes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    public List<Note> remap(String line) {
        List<Note> notes = new ArrayList<>();
        String[] lines = line.split("},");
        for (String item: lines) {
            item = item.replace("{", "");
            item = item.replace("}", "");
            item = item.replace("[", "");
            item = item.replace("]", "");
            item = "{" + item + "}";

            StringReader reader = new StringReader(item);
            ObjectMapper mapper = new ObjectMapper();
            try {

                notes.add(mapper.readValue(reader, Note.class));
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return notes;
    }
}
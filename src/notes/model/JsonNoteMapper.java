package notes.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class JsonNoteMapper {

    public String map(Note note) {

        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(writer, note);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    public Note map(String line) {
        StringReader reader = new StringReader(line);
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(reader, Note.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Zad7 {
    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper=new ObjectMapper();
        Person tomasz=new Person("Tomasz", 31);
        String classJson=objectMapper.writeValueAsString(tomasz);
        System.out.println(classJson);
    }
}


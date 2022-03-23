import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Zad8 {
    public static void main(String[] args)  throws IOException{
        ObjectMapper objectMapper=new ObjectMapper();
        //Person tomasz=new Person("Tomasz", 31);
        String personJSON="{\"name\":\"Tomasz\",\"age\":31}";
        Person tomasz=objectMapper.readValue(personJSON, Person.class);
        //System.out.println(personJSON);
        System.out.println(tomasz.getName());
        System.out.println(tomasz.getAge());
    }
}


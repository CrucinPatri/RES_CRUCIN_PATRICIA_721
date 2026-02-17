package org.example.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Astronaut;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AstronautRepository {
    private final ObjectMapper mapper = new ObjectMapper();
    public List<Astronaut> loadAstronauts(String filePath){
        try{
            return mapper.readValue(new File(filePath), new TypeReference<List<Astronaut>>() {});
        }catch(IOException e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

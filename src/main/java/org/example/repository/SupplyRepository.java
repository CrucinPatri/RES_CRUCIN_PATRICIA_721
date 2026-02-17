package org.example.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Astronaut;
import org.example.model.Supply;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SupplyRepository {
    private final ObjectMapper mapper = new ObjectMapper();
    public List<Supply> loadSupply(String filePath){
        try{
            return mapper.readValue(new File(filePath), new TypeReference<List<Supply>>() {});
        }catch(IOException e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}


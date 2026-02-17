package org.example.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Astronaut;
import org.example.model.MissionEvent;

import java.io.File;
import java.io.IOException;
import java.lang.classfile.instruction.NewMultiArrayInstruction;
import java.util.ArrayList;
import java.util.List;

public class MissionEventRepository {
    private final ObjectMapper mapper = new ObjectMapper();
    public List<MissionEvent> loadEvents(String filePath){
        try{
            return mapper.readValue(new File(filePath), new TypeReference<List<MissionEvent>>() {});
        }catch(IOException e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

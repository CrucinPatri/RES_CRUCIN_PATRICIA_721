package org.example.service;

import org.example.model.*;
import org.example.repository.AstronautRepository;
import org.example.repository.MissionEventRepository;
import org.example.repository.SupplyRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpaceMissionService {
    private List<Astronaut> astronauts;
    private List<MissionEvent> events;
    private List<Supply> supplies;
    private final AstronautRepository astronautRepository = new AstronautRepository();
    private final MissionEventRepository missionEventRepository = new MissionEventRepository();
    private final SupplyRepository supplyRepository = new SupplyRepository();

    public void loadData(){
        this.astronauts = this.astronautRepository.loadAstronauts("astronauts.json");
        this.events = this.missionEventRepository.loadEvents("events.json");
        this.supplies = this.supplyRepository.loadSupply("supplies.json");

        System.out.println("Astronauts loaded:" + astronauts.size());
        System.out.println("Events loaded:" + events.size());
        System.out.println("Supplies loaded:" + supplies.size());
    }

    public void printAllAstronauts(){
        for(Astronaut a : astronauts){
            System.out.println(a);
        }
    }

    public void filterBySpacecraftAndStatus(String spacecraft){
        System.out.println("Filter results (Spacecraft " + spacecraft + ", ALIVE):");
        Stream var = this.astronauts.stream().filter((t) -> t.getSpacecraft() == spacecraft && t.getStatus() == AstronautStatus.ACTIVE);
        PrintStream var1 = System.out;
        Objects.requireNonNull(var1);
        var.forEach(var1::println);

    }

    public List<Astronaut> getsortedAstronauts(){
        return this.astronauts.stream().sorted(Comparator.comparingInt(Astronaut::getExperienceLevel).reversed(). thenComparing(Astronaut::getName)).collect(Collectors.toList());

    }

    public void writeAstronautsToFile(){
        List<Astronaut> sorted = this.getsortedAstronauts();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("astronauts_sorted.txt"))){
            for(Astronaut t : sorted){
                writer.write(t.toString());
                writer.newLine();
            }
            System.out.println("Fisierul a fost creat");
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void calculatePoints(){
        int limit = Math.min(5, this.events.size());
        for(int i = 0; i < limit; i++){
            MissionEvent e = (MissionEvent) this.events.get(i);
            int computedPoints = 0;
            switch (e.getType()){
                case EVA -> computedPoints = e.getBasePoints() + 2 * e.getDay();
                case SYSTEM_FAILURE ->  computedPoints = e.getBasePoints() - 3 - e.getDay();
                case SCIENCE ->   computedPoints = e.getBasePoints() + (4 + e.getDay());
                case MEDICAL ->   computedPoints = e.getBasePoints() - 2 * (e.getDay()%3);
                case COMMUNICATION ->   computedPoints = e.getBasePoints() + 5;
            }
            PrintStream var = System.out;
            var.println("Event " + e.getType() + " -> rawPoints=" + e.getBasePoints() + " -> computedPoints=" + computedPoints);
        }

    }

    public void generateEventReport(String filePath) {
        Map<MissionEventType, Long> eventCounts = this.events.stream()
                .collect(Collectors.groupingBy(MissionEvent::getType, Collectors.counting()));

        List<Map.Entry<MissionEventType, Long>> sortedEventCounts = eventCounts.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .toList();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<MissionEventType, Long> entry : sortedEventCounts) {
                writer.write(entry.getKey() + " -> " + entry.getValue());
                writer.newLine();
            }
            System.out.println("Event report generated successfully: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing event report: " + e.getMessage());
        }
    }

}

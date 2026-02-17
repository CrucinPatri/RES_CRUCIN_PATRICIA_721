package org.example.service;

import org.example.model.Astronaut;
import org.example.model.AstronautStatus;
import org.example.model.MissionEvent;
import org.example.model.Supply;
import org.example.repository.AstronautRepository;
import org.example.repository.MissionEventRepository;
import org.example.repository.SupplyRepository;

import java.io.PrintStream;
import java.util.List;
import java.util.Objects;
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

}

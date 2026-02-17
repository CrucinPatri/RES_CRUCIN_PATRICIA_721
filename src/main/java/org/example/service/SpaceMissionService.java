package org.example.service;

import org.example.model.Astronaut;
import org.example.model.MissionEvent;
import org.example.model.Supply;
import org.example.repository.AstronautRepository;
import org.example.repository.MissionEventRepository;
import org.example.repository.SupplyRepository;

import java.util.List;

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
}

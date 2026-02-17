package org.example.controller;

import org.example.service.SpaceMissionService;

public class SpaceMissionController {
    SpaceMissionService missionService = new SpaceMissionService();

    public void run(){
        System.out.println("--- Task1 ---");
        this.missionService.loadData();
        this.missionService.printAllAstronauts();

    }
}

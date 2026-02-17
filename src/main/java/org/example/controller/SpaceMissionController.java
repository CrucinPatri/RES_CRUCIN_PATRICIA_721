package org.example.controller;

import org.example.service.SpaceMissionService;

import java.util.Scanner;

public class SpaceMissionController {
    SpaceMissionService missionService = new SpaceMissionService();

    public void run(){
        System.out.println("--- Task1 ---");
        this.missionService.loadData();
        this.missionService.printAllAstronauts();

        System.out.println("\n--- Task2 ---");
        Scanner scan = new Scanner(System.in);
        System.out.println("Input spacecraft: ");
        if(scan.hasNext()){
            String s = scan.nextLine();
            this.missionService.filterBySpacecraftAndStatus(s);

        }
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Input spacecraft: ");
//        String spacecraft = scanner.nextLine();
//        System.out.print("Input status: ");
//        String status = scanner.nextLine();
//        this.missionService.filterBySpacecraftAndStatus(spacecraft, status).forEach(System.out::println);

        System.out.println("\n--- Task3 ---");
        this.missionService.printAllAstronauts();

        System.out.println("\n--- Task 4 ---");
        this.missionService.writeAstronautsToFile();

        System.out.println("\n--- Task 5 ---");
        this.missionService.calculatePoints();

        System.out.println("\n--- Task 6 ---");

        System.out.println("\n--- Task 7 ---");
        this.missionService.generateEventReport("mission_report.txt");

    }
}

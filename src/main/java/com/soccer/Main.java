package com.soccer;

import java.util.Scanner;

import com.soccer.view.viewCoach;
import com.soccer.view.viewDoctor;
import com.soccer.view.viewPlayer;
import com.soccer.view.viewTeam;

public class Main {
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        Controller controlador = new Controller();
        Controller ctrlTeams = controlador;
        Controller ctrlPlayers = controlador;
        Controller ctrlCoachs = controlador;
        Controller ctrlDoctors = controlador;

        viewTeam.controlador = ctrlTeams;
        viewPlayer.controlador = ctrlPlayers;
        viewDoctor.controlador = ctrlDoctors;
        viewCoach.controlador = ctrlCoachs;
        
        viewTeam vt = new viewTeam();
        viewPlayer vp = new viewPlayer();
        viewDoctor vd = new viewDoctor();
        viewCoach vc = new viewCoach();
        while (true) {
            displayMenu();
            int op = scanner.nextInt();
            scanner.nextLine();
            switch (op) {
                case 1:
                    vt.start();
                    break;
                case 2:
                    vp.start();
                    break;
                case 3:
                    vd.start();
                    break;
                case 4:
                    vc.start();
                    break;
                case 5:
                    System.exit(0);
                    return;
                default:
                    break;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("1. Equipos");
        System.out.println("2. Jugadores");
        System.out.println("3. Doctores");
        System.out.println("4. Entrenadores");
        System.out.println("5. Salir");
    }

}
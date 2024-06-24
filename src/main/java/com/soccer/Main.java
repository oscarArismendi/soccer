package com.soccer;

import java.util.Scanner;

import com.soccer.view.viewPlayer;
import com.soccer.view.viewTeam;

public class Main {
    private static Scanner scanner;
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        Controller ctrlTeams = new Controller();
        Controller ctrlPlayers = new Controller();
        Controller ctrlCoachs = new Controller();
        Controller ctrlDoctos = new Controller();

        viewTeam.controlador = ctrlTeams;
        viewPlayer.controlador = ctrlPlayers;
        viewTeam vt = new viewTeam();
        viewPlayer vp = new viewPlayer();
        while(true){
            displayMenu();
            int op = scanner.nextInt();
            scanner.nextLine();
            switch (op) {
                case 1:
                    vp.start();
                    break;
                case 2:
                    vt.start();
                    break;
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
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
            cleanScreen();
            displayMenu();
            int op = option_validation("opcion:", 1, 5);
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
                    System.out.println("Good bye!");
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

    // util functions
    private static int option_validation(String statement, int lower, int upper) {
        while (true) {
            try {
                System.out.println(statement);
                int option = scanner.nextInt();
                scanner.nextLine();
                if (option >= lower && option <= upper) {
                    return option;
                } else {
                    System.out.println(String.format("The option is not in the interval: %1$d-%2$d", lower, upper));
                }
            } catch (Exception e) {
                System.out.println("Please, type a valid number.");
                scanner.nextLine();
            }
        }
    }

    private static void pause() {
        System.out.println("Press enter to continue");
        scanner.nextLine();
    }

    private static void cleanScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
package com.soccer.view;

import java.util.Scanner;
import java.util.Set;

import com.soccer.Controller;
import com.soccer.model.entity.Team;
import com.soccer.model.entity.Player;
import com.soccer.model.entity.Coach;
import com.soccer.model.entity.Doctor;

public class viewTeam {
    public static Controller controlador;
    private Scanner scanner;

    public void start() {
        scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = option_validation("opcion: ", 1, 6);

            switch (choice) {
                case 1:
                    createTeam();
                    break;
                case 2:
                    updateTeam();
                    break;
                case 3:
                    searchTeam();
                    break;
                case 4:
                    deleteTeam();
                    break;
                case 5:
                    listAllTeams();
                    break;
                case 6:
                    return; // Return to the main menu
                default:
                    System.out.println("Opción inválida, intentelo de nuevo.");
            }
        }
    }

    private void displayMenu() {
        cleanScreen();
        System.out.println("1. Crear equipo");
        System.out.println("2. Actualizar equipo");
        System.out.println("3. Buscar equipo");
        System.out.println("4. Eliminar equipo");
        System.out.println("5. Listar todos los equipos");
        System.out.println("6. Salir");
    }

    private void createTeam() {
        System.out.println("Introduce el codigo del equipo:");
        String codigoEquipo = scanner.nextLine();
        System.out.println("Introduce el nombre del equipo:");
        String nombre = scanner.nextLine();
        System.out.println("Introduce la ciudad del equipo:");
        String ciudad = scanner.nextLine();

        Team team = new Team();
        team.setNombre(nombre);
        team.setCiudad(ciudad);

        controlador.equipos.put(codigoEquipo, team);
        System.out.println("Equipo creado exitosamente.");
        pause();
    }

    private void updateTeam() {
        System.out.println("Introduce el codigo del equipo:");
        String codigoEquipo = scanner.nextLine();

        if (!controlador.equipos.containsKey(codigoEquipo)) {
            System.out.println("No se encontró un equipo con ese nombre");
            return;
        }

        Team team = controlador.equipos.get(codigoEquipo);
        cleanScreen();
        System.out.println("1. Cambiar nombre");
        System.out.println("2. Cambiar ciudad");
        System.out.println("3. Añadir jugador");
        System.out.println("4. Añadir entrenador");
        System.out.println("5. Añadir doctor");
        System.out.println("6. Cancelar");

        int option = option_validation("opcion: ", 1, 6);

        switch (option) {
            case 1:
                System.out.println("Ingrese el nuevo nombre:");
                String nuevoNombre = scanner.nextLine();
                team.setNombre(nuevoNombre);
                break;
            case 2:
                System.out.println("Ingrese la nueva ciudad:");
                team.setCiudad(scanner.nextLine());
                break;
            case 3:
                System.out.println("Ingrese el codigo del jugador a añadir:");
                String idJugador = scanner.nextLine();
                Player player = controlador.jugadores.get(idJugador);
                if (player != null) {
                    team.setLstJugadores(player);
                    System.out.println("Jugador añadido exitosamente.");
                } else {
                    System.out.println("Jugador no encontrado.");
                }
                break;
            case 4:
                System.out.println("Ingrese el codigo del entrenador a añadir:");
                String idEntrenador = scanner.nextLine();
                Coach coach = controlador.entrenadores.get(idEntrenador);
                if (coach != null) {
                    team.setLstEntrenadores(coach);
                    System.out.println("Entrenador añadido exitosamente.");
                } else {
                    System.out.println("Entrenador no encontrado.");
                }
                break;
            case 5:
                System.out.println("Ingrese el codigo del doctor a añadir:");
                String idDoctor = scanner.nextLine();
                Doctor doctor = controlador.doctores.get(idDoctor);
                if (doctor != null) {
                    team.setLstMasajistas(doctor);
                    System.out.println("Doctor añadido exitosamente.");
                } else {
                    System.out.println("Doctor no encontrado.");
                }
                break;
            case 6:
                return;
            default:
                System.out.println("Opción inválida.");
        }
        System.out.println("Equipo actualizado exitosamente.");
        pause();
    }

    private void searchTeam() {
        System.out.println("Introduce el codigo del equipo:");
        String codigoEquipo = scanner.nextLine();
        Team team = controlador.equipos.get(codigoEquipo);
        if (team != null) {
            displayTeamDetails(team);
        } else {
            System.out.println("Equipo no encontrado.");
        }
        pause();
    }

    private void deleteTeam() {
        System.out.println("Introduce el codigo del equipo:");
        String codigoEquipo = scanner.nextLine();
        if (controlador.equipos.remove(codigoEquipo) != null) {
            System.out.println("Equipo eliminado exitosamente.");
        } else {
            System.out.println("Equipo no encontrado.");
        }
        pause();
    }

    private void listAllTeams() {
        Set<String> keys = controlador.equipos.keySet();
        for (String key : keys) {
            Team team = controlador.equipos.get(key);
            displayTeamDetails(team);
        }
        pause();
    }

    private void displayTeamDetails(Team team) {
        System.out.println("----------------------------------------------------");
        System.out.println("Nombre: " + team.getNombre());
        System.out.println("Ciudad: " + team.getCiudad());
        System.out.println("Jugadores:");
        for (Player player : team.getLstJugadores()) {
            System.out.println(" - " + player.getNombre() + " " + player.getApellido());
        }
        System.out.println("Entrenadores:");
        for (Coach coach : team.getLstEntrenadores()) {
            System.out.println(" - " + coach.getNombre() + " " + coach.getApellido());
        }
        System.out.println("Doctores:");
        for (Doctor doctor : team.getLstMasajistas()) {
            System.out.println(" - " + doctor.getNombre() + " " + doctor.getApellido());
        }
        System.out.println("----------------------------------------------------");
    }

    // util functions
    private int option_validation(String statement, int lower, int upper) {
        while (true) {
            try {
                System.out.println(statement);
                int option = scanner.nextInt();
                scanner.nextLine();
                if (option >= lower && option <= upper) {
                    return option;
                } else {
                    System.out.println(String.format("La opción no está en el intervalo: %1$d-%2$d", lower, upper));
                }
            } catch (Exception e) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine();
            }
        }
    }

    private void pause() {
        System.out.println("Presione Enter para continuar");
        scanner.nextLine();
    }

    private void cleanScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

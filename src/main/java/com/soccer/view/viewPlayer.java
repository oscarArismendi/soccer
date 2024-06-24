package com.soccer.view;

import java.util.Scanner;
import java.util.Set;

import com.soccer.Controller;
import com.soccer.model.entity.Player;

public class viewPlayer {
    public static Controller controlador;
    private Scanner scanner;

    public void start() {
        scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createPlayer();
                    break;
                case 2:
                    updatePlayer();
                    break;
                case 3:
                    searchPlayer();
                    break;
                case 4:
                    deletePlayer();
                    break;
                case 5:
                    listAllPlayers();
                    break;
                case 6:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción inválida, intentelo de nuevo.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("1. Crear jugador");
        System.out.println("2. Actualizar jugador");
        System.out.println("3. Buscar jugador");
        System.out.println("4. Eliminar jugador");
        System.out.println("5. Listar todos los jugadores");
        System.out.println("6. Salir");
    }

    private void createPlayer() {
        System.out.println("Ingrese el codigo del jugador:");
        String codigoJugador = scanner.nextLine();
        System.out.println("Ingrese id del jugador:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese Nombre del jugador:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese apellido del jugador:");
        String apellido = scanner.nextLine();
        System.out.println("Ingrese la edad:");
        int edad = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese la dorsal:");
        int dorsal = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese la posicion:");
        String posicion = scanner.nextLine();

        Player jugador = new Player(id, nombre, apellido, edad, dorsal, posicion);
        controlador.jugadores.put(codigoJugador, jugador);
    }

    private void updatePlayer() {
        System.out.println("Ingresa el codigo del jugador:");
        String codigoJugador = scanner.nextLine();

        if (!controlador.jugadores.containsKey(codigoJugador)) {
            System.out.println("No se encontró un jugador con ese codigo");
            return;
        }

        Player jugador = controlador.jugadores.get(codigoJugador);
        System.out.println("1. Cambiar nombre");
        System.out.println("2. Cambiar apellido");
        System.out.println("3. Cambiar edad");
        System.out.println("4. Cambiar dorsal");
        System.out.println("5. Cambiar posicion");
        System.out.println("6. Cancelar");

        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                System.out.println("Ingrese el nuevo nombre:");
                jugador.setNombre(scanner.nextLine());
                break;
            case 2:
                System.out.println("Ingrese el nuevo apellido:");
                jugador.setApellido(scanner.nextLine());
                break;
            case 3:
                System.out.println("Ingrese la nueva edad:");
                jugador.setEdad(scanner.nextInt());
                scanner.nextLine();
                break;
            case 4:
                System.out.println("Ingrese la nueva dorsal:");
                jugador.setDorsal(scanner.nextInt());
                scanner.nextLine();
                break;
            case 5:
                System.out.println("Ingrese la nueva posicion:");
                jugador.setPosicion(scanner.nextLine());
                break;
            case 6:
                return;
            default:
                System.out.println("Opción inválida.");
        }
    }

    private void searchPlayer() {
        System.out.println("Ingresa el codigo del jugador:");
        String codigoJugador = scanner.nextLine();

        if (controlador.jugadores.containsKey(codigoJugador)) {
            Player jugador = controlador.jugadores.get(codigoJugador);
            displayPlayerDetails(jugador);
        } else {
            System.out.println("No se encontró un jugador con el codigo " + codigoJugador);
        }
    }

    private void deletePlayer() {
        System.out.println("Ingrese el codigo del jugador que va a remover:");
        String codigoJugador = scanner.nextLine();
        controlador.jugadores.remove(codigoJugador);
    }

    private void listAllPlayers() {
        Set<String> keys = controlador.jugadores.keySet();
        for (String key : keys) {
            Player jugador = controlador.jugadores.get(key);
            displayPlayerDetails(jugador);
        }
    }

    private void displayPlayerDetails(Player jugador) {
        System.out.println("------------------------------------------");
        System.out.println("id: " + jugador.getId());
        System.out.println("Nombre: " + jugador.getNombre() + " " + jugador.getApellido());
        System.out.println("Edad: " + jugador.getEdad());
        System.out.println("Dorsal: " + jugador.getDorsal());
        System.out.println("Posicion: " + jugador.getPosicion());
    }
}

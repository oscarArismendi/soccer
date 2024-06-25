package com.soccer.view;

import java.util.Scanner;
import java.util.Set;

import com.soccer.Controller;
import com.soccer.model.entity.Coach;

public class viewCoach {
    public static Controller controlador;
    private Scanner scanner;

    public void start() {
        scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = option_validation("opcion: ", 1, 6);

            switch (choice) {
                case 1:
                    createCoach();
                    break;
                case 2:
                    updateCoach();
                    break;
                case 3:
                    searchCoach();
                    break;
                case 4:
                    deleteCoach();
                    break;
                case 5:
                    listAllCoaches();
                    break;
                case 6:
                    return;  // Return to the main menu
                default:
                    System.out.println("Opción inválida, intentelo de nuevo.");
            }
        }
    }

    private void displayMenu() {
        cleanScreen();
        System.out.println("1. Crear entrenador");
        System.out.println("2. Actualizar entrenador");
        System.out.println("3. Buscar entrenador");
        System.out.println("4. Eliminar entrenador");
        System.out.println("5. Listar todos los entrenadores");
        System.out.println("6. Salir");
    }

    private void createCoach() {
        System.out.println("Ingresar codigo del entrenador:");
        String codigoEntrenador = scanner.nextLine();
        System.out.println("Ingresar la identificacion del jugador");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduce el nombre:");
        String nombre = scanner.nextLine();
        System.out.println("Introduce el apellido:");
        String apellido = scanner.nextLine();
        System.out.println("Introduce la edad:");
        int edad = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduce el id de federación:");
        int idFederacion = scanner.nextInt();
        scanner.nextLine();

        Coach coach = new Coach(id, nombre, apellido, edad, idFederacion);
        controlador.entrenadores.put(codigoEntrenador, coach);
        System.out.println("Entrenador creado exitosamente.");
        pause();
    }

    private void updateCoach() {
        System.out.println("Introduce el codigo del entrenador a actualizar:");
        String id = scanner.nextLine();

        if (!controlador.entrenadores.containsKey(id)) {
            System.out.println("No se encontró un entrenador con ese codigo");
            return;
        }

        Coach coach = controlador.entrenadores.get(id);
        cleanScreen();
        System.out.println("1. Cambiar nombre");
        System.out.println("2. Cambiar apellido");
        System.out.println("3. Cambiar edad");
        System.out.println("4. Cambiar id de federación");
        System.out.println("5. Cancelar");

        int option = option_validation("opcion: ", 1, 5);

        switch (option) {
            case 1:
                System.out.println("Ingrese el nuevo nombre:");
                coach.setNombre(scanner.nextLine());
                break;
            case 2:
                System.out.println("Ingrese el nuevo apellido:");
                coach.setApellido(scanner.nextLine());
                break;
            case 3:
                System.out.println("Ingrese la nueva edad:");
                coach.setEdad(scanner.nextInt());
                scanner.nextLine();
                break;
            case 4:
                System.out.println("Ingrese el nuevo id de federación:");
                coach.setIdFederacion(scanner.nextInt());
                scanner.nextLine();
                break;
            case 5:
                return;
            default:
                System.out.println("Opción inválida.");
        }
        controlador.entrenadores.put(id, coach);
        System.out.println("Entrenador actualizado exitosamente.");
        pause();
    }

    private void searchCoach() {
        System.out.println("Introduce el codigo del entrenador a buscar:");
        String id = scanner.nextLine();
        Coach coach = controlador.entrenadores.get(id);
        if (coach != null) {
            displayCoachDetails(coach);
        } else {
            System.out.println("Entrenador no encontrado.");
        }
        pause();
    }

    private void deleteCoach() {
        System.out.println("Introduce el codigo del entrenador a eliminar:");
        String id = scanner.nextLine();
        if (controlador.entrenadores.remove(id) != null) {
            System.out.println("Entrenador eliminado exitosamente.");
        } else {
            System.out.println("Entrenador no encontrado.");
        }
        pause();
    }

    private void listAllCoaches() {
        Set<String> keys = controlador.entrenadores.keySet();
        for (String key : keys) {
            Coach coach = controlador.entrenadores.get(key);
            displayCoachDetails(coach);
        }
        pause();
    }

    private void displayCoachDetails(Coach coach) {
        System.out.println("----------------------------------------------------");
        System.out.println("ID: " + coach.getId());
        System.out.println("Nombre: " + coach.getNombre() + " " + coach.getApellido());
        System.out.println("Edad: " + coach.getEdad());
        System.out.println("ID de Federación: " + coach.getIdFederacion());
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

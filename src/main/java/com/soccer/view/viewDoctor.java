package com.soccer.view;

import java.util.Scanner;
import java.util.Set;

import com.soccer.Controller;
import com.soccer.model.entity.Doctor;

public class viewDoctor {
    public static Controller controlador;
    private Scanner scanner;

    public void start() {
        scanner = new Scanner(System.in);
        boolean salidaDoctor = true;
        while (salidaDoctor) {
            displayMenu();
            int choice = option_validation("opcion: ", 1, 6);

            switch (choice) {
                case 1:
                    createDoctor();
                    break;
                case 2:
                    updateDoctor();
                    break;
                case 3:
                    searchDoctor();
                    break;
                case 4:
                    deleteDoctor();
                    break;
                case 5:
                    listAllDoctors();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Opción inválida, intentelo de nuevo.");
            }
        }
    }

    private void displayMenu() {
        cleanScreen();
        System.out.println("---------------------MENU DOCTOR------------------------------");
        System.out.println("1. Crear doctor");
        System.out.println("2. Actualizar doctor");
        System.out.println("3. Buscar doctor");
        System.out.println("4. Eliminar doctor");
        System.out.println("5. Listar todos los doctores");
        System.out.println("6. Salir");
    }

    private void createDoctor() {
        cleanScreen();
        System.out.println("---------------------MENU CREAR DOCTOR------------------------");
        System.out.println("Ingresar codigo del doctor:");
        String codigoDoctor = scanner.nextLine();
        System.out.println("Ingresar id del doctor:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduce el nombre:");
        String nombre = scanner.nextLine();
        System.out.println("Introduce el apellido:");
        String apellido = scanner.nextLine();
        System.out.println("Introduce la edad:");
        int edad = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduce el titulo:");
        String titulo = scanner.nextLine();
        System.out.println("Introduce el año de experiencia:");
        int expYear = scanner.nextInt();
        scanner.nextLine();

        Doctor doctor = new Doctor(id, nombre, apellido, edad, titulo, expYear);
        controlador.doctores.put(codigoDoctor, doctor);
        System.out.println("Doctor creado exitosamente.");
        pause();
    }

    private void updateDoctor() {
        cleanScreen();
        System.out.println("---------------------MENU ACTUALIZAR DOCTOR-------------------");
        System.out.println("Introduce el codigo del doctor a actualizar:");
        String id = scanner.nextLine();

        if (!controlador.doctores.containsKey(id)) {
            System.out.println("No se encontró un doctor con ese codigo");
            pause();
            return;
        }

        Doctor doctor = controlador.doctores.get(id);
        cleanScreen();
        System.out.println("---------------------MENU ACTUALIZAR DOCTOR-------------------");
        System.out.println("1. Cambiar nombre");
        System.out.println("2. Cambiar apellido");
        System.out.println("3. Cambiar edad");
        System.out.println("4. Cambiar titulo");
        System.out.println("5. Cambiar año de experiencia");
        System.out.println("6. Cancelar");

        int option = option_validation("opcion: ", 1, 6);

        switch (option) {
            case 1:
                System.out.println("Ingrese el nuevo nombre:");
                doctor.setNombre(scanner.nextLine());
                System.out.println("Doctor actualizado exitosamente.");
                pause();
                break;
            case 2:
                System.out.println("Ingrese el nuevo apellido:");
                doctor.setApellido(scanner.nextLine());
                System.out.println("Doctor actualizado exitosamente.");
                pause();
                break;
            case 3:
                System.out.println("Ingrese la nueva edad:");
                doctor.setEdad(scanner.nextInt());
                System.out.println("Doctor actualizado exitosamente.");
                pause();
                scanner.nextLine();
                break;
            case 4:
                System.out.println("Ingrese el nuevo titulo:");
                doctor.setTitulo(scanner.nextLine());
                System.out.println("Doctor actualizado exitosamente.");
                pause();
                break;
            case 5:
                System.out.println("Ingrese el nuevo año de experiencia:");
                doctor.setExpYear(scanner.nextInt());
                scanner.nextLine();
                System.out.println("Doctor actualizado exitosamente.");
                pause();
                break;
            case 6:
                return;
            default:
                System.out.println("Opción inválida.");
        }

    }

    private void searchDoctor() {
        cleanScreen();
        System.out.println("---------------------MENU BUSCAR DOCTOR-----------------------");
        System.out.println("Introduce el codigo del doctor a buscar:");
        String codigoDoctor = scanner.nextLine();
        Doctor doctor = controlador.doctores.get(codigoDoctor);
        if (doctor != null) {
            displayDoctorDetails(doctor);
        } else {
            System.out.println("Doctor no encontrado.");
        }
        pause();
    }

    private void deleteDoctor() {
        cleanScreen();
        System.out.println("---------------------MENU ELIMINAR DOCTOR---------------------");
        System.out.println("Introduce el codigo del doctor a eliminar:");
        String codigoDoctor = scanner.nextLine();

        if (controlador.doctores.remove(codigoDoctor) != null) {
            System.out.println("Doctor eliminado exitosamente.");
        } else {
            System.out.println("Doctor no encontrado.");
        }
        pause();
    }

    private void listAllDoctors() {
        cleanScreen();
        System.out.println("---------------------MENU TODOS LOS DOCTORES------------------");
        Set<String> keys = controlador.doctores.keySet();
        for (String key : keys) {
            Doctor doctor = controlador.doctores.get(key);
            displayDoctorDetails(doctor);
        }
        pause();
    }

    private void displayDoctorDetails(Doctor doctor) {
        System.out.println("ID: " + doctor.getId());
        System.out.println("Nombre: " + doctor.getNombre() + " " + doctor.getApellido());
        System.out.println("Edad: " + doctor.getEdad());
        System.out.println("Titulo: " + doctor.getTitulo());
        System.out.println("Año de experiencia: " + doctor.getExpYear());
        System.out.println("--------------------------------------------------------------");
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
                    System.out.println(String.format("The option is not in the interval: %1$d-%2$d", lower, upper));
                }
            } catch (Exception e) {
                System.out.println("Please, type a valid number.");
                scanner.nextLine();
            }
        }
    }

    private void pause() {
        System.out.println("Press enter to continue");
        scanner.nextLine();
    }

    private void cleanScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

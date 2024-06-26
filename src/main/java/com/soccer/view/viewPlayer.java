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
                     int choice = option_validation("opcion: ", 1, 6);

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
                                   return;
                            default:
                                   System.out.println("Opción inválida, intentelo de nuevo.");
                     }
              }
       }

       private void displayMenu() {
              cleanScreen();
              System.out.println("---------------------MENU JUGADOR-----------------------------");
              System.out.println("1. Crear jugador");
              System.out.println("2. Actualizar jugador");
              System.out.println("3. Buscar jugador");
              System.out.println("4. Eliminar jugador");
              System.out.println("5. Listar todos los jugadores");
              System.out.println("6. Salir");
       }

       private void createPlayer() {
              cleanScreen();
              System.out.println("---------------------MENU CREAR JUGADOR-----------------------");
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
              System.out.println("Jugador creado exitosamente.");
              pause();
       }

       private void updatePlayer() {
              cleanScreen();
              System.out.println("---------------------MENU ACTUALIZAR JUGADOR------------------");
              System.out.println("Ingresa el codigo del jugador:");
              String codigoJugador = scanner.nextLine();

              if (!controlador.jugadores.containsKey(codigoJugador)) {
                     System.out.println("No se encontró un jugador con ese codigo");
                     pause();
                     return;
              }

              Player jugador = controlador.jugadores.get(codigoJugador);
              cleanScreen();
              System.out.println("1. Cambiar nombre");
              System.out.println("2. Cambiar apellido");
              System.out.println("3. Cambiar edad");
              System.out.println("4. Cambiar dorsal");
              System.out.println("5. Cambiar posicion");
              System.out.println("6. Cancelar");

              int option = option_validation("opcion: ", 1, 6);

              switch (option) {
                     case 1:
                            System.out.println("Ingrese el nuevo nombre:");
                            jugador.setNombre(scanner.nextLine());
                            System.out.println("Jugador actualizado exitosamente.");
                            pause();
                            break;
                     case 2:
                            System.out.println("Ingrese el nuevo apellido:");
                            jugador.setApellido(scanner.nextLine());
                            System.out.println("Jugador actualizado exitosamente.");
                            pause();
                            break;
                     case 3:
                            System.out.println("Ingrese la nueva edad:");
                            jugador.setEdad(scanner.nextInt());
                            scanner.nextLine();
                            System.out.println("Jugador actualizado exitosamente.");
                            pause();
                            break;
                     case 4:
                            System.out.println("Ingrese la nueva dorsal:");
                            jugador.setDorsal(scanner.nextInt());
                            scanner.nextLine();
                            System.out.println("Jugador actualizado exitosamente.");
                            pause();
                            break;
                     case 5:
                            System.out.println("Ingrese la nueva posicion:");
                            jugador.setPosicion(scanner.nextLine());
                            System.out.println("Jugador actualizado exitosamente.");
                            pause();
                            break;
                     case 6:
                            return;
                     default:
                            System.out.println("Opción inválida.");
              }
       }

       private void searchPlayer() {
              cleanScreen();
              System.out.println("---------------------MENU BUSCAR JUGADOR------------------------");
              System.out.println("Ingresa el codigo del jugador:");
              String codigoJugador = scanner.nextLine();

              if (controlador.jugadores.containsKey(codigoJugador)) {
                     Player jugador = controlador.jugadores.get(codigoJugador);
                     displayPlayerDetails(jugador);
              } else {
                     System.out.println("No se encontró un jugador con el codigo " + codigoJugador);
              }
              pause();
       }

       private void deletePlayer() {
              cleanScreen();
              System.out.println("---------------------MENU ELIMANAR JUGADOR--------------------------");
              System.out.println("Ingrese el codigo del jugador que va a remover:");
              String codigoJugador = scanner.nextLine();

              if (controlador.jugadores.remove(codigoJugador) != null) {
                     System.out.println("Jugador eliminado exitosamente.");
              } else {
                     System.out.println("Jugador no encontrado.");
              }
              pause();
       }

       private void listAllPlayers() {
              cleanScreen();
              System.out.println("---------------------MENU TODOS LOS JUGADORES-----------------");
              Set<String> keys = controlador.jugadores.keySet();
              for (String key : keys) {
                     Player jugador = controlador.jugadores.get(key);
                     displayPlayerDetails(jugador);
              }
              pause();
       }

       private void displayPlayerDetails(Player jugador) {
              System.out.println("id: " + jugador.getId());
              System.out.println("Nombre: " + jugador.getNombre() + " " + jugador.getApellido());
              System.out.println("Edad: " + jugador.getEdad());
              System.out.println("Dorsal: " + jugador.getDorsal());
              System.out.println("Posicion: " + jugador.getPosicion());
              System.out.println("--------------------------------------------------------------");

       }

       // util functions
       private int option_validation(String statement, int lower, int upper) {// return a int >= lower and <= upper
              while (true) {
                     try {
                            System.out.println(statement);
                            int option = scanner.nextInt();
                            scanner.nextLine();
                            if (option >= lower && option <= upper) {
                                   return option;
                            } else {
                                   System.out.println(String.format("The option is not in the interval: %1$d-%2$d",
                                                 lower, upper));
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

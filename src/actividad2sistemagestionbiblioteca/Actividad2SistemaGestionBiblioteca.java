package actividad2sistemagestionbiblioteca;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Actividad2SistemaGestionBiblioteca {

    public static void main(String[] args) {

        ArrayList<String[]> libros = new ArrayList<>();
        LinkedList<String[]> usuarios = new LinkedList<>();
        Stack<String[]> librosPrestados = new Stack<>();
        Queue<String[]> colaEspera = new LinkedList<>();

        Scanner entrada = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("============================================");
            System.out.println("                 BIENVENIDO");
            System.out.println("============================================");
            System.out.println("         SGB Alex Patiño 1054992551");
            System.out.println("============================================");
            System.out.println("1. Agregar Libro");
            System.out.println("2. Registrar Usuario");
            System.out.println("3. Prestar Libro");
            System.out.println("4. Devolver Libro");
            System.out.println("5. Mostrar Libros Disponibles");
            System.out.println("6. Mostrar Usuarios registrados");
            System.out.println("7. Salir");
            System.out.println("============================================");
            System.out.println(" Aprecioado usuario, Seleccione una Opción");
            System.out.println("============================================");


            while (!entrada.hasNextInt()) {
                System.out.println("Error: ¡Ingrese un número válido!");
                entrada.next();
                System.out.println("Seleccione una opción: ");
            }

            opcion = entrada.nextInt();
            entrada.nextLine();
            switch (opcion) {

                case 1: // Añadir a la base de datos el libro
                    System.out.println("Ingrese el ID del libro (único): ");
                    String idLibro = entrada.nextLine();
                    boolean idDuplicado = false;
                    for (String[] libro : libros) {
                        if (libro[0].equals(idLibro)) {
                            idDuplicado = true;
                            break;
                        }
                    }
                    if (idDuplicado) {
                        System.out.println("Error: ¡El ID del libro ya existe!");
                    } else {
                        System.out.println("Ingrese el nombre del libro: ");
                        String nombreLibro = entrada.nextLine();
                        System.out.println("Ingrese el autor del libro: ");
                        String autorLibro = entrada.nextLine();
                        libros.add(new String[]{idLibro, nombreLibro, autorLibro});
                        System.out.println("¡Libro agregado con éxito!");
                    }

                    break;

                case 2: // Añadir a la base de datos el usuario autorizado para acceder a libros
                    System.out.println("Ingrese la cédula del Usuario (Solo Números): ");

                    while (!entrada.hasNextInt()) {
                        System.out.println("Error: ¡Ingrese un número válido!");
                        entrada.next();
                        System.out.println("Ingrese la cédula del Usuario (¡SOLO NÚMEROS!): ");
                    }
                    int cedulaUsuario = entrada.nextInt();
                    entrada.nextLine();
                    System.out.println("Ingrese el nombre del Usuario: ");
                    String nombreUsuario = entrada.nextLine();
                    System.out.println("Ingrese los Apellidos del Usuario: ");
                    String apellidosUsuario = entrada.nextLine();

                    boolean cedulaDuplicado = false;
                    for (String[] usuario : usuarios) {
                        if (usuario[0].equals(String.valueOf(cedulaUsuario))) {
                            cedulaDuplicado = true;
                            break;
                        }
                    }
                    if (cedulaDuplicado) {
                        System.out.println("Error: ¡El Usuario ya existe!");
                    } else {
                        usuarios.add(new String[]{String.valueOf(cedulaUsuario), nombreUsuario, apellidosUsuario});
                        System.out.println("¡Usuario registrado con éxito!");
                    }
                    break;

                case 3: // Ingresar el "ID" del libro para consultar su existencia a disponibilidad vs datos de usuario
                    System.out.println("Ingrese el ID del libro que desea prestar: ");
                    String idPrestar = entrada.nextLine();
                    System.out.println("Ingrese la cédula del usuario que presta el libro: ");
                    while (!entrada.hasNextInt()) {
                        System.out.println("Error: ¡Ingrese un número válido!");
                        entrada.next();
                        System.out.println("Ingrese la cédula del usuario (SOLO NÚMEROS): ");
                    }
                    int cedulaPrestar = entrada.nextInt();
                    entrada.nextLine();

                    boolean libroEncontrado = false;
                    for (String[] libro : libros) {
                        if (libro[0].equals(idPrestar)) {
                            librosPrestados.push(new String[]{idPrestar, libro[1], libro[2], String.valueOf(cedulaPrestar)});
                            libros.remove(libro);
                            libroEncontrado = true;
                            System.out.println("¡Libro prestado con éxito!");
                            break;
                        }
                    }
                    if (!libroEncontrado) {
                        System.out.println("Libro no disponible");
                    }
                    break;

                case 4:// Devolver librtos e ingresarlo al inventario y base de datos 

                    System.out.println("Ingrese el ID del libro que desea devolver: ");
                    String idDevolver = entrada.nextLine();
                    boolean libroPrestadoEncontrado = false;

                    for (String[] prestamo : librosPrestados) {
                        if (prestamo[0].equals(idDevolver)) {
                            librosPrestados.remove(prestamo);
                            // añadir el libro con sus datos completos ("ID", nombre, autor)
                            libros.add(new String[]{prestamo[0], prestamo[1], prestamo[2]});
                            libroPrestadoEncontrado = true;
                            System.out.println("¡Libro devuelto con éxito!");
                            break;
                        }
                    }
                    if (!libroPrestadoEncontrado) {
                        System.out.println("El libro no está prestado.");
                    }
                    break;

                case 5:// inventario libros disponibles
                    if (libros.isEmpty()) {
                        System.out.println("No hay libros disponibles.");
                    } else {
                        System.out.println("Libros disponibles: ");
                        for (String[] libro : libros) {
                            System.out.println("ID: " + libro[0] + ", Nombre: " + libro[1] + ", Autor: " + libro[2]);
                        }
                    }
                    break;

                case 6:// Listado usuarios registrados
                    if (usuarios.isEmpty()) {
                        System.out.println("No hay usuarios registrados.");
                    } else {
                        System.out.println("Usuarios registrados: ");
                        for (String[] usuario : usuarios) {
                            System.out.println("Cédula: " + usuario[0] + ", Nombre: " + usuario[1] + " " + usuario[2]);
                        }
                    }
                    break;

                case 7:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 7);

        entrada.close();
    }
}



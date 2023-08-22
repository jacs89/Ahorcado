package array.ahorcado;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @version 1.3
 * @author Jose Antonio Cabello Santacruz
 * 
 * @changelog
 * - Se ha refactorizado el proyecto para mayor legibilidad, facilitar mantenimiento o nuevas implementaciones.
 * - Crear un menú previo donde:
 * - Seleccionar 2 jugadores.
 * 
 * @todolist
 * - Seleccionar 1 jugador.
 * - Añadir el diccionario castellano.
 * - Comprobar si la palabra escrita está en el diccionario.
 * - Si la palabra no está en el diccionario solicitar al jugador introducirla o elegir una nueva palabra.
 * - Las palabras que se escriban en este modo de juego se añadiran a un diccionario de palabras para el modo 1 jugador.
 * - Tabla de puntuaciones.
 * 
 */
public class MenuJuego {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        MenuJuego menu = new MenuJuego();
        menu.mostrarMenu();
    }
    
    public void mostrarMenu() throws IOException {
        try (Scanner teclado = new Scanner (System.in)) {
            int opcion;
            
            do {
                System.out.println("    MENÚ PRINCIPAL:");
                System.out.println("0. Salir.");
                System.out.println("1. Seleccionar 1 jugador.");
                System.out.println("2. Seleccionar 2 jugadores.");
                System.out.println("3. Mostrar tabla de puntuaciones.");
                System.out.println("4. Mostrar diccionarios.");
                
                System.out.println("");
                System.out.print("Seleccione una opción: ");
                
                opcion = teclado.nextInt();
                System.out.println("");
                
                switch (opcion) {
                    case 0:
                        System.out.println("Saliendo del juego. ¡Hasta luego!");
                        break;                        
                    case 1:
                        OpcionUnJugador opcionUnJugador = new OpcionUnJugador();
                        opcionUnJugador.ejecutar();
                        break;                        
                    case 2:
                        OpcionDosJugadores opcionDosJugadores = new OpcionDosJugadores();
                        opcionDosJugadores.ejecutar();
                        break;
                    case 3:
                        OpcionTablaPuntuaciones opcionTablaPuntuaciones = new OpcionTablaPuntuaciones();
                        opcionTablaPuntuaciones.ejecutar();
                        break;                        
                    case 4:
                        OpcionMostrarDiccionarios opcionMostrarDiccionarios = new OpcionMostrarDiccionarios();
                        opcionMostrarDiccionarios.ejecutar();
                        break;                        
                    default:
                        System.out.println("Opción no válida: Por favor, seleccione una opción válida.");
                }
            }
            while (opcion != 0);
        }
    }
}

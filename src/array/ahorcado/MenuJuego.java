package array.ahorcado;

import java.io.IOException;
import java.util.Scanner;

/**
 * @version 1.5.2
 * @author Jose Antonio Cabello Santacruz
 * 
 * @changelog
 * - Se ha refactorizado el proyecto para mayor legibilidad, facilitar mantenimiento o nuevas implementaciones.
 * - Crear un menú previo donde:
 * - Seleccionar 2 jugadores.
 * - Añadir el diccionario castellano.
 * - En 2 jugadores comprobar si la palabra escrita por el jugador 1 está en el diccionario para añadirla o rechazarla.
 * - Las palabras que se escriban en este modo de juego se añadiran a un diccionario de palabras para el modo 1 jugador.
 * 
 * @todolist
 * - Seleccionar 1 jugador.
 * - Tabla de puntuaciones.
 * - Corregir error cuando no se quiere continuar con la palabra que no está en el diccionario, debe pedirse una nueva palabra
 * - Corregir error no se muestra el tamaño de huecos de la palabra oculta
 * - Corregir error partida termina inmediatamente cuando se introduce una letra
 * 
 */
public class MenuJuego {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
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
                        ClaseUnJugador unJugador = new ClaseUnJugador();
                        unJugador.ejecutar();
                        break;                        
                    case 2:
                        ClaseDosJugadores dosJugadores = new ClaseDosJugadores();
                        dosJugadores.ejecutar();
                        break;
                    case 3:
                        ClaseTablaPuntuaciones tablaPuntuaciones = new ClaseTablaPuntuaciones();
                        tablaPuntuaciones.ejecutar();
                        break;                        
                    case 4:
                        ClaseMostrarDiccionarios mostrarDiccionarios = new ClaseMostrarDiccionarios();
                        mostrarDiccionarios.ejecutar();
                        break;                        
                    default:
                        System.out.println("Opción no válida: Por favor, seleccione una opción válida.");
                }
            }
            while (opcion != 0);
        }
    }
}

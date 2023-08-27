package array.ahorcado;

import java.io.IOException;
import java.util.Scanner;

/**
 * @version 1.6.1
 * @author Jose Antonio Cabello Santacruz
 * 
 * @changelog
 * - Crear titulo del juego
 * - Se ha refactorizado el proyecto para mayor legibilidad, facilitar mantenimiento o nuevas implementaciones.
 * - Crear un menú previo donde:
 * - Seleccionar 2 jugadores.
 * - Añadir el diccionario castellano.
 * - En 2 jugadores comprobar si la palabra escrita por el jugador 1 está en el diccionario para añadirla o rechazarla.
 * - Las palabras que se escriban en este modo de juego se añadiran a un diccionario de palabras para el modo 1 jugador.
 * - Corregir error cuando no se quiere continuar con la palabra que no está en el diccionario, debe pedirse una nueva palabra
 * - Corregir error no se muestra el tamaño de huecos de la palabra oculta
 * - Corregir error partida termina inmediatamente cuando se introduce una letra
 * 
 * @todolist
 * - Seleccionar 1 jugador.
 * - Tabla de puntuaciones.
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

    private static void generarTitulo() {        

        System.out.println("\n\n");
        System.out.println("                ,,                                                      ,,             ");
        System.out.println("      db      `7MM                                                    `7MM             ");
        System.out.println("     ;MM:       MM                                                      MM             ");
        System.out.println("    ,V^MM.      MMpMMMb.   ,pW''Wq.  `7Mb,od8   ,p60bo   ,60Yb.    ,M00bMM   ,pW0Wq.   ");
        System.out.println("   ,M  `MM      MM    MM  6W'   `Wb   MM'  '  6M'   OO  8)   MM   ,AP    MM  6W'   `Wb ");
        System.out.println("   AbmmmqMA     MM    MM  8M     M8   MM      8M         ,pm9MM   8MI    MM  8M     M8 ");
        System.out.println("  A'     VML    MM    MM  YA.   ,A9   MM      YM.    ,  8M   MM   `Mb    MM  YA.   ,A9 ");
        System.out.println(".AMA.   .AMMA..JMML  JMML. `Ybmd9'  .JMML.     YMbmd'  `Moo9^Yo.  `Wbmd\"MML. `Ybmd9'  ");
        System.out.println("\n\n");
    }
    
    public void mostrarMenu() throws IOException {
        try (Scanner teclado = new Scanner (System.in)) {
            int opcion;
            
            do {
                generarTitulo();
                System.out.println("   MENÚ PRINCIPAL:");
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

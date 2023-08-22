package array.ahorcado;

import java.util.Scanner;

/**
 *
 * @author Jose Cabello jcabello86@gmail.com
 */
public class OpcionDosJugadores {
    public void ejecutar() {
        
            //INTRODUCCION DE VARIABLES
        
        String jugador1, jugador2;
        
        int intentos = 6;
        
        boolean finDelJuego = false;        
        
        //Clase Scanner para la introduccion de datos por teclado
        Scanner teclado = new Scanner (System.in);

        
                //CODIGO DEL PROGRAMA
                
        System.out.println("        JUEGO DEL AHORACADO");
        System.out.println("------------------------------------");
        
        
        System.out.print("Jugador 1, introduzca su nombre: ");
        jugador1 = teclado.nextLine();        
        System.out.print("Jugador 2, introduzca su nombre: ");
        jugador2 = teclado.nextLine();
        
        //Comienza el juego con la introduccion de la palabra secreta por parte del jugador 1        
        String palabraSecreta = obtenerPalabraSecreta (jugador1, jugador2, teclado);
        char[] abecedario = generarAbecedario();
        
        
        System.out.println("--------------PROHIBIDO SUBIR A PARTIR DE AQUI--------------------------");

        char[] palabraAdivinando = new char[palabraSecreta.length()]; 
        //OpcionDosJugadoresalabraAdivinando (palabraAdivinando);
        
        //Comienza el jugador 2 a adivinar la palabra        
        while (!finDelJuego) {   
            
            mostrarEstadoJuego(jugador2, intentos, abecedario, palabraAdivinando);
            char caracterCandidato = teclado.next().charAt (0);
            abecedario = eliminarCaracter (abecedario, caracterCandidato);
            boolean caracterEncontrado = actualizarPalabraAdivinando (palabraSecreta, palabraAdivinando, caracterCandidato);
            
            if (!caracterEncontrado) {
                intentos--;
                mostrarLetrasFaltantes (abecedario);

                if (intentos == 0){
                    finDelJuego = true;
                    mostrarFinDelJuego (false,palabraSecreta);
                }
            }
            
            if (esPalabraCompleta (palabraAdivinando)) {
                finDelJuego = true;
                mostrarFinDelJuego(true, palabraSecreta);
            }
        }
    }
    
    
            //METODOS AUXILIARES
            
    /**
     * Obtiene la palabra secreta que el segundo jugador deberá adivinar.
     *
     * @param jugador1 El nombre del primer jugador.
     * @param jugador2 El nombre del segundo jugador.
     * @param teclado  Scanner para la entrada del usuario.
     * @return La palabra secreta validada.
     */
    private static String obtenerPalabraSecreta (String jugador1, String jugador2, Scanner teclado) {
        String palabraSecreta;
        boolean palabraValida;

        do {
            System.out.printf("%s introduzca la palabra que tendrá que adivinar %s: ", jugador1, jugador2);
            palabraSecreta = teclado.nextLine().toLowerCase();
            palabraValida = esPalabraValida (palabraSecreta);

            if (!palabraValida) {
                System.out.println("La palabra no es válida, por favor inténtalo de nuevo.");
            }
        }
        while (!palabraValida);

        return palabraSecreta;
    }
    
    
    /**
     * Verifica si una palabra es válida (compuesta solo de letras).
     *
     * @param palabra La palabra a verificar.
     * @return True si la palabra es válida, False en caso contrario.
     */
    private static boolean esPalabraValida (String palabra){
        
        for (char cadaCaracter: palabra.toCharArray() ){
            if (!Character.isLetter (cadaCaracter) ){
                return false;
            }
        }
        
        return true;
    }
    
    
    /**
    * Genera el abecedario con el carácter 'ñ' en la posición correcta.
    *
    * @return Un array de caracteres representando el abecedario.
    */
    private static char[] generarAbecedario() {
        char[] abecedario = new char[27];

        for (int i = 0; i < abecedario.length; i++) {
            
            //Antes de la ñ
            if (i < 14){ 
                abecedario[i] = (char) ('a' + i);                
            }
            //La l
            else if (i == 14){
                abecedario[14] = 'ñ';
            }
            //Despues de la ñ
            else {
                abecedario[i] = (char) ('a' + i - 1);
            }            
        }  

        return abecedario;
    }
    
    
    /**
     * Inicializa el array de palabra adivinando con guiones bajos.
     *
     * @param palabraAdivinando El array de caracteres que representa la palabra a adivinar.
     */
    private static void inicializarPalabraAdivinando (char[] palabraAdivinando) {
        for (int i = 0; i < palabraAdivinando.length; i++) {
            palabraAdivinando[i] = '_';
        }            
    }
    
    
    /**
     * Muestra el estado actual del juego, incluyendo letras disponibles y palabra a adivinar.
     *
     * @param jugador        El nombre del jugador actual.
     * @param intentos       El número de intentos restantes.
     * @param abecedario     El array de caracteres representando las letras disponibles.
     * @param palabraAdivinando El array de caracteres representando la palabra a adivinar.
     */
    private static void mostrarEstadoJuego (String jugador, int intentos, char[] abecedario, char[] palabraAdivinando) {
        if (intentos == 6) {
            System.out.printf("%s, adivina la palabra, tiene %d intentos....\n\n", jugador, intentos);                
        }
        else {
            System.out.printf("\nTe quedan %d intentos.\n", intentos);
        }
        
        mostrarLetrasFaltantes (abecedario);
        mostrarPalabraAdivinando (palabraAdivinando);
        System.out.print("Introduce una letra: ");
    }
    
    
    /**
     * Muestra las letras que quedan por adivinar.
     *
     * @param abecedario El array de caracteres representando las letras disponibles.
     */
    private static void mostrarLetrasFaltantes(char[] abecedario) {
        System.out.print("Las letras que quedan por decir son: ");
        for (char c : abecedario) {
            System.out.print(c + " ");
        }
        System.out.println();
    }
    
    
    /**
     * Muestra la palabra adivinada hasta el momento.
     *
     * @param palabraAdivinando El array de caracteres representando la palabra a adivinar.
     */
    private static void mostrarPalabraAdivinando(char[] palabraAdivinando) {
        System.out.print("Así llevas la palabra adivinada: ");
        for (char c : palabraAdivinando) {
            System.out.print(c + " ");
        }
        System.out.println();
    }
    
    
    /**
     * Elimina una letra del abecedario disponible.
     *
     * @param array    El array de caracteres del abecedario.
     * @param caracter El caracter a eliminar.
     * @return El array de caracteres actualizado.
     */
    private static char[] eliminarCaracter (char[] array, char caracter) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == caracter) {
                array[i] = '_';
            }
        }
        
        return array;
    }
    
    
    /**
     * Actualiza el array de palabra adivinando con el caracter adivinado.
     *
     * @param palabraSecreta     La palabra secreta a adivinar.
     * @param palabraAdivinando El array de caracteres representando la palabra a adivinar.
     * @param caracter           El caracter adivinado por el jugador.
     * @return True si el caracter estaba en la palabra secreta, False en caso contrario.
     */
    private static boolean actualizarPalabraAdivinando (String palabraSecreta, char[] palabraAdivinando, char caracter) {
        boolean caracterEncontrado = false;
        
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (caracter == palabraSecreta.charAt(i)) {
                palabraAdivinando[i] = caracter;
                caracterEncontrado = true;
            }
        }
        return caracterEncontrado;
    }
    
    
    /**
     * Verifica si la palabra ha sido completada.
     *
     * @param palabraAdivinando El array de caracteres representando la palabra a adivinar.
     * @return True si la palabra ha sido completada, False en caso contrario.
     */
    private static boolean esPalabraCompleta(char[] palabraAdivinando) {
        for (char c : palabraAdivinando) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }
    
    
    /**
     * Muestra el mensaje de fin de juego.
     *
     * @param ganador       Indica si el jugador ganó.
     * @param palabraSecreta La palabra secreta que se estaba adivinando.
     */
    private static void mostrarFinDelJuego(boolean ganador, String palabraSecreta) {
        if (ganador) {
            System.out.printf("\nCORRECTO. TE HAS SALVADO :) \nLa palabra era %s.\n", palabraSecreta);
        } else {
            System.out.printf("NO TE QUEDAN MÁS OPORTUNIDADES, HAS SIDO AHORCADO :( \nLa palabra era %s.\n", palabraSecreta);
        }
    }    
}
        


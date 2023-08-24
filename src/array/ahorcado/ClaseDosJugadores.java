package array.ahorcado;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Jose Cabello jcabello86@gmail.com
 */
public class ClaseDosJugadores {
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
                System.out.println ("La palabra no es válida, por favor inténtalo de nuevo.");
            }

            if (palabraValida) {
                
                char letraInicial = palabraSecreta.charAt(0);
                    
                if  (!buscarEnDiccionario (palabraSecreta, letraInicial) ) {
                    incluirEnDiccionario(palabraSecreta, letraInicial, teclado);
                }
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
                    * Busca una palabra en el diccionario correspondiente a la letra inicial dada.
                    *
                    * @param palabra La palabra a buscar en el diccionario.
                    * @param letraInicial La letra inicial de la palabra que determina el diccionario.
                    * @return true si la palabra se encuentra en el diccionario, false si no se encuentra o si ocurre un error de lectura.
                    */
                    private static boolean buscarEnDiccionario (String palabra, char letraInicial) {
                                String rutaDelArchivo = "Diccionarios/" + letraInicial + ".txt";
                                boolean palabraEncontrada = false;
                                try (BufferedReader reader = new BufferedReader(new FileReader (rutaDelArchivo) )) {

                                    String linea;

                                    while ( (linea = reader.readLine() ) != null) {
                                        if ( palabra.equalsIgnoreCase(linea) ) {
                                            palabraEncontrada = true;
                                        }
                                    }
                                }
                                catch (IOException e) {
                                    System.out.println("No se pudo abrir el archivo.\n");
                                }

                                return palabraEncontrada;
                            }

                    /**
                    * Incluye una palabra en el diccionario correspondiente a la letra inicial dada si no está presente.
                    *
                    * @param palabra La palabra a incluir en el diccionario.
                    * @param letraInicial La letra inicial de la palabra que determina el diccionario.
                    * @param teclado El objeto Scanner utilizado para la entrada del usuario.
                    */
                    private static void incluirEnDiccionario(String palabra, char letraInicial, Scanner teclado) {
                        System.out.printf("La palabra %s no se encuentra en nuestro diccionario, ¿desea incluirla? (S/N)" ,palabra);
                        String decisionIncluirPalabra = obtenerRespuestaValida (teclado);

                        if (decisionIncluirPalabra.equalsIgnoreCase("s") ) {
                            try (BufferedWriter writer = new BufferedWriter (new FileWriter ("Diccionarios/" + letraInicial + ".txt", true))) {
                                writer.newLine();
                                writer.write(palabra);
                                System.out.printf("La palabra %s ha sido añadida al diccionario.\n", palabra);
                            }
                            catch (IOException e) {
                                System.out.println("Error al escribir la nueva palabra en el archivo.");
                            }
                        }
                        else {
                            System.out.printf("Entendido, no añadiremos la palabra al diccionario. Aun así ¿Desea jugar con la palabra '%s'? (S/N)", palabra);
                            String mantenerPalabra = obtenerRespuestaValida(teclado);
                        }
                    }   
                                /**
                             * Solicita y valida una respuesta hasta que sea "S" o "N".
                             *
                             * @param teclado El objeto Scanner utilizado para la entrada del usuario.
                             * @return La respuesta válida ("S" o "N").
                             */
                                private static String obtenerRespuestaValida (Scanner teclado) {

                                    String respuesta;
                                    do {
                                        respuesta = teclado.nextLine();

                                        if (!esRespuestaValida (respuesta) ) {
                                            System.out.println("Respuesta no válida. Por favor, ingresa 'S' o 'N'.");
                                        }
                                    }
                                    while (!esRespuestaValida (respuesta) );

                                    return respuesta;
                                }
                                            /**
                                         * Verifica si una respuesta dada es válida, es decir, si es "S" o "N" (ignorando mayúsculas/minúsculas).
                                         *
                                         * @param respuesta La respuesta a verificar.
                                         * @return true si la respuesta es "S" o "N", false en caso contrario.
                                         */
                                            private static boolean esRespuestaValida (String respuesta) {
                                                return respuesta.equalsIgnoreCase("s") || respuesta.equalsIgnoreCase ("n");
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
     * @param jugador           El nombre del jugador actual.
     * @param intentos          El número de intentos restantes.
     * @param abecedario        El array de caracteres representando las letras disponibles.
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
                        for (char cadaCaracter : palabraAdivinando) {
                            System.out.print(cadaCaracter + " ");
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
     * Muestra el mensaje de fin de juego. Luego del fin del juego se vuelve al menú de inicio
     *
     * @param ganador       Indica si el jugador ganó.
     * @param palabraSecreta La palabra secreta que se estaba adivinando.
     */
        private static void mostrarFinDelJuego(boolean ganador, String palabraSecreta) {
        if (ganador) {
            System.out.printf("\nCORRECTO. TE HAS SALVADO :) \nLa palabra era %s.\n\n", palabraSecreta);
        } else {
            System.out.printf("NO TE QUEDAN MÁS OPORTUNIDADES, HAS SIDO AHORCADO :( \nLa palabra era %s.\n", palabraSecreta);
        }
    }    
    
        /**
     * Verifica si la palabra ha sido completada.
     *
     * @param palabraAdivinando El array de caracteres representando la palabra a adivinar.
     * @return True si la palabra ha sido completada, False en caso contrario.
     */
        private static boolean esPalabraCompleta(char[] palabraAdivinando) {
        for (char cadaCaracter : palabraAdivinando) {
            if (cadaCaracter == '_') {
                return false;
            }
        }
        return true;
    }
    }
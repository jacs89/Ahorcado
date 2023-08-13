package array.ahorcado;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author jose
 */
public class ArrayAhorcado {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
                //INTRODUCCION DE LAS VARIABLES
                
        Integer puntuacionJugador1, puntuacionJugador2;
        Integer turno = 1;
        Integer intentos = 6;
        
        String jugador1, jugador2;
        String palabraSecreta = ""; 
        
        boolean palabravalida = true;
        boolean palabraEncontrada = false;
        boolean caracterEncontrado = false;
        boolean finDelJuego = false;
        boolean sonIguales = false;
        
        char caracterCandidato;
        
        char[] arrayPalabraSecreta = null;
        char[] arrayPalabraAdivinando = null;
   
        char[] arrayAbecedario = new char[27];
        
        //Clase Scaner para la introduccion de datos por teclado
        Scanner teclado = new Scanner (System.in);
        
        
        
                //CODIGO DEL PROGRAMA
                
        System.out.println("        JUEGO DEL AHORACADO");
        System.out.println("------------------------------------");
        
        //Introduccion del nombre de los jugadores
        System.out.print("Jugador 1, introduzca su nombre: ");
        jugador1 = teclado.nextLine();        
        System.out.print("Jugador 2, introduzca su nombre: ");
        jugador2 = teclado.nextLine();
        
        //Comienza el juego con la introduccion de la palabra secreta por parte del jugador 1
        do {
            System.out.printf("%s introduzca la palabra que tendrá que adivinar %s: ", jugador1, jugador2);
            palabraSecreta = teclado.nextLine();
            palabraSecreta = palabraSecreta.toLowerCase();
            arrayPalabraSecreta = palabraSecreta.toCharArray();
        
            //Debido a que debe ser una sola letra no se debe permitir lo contrario
            for (char cadaCaracter : arrayPalabraSecreta ) {
                if (Character.isDigit(cadaCaracter) || Character.isSpaceChar(cadaCaracter) ) {
                    System.out.println("Lo introducido no es una sola palabra o una palabra válida...venga, tu puedes.\n");
                    palabravalida = false;
                }
            }
        }
        while (!palabravalida);
      
        //Rellenar el arrayAbecedario
        for (int i = 0; i < arrayAbecedario.length; i++) {
            if (i == 14){
                arrayAbecedario[14] = 'ñ';
            }
            else if (i < 14){
                arrayAbecedario[i] = (char) ('a' + i);                
            }
            else {
                arrayAbecedario[i] = (char) ('a' + i - 1);
            }            
        }

        
        System.out.println("\n\n\n\n\n\n\n");
        System.out.println("--------------PROHIBIDO SUBIR A PARTIR DE AQUI--------------------------");
            
        
        //La palabra sin adivinar debe tener la longitud de la palabra secreta
        arrayPalabraAdivinando = new char[palabraSecreta.length()]; 

        for (int i = 0; i < palabraSecreta.length(); i++) {
            arrayPalabraAdivinando[i] = '_';
        }
        
        //Comienza el jugador 2 a adivinar la palabra        
        while (!finDelJuego) {      
            
            //Texto de inicio del juego y nuevo intento
            if (turno == 1) {
                System.out.printf("%s, adivina la palabra, tiene %d intentos....\n\n", jugador2, intentos);                
            }
            else{
                System.out.printf("\nTe quedan %d intentos.\n", intentos);
            }
            
            //Mostrar las letras que estan disponibles y como lleva el jugador la palabra
            System.out.println("Las letras que quedan por decir son:");
            System.out.println("    " + Arrays.toString(arrayAbecedario));

            System.out.println("Así llevas la palabra adivinada:");
            System.out.println("    " + Arrays.toString(arrayPalabraAdivinando));

            //Introduccion del caracter candidato
            caracterCandidato = teclado.next().charAt(0);
            
            //Eliminar el caracter candidato del arrayAbecedario y sustituirlo por _
            for (int i = 0; i < arrayAbecedario.length; i++) {
                if (arrayAbecedario[i] == caracterCandidato) {
                    arrayAbecedario[i] = '_';
                }
            }

            //Comprobar si el caracter candidato se encuentra en la palabra secreta
            for (int i = 0; i < arrayPalabraSecreta.length; i++) {

                if (caracterCandidato == arrayPalabraSecreta[i]){
                    arrayPalabraAdivinando[i] = caracterCandidato;
                    caracterEncontrado = true;
                    
                    //Comprobar si arrayPalabraAdivinando = arrayPalabraSecreta para fin de juego
                    int sumatorioDeIguales = 0;
                    
                    for (int j = 0; j < arrayPalabraSecreta.length; j++) {
                        if (arrayPalabraAdivinando[j] == arrayPalabraSecreta[j] ) {
                            sumatorioDeIguales++;
                        }
                    }
                    
                    //Comprobar si la cantidad de comparaciones positivas es igual al numero de caracteres de la palabra
                    if (sumatorioDeIguales == arrayPalabraSecreta.length) {
                        sonIguales = true;
                    }
                    
                    //Si son iguales los dos arrays es fin de juego
                    if (sonIguales) {
                        finDelJuego = true;
                        System.out.printf("\nCORRECTO. TE HAS SALVADO :) \nLa palabra era %s.\n",palabraSecreta);
                    }
                }
            }
            
            //Si la letra introducida no está en la palabra
            if (!caracterEncontrado) {
                intentos--;
                System.out.printf("La letra \"%c\" no está en la palabra. Te quedan %d intentos.\n", caracterCandidato, intentos);
                System.out.println("Estas son las letras que no estan en la palabra:");

                //Si el jugador se queda sin intentos se acaba el juego
                if (intentos == 0){
                    finDelJuego = true;
                    System.out.printf("NO TE QUEDAN MAS OPORTUNIDADES, HAS SIDO AHORCADO :( \nLa palabra era %s.\n", palabraSecreta);
                }
            }
            
            //Pasar al siguiente turno
            turno++;
            
            //Resetear caracterEncontrado para el siguiente turno
            caracterEncontrado = false;

            
        }
    }
    
}

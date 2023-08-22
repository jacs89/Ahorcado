package array.ahorcado;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Jose Cabello jcabello86@gmail.com
 */
class OpcionMostrarDiccionarios {
    public void ejecutar() throws FileNotFoundException, IOException {
        
        char letra = '\0';        
        boolean entradaValida = false;
        
        Scanner teclado = new Scanner (System.in);
        
        while (!entradaValida) {
            System.out.println("Introduzca la inicial del diccionario que quiera mostrar.");
            String entrada = teclado.next();
            if (entrada.length() == 1 && Character.isLetter (entrada.charAt(0) )){
                letra = Character.toLowerCase (entrada.charAt(0) );
                entradaValida = true;
            }
            else{
                System.out.println("Entrada no válida. Introduzca una única letra.\n");
            }
        }
        String rutaDelArchivo = "Diccionarios/" + letra + ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader (rutaDelArchivo) )) {
            
            String linea;
            
            while ( (linea = reader.readLine() ) != null) {
                System.out.println(linea);                
            }
        }
        catch (IOException e) {
            System.out.println("No se pudo abrir el archivo.\n");
        }
    }    
}

package array.ahorcado;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Jose Cabello jcabello86@gmail.com
 */
class ClaseTablaPuntuaciones {

    private static final String RUTA_DE_ACCESO = "Puntuaciones/Puntuaciones.txt";
    
    public void ejecutar() throws IOException {
        
        File archivoPuntuaciones = new File (RUTA_DE_ACCESO);
        
        if (!archivoPuntuaciones.exists() ){
            crearArchivoPuntuaciones();
            mostrarContenidoArchivoPuntuaciones();
        }
        else{
            mostrarContenidoArchivoPuntuaciones();
        }   
    }

    
    private void crearArchivoPuntuaciones() throws IOException {
        File carpetaPuntuaciones = new File ("Puntuaciones");
        carpetaPuntuaciones.mkdirs();
        
        try (BufferedWriter escritoPuntuaciones = new BufferedWriter(new FileWriter (RUTA_DE_ACCESO) )){
            System.out.println("El archivo de puntuaciones no existia. Ha sido creado.");
            escritoPuntuaciones.write("No existen puntuaciones, juegue alguna partida. \n");            
        }
        catch (IOException e) {
            System.out.println("Error al crear el archivo de puntuaciones.");
        }
    }

    
    private void mostrarContenidoArchivoPuntuaciones() {
        try (BufferedReader lectorPuntuaciones = new BufferedReader(new FileReader (RUTA_DE_ACCESO) )) {
            
            String linea;
            while ( (linea = lectorPuntuaciones.readLine() ) != null) {
                System.out.println(linea);
            }
        }
        catch (IOException e) {
            System.out.println("Error al leer el archivo de puntuaciones.");
        }
    }    
}

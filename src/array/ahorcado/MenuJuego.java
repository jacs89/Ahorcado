package array.ahorcado;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @version 2.6.2
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
 * - Interface gráfica del menú
 * - Añadir hilo musical.
 * 
 * @todolist
 * - Seleccionar 1 jugador.
 * - Tabla de puntuaciones.
 * 
 * - Ordenar carpetas del programa
 * - Ver por que no funcionó el primer intento de audio
 * - Traducir las demas partes del juego a FX
 * - Añadir boton para apagar hilo musical.
 * - Mover botones. Los de juego arriba, los demas abajo.
 * - Cambiar accion de los botones al pasar por encima.
 * - Cambiar estilo de los botones.
 */
public class MenuJuego extends Application {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        launch(args);                  
    }

    @Override
    public void start (Stage escenario) throws IOException {
        
        //Titulo de la ventana de juego
        escenario.setTitle("JUEGO DEL AHORCADO");        
        
        //Panel que lo contendrá todo
        StackPane panelRaiz = new StackPane();
        
        //Crear la escena con el contenedor
        Scene escena = new Scene(panelRaiz, 800, 600);
     
        //Cargar la imagen de fondo
        Image rutaImagenFondo = new Image("fondo.jpg");
        ImageView fondoView = new ImageView(rutaImagenFondo);        
         
        //El panel que contendrá los botones
        GridPane panelBotones = new GridPane();
        panelBotones.setAlignment(Pos.CENTER);
        panelBotones.setTranslateY(-100);
        panelBotones.setHgap(30);
        panelBotones.setVgap(30);        
        
        //Botones
        Button botonUnJugador    = new Button("Un Jugador");
        Button botonDosJugadores = new Button("Dos Jugadores");
        Button botonPuntuaciones = new Button("Puntuaciones");
        Button botonDiccionarios = new Button("Mostrar diccionarios");
        
        Button botonSalida       = new Button("Terminar");
        
        //Tamaño botones
        botonUnJugador.setPrefSize    (150, 50);
        botonDosJugadores.setPrefSize (150, 50);
        botonPuntuaciones.setPrefSize (150, 50);
        botonDiccionarios.setPrefSize (150, 50);        
        
        botonSalida.setPrefSize       (200, 60);
        botonSalida.setTranslateY(200);
        
        //Accion de los botones        
        botonUnJugador.setOnAction(event -> {
            try {
                ClaseUnJugador unJugador = new ClaseUnJugador();
                unJugador.ejecutar();                
            } 
            catch (Exception ex) {
                Logger.getLogger(MenuJuego.class.getName()).log(Level.SEVERE, null, ex);
            }
        });        
        
        botonDosJugadores.setOnAction(event -> {
            ClaseDosJugadores dosJugadores = new ClaseDosJugadores();
            dosJugadores.ejecutar();
        });
        
        botonPuntuaciones.setOnAction(event -> {
            ClaseTablaPuntuaciones tablaPuntuaciones = new ClaseTablaPuntuaciones();
            try {
                tablaPuntuaciones.ejecutar();
            }
            catch (IOException ex) {
                Logger.getLogger(MenuJuego.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        botonDiccionarios.setOnAction(event -> {
            ClaseMostrarDiccionarios mostrarDiccionarios = new ClaseMostrarDiccionarios();
            try {
                mostrarDiccionarios.ejecutar();
            }
            catch (IOException ex) {
                Logger.getLogger(MenuJuego.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        botonSalida.setOnAction (event -> {
            escenario.close();
        });
        
        //Añadir botones al panel de botones y ordenacion dentro del panel
        panelBotones.add(botonUnJugador,   0, 0);
        panelBotones.add(botonDosJugadores,1, 0);
        panelBotones.add(botonPuntuaciones,0, 1);
        panelBotones.add(botonDiccionarios,1, 1);
        
        String rutaAudio = "crow.wav";
        reproducirSonido(rutaAudio);
        /*
        //Musica y efectos de audio
        Media audioFondo = new Media("crow.wav");
        MediaPlayer mediaPlayer = new MediaPlayer(audioFondo);
        /*
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
     */
    
        //Al panel raiz añadirle el fondo, el panel con los botones y el boton de salida        
        panelRaiz.getChildren().addAll(fondoView, panelBotones, botonSalida);        
        escenario.setScene(escena);
        escenario.show();        
    }   
    
    public void reproducirSonido(String nombreSonido){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
        catch(UnsupportedAudioFileException ex) {
           System.err.println("Error al reproducir el sonido.");
        }
        catch(IOException ex) {
           System.err.println("Error al leer el archivo de audio.");
        }
        catch(LineUnavailableException ex) {
           System.err.println("Error al reproducir el sonido.");
        }
     }
}
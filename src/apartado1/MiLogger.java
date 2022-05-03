package apartado1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Bello
 * Actividad 6.1. Crea una aplicación que realice los siguientes pasos:

Solicita el nombre del usuario que va a utilizar la aplicación. El login tiene una longitud de 8 caracteres y está compuesto únicamente por letras minúsculas.
Solicita al usuario el nombre de un fichero que quiere mostrar. El nombre del fichero es como máximo de 8 caracteres y tiene una extensión de 3 caracteres.
Visualiza en pantalla el contenido del fichero.
 */
public class MiLogger {

    public static void main(String[] args) throws IOException {
        Scanner teclado = new Scanner(System.in);
        //configuracion del logger a utilizar.
        Logger logger = Logger.getLogger("Mylog");
        FileHandler fh;
        //fichero asociado, fichero de registro. Ademas indicamos con el true que se añadan los registros a los que ya disponemos en el fichero.
        fh = new FileHandler("logger.log", true);
        logger.addHandler(fh);
        //tipo de eventos que deseo registrar.
        logger.setLevel(Level.ALL);
        //indicamos que no deseams mostrar los registros por pantalla.
        logger.setUseParentHandlers(false);
        //Establecedos el formato del fichero, en mi caso un fichero simple.
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
        //Primer registro.
        logger.log(Level.INFO, "-Inicio aplicación (Mario Bello García)-");
        
        
        
        
        
        boolean correcto = true;
        while (correcto) {

            try {

                System.out.println("Ingrese el nombre de usuario"+"\n"
                                  +"-longitud de 8 caracteres de letras en minusculas-");
                String usuario = teclado.nextLine();
                Pattern pat = null;
                Matcher mat = null;
                pat = Pattern.compile("[a-z]{8}");//8caracteres y solo minusculas.
                mat = pat.matcher(usuario);

                if (mat.find()) {
                    System.out.println("El usuario es correcto, continua con el proceso.");
                    logger.log(Level.INFO, "-Usuario correcto-");
                    correcto = false;

                } else {
                    System.out.println("El usuario es incorrecto, repita el proceso y recuerde el formato.");
                    logger.log(Level.INFO, "-Usuario incorrecto-");
                    correcto = true;
                }

            } catch (Exception e) {

            }

        }
        correcto = true;
        while (correcto) {

            try {

                System.out.println("Ingrese el nombre del fichero"+"\n"
                        + "-longitud entre 1 y 8 caracteres de letras en minusculas, con una extension de 3 caracteres en letras minusculas-");
                String fichero = teclado.nextLine();
                Pattern pat = null;
                Matcher mat = null;
                pat = Pattern.compile("[a-z]{1,8}\\.[a-z]{3}");
                mat = pat.matcher(fichero);

                if (mat.find()) {
                    logger.log(Level.INFO, "-fichero correcto-");
                    System.out.println("El nombre de fichero es correcto");
                    File archivo = new File(fichero);
                    FileReader fr = new FileReader(archivo);
                    BufferedReader br = new BufferedReader(fr);
                    String linea;
                        System.out.println("##################################"+"\n"
                                         + "              Fichero             "+"\n"
                                         + "##################################");
                    //Bucle while para ver lineas del fichero por pantalla.
                    while ((linea = br.readLine()) != null) {
                        System.out.println(linea);
                    }

                    br.close();
                    fr.close();
                    logger.log(Level.INFO, "-fichero entregado-");
                    
                    correcto = false;
                    

                } else {
                    logger.log(Level.INFO, "-fichero incorrecto-");
                    System.out.println("Nombre de fichero incorrecto, asegurese de cumplir el formato indicado.");
                    correcto = true;
                }
            } catch (Exception e) {

            }
        }
    }
}

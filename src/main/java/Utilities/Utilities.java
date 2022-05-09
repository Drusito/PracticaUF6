package Utilities;

import java.util.Scanner;

public final class Utilities {

    private static Scanner input = new Scanner(System.in);

    private Utilities(){}

    /**
     * Este método sirve para leer un entero de teclado con control de errores
     * @param mensaje string con el texto que se le quiere mostrar al usuario
     * @param min valor mínimo aceptado
     * @param max     Valor máximo aceptado
     * @return int después de pasar el control de errores
     */
    public static int leerIntLimites(String mensaje, int min, int max) {
        int opcion = 0;
        boolean valorCorrecto;

        do {
            System.out.println(mensaje);

            valorCorrecto = input.hasNextInt();

            if (!valorCorrecto) {
                System.out.println("ERROR: No has introducido un entero");
                input.nextLine();
            } else {
                opcion = input.nextInt();
                input.nextLine();

                if (opcion < min || opcion > max) {
                    System.out.println( "ERROR: Opción no correcta");
                    valorCorrecto = false;
                }
            }

        } while (!valorCorrecto);

        return opcion;
    }

    /**
     * Método para leer un float
     * @param mensaje mensaje que se le quiere mostrar al usuario
     * @return float
     */
    public static float leerFloat(String mensaje) {
        float opcion = 0;
        boolean valorCorrecto;

        do {
            System.out.println(mensaje);

            valorCorrecto = input.hasNextFloat();

            if (!valorCorrecto) {
                System.out.println("ERROR: No has introducido un entero");
                input.nextLine();
            } else {
                opcion = input.nextFloat();
                input.nextLine();
            }

        } while (!valorCorrecto);

        return opcion;
    }

    /**
     * Método para leer un float con control de errores
     * @param mensaje mensaje que se le quiere mostrar al usuario
     * @param min valor mínimo aceptado
     * @param max: valor máximo aceptado
     * @return float después del control de errores
     */
    public static float leerFloatLimite(String mensaje, int min, int max) {
        float opcion = 0;
        boolean valorCorrecto;

        do {
            System.out.println(mensaje);

            valorCorrecto = input.hasNextFloat();

            if (!valorCorrecto) {
                System.out.println("ERROR: No has introducido un entero");
            } else {
                opcion = input.nextFloat();
                if(opcion < min || opcion > max){
                    System.out.println("ERROR: Valor fuera de rango");
                    valorCorrecto = false;
                }
            }
            input.nextLine();

        } while (!valorCorrecto);

        return opcion;
    }

    /**
     * Método para leer un string
     * @param mensaje mensaje que se le quiere mostrar al usuario
     * @return string con texto escrito por el usuario
     */
    public static String leerString(String mensaje) {
        System.out.println(mensaje);

        return input.nextLine();
    }

    /**
     * Método para leer un char
     * @param mensaje mensaje que se le quiere mostrar al usuario
     * @return un char
     */
    public static char leerChar(String mensaje){
        String palabra;
        palabra = input.next();
        input.nextLine();

        return palabra.charAt(0);
    }
}
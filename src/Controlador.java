import java.util.Scanner;


public class Controlador {
    public void ejecucionPrograma() {
        System.out.println("Bienvenido/a al conversor de monedas");
        Scanner lectura = new Scanner(System.in);
        Servicio servicio = new Servicio();

        while (true) {
            System.out.println("""
                
                ----------------------------------------------------------------
                
                Ingrese una opción para convertir
                
                1) Dólar estadounidense => Peso argentino
                2) Peso argentino => Dólar estadounidense
                3) Dólar estadounidense => Peso uruguayo
                4) Peso uruguayo => Dólar estadounidense
                5) Dólar estadounidense => Peso chileno
                6) Peso chileno => Dólar estadounidense
                7) Salir
                
                ----------------------------------------------------------------
                
                """);

            try {
                var opcion = Integer.valueOf(lectura.nextLine());

                if (opcion < 1 || opcion > 7) {
                    System.out.println("\nPor favor ingrese una opción válida");
                    continue;
                } else if (opcion == 7) {
                    System.out.println("\nGracias por utilizar el programa. ¡Adiós!");
                    return;
                }

                System.out.println("\nIngrese el valor a convertir\n");
                var valor_a_convertir = Float.valueOf(lectura.nextLine());
                Respuesta respuesta = null;

                switch (opcion) {
                    case 1:
                        respuesta = servicio.consultaApi("USD", "ARS", valor_a_convertir);
                        break;
                    case 2:
                        respuesta = servicio.consultaApi("ARS", "USD", valor_a_convertir);
                        break;
                    case 3:
                        respuesta = servicio.consultaApi("USD", "UYU", valor_a_convertir);
                        break;
                    case 4:
                        respuesta = servicio.consultaApi("UYU", "USD", valor_a_convertir);
                        break;
                    case 5:
                        respuesta = servicio.consultaApi("USD", "CLP", valor_a_convertir);
                        break;
                    case 6:
                        respuesta = servicio.consultaApi("CLP", "USD", valor_a_convertir);
                        break;
                    default:
                        System.out.println("Por favor ingrese una opción válida");
                        break;
                }
                if (respuesta != null) {
                    System.out.printf("\nEl valor de %.2f [%s], con una tasa de conversión del %.2f, corresponde al valor final de %.2f [%s]\n", valor_a_convertir, respuesta.base_code(), respuesta.conversion_rate(), respuesta.conversion_result(), respuesta.target_code());
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

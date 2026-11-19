import java.util.Locale;

/**
 * Metodo de Euler para y' = x - y, y(0) = 1, x_final = 0.3
 * Solucion exacta: y(x) = x - 1 + 2e^(-x)
 */
public class MetodoEuler {

    /** f(x, y) = x - y */
    public static double evaluarFuncion(double x, double y) {
        return x - y;
    }

    /** Solucion analitica exacta de y' = x - y, y(0) = 1 */
    public static double solucionExacta(double x) {
        return x - 1.0 + 2.0 * Math.exp(-x);
    }

    /** Un paso de Euler: y_{n+1} = y_n + h * f(x_n, y_n) */
    public static double pasoEuler(double x, double y, double h) {
        double pendiente = evaluarFuncion(x, y);
        return y + h * pendiente;
    }

    /** Resuelve el PVI con paso h e imprime la tabla comparativa. Devuelve el error absoluto final. */
    public static double resolver(double x0, double y0, double h, double xFinal) {
        // Criterio de paro (numero total de pasos N)
        int nPasos = (int) Math.round((xFinal - x0) / h);
        double x = x0;
        double y = y0;
        double error = 0.0;

        System.out.printf(Locale.US, "%n=== Euler con h = %s (N = %d) ===%n", h, nPasos);
        System.out.println("n\t x_n\t y_n (Euler)\t y_exacta\t Error abs.");
        System.out.println("---------------------------------------------------------");
        System.out.printf(Locale.US, "%d\t %.4f\t %.6f\t %.6f\t %.6f%n",
                0, x, y, solucionExacta(x), Math.abs(solucionExacta(x) - y));

        for (int i = 0; i < nPasos; i++) {
            y = pasoEuler(x, y, h);
            x = x0 + (i + 1) * h; // evita acumular error de redondeo
            double exacta = solucionExacta(x);
            error = Math.abs(exacta - y);
            System.out.printf(Locale.US, "%d\t %.4f\t %.6f\t %.6f\t %.6f%n",
                    i + 1, x, y, exacta, error);
        }
        return error;
    }

    public static void main(String[] args) {
        double x0 = 0.0;
        double y0 = 1.0;
        double xFinal = 0.3;

        // Si se pasa h por terminal se usa ese; si no, se corren los tres valores pedidos
        double[] pasos = (args.length > 0)
                ? new double[] { Double.parseDouble(args[0]) }
                : new double[] { 0.1, 0.05, 0.01 };

        System.out.println("Resumen de error absoluto en x = " + xFinal);
        double[] errores = new double[pasos.length];
        for (int k = 0; k < pasos.length; k++) {
            errores[k] = resolver(x0, y0, pasos[k], xFinal);
        }

        System.out.println("\n--- Resumen ---");
        System.out.println("h\t Error abs. en x_final");
        for (int k = 0; k < pasos.length; k++) {
            System.out.printf(Locale.US, "%s\t %.8f%n", pasos[k], errores[k]);
        }
    }
}

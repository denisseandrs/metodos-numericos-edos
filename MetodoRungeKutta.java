import java.util.Locale;

/**
 * Metodo de Runge-Kutta de 4to orden para y' = x + y, y(0) = 1, x_final = 0.3
 * Solucion exacta: y(x) = 2e^x - x - 1
 */
public class MetodoRungeKutta {

    /** f(x, y) = x + y */
    public static double evaluarFuncion(double x, double y) {
        return x + y;
    }

    /** Solucion analitica exacta de y' = x + y, y(0) = 1 */
    public static double solucionExacta(double x) {
        return 2.0 * Math.exp(x) - x - 1.0;
    }

    /** Un paso de RK4 */
    public static double pasoRK4(double x, double y, double h) {
        double k1 = evaluarFuncion(x, y);
        double k2 = evaluarFuncion(x + h / 2.0, y + (h / 2.0) * k1);
        double k3 = evaluarFuncion(x + h / 2.0, y + (h / 2.0) * k2);
        double k4 = evaluarFuncion(x + h, y + h * k3);
        return y + (h / 6.0) * (k1 + 2.0 * k2 + 2.0 * k3 + k4);
    }

    /** Resuelve el PVI con paso h e imprime la tabla comparativa. Devuelve el error absoluto final. */
    public static double resolver(double x0, double y0, double h, double xFinal) {
        int nPasos = (int) Math.round((xFinal - x0) / h);
        double x = x0;
        double y = y0;
        double error = 0.0;

        System.out.printf(Locale.US, "=== RK4 con h = %s (N = %d) ===%n", h, nPasos);
        System.out.println("n\t x_n\t y_n (RK4)\t y_exacta\t Error abs.");
        System.out.println("---------------------------------------------------------");
        System.out.printf(Locale.US, "%d\t %.4f\t %.8f\t %.8f\t %.2e%n",
                0, x, y, solucionExacta(x), Math.abs(solucionExacta(x) - y));

        for (int i = 0; i < nPasos; i++) {
            y = pasoRK4(x, y, h);
            x = x0 + (i + 1) * h;
            double exacta = solucionExacta(x);
            error = Math.abs(exacta - y);
            System.out.printf(Locale.US, "%d\t %.4f\t %.8f\t %.8f\t %.2e%n",
                    i + 1, x, y, exacta, error);
        }
        return error;
    }

    public static void main(String[] args) {
        double x0 = 0.0;
        double y0 = 1.0;
        double h = (args.length > 0) ? Double.parseDouble(args[0]) : 0.1;
        double xFinal = 0.3;

        resolver(x0, y0, h, xFinal);
    }
}

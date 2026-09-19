package arslan;

import java.util.Scanner;

public class Secante {
    // Función definida: Math.pow(x, 2) - 4.5x + 4.16
    private double x0, x1, x2, fx0, fx1, error;
    private final double tol = 0.001;
    private final int maxIter = 20;
    private Scanner sc = new Scanner(System.in);

    public Secante() {
        datos();
    }

    public Secante(double x0, double x1) {
        this.x0 = x0;
        this.x1 = x1;
    }

    public void datos() {
        boolean run;
        System.out.println("Ingrese los valores iniciales [x0, x1]");

        do {
            System.out.print("x0 = ");
            String temp0 = sc.nextLine();
            run = !validate(temp0);
            if (!run) {
                this.x0 = Double.parseDouble(temp0);
            }
        } while (run);

        do {
            System.out.print("x1 = ");
            String temp1 = sc.nextLine();
            run = !validate(temp1);
            if (!run) {
                this.x1 = Double.parseDouble(temp1);
            }
        } while (run);
    }

    boolean validate(String temp) {
        try {
            Double.parseDouble(temp);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("¡Número inválido! Ingrese un valor decimal con PUNTO.");
            return false;
        }
    }

    double f(double x) {
        return Math.pow(x, 2) - (4.5 * x) + 4.16;
    }

    double getError(double xr, double xrOld) {
        return (Math.abs((xr - xrOld) / xr)) * 100;
    }

    public String getResults() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        double currX0 = this.x0;
        double currX1 = this.x1;
        double currX2;
        String errorStr;

        sb.append("\n-----------------------------------------------------------------------------------------\n");
        sb.append(String.format("%-3s | %-8s | %-8s | %-8s | %-8s | %-8s | %-8s%n",
                "i", "x(i-1)", "xi", "f(x(i-1))", "f(xi)", "x(i+1)", "Error (%)"));
        sb.append("-----------------------------------------------------------------------------------------\n");

        do {
            this.fx0 = f(currX0);
            this.fx1 = f(currX1);

            if (Math.abs(this.fx1 - this.fx0) < 1e-12) {
                sb.append("La diferencia entre f(xi) y f(x(i-1)) es casi cero. El método se detiene.\n");
                return sb.toString();
            }

            // Fórmula de la Secante: x(i+1) = xi - (f(xi) * (xi - x(i-1))) / (f(xi) - f(x(i-1)))
            currX2 = currX1 - (this.fx1 * (currX1 - currX0)) / (this.fx1 - this.fx0);

            if (i > 0) {
                this.error = getError(currX2, currX1);
                errorStr = String.format("%.4f", this.error);
            } else {
                errorStr = "----";
            }

            sb.append(String.format("%-3d | %-8.4f | %-8.4f | %-8.4f | %-8.4f | %-8.4f | %-8s%n",
                    i, currX0, currX1, this.fx0, this.fx1, currX2, errorStr));

            if (i > 0 && this.error < this.tol) {
                sb.append("-----------------------------------------------------------------------------------------\n");
                sb.append("¡Raíz encontrada con éxito en la iteración ").append(i).append("!\n");
                sb.append(String.format("Valor aproximado de la raíz: %.5f%n", currX2));
                return sb.toString();
            }

            // Actualización de variables para la siguiente iteración
            currX0 = currX1;
            currX1 = currX2;
            i++;
        } while (i < this.maxIter);

        sb.append("-----------------------------------------------------------------------------------------\n");
        sb.append("Se alcanzó el número máximo de iteraciones.\n");
        sb.append(String.format("Última aproximación (xr): %.5f%n", currX1));

        return sb.toString();
    }

    public void operate() {
        System.out.print(getResults());
    }

    public static void main(String[] args) {
        System.out.println("============== METODO DE LA SECANTE ===============");
        System.out.println("Formula utilizada: X^2 - 4.5x + 4.16");
        System.out.println("-----------------------------------------------------------------------------------------");
        Secante metodo = new Secante();
        metodo.operate();
    }
}
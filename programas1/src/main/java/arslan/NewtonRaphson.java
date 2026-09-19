package arslan;

import java.util.Scanner;

public class NewtonRaphson {
    // Función definida: Math.pow(x, 2) - 4.5x + 4.16
    // Derivada: 2x - 4.5
    private double x0, x1, fx0, dfx0, error;
    private final double tol = 0.001;
    private final int maxIter = 20;
    private Scanner sc = new Scanner(System.in);

    public NewtonRaphson() {
        datos();
    }

    public NewtonRaphson(double x0) {
        this.x0 = x0;
    }

    public void datos() {
        boolean run;
        System.out.println("Ingrese el valor inicial x0:");

        do {
            System.out.print("x0 = ");
            String temp = sc.nextLine();
            run = !validate(temp);
            if (!run) {
                this.x0 = Double.parseDouble(temp);
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

    double df(double x) {
        return (2 * x) - 4.5; // Derivada de la función
    }

    double getError(double xr, double xrOld) {
        return (Math.abs((xr - xrOld) / xr)) * 100;
    }

    public String getResults() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        double currentX = this.x0;
        double nextX;
        String errorStr;

        sb.append("\n-----------------------------------------------------------------------------------------\n");
        sb.append(String.format("%-3s | %-8s | %-8s | %-8s | %-8s | %-8s%n",
                "i", "xi", "f(xi)", "f'(xi)", "x(i+1)", "Error (%)"));
        sb.append("-----------------------------------------------------------------------------------------\n");

        do {
            this.fx0 = f(currentX);
            this.dfx0 = df(currentX);

            if (Math.abs(this.dfx0) < 1e-12) {
                sb.append("La derivada es cercana a cero. El método se detiene para evitar división entre cero.\n");
                return sb.toString();
            }

            nextX = currentX - (this.fx0 / this.dfx0);

            if (i > 0) {
                this.error = getError(nextX, currentX);
                errorStr = String.format("%.4f", this.error);
            } else {
                errorStr = "----";
            }

            sb.append(String.format("%-3d | %-8.4f | %-8.4f | %-8.4f | %-8.4f | %-8s%n",
                    i, currentX, this.fx0, this.dfx0, nextX, errorStr));

            if (i > 0 && this.error < this.tol) {
                sb.append("-----------------------------------------------------------------------------------------\n");
                sb.append("¡Raíz encontrada con éxito en la iteración ").append(i).append("!\n");
                sb.append(String.format("Valor aproximado de la raíz: %.5f%n", nextX));
                return sb.toString();
            }

            currentX = nextX;
            i++;
        } while (i < this.maxIter);

        sb.append("-----------------------------------------------------------------------------------------\n");
        sb.append("Se alcanzó el número máximo de iteraciones.\n");
        sb.append(String.format("Última aproximación (xr): %.5f%n", currentX));

        return sb.toString();
    }

    public void operate() {
        System.out.print(getResults());
    }

    public static void main(String[] args) {
        System.out.println("============== METODO DE NEWTON-RAPHSON ===============");
        System.out.println("Formula utilizada: X^2 - 4.5x + 4.16");
        System.out.println("-----------------------------------------------------------------------------------------");
        NewtonRaphson metodo = new NewtonRaphson();
        metodo.operate();
    }
}
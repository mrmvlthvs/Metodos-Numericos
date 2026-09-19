package arslan;

import java.util.Scanner;

public class Biseccion {
    // Función definida en el programa: Math.pow(x, 2) - 4.5x + 4.16
    private double error, xr1, fa, fb, fxr, a, b;
    private final double tol = 0.001;
    private final int maxIter = 20;
    private Scanner sc = new Scanner(System.in);

    // Constructor por defecto
    public Biseccion() {
        datos();
    }

    // Constructor con parámetros
    public Biseccion(double a, double b) {
        if (!setIntervalo(a, b)) {
            System.out.println("El intervalo inicial proporcionado no es válido.");
        }
    }

    public void datos() {
        boolean run;
        double tempA = 0, tempB = 0;
        System.out.println("Ingrese los intervalos [a, b]");

        do {
            System.out.print("a = ");
            String atemp = sc.nextLine();
            run = !validate(atemp);
            if (!run) {
                tempA = Double.parseDouble(atemp);
            }
        } while (run);

        do {
            System.out.print("b = ");
            String btemp = sc.nextLine();
            run = !validate(btemp);
            if (!run) {
                tempB = Double.parseDouble(btemp);
            }
        } while (run);

        if (!setIntervalo(tempA, tempB)) {
            System.out.println("Por favor, intente nuevamente con otro intervalo.\n");
            datos();
        }
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

    public String getResults() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        double xrOld = 0;
        double aTemp = this.a;
        double bTemp = this.b;
        String errorStr;

        sb.append("\n-----------------------------------------------------------------------------------------\n");
        sb.append(String.format("%-3s | %-8s | %-8s | %-8s | %-8s | %-8s | %-8s | %-8s%n",
                "i", "a", "xr", "b", "f(a)", "f(xr)", "f(b)", "Error (%)"));
        sb.append("-----------------------------------------------------------------------------------------\n");

        do {
            this.fa = f(aTemp);
            this.fb = f(bTemp);
            this.xr1 = xr(aTemp, bTemp);
            this.fxr = f(this.xr1);

            if (i > 0) {
                this.error = getError(this.xr1, xrOld);
                errorStr = String.format("%.4f", this.error);
            } else {
                errorStr = "----";
            }

            sb.append(String.format("%-3d | %-8.4f | %-8.4f | %-8.4f | %-8.4f | %-8.4f | %-8.4f | %-8s%n",
                    i, aTemp, this.xr1, bTemp, this.fa, this.fxr, this.fb, errorStr));

            if (i > 0 && this.error < this.tol) {
                sb.append("-----------------------------------------------------------------------------------------\n");
                sb.append("¡Raíz encontrada con éxito en la iteración ").append(i).append("!\n");
                sb.append(String.format("Valor aproximado de la raíz: %.5f%n", this.xr1));
                return sb.toString();
            }

            xrOld = this.xr1;
            if (this.fa * this.fxr < 0) {
                bTemp = this.xr1;
            } else {
                aTemp = this.xr1;
            }

            i++;
        } while (i < this.maxIter);

        sb.append("-----------------------------------------------------------------------------------------\n");
        sb.append("Se alcanzó el número máximo de iteraciones.\n");
        sb.append(String.format("Última aproximación (xr): %.5f%n", this.xr1));

        return sb.toString();
    }

    public void operate() {
        System.out.print(getResults());
    }

    boolean setIntervalo(double a, double b) {
        if (f(a) * f(b) >= 0) {
            System.out.println("El intervalo no es válido, f(a) y f(b) deben tener signos opuestos.");
            return false;
        } else {
            this.a = a;
            this.b = b;
            return true;
        }
    }

    double f(double x) {
        return Math.pow(x, 2) - (4.5 * x) + 4.16;
    }

    double xr(double a, double b) {
        return (a + b) / 2;
    }

    double getError(double xr, double xrOld) {
        return (Math.abs((xr - xrOld) / xr)) * 100;
    }

    public static void main(String[] args) {
        System.out.println("===== METODO DE BISECCION =====");
        System.out.println("Formula utilizada: X^2 - 4.5x + 4.16");
        System.out.println("-----------------------------------------------------------------------------------------");
        Biseccion metodo = new Biseccion();
        metodo.operate();
    }
}
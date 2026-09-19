package arslan;

import javax.swing.*;

public class GuiJOP {
    void biseccion(){
        Biseccion met = new Biseccion();
        JOptionPane.showMessageDialog(null, met.getResults(),
                "RESULTADOS DE BISECCION", JOptionPane.PLAIN_MESSAGE);

    }

    void newtonraphson(){
        NewtonRaphson met = new NewtonRaphson();
        JOptionPane.showMessageDialog(null, met.getResults(),
                "RESULTADOS DE NEWTON-RAPHSON", JOptionPane.PLAIN_MESSAGE);

    }

    void reglafalsa(){
        ReglaFalsa met = new ReglaFalsa();
        JOptionPane.showMessageDialog(null, met.getResults(),
                "RESULTADOS DE REGLA FALSA", JOptionPane.PLAIN_MESSAGE);

    }

    void secante(){
        Secante met = new Secante();
        JOptionPane.showMessageDialog(null, met.getResults(),
                "RESULTADOS DE SECANTE", JOptionPane.PLAIN_MESSAGE);

    }
}

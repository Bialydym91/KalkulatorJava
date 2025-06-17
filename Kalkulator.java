package kalkulator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Kalkulator implements ActionListener {
    JFrame okno;
    JPanel panel;
    JButton[] numery = new JButton[10];
    JButton[] dzialania = new JButton[8];
    JButton dodaj, odejmij, razy, dziel;
    JButton kropka, rowne, usun, odwrot;
    JTextField poleTextowe;
    boolean czyNowaLiczba = false;

    double numer1 = 0, numer2 = 0, wynik = 0;
    String operator;

    public Kalkulator() {
        okno = new JFrame("Kalkulator");
        okno.setSize(300, 340);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setLayout(null);

        poleTextowe = new JTextField();
        Font boldFont = poleTextowe.getFont().deriveFont(Font.BOLD, 18);
        poleTextowe.setFont(boldFont);
        poleTextowe.setHorizontalAlignment(JTextField.RIGHT);
        poleTextowe.setBounds(30, 25, 220, 25);
        poleTextowe.setEditable(false);

        dodaj = new JButton("+");
        odejmij = new JButton("-");
        razy = new JButton("*");
        dziel = new JButton("/");
        kropka = new JButton(".");
        rowne = new JButton("=");
        usun = new JButton("c");
        odwrot = new JButton("+/-");

        dzialania[0] = dodaj;
        dzialania[1] = odejmij;
        dzialania[2] = razy;
        dzialania[3] = dziel;
        dzialania[4] = kropka;
        dzialania[5] = rowne;
        dzialania[6] = usun;
        dzialania[7] = odwrot;

        for (int i = 0; i < 8; i++) {
            dzialania[i].addActionListener(this);
            dzialania[i].setFocusable(true);
        }

        for (int i = 0; i < 10; i++) {
            numery[i] = new JButton(String.valueOf(i));
            numery[i].addActionListener(this);
            numery[i].setFont(boldFont);
            numery[i].setFocusable(false);
        }

        odwrot.setBounds(65, 260, 60, 30);
        rowne.setBounds(150, 260, 95, 30);

        panel = new JPanel();
        panel.setBounds(30, 60, 220, 180);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numery[1]);
        panel.add(numery[2]);
        panel.add(numery[3]);
        panel.add(dodaj);
        panel.add(numery[4]);
        panel.add(numery[5]);
        panel.add(numery[6]);
        panel.add(odejmij);
        panel.add(numery[7]);
        panel.add(numery[8]);
        panel.add(numery[9]);
        panel.add(razy);
        panel.add(numery[0]);
        panel.add(usun);
        panel.add(kropka);
        panel.add(dziel);

        okno.add(poleTextowe);
        okno.add(odwrot);
        okno.add(rowne);
        okno.add(panel);
        okno.setVisible(true);
    }

    public static void main(String[] args) {
        Kalkulator kalkulator = new Kalkulator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

   
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numery[i]) {
                if (czyNowaLiczba) {
                    poleTextowe.setText(String.valueOf(i));
                    czyNowaLiczba = false;
                } else {
                    poleTextowe.setText(poleTextowe.getText().concat(String.valueOf(i)));
                }
                return;
            }
        }

  
        if (e.getSource() == kropka) {
            if (!poleTextowe.getText().contains(".")) {
                poleTextowe.setText(poleTextowe.getText().concat("."));
            }
            return;
        }

        if (e.getSource() == dodaj) {
            numer1 = Double.parseDouble(poleTextowe.getText());
            operator = "+";
            czyNowaLiczba = true;
            return;
        }

        if (e.getSource() == odejmij) {
            numer1 = Double.parseDouble(poleTextowe.getText());
            operator = "-";
            czyNowaLiczba = true;
            return;
        }

        if (e.getSource() == razy) {
            numer1 = Double.parseDouble(poleTextowe.getText());
            operator = "*";
            czyNowaLiczba = true;
            return;
        }

        if (e.getSource() == dziel) {
            numer1 = Double.parseDouble(poleTextowe.getText());
            operator = "/";
            czyNowaLiczba = true;
            return;
        }


        if (e.getSource() == odwrot) {
            String tekst = poleTextowe.getText();
            if (!tekst.isEmpty()) {
                double temp = Double.parseDouble(tekst);
                temp *= -1;
                poleTextowe.setText(String.valueOf(temp));
            }
            return;
        }


        if (e.getSource() == rowne) {
            numer2 = Double.parseDouble(poleTextowe.getText());
            switch (operator) {
                case "+" -> wynik = numer1 + numer2;
                case "-" -> wynik = numer1 - numer2;
                case "*" -> wynik = numer1 * numer2;
                case "/" -> wynik = numer2 != 0 ? numer1 / numer2 : 0;
            }
            poleTextowe.setText(String.valueOf(wynik));
            numer1 = wynik;
            czyNowaLiczba = true;
            return;
        }


        if (e.getSource() == usun) {
            poleTextowe.setText("");
            numer1 = 0;
            numer2 = 0;
            wynik = 0;
            operator = "";
        }
    }
}

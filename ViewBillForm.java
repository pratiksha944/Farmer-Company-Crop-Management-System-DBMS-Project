package gui;

import javax.swing.*;
import java.awt.*;

public class ViewBillForm {

    public ViewBillForm(String name){

        JFrame f = new JFrame("My Bill");
        f.setSize(400,300);
        f.setLocationRelativeTo(null);

        JLabel lbl = new JLabel("Bills of " + name);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);

        f.add(lbl);

        f.setVisible(true);
    }
}
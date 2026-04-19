package gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class FarmerCropForm {

    public FarmerCropForm(String name, JFrame dashboard){

        JFrame f = new JFrame("Add Crop");
        f.setSize(400,300);

        JTextField txtCrop = new JTextField();
        JTextField txtQty = new JTextField();

        JButton btnSubmit = new JButton("Submit");
        JButton btnBack = new JButton("Back");

        f.setLayout(new GridLayout(3,2));

        f.add(new JLabel("Crop"));
        f.add(txtCrop);
        f.add(new JLabel("Qty"));
        f.add(txtQty);
        f.add(btnSubmit);
        f.add(btnBack);

        btnSubmit.addActionListener(e -> {

            try{
                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/farmer_db",
                    "root","Pratu@60"
                );

                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO FarmerCrop(farmer_name,crop,quantity,status) VALUES (?,?,?,?)"
                );

                ps.setString(1,name);
                ps.setString(2,txtCrop.getText());
                ps.setInt(3,Integer.parseInt(txtQty.getText()));
                ps.setString(4,"Available");

                ps.executeUpdate();

                JOptionPane.showMessageDialog(f,"Crop Added!");

                con.close();

            }catch(Exception ex){
                ex.printStackTrace();
            }
        });

        btnBack.addActionListener(e -> {
            f.dispose();
            dashboard.setVisible(true);
        });

        f.setVisible(true);
    }
}
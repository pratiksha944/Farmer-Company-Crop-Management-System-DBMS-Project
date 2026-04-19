package gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class UpdateCropForm {

    public UpdateCropForm(JFrame dashboard, String name){

        JFrame f = new JFrame("Update Crop");
        f.setSize(400,300);
        f.setLocationRelativeTo(null);

        JTextField txtId = new JTextField();
        JTextField txtCrop = new JTextField();
        JTextField txtQty = new JTextField();

        JButton btnUpdate = new JButton("Update");
        JButton btnBack = new JButton("Back");

        f.setLayout(new GridLayout(5,2,10,10));

        f.add(new JLabel("Enter ID")); f.add(txtId);
        f.add(new JLabel("New Crop")); f.add(txtCrop);
        f.add(new JLabel("New Quantity")); f.add(txtQty);
        f.add(btnUpdate); f.add(btnBack);

        btnUpdate.addActionListener(e -> {

            try{
                int id = Integer.parseInt(txtId.getText());
                int qty = Integer.parseInt(txtQty.getText());

                Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/farmer_db","root","Pratu@60"
                );

                // ✅ FIXED QUERY
                PreparedStatement ps = con.prepareStatement(
                    "UPDATE FarmerCrop SET crop=?, quantity=? WHERE id=? AND farmer_name=?"
                );

                ps.setString(1, txtCrop.getText());
                ps.setInt(2, qty);
                ps.setInt(3, id);
                ps.setString(4, name);

                int rows = ps.executeUpdate();

                if(rows > 0){
                    JOptionPane.showMessageDialog(f,"✅ Updated Successfully!");
                } else {
                    JOptionPane.showMessageDialog(f,"❌ No Record Found!");
                }

                con.close();

            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(f,"Enter valid numbers!");
            }
            catch(Exception ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(f,"Database Error!");
            }
        });

        btnBack.addActionListener(e -> {
            f.dispose();
            dashboard.setVisible(true);
        });

        f.setVisible(true);
    }
}
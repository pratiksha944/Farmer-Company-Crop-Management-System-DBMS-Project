package gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class DeleteCropForm {

    public DeleteCropForm(JFrame dashboard, String name){

        JFrame f = new JFrame("Delete Crop");
        f.setSize(300,200);
        f.setLocationRelativeTo(null);

        JTextField txtId = new JTextField();

        JButton btnDelete = new JButton("Delete");
        JButton btnBack = new JButton("Back");

        f.setLayout(new GridLayout(3,2));

        f.add(new JLabel("Enter ID"));
        f.add(txtId);
        f.add(btnDelete);
        f.add(btnBack);

        btnDelete.addActionListener(e -> {
            try{
                Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/farmer_db","root","Pratu@60"
                );

                PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM FarmerCrop WHERE id=? AND farmer_name=?"
                );

                ps.setInt(1, Integer.parseInt(txtId.getText()));
                ps.setString(2, name);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(f,"Deleted!");

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
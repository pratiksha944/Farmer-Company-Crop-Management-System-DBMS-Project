package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class CustomerViewCrop {

    public CustomerViewCrop(JFrame dashboard){

        JFrame f = new JFrame("Available Crops");
        f.setSize(500,300);

        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Farmer","Crop","Qty"},0
        );
        table.setModel(model);

        try{
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/farmer_db","root","Pratu@60"
            );

            PreparedStatement ps = con.prepareStatement(
                "SELECT farmer_name,crop,quantity FROM FarmerCrop"
            );

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                model.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getInt(3)
                });
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        JButton back = new JButton("Back");

        back.addActionListener(e -> {
            f.dispose();
            dashboard.setVisible(true);
        });

        f.add(new JScrollPane(table));
        f.add(back, BorderLayout.SOUTH);

        f.setVisible(true);
    }
}
package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class FarmerViewRequirement {

    JFrame dashboard;

    public FarmerViewRequirement(JFrame dashboard){

        this.dashboard = dashboard;

        JFrame f = new JFrame("Company Requirements (View Only)");
        f.setSize(600,350);
        f.setLocationRelativeTo(null);
        f.setLayout(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel(
            new String[]{"Company Name","Crop","Quantity","Last Date"},0
        );

        JTable table = new JTable(model);

        // 🔥 LOAD DATA FROM DB
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/farmer_db",
                "root","Pratu@60"
            );

            ResultSet rs = con.createStatement().executeQuery(
                "SELECT company_name, crop_name, quantity, last_date FROM CompanyRequirement"
            );

            while(rs.next()){
                model.addRow(new Object[]{
                    rs.getString("company_name"),
                    rs.getString("crop_name"),
                    rs.getInt("quantity"),
                    rs.getString("last_date")
                });
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        // 🔙 BACK BUTTON
        JButton back = new JButton("Back");
        back.setBackground(Color.GRAY);
        back.setForeground(Color.WHITE);

        back.addActionListener(e -> {
            dashboard.setVisible(true);
            f.dispose();
        });

        f.add(new JScrollPane(table), BorderLayout.CENTER);
        f.add(back, BorderLayout.SOUTH);

        f.setVisible(true);
    }
}
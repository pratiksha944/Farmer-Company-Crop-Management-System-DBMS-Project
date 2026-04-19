package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewData {

    public ViewData(JFrame dashboard, String name, String role){

        JFrame f = new JFrame("View Data");
        f.setSize(600,400);

        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID","Farmer","Crop","Qty","Status"},0
        );
        table.setModel(model);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/farmer_db","root","Pratu@60"
            );

            PreparedStatement ps;

            if(role.equals("Farmer")){
                ps = con.prepareStatement(
                        "SELECT * FROM FarmerCrop WHERE farmer_name=?"
                );
                ps.setString(1, name);
            } else {
                ps = con.prepareStatement("SELECT * FROM FarmerCrop");
            }

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                model.addRow(new Object[]{
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5)
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
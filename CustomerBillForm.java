package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class CustomerBillForm {

    public CustomerBillForm(JFrame dashboard, String name){

        JFrame f = new JFrame("My Bills");
        f.setSize(500,300);

        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID","Crop","Qty","Price"},0
        );
        table.setModel(model);

        try{
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/farmer_db","root","Pratu@60"
            );

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM CustomerOrder WHERE name=?"
            );
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("crop"),
                    rs.getInt("quantity"),
                    rs.getInt("price")
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
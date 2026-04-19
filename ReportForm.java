package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.*;

public class ReportForm {

    public ReportForm(JFrame dashboard){

        JFrame f = new JFrame("Company Report - Customer Orders");
        f.setSize(800,450);
        f.setLocationRelativeTo(null);

        // 📊 Table
        JTable table = new JTable();

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID","Name","Phone","Address","Email","Crop","Qty","Price"},0
        );

        table.setModel(model);

        // 🎨 Table Design
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 13));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 15));
        header.setBackground(new Color(41,128,185));
        header.setForeground(Color.WHITE);

        // 🔥 DATABASE FETCH
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/farmer_db",
                    "root","Pratu@60"
            );

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM CustomerOrder"
            );

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getString("crop"),
                        rs.getInt("quantity"),
                        rs.getInt("price")
                });
            }

            con.close();

        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(f,"❌ Database Error!");
        }

        // 🔙 Back Button
        JButton btnBack = new JButton("Back");
        btnBack.setBackground(new Color(192,57,43));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));

        btnBack.addActionListener(e -> {
            f.dispose();
            dashboard.setVisible(true);
        });

        // 📦 Layout
        f.setLayout(new BorderLayout());
        f.add(new JScrollPane(table), BorderLayout.CENTER);
        f.add(btnBack, BorderLayout.SOUTH);

        f.setVisible(true);
    }
}
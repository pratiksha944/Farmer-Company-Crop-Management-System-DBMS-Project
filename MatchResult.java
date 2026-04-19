package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class MatchResult {

    JFrame dashboard;

    public MatchResult(JFrame dashboard){

        this.dashboard = dashboard;

        JFrame f = new JFrame("Matching Result");
        f.setSize(700,400);
        f.setLocationRelativeTo(null);
        f.setLayout(new BorderLayout());

        // 🏷️ TITLE
        JLabel title = new JLabel("Farmer & Company Crop Matching");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        f.add(title, BorderLayout.NORTH);

        // 📊 TABLE
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"Farmer Name","Crop","Quantity","Company Name"},0
        );

        JTable table = new JTable(model);

        // 🎨 Row color
        table.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer(){
            public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int col){

                Component c = super.getTableCellRendererComponent(
                    table,value,isSelected,hasFocus,row,col);

                c.setBackground(new Color(200,255,200));
                return c;
            }
        });

        f.add(new JScrollPane(table), BorderLayout.CENTER);

        // 🔙 BACK BUTTON
        JButton back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        f.add(back, BorderLayout.SOUTH);

        // 🔥 LOAD DATA
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/farmer_db",
                "root","Pratu@60"
            );

            // ✅ CORRECT QUERY
            String query =
                "SELECT f.farmer_name, f.crop, f.quantity, c.company_name " +
                "FROM FarmerCrop f JOIN companyrequirement c " +
                "ON f.crop = c.crop_name";

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                model.addRow(new Object[]{
                    rs.getString("farmer_name"),
                    rs.getString("crop"),
                    rs.getInt("quantity"),
                    rs.getString("company_name")
                });
            }

            con.close();

        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(f,"Database Error!");
        }

        // 🔙 BACK
        back.addActionListener(e -> {
            if(dashboard != null){
                dashboard.setVisible(true);
            }
            f.dispose();
        });

        f.setVisible(true);
    }

    public static void main(String[] args){
        new MatchResult(null);
    }
}
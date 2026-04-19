package gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class CompanyForm {

    JFrame dashboard;
    boolean isEditable;

    public CompanyForm(JFrame dashboard, boolean isEditable){

        this.dashboard = dashboard;
        this.isEditable = isEditable;

        JFrame f = new JFrame("Company Requirement");
        f.setSize(350,300);
        f.setLocationRelativeTo(null);
        f.setLayout(new GridLayout(5,2));

        JTextField company = new JTextField();
        JTextField crop = new JTextField();
        JTextField qty = new JTextField();
        JTextField date = new JTextField();

        JButton submit = new JButton("Submit");
        JButton back = new JButton("Back");

        f.add(new JLabel("Company Name:")); f.add(company);
        f.add(new JLabel("Crop:")); f.add(crop);
        f.add(new JLabel("Quantity:")); f.add(qty);
        f.add(new JLabel("Last Date (YYYY-MM-DD):")); f.add(date);
        f.add(submit); f.add(back);

        // 🔥 Farmer → disable edit
        if(!isEditable){
            submit.setEnabled(false);
        }

        submit.addActionListener(e -> {
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/farmer_db",
                    "root","Pratu@60"
                );

                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO CompanyRequirement(company_name,crop_name,quantity,last_date) VALUES (?,?,?,?)"
                );

                ps.setString(1, company.getText());
                ps.setString(2, crop.getText());
                ps.setInt(3, Integer.parseInt(qty.getText()));
                ps.setString(4, date.getText());

                ps.executeUpdate();

                JOptionPane.showMessageDialog(f,"Saved!");

                dashboard.setVisible(true);
                f.dispose();

            }catch(Exception ex){
                ex.printStackTrace();
            }
        });

        back.addActionListener(e -> {
            dashboard.setVisible(true);
            f.dispose();
        });

        f.setVisible(true);
    }
}
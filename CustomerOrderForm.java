package gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class CustomerOrderForm {

    public CustomerOrderForm(JFrame dashboard, String name){

        JFrame f = new JFrame("Place Order");
        f.setSize(400,450);
        f.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(8,2,10,10));

        JTextField txtPhone = new JTextField();
        JTextField txtAddress = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtCrop = new JTextField();
        JTextField txtQty = new JTextField();

        JButton btnSubmit = new JButton("Submit");
        JButton btnBack = new JButton("Back");

        // 🎨 Colors
        btnSubmit.setBackground(new Color(46,204,113));
        btnSubmit.setForeground(Color.WHITE);

        btnBack.setBackground(new Color(231,76,60));
        btnBack.setForeground(Color.WHITE);

        // ➕ Fields
        panel.add(new JLabel("Customer Name")); panel.add(new JLabel(name));
        panel.add(new JLabel("Phone")); panel.add(txtPhone);
        panel.add(new JLabel("Address")); panel.add(txtAddress);
        panel.add(new JLabel("Email")); panel.add(txtEmail);
        panel.add(new JLabel("Crop")); panel.add(txtCrop);
        panel.add(new JLabel("Quantity")); panel.add(txtQty);

        panel.add(btnSubmit); panel.add(btnBack);

        f.add(panel);

        // 🔥 SUBMIT
        btnSubmit.addActionListener(e -> {
            try{
                String phone = txtPhone.getText();
                String address = txtAddress.getText();
                String email = txtEmail.getText();
                String crop = txtCrop.getText();
                int qty = Integer.parseInt(txtQty.getText());

                if(phone.equals("") || address.equals("") || email.equals("") || crop.equals("")){
                    JOptionPane.showMessageDialog(f,"Fill all fields!");
                    return;
                }

                int price = qty * 50;

                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/farmer_db",
                    "root","Pratu@60"
                );

                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO CustomerOrder(name,phone,address,email,crop,quantity,price) VALUES (?,?,?,?,?,?,?)"
                );

                ps.setString(1, name);
                ps.setString(2, phone);
                ps.setString(3, address);
                ps.setString(4, email);
                ps.setString(5, crop);
                ps.setInt(6, qty);
                ps.setInt(7, price);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(f,"✅ Order Placed! ₹" + price);

                // clear fields
                txtPhone.setText("");
                txtAddress.setText("");
                txtEmail.setText("");
                txtCrop.setText("");
                txtQty.setText("");

                con.close();

            }catch(Exception ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(f,"Error!");
            }
        });

        // 🔙 BACK
        btnBack.addActionListener(e -> {
            f.dispose();
            dashboard.setVisible(true);
        });

        f.setVisible(true);
    }
}
package gui;

import javax.swing.*;
import java.awt.*;

public class LoginForm {

    public LoginForm(){

        JFrame f = new JFrame("Login");
        f.setSize(400,300);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 🌈 Gradient Panel (Professional Look)
        JPanel panel = new JPanel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0,0,new Color(44,62,80),      // dark blue-gray
                        getWidth(),getHeight(),new Color(52,152,219) // light blue
                );
                g2.setPaint(gp);
                g2.fillRect(0,0,getWidth(),getHeight());
            }
        };

        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);

        // 🏷️ Title
        JLabel title = new JLabel("LOGIN");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        // Fields
        JTextField txtName = new JTextField(15);
        JPasswordField txtPass = new JPasswordField(15);

        JLabel lblName = new JLabel("Name:");
        JLabel lblPass = new JLabel("Password:");
        JLabel lblRole = new JLabel("Role:");

        lblName.setForeground(Color.WHITE);
        lblPass.setForeground(Color.WHITE);
        lblRole.setForeground(Color.WHITE);

        String roles[] = {"Farmer","Company","Customer"};
        JComboBox<String> cbRole = new JComboBox<>(roles);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(39,174,96)); // green button
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));

        // Layout
        gbc.gridx=0; gbc.gridy=0; gbc.gridwidth=2;
        panel.add(title, gbc);

        gbc.gridwidth=1;
        gbc.gridy=1;
        panel.add(lblName, gbc);
        gbc.gridx=1;
        panel.add(txtName, gbc);

        gbc.gridx=0; gbc.gridy=2;
        panel.add(lblPass, gbc);
        gbc.gridx=1;
        panel.add(txtPass, gbc);

        gbc.gridx=0; gbc.gridy=3;
        panel.add(lblRole, gbc);
        gbc.gridx=1;
        panel.add(cbRole, gbc);

        gbc.gridx=0; gbc.gridy=4; gbc.gridwidth=2;
        panel.add(btnLogin, gbc);

        f.add(panel);

        // 🔐 LOGIN LOGIC (same)
        btnLogin.addActionListener(e -> {

            String name = txtName.getText();
            String pass = new String(txtPass.getPassword());
            String role = cbRole.getSelectedItem().toString();

            if(role.equals("Farmer") && !pass.equals("f123")){
                JOptionPane.showMessageDialog(f,"Wrong Farmer Password!");
                return;
            }
            if(role.equals("Company") && !pass.equals("c123")){
                JOptionPane.showMessageDialog(f,"Wrong Company Password!");
                return;
            }
            if(role.equals("Customer") && !pass.equals("cu123")){
                JOptionPane.showMessageDialog(f,"Wrong Customer Password!");
                return;
            }

            if(role.equals("Customer")){
                new CustomerDashboard(name);
            } else {
                new Dashboard(name, role);
            }

            f.dispose();
        });

        f.setVisible(true);
    }

    public static void main(String[] args){
        new LoginForm();
    }
}
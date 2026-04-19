package gui;

import javax.swing.*;
import java.awt.*;

public class CustomerDashboard {

    JFrame frame;

    public CustomerDashboard(String name){

        frame = new JFrame("Customer Dashboard - " + name);
        frame.setSize(500,450);
        frame.setLocationRelativeTo(null);

        // 🌈 Gradient Background
        JPanel panel = new JPanel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0,0,new Color(44,62,80),
                        getWidth(),getHeight(),new Color(52,152,219)
                );
                g2.setPaint(gp);
                g2.fillRect(0,0,getWidth(),getHeight());
            }
        };

        panel.setLayout(new BorderLayout(10,10));

        // 🏷️ TITLE
        JLabel title = new JLabel("WELCOME " + name.toUpperCase());
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(title, BorderLayout.NORTH);

        // 🔽 CENTER PANEL
        JPanel centerPanel = new JPanel(new BorderLayout(10,10));
        centerPanel.setOpaque(false);

        // 🔹 View Available Crops (BIG BUTTON)
        JButton btnView = new JButton("View Available Crops");
        btnView.setBackground(new Color(52,152,219));
        btnView.setForeground(Color.WHITE);
        btnView.setFont(new Font("Arial", Font.BOLD, 16));
        btnView.setPreferredSize(new Dimension(300,50));

        JPanel viewPanel = new JPanel();
        viewPanel.setOpaque(false);
        viewPanel.add(btnView);

        centerPanel.add(viewPanel, BorderLayout.NORTH);

        // 🔲 GRID BUTTONS
        JPanel gridPanel = new JPanel(new GridLayout(2,2,15,15));
        gridPanel.setOpaque(false);
        gridPanel.setPreferredSize(new Dimension(320,180));

        JButton btnOrder = new JButton("Place Order");
        JButton btnBill = new JButton("View My Bill");
        JButton btnLogout = new JButton("Logout");
        JButton btnBack = new JButton("Back to Login");

        // 🎨 Colors
        btnOrder.setBackground(new Color(46,204,113));
        btnBill.setBackground(new Color(241,196,15));
        btnLogout.setBackground(new Color(192,57,43));
        btnBack.setBackground(new Color(127,140,141));

        JButton[] buttons = {btnOrder, btnBill, btnLogout, btnBack};

        for(JButton b : buttons){
            b.setForeground(Color.WHITE);
            b.setFont(new Font("Arial", Font.BOLD, 14));
            b.setFocusPainted(false);
        }

        gridPanel.add(btnOrder);
        gridPanel.add(btnBill);
        gridPanel.add(btnLogout);
        gridPanel.add(btnBack);

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setOpaque(false);
        wrapper.add(gridPanel);

        centerPanel.add(wrapper, BorderLayout.CENTER);

        panel.add(centerPanel, BorderLayout.CENTER);

        frame.add(panel);

        // 🔥 NAVIGATION
        btnView.addActionListener(e -> {
            new CustomerViewCrop(frame);
            frame.setVisible(false);
        });

        btnOrder.addActionListener(e -> {
            new CustomerOrderForm(frame, name);
            frame.setVisible(false);
        });

        btnBill.addActionListener(e -> {
            new CustomerBillForm(frame, name);
            frame.setVisible(false);
        });

        btnLogout.addActionListener(e -> {
            frame.dispose();
            new LoginForm();
        });

        btnBack.addActionListener(e -> {
            frame.dispose();
            new LoginForm();
        });

        frame.setVisible(true);
    }
}
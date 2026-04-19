package gui;

import javax.swing.*;
import java.awt.*;

public class Dashboard {

    JFrame frame;
    String role;

    public Dashboard(String name, String role){

        this.role = role;

        frame = new JFrame("Dashboard");
        frame.setSize(700,520);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 🌈 Gradient Background
        JPanel panel = new JPanel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0,0,new Color(58,123,213),
                        getWidth(),getHeight(),new Color(0,210,255)
                );
                g2.setPaint(gp);
                g2.fillRect(0,0,getWidth(),getHeight());
            }
        };

        panel.setLayout(new BorderLayout(10,10));

        // 🏷️ Title
        JLabel title = new JLabel("Dashboard - " + name + " (" + role + ")");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        // 🔙 Back Button
        JButton btnBackLogin = new JButton("Back to Login");
        btnBackLogin.setBackground(Color.BLACK);
        btnBackLogin.setForeground(Color.WHITE);

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.add(title, BorderLayout.CENTER);
        header.add(btnBackLogin, BorderLayout.EAST);

        panel.add(header, BorderLayout.NORTH);

        // 🎨 Buttons
        JButton btnAdd = new JButton("Add Crop");
        JButton btnView = new JButton("View Crop");
        JButton btnUpdate = new JButton("Update Crop");
        JButton btnDelete = new JButton("Delete Crop");
        JButton btnCompany = new JButton("Company Requirement");
        JButton btnMatch = new JButton("Matching");
        JButton btnCustomer = new JButton("Customer Order");
        JButton btnReport = new JButton("Report");
        JButton btnChart = new JButton("Chart");

        JButton[] buttons = {
            btnAdd,btnView,btnUpdate,btnDelete,
            btnCompany,btnMatch,btnCustomer,btnReport,btnChart
        };

        for(JButton b : buttons){
            b.setFont(new Font("Arial",Font.BOLD,14));
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
        }

        // 🎨 Colors
        btnAdd.setBackground(new Color(46,204,113));
        btnView.setBackground(new Color(52,152,219));
        btnUpdate.setBackground(new Color(241,196,15));
        btnDelete.setBackground(new Color(231,76,60));
        btnCompany.setBackground(new Color(155,89,182));
        btnMatch.setBackground(new Color(26,188,156));
        btnCustomer.setBackground(new Color(230,126,34));
        btnReport.setBackground(new Color(127,140,141));
        btnChart.setBackground(new Color(52,73,94));

        // 🔥 FARMER UI (FIXED)
        if(role.equals("Farmer")){

            btnCompany.setFont(new Font("Arial", Font.BOLD, 18));
            btnCompany.setPreferredSize(new Dimension(300,50));

            JPanel centerPanel = new JPanel(new BorderLayout());
            centerPanel.setOpaque(false);

            // Company button top
            JPanel topPanel = new JPanel();
            topPanel.setOpaque(false);
            topPanel.add(btnCompany);

            centerPanel.add(topPanel, BorderLayout.NORTH);

            // Center buttons
            JPanel gridWrapper = new JPanel(new GridBagLayout());
            gridWrapper.setOpaque(false);

            JPanel grid = new JPanel(new GridLayout(2,2,20,20));
            grid.setOpaque(false);
            grid.setPreferredSize(new Dimension(350,200));

            grid.add(btnAdd);
            grid.add(btnView);
            grid.add(btnUpdate);
            grid.add(btnDelete);

            gridWrapper.add(grid);

            centerPanel.add(gridWrapper, BorderLayout.CENTER);

            panel.add(centerPanel, BorderLayout.CENTER);

        } else {

            JPanel buttonPanel = new JPanel(new GridLayout(5,2,15,15));
            buttonPanel.setOpaque(false);

            buttonPanel.add(btnAdd);
            buttonPanel.add(btnView);
            buttonPanel.add(btnUpdate);
            buttonPanel.add(btnDelete);
            buttonPanel.add(btnCompany);
            buttonPanel.add(btnMatch);
            buttonPanel.add(btnCustomer);
            buttonPanel.add(btnReport);
            buttonPanel.add(btnChart);

            panel.add(buttonPanel, BorderLayout.CENTER);
        }

        frame.add(panel);

        // 🔥 ROLE CONTROL
        if(role.equals("Farmer")){
            btnMatch.setVisible(false);
            btnCustomer.setVisible(false);
            btnReport.setVisible(false);
            btnChart.setVisible(false);
        }

        if(role.equals("Company")){
            btnCustomer.setVisible(false);
        }

        // 🔥 Navigation
        btnAdd.addActionListener(e -> {
            frame.setVisible(false);
            new FarmerCropForm(name, frame);
        });

        btnView.addActionListener(e -> {
            frame.setVisible(false);
            new ViewData(frame, name, role);
        });

        btnUpdate.addActionListener(e -> {
            frame.setVisible(false);
            new UpdateCropForm(frame, name);
        });

        btnDelete.addActionListener(e -> {
            frame.setVisible(false);
            new DeleteCropForm(frame, name);
        });

        btnCompany.addActionListener(e -> {
            frame.setVisible(false);

            if(role.equals("Farmer")){
                new FarmerViewRequirement(frame);
            } else {
                new CompanyForm(frame, true);
            }
        });

        btnMatch.addActionListener(e -> {
            frame.setVisible(false);
            new MatchResult(frame);
        });

        btnCustomer.addActionListener(e -> {
            frame.setVisible(false);
            new CustomerOrderForm(frame, name);
        });

        btnReport.addActionListener(e -> {
            frame.setVisible(false);
            new ReportForm(frame);
        });

        btnChart.addActionListener(e -> {
            frame.setVisible(false);

            JFrame cf = new JFrame("Chart");
            cf.setSize(600,400);
            cf.setLocationRelativeTo(null);

            cf.add(new ChartForm());

            JButton back = new JButton("Back");
            back.addActionListener(ev -> {
                frame.setVisible(true);
                cf.dispose();
            });

            cf.add(back, BorderLayout.SOUTH);
            cf.setVisible(true);
        });

        // 🔙 Back to Login (FINAL FIX)
        btnBackLogin.addActionListener(e -> {
            frame.dispose();
            new LoginForm();
        });

        frame.setVisible(true);
    }
}
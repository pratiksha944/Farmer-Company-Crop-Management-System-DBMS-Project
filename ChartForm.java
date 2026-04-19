package gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ChartForm extends JPanel {

    int[] data = new int[12];

    public ChartForm(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/farmer_db",
                "root","Pratu@60"
            );

            // 🔥 FIXED QUERY
            ResultSet rs = con.createStatement().executeQuery(
                "SELECT MONTH(CURDATE()) as m, SUM(price) FROM CustomerOrder GROUP BY m"
            );

            while(rs.next()){
                int month = rs.getInt(1);
                int total = rs.getInt(2);

                data[month-1] = total;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        int barWidth = width / 12;

        for(int i=0;i<12;i++){

            int value = data[i];
            int barHeight = value / 5; // scale

            g.setColor(new Color(52,152,219)); // nice blue

            g.fillRect(
                i * barWidth,
                height - barHeight,
                barWidth - 5,
                barHeight
            );

            // month label
            g.setColor(Color.BLACK);
            g.drawString(""+(i+1), i*barWidth + 10, height - 5);
        }
    }
}
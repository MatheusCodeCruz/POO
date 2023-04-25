package logo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LogoDois {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Dynamic Logo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(new DynamicLogo());
        frame.setVisible(true);
    }
    
    static class DynamicLogo extends JPanel {
        private static final long serialVersionUID = 1L;
        private int angle = 0;
        private int step = 1;

        public void paint(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setFont(new Font("Arial", Font.BOLD, 50));
            AffineTransform old = g2d.getTransform();
            g2d.rotate(Math.toRadians(angle), getWidth() / 2, getHeight() / 2);
            g2d.setColor(Color.BLUE);
            g2d.drawString("PYRAMID", getWidth() / 2 - 80, getHeight() / 2);
            g2d.setTransform(old);
            angle += step;
            if (angle >= 360) {
                angle = 0;
            }
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class CrystalVisualization extends JPanel {
    private int numberOfCells;
    private ParticlesPositions particlesPositions;
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 200;

    CrystalVisualization(int numberOfCells, ParticlesPositions particlesPositions){
        this.numberOfCells = numberOfCells;
        this.particlesPositions = particlesPositions;
    }

    //draw crystal with cells and particles
    public void paint(Graphics g){
        g.drawRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        for(int i = 0; i < numberOfCells; i++){
            int prevX = i == 0 ? 0 : (WINDOW_WIDTH / particlesPositions.getSize()) * i;
            int x = (WINDOW_WIDTH / particlesPositions.getSize()) * (i + 1);
            g.drawLine(x, 0, x, WINDOW_HEIGHT);
            int numberOfParticles = particlesPositions.getNumberOfParticlesInCell(i);
            g.drawString(String.valueOf(numberOfParticles), prevX + 5, 20);
            for(int j = 0; j < numberOfParticles; j++){
                printParticle(prevX, x, g);
                g.setColor(Color.BLACK);
            }
        }
    }

    private void printParticle(int x1, int x2, Graphics g){
        Random random = new Random();
        int x = random.nextInt((x2 - 10) - (x1 + 10)) + (x1 + 10);
        int y = random.nextInt(WINDOW_HEIGHT - 10);
        g.setColor(Color.orange);
        g.fillOval(x, y, 10, 10);
    }

    //create window and update it
    void startVisualization(double delay, int iterations, String modeName) throws InterruptedException {
        JFrame frame= new JFrame("Crystal Visualization. Mode: " + modeName);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().add(this);
        for(int i = 0; i < iterations; i++){
            frame.repaint(500);
            Thread.sleep((long) (delay * 1000L));
        }
        JOptionPane.showMessageDialog(null, "Modeling finished");
    }
}

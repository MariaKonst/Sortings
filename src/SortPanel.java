import javax.swing.*;
import java.awt.*;

class SortPanel extends JPanel {
    private final GraphicsElement[] graphicsElements;

    public SortPanel() {
        setLayout(new BorderLayout());

        graphicsElements = new GraphicsElement[5];
        for (int i = 0; i < graphicsElements.length; i++) {
            graphicsElements[i] = new GraphicsElement(i + 1, 100);
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x=20;
        for (int i = graphicsElements.length-1; i>=0 ; i--) {
          g.drawImage(graphicsElements[i].getImage(),x,170,null);
          x=x+graphicsElements[i].getImage().getWidth(null);
        }
    }

    public GraphicsElement[] getGraphicsElements() {
        return graphicsElements;
    }
}

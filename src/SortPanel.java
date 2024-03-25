import javax.swing.*;
import java.awt.*;

class SortPanel extends JPanel {
    private final GraphicsElement[] graphicsElements;

    public SortPanel() {
        setLayout(new BorderLayout());

        graphicsElements = new GraphicsElement[4];
        for (int i = 0; i < graphicsElements.length; i++) {
            graphicsElements[i] = new GraphicsElement(i + 1, 100);
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (GraphicsElement element : graphicsElements) {
            element.draw(g);
        }
    }

    public GraphicsElement[] getGraphicsElements() {
        return graphicsElements;
    }
}

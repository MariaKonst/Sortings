import javax.swing.*;
import java.awt.*;

class GraphicsElement {
    private final int size;
    private final Image image;

    public GraphicsElement(int size, int width) {
        this.size = size;
        this.image = new ImageIcon("resources/"+ size +".png").getImage();
    }

    public int getSize() {
        return size;
    }

    public void draw(Graphics g) {
        g.drawImage(image, 50 + (size - 1) * 10, 200, null);
    }
}
import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

class GraphicsElement {
    private final int size;
    private final Image image;

    public GraphicsElement(int size, int width) {

        Path resourceDirectory = Paths.get("src","resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        this.image = new ImageIcon(absolutePath+"/"+"image"+size+".png").getImage();
        this.size = 6-size;
    }

    public int getSize() {
        return size;
    }

    /*public void draw(Graphics g) {
        g.drawImage(image, 80 + (6-size) * 100, 170, null);
    }*/

    public Image getImage() {
        return image;
    }
}
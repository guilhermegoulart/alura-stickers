import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class StickerGenerator {
    public void create(InputStream original, String fileName) throws IOException {

        BufferedImage image = ImageIO.read(original);
        int width = image.getWidth();
        int height = image.getHeight();

        int newHeight = height + 200;
        var newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);
        var graphics = newImage.getGraphics();

        graphics.drawImage(image, 0, 0, null);

        var font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setFont(font);
        graphics.setColor(Color.GREEN);

        graphics.drawString("Esse é top", 225, newHeight - 100);

        ImageIO.write(newImage, "png", new File(fileName));
    }
}

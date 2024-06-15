import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PictureComponent extends JPanel{
    private Image image;
    private int width;
    private int height;

    public PictureComponent(int width, int height){
        this.width =width;
        this.height=height;
        this.setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Graphics2D g2 = (Graphics2D) g;      
        g2.drawImage(image, 0, 0, width, height, null);      
     
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
   
    public void setImage(String path){
        image = new ImageIcon(getClass().getResource(path)).getImage();
        setPreferredSize(new Dimension(width,height));
        repaint();
    }
    public Image getImage(){

        return image;
    }


}

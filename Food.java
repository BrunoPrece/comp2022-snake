import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.lang.Math;
/**
 * Escreva a descrição da classe Food aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Food extends JPanel
{
    private String fries = "images/fries.png";
    private Image image;
    private int x;
    private int y;
    double x1= Math.random() * (751);
    double y2 = Math.random() * (551);
    /**
     * COnstrutor para objetos da classe Food
     */
    public Food()
    {   ImageIcon ii = new ImageIcon(this.getClass().getResource(fries));
        this.image = ii.getImage();      
        this.x = (int)x1;
        this.y =(int)y2;
    }

    public void setX(){
        x1= Math.random() * (751);
        this.x= (int)x1;
    }

    public void setY(){
        y2 = Math.random() * (551);
        this.y = (int)y2;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public Image getImage(){
        return this.image;
    }
}


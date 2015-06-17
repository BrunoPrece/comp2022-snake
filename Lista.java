import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
public class Lista
{
    private Snake inicio;
    public Lista()
    {this.inicio = new Snake();
    }

    public Snake getInicio(){
        return this.inicio;
    }

    public void moveCb(){
        Snake anterior = inicio;
        move(anterior);
        inicio.setY(inicio.getY()+40);
        inicio.setX(inicio.getX()+20);
       
    }

    public void move(Snake anterior){
        Snake aux = anterior;
        Snake ini = anterior;
        Snake n;
        while(aux.getProximo() != null){
            n = aux = aux.getProximo();
            aux.setX(ini.getX());
            aux.setY(ini.getY());
            ini= n.getProximo();

        }
       
    }
    public void adiciona(){
        if(inicio.getProximo() == null){
            Snake aux = inicio.fabrica();
            aux.setX(inicio.getX()+50);
            aux.setY(inicio.getY());
            inicio.setProximo(aux);
        }else{  
            Snake aux = inicio;
            Snake novo = aux.fabrica();
            while(aux.getProximo() != null){
                aux = aux.getProximo();
            }
            novo.setX(aux.getX()+30);
            novo.setY(aux.getY());
            aux.setProximo(novo);
        }
    }

    public boolean isEmpty(){
        if(inicio == null){
            return true;
        }else{
            return false;
        }
    }
}

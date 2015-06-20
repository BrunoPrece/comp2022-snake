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

    public void moveCb(int x, int y){
        Snake anterior = inicio.fabrica();
        anterior.setX(inicio.getX());
        anterior.setY(inicio.getY()-20);
        anterior.setProximo(inicio.getProximo());

        move(anterior);
        inicio.setX(inicio.getX()+x);
        inicio.setY(inicio.getY()+y);

    }

    public void move(Snake anterior){
        Snake aux = anterior;
        Snake ant = inicio.fabrica();

        while(aux.getProximo() != null){
            aux = aux.getProximo();
            ant.setX(aux.getX());
            ant.setY(aux.getY());

            aux.setX(anterior.getX());
            aux.setY(anterior.getY());

            anterior.setX(ant.getX());
            anterior.setY(ant.getY());

        }

    }

    public void adiciona(int x, int y){
        if(inicio.getProximo() == null){
            Snake aux = inicio.fabrica();
            aux.setX(inicio.getX()+x);
            aux.setY(inicio.getY()+y);
            inicio.setProximo(aux);
        }else{  
            Snake aux = inicio;
            Snake novo = aux.fabrica();
            while(aux.getProximo() != null){
                aux = aux.getProximo();
            }
            novo.setX(aux.getX()+x);
            novo.setY(aux.getY()+y);
            aux.setProximo(novo);
        }
    }

    public void imageDirection(String direcao){
        switch(direcao){
            case "esquerda":
            inicio.setImage("images/head.png");
            break;

            case "direita":
            inicio.setImage("images/head.png");
            break;

            case "cima":
            inicio.setImage("images/headC.png");
            break;

            case "baixo":
            inicio.setImage("images/headB.png");
            break;
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

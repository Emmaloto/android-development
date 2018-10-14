package com.example.emmalotopassy.visualmedia;

public class Card {


    private int front, back, state;
    /*
    private Image front, back, state;
    private JComponent canvas;
    private AffineTransform transform;
*/
    public Card(int f, int b){
        front = f;
        back = b;

        state = back;
        //System.out.println("Height card " + f.getHeight(null));
    }

/*
    public void drawCard(AffineTransform t){
        transform = t;
        if(state != null) g.drawImage(state, t, null);

        //System.out.println(getHeight());
    }

*/

    public void switchState(){

        if(state == front)
            state = back;
        else if(state == back)
            state = front;

    }

    public void flipCard(boolean val){

        if(val)  state = front;
        else     state = back;

    }

    public void hideCard(){
        state = 0;
    }

    public boolean itExists(){
        return state != 0;
    }

    public boolean isRemoved(){
        return state == 0;
    }

    public int getFace(){
        return front;
    }

    public void setFace(int newfront){
        front = newfront;
    }

    /*
    public double getX(){
        return transform.getTranslateX();

    }

    public double getY(){
        return transform.getTranslateY();

    }

    public double getWidth(){
        return transform.getScaleX() * front.getWidth(canvas);

    }

    public double getHeight(){
        return transform.getScaleY() * front.getHeight(canvas);

    }

*/
}

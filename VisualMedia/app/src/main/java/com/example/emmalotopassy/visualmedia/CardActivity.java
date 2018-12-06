package com.example.emmalotopassy.visualmedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

// https://stackoverflow.com/questions/3441396/defining-custom-attrs
// https://developer.android.com/guide/topics/ui/declaring-layout


public class CardActivity extends AppCompatActivity {

    public Card [] playingCards;

    public int cardFaces[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        // Put possible cards into array
        int tempCard[] = {R.drawable.card_basil, R.drawable.card_butterfly, R.drawable.card_cake, R.drawable.card_car, R.drawable.card_clock,
                R.drawable.card_cow, R.drawable.card_diamond, R.drawable.card_gift, R.drawable.card_hippo, R.drawable.card_house,
                R.drawable.card_phone, R.drawable.card_roses, R.drawable.card_target, R.drawable.card_tortise, R.drawable.card_water};
        cardFaces = tempCard;

        //setCards();
    }

    public void setCards(){

        int cardOptions = 12;
        playingCards = new Card[cardOptions];

        // Create cards and place in cardArray
        for(int i = 0, j = 0; i < playingCards.length; i++){
            if(i % 2 == 0) j = generateRandom(0, cardFaces.length);                        // Give two cards same value

            playingCards[i] = new Card(cardFaces[j], R.drawable.cardback);
        }

        // Swaps face of 2 random cards in our card list
        for(int i = 0; i < playingCards.length; i++){
            int index = generateRandom(0, playingCards.length);
            int index2 = generateRandom(0, playingCards.length);

            int swapface = playingCards[index].getFace();
            playingCards[index].setFace(playingCards[index2].getFace());
            playingCards[index2].setFace(swapface);
        }

        // Associate each image in array to a CardView
        android.support.v7.widget.GridLayout grid  = (android.support.v7.widget.GridLayout)findViewById(R.id.gridLayout);
        int noOfCards = grid.getChildCount();

        for(int child = 0; child < noOfCards; child++){
            //CardView card = (CardView)grid.getChildAt(child);
            //card.setFront(playingCards[child].getFace());


        }

        Log.i("Info", "Grid has " + noOfCards);

        /*
        // Associate each image number with each imageview available
        android.support.v7.widget.GridLayout grid  = (android.support.v7.widget.GridLayout)findViewById(R.id.gridLayout);
        int noOfCards = grid.getChildCount();

        for(int child = 0; child < noOfCards; child++){
            ImageView card = (ImageView)grid.getChildAt(child);
            card.setTag(playingCards[child]);
        }

        Log.i("Info", "Grid has " + noOfCards);
        // ImageView test = findViewWithTag("card");
        */
    }


    public void selectCard(View view){
        // Get image view that was clicked on
        CardView card = (CardView)view;

        if(true);
            //img.setImageResource((int)img.getTag());

        card.flipCard();
    }

    private int generateRandom(int min_n, int max_n){
        int random = (int)(Math.random() * max_n + min_n);
        return random;
    }


}

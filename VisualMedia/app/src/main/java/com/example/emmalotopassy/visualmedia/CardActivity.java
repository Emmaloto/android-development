package com.example.emmalotopassy.visualmedia;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;

// https://stackoverflow.com/questions/3441396/defining-custom-attrs
// https://developer.android.com/guide/topics/ui/declaring-layout


public class CardActivity extends AppCompatActivity {

    //public Card [] playingCards;

    public int [] playingCards;
    public int cardFaces[];
    public int counter = 0;
    public boolean cardDisabled = false;

    public CardView firstCard;
    public ImageView endScreen;
    private Button replay;
    public android.support.v7.widget.GridLayout grid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        // Put possible cards into array
        int tempCard[] = {R.drawable.card_basil, R.drawable.card_butterfly, R.drawable.card_cake, R.drawable.card_car, R.drawable.card_clock,
                R.drawable.card_cow, R.drawable.card_diamond, R.drawable.card_gift, R.drawable.card_hippo, R.drawable.card_house,
                R.drawable.card_phone, R.drawable.card_roses, R.drawable.card_target, R.drawable.card_tortise, R.drawable.card_water};
        cardFaces = tempCard;

        setCards();

        endScreen = findViewById(R.id.endScreen);
        replay = findViewById(R.id.replay);
    }

    public void setCards(){

        int cardOptions = 12;
        //playingCards = new Card[cardOptions];
        playingCards = new int[cardOptions];

        // Create cards and place in cardArray
        for(int i = 0, j = 0; i < playingCards.length; i++){
            // Give two cards same value
            if(i % 2 == 0) j = generateRandom(0, cardFaces.length);
            playingCards[i] = cardFaces[j];

            //playingCards[i] = new Card(cardFaces[j], R.drawable.cardback);
        }

        // Swaps face of 2 random cards in our card list
        for(int i = 0; i < playingCards.length; i++){
            int index = generateRandom(0, playingCards.length);
            int index2 = generateRandom(0, playingCards.length);

            int swapface = playingCards[index];
            playingCards[index] = playingCards[index2];
            playingCards[index2] = swapface;
        }

        // Associate each image in array to a CardView
        grid  = findViewById(R.id.gridLayout);
        int noOfCards = grid.getChildCount();

        for(int child = 0; child < noOfCards; child++){
            CardView card = (CardView)grid.getChildAt(child);
            card.setBack(R.drawable.cardback);
            card.setFront(playingCards[child]);

        }

        Log.i("Info", "Grid has " + noOfCards);

    }


    public void selectCard(View view) {
        // Get image view that was clicked on
        CardView card = (CardView) view;

        //card.setImageResource(card.getFront());
        card.flipCard();

        // Check if cards are active
        // Check to make sure 2nd card is different card
        // then card with same face
        if (!cardDisabled) {
            if (firstCard != card) {
                if (counter >= 1) {
                    cardDisabled = true;
                    // All this for a 1s delay in code -_-
                    final CardView c = card;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            checkCards(firstCard, c);
                        }
                    }, 1000);

                }else {
                    // We haven't clicked on 2 cards, so keep counting
                    firstCard = card;
                    counter++;
                }
            } else
                // The cards are the same, so ignore and reset counter
                counter = 0;

            //Log.i("Info", counter + " Card index count");
        }
    }

    private void checkCards(CardView card1, CardView card2){

        if(card1.getFront() == card2.getFront()){
            card1.setVisibility(View.INVISIBLE);
            card2.setVisibility(View.INVISIBLE);
        }else{
            card1.flipCard();
            card2.flipCard();
        }

        cardDisabled = false;
        counter = 0;

        checkIfEnded();
    }

    private void checkIfEnded(){
        boolean ended = true;
        int noOfCards = grid.getChildCount();

        for(int child = 0; child < noOfCards; child++){
            CardView card = (CardView)grid.getChildAt(child);
            ended = ended && (card.getVisibility() == View.INVISIBLE);
        }

        if(ended){
            endScreen.setVisibility(View.VISIBLE);
            replay.setVisibility(View.VISIBLE);
        }
    }

    public void restartActivity(View v){
        finish();
        startActivity(getIntent());
    }

    private int generateRandom(int min_n, int max_n){
        int random = (int)(Math.random() * max_n + min_n);
        return random;
    }


}

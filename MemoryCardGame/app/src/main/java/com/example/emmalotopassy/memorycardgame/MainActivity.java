package com.example.emmalotopassy.memorycardgame;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.view.MenuItem;
import android.view.View;


import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;


// https://stackoverflow.com/questions/3441396/defining-custom-attrs
// https://developer.android.com/guide/topics/ui/declaring-layout


public class MainActivity extends AppCompatActivity {

    //public Card [] playingCards;

    public int [] playingCards;
    public int cardFaces[];
    public int counter = 0;
    public int cardOptions = 12;
    public boolean cardDisabled = false;
    private int game_bg = R.drawable.triangle_bg;

    public CardView firstCard;
    public ImageView endScreen;
    public Button replay;
    public android.support.v7.widget.GridLayout grid;
    public DrawerLayout mDrawerLayout;
    public NavigationView navigationView;

    //private PopupWindow popupWindow;
    //private LayoutInflater layoutInflater;

    private MediaPlayer mediaPlayer;
    private int songs [] = {R.raw.cardflip, R.raw.goodcardselect, R.raw.goodresult};

    // https://www.spaceotechnologies.com/android-floating-widget-tutorial/
    // https://www.nextgenearn.com/android-popup-window-example/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Put possible cards into array
        int tempCard[] = {R.drawable.card_basil, R.drawable.card_butterfly, R.drawable.card_cake, R.drawable.card_car, R.drawable.card_clock,
                R.drawable.card_cow, R.drawable.card_diamond, R.drawable.card_gift, R.drawable.card_hippo, R.drawable.card_house,
                R.drawable.card_phone, R.drawable.card_roses, R.drawable.card_target, R.drawable.card_tortise, R.drawable.card_water};
        cardFaces = tempCard;

        setCards();

        endScreen = findViewById(R.id.endScreen);
        replay = findViewById(R.id.replay);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menuicon);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        navBarActions(menuItem);
                        return true;
                    }
                });

        mediaPlayer = MediaPlayer.create(this, songs[0]);

    }

    // Selection of nav drawer elements
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // What nav drawer elements do
    private void navBarActions(MenuItem menuItem) {
        if(menuItem.getTitle() == getString(R.string.refresh)){
            restartActivity(null); // Will this work?
        }else if(menuItem.getTitle() == getString(R.string.credits)){
            startActivity(new Intent(MainActivity.this, Pop.class));
        }else if (menuItem.getTitle() == getString(R.string.new_game)){
            startActivityForResult(new Intent(MainActivity.this, PopText.class), 11);
        }else if (menuItem.getTitle() == getString(R.string.photos)){
            startActivityForResult(new Intent(MainActivity.this, PopImageText.class), 22);
        }

/*
*             layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            ViewGroup container = (ViewGroup)layoutInflater.inflate(R.layout.card_select, null);

            popupWindow = new PopupWindow(container, 400, 400, true);*/
        //Log.i("Nabbed", menuItem.getTitle() + " selected");
    }

    // Gives the results selected from the listview
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Gets the number of cards
        if(requestCode == 11 & resultCode == RESULT_OK){
            cardOptions = data.getIntExtra("noOfCards", 12);
            restartActivity(null);
        }else if(requestCode == 22 & resultCode == RESULT_OK){
            game_bg = data.getIntExtra("bg_photo", R.drawable.scales);
            RelativeLayout r = findViewById(R.id.relativeLayoutContent);
            r.setBackgroundResource(game_bg);

        }
    }




    // Sets the cards
    public void setCards(){

        //cardDisabled = true;
        //playingCards = new Card[cardOptions];
        playingCards = new int[cardOptions];

        // Create cards and place in cardArray
        for(int i = 0, j = 0; i < playingCards.length; i++){
            // Give two cards same value
            if(i % 2 == 0) j = generateRandom(0, cardFaces.length);
            playingCards[i] = cardFaces[j];

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

        // Only sets the given number of cards, and hides the rest
        for(int child = 0; child < noOfCards; child++){
            CardView card = (CardView)grid.getChildAt(child);
            if(child < cardOptions){
                card.setBack(R.drawable.cardback);
                card.setFront(playingCards[child]);
            }else{
                card.setVisibility(View.GONE);
            }

        }

        Log.i("Info", "Grid has " + noOfCards);

    }


    // When a card is selected
    public void selectCard(View view) {
        // Get image view that was clicked on
        CardView card = (CardView) view;


        // Check if cards are active
        // Check to make sure 2nd card is different card
        // then card with same face
        if (!cardDisabled) {
            card.flipCard();
            mediaPlayer.start();

            Log.i("Question","Run code because card enabled: " + !cardDisabled);
            Log.i("Counter","Card counter first " + counter);
            if (firstCard != card) {
                if (counter >= 1) {
                    cardDisabled = true;
                    // All this for a 1s delay in code -_-
                    final CardView c = card;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            Log.i("Question","Still disabled?" + cardDisabled);
                            Log.i("Counter","Card counter afta " + counter);
                            checkCards(firstCard, c);
                        }
                    }, 1000);
                    // overkill
                    //counter = 0;
                }else {
                    // We haven't clicked on 2 cards, so keep counting
                    firstCard = card;
                    counter++;
                }
            } else {
                // The cards are the same, so ignore and reset counter
                counter = 0;
                mediaPlayer.start();
            }

        }
        Log.i("Question","Cards disabled?" + cardDisabled);
    }

    // Compares the values of 2 cards
    private void checkCards(CardView card1, CardView card2){

        if(card1.getFront() == card2.getFront()){
            card1.setVisibility(View.INVISIBLE);
            card2.setVisibility(View.INVISIBLE);
            mediaPlayer = MediaPlayer.create(this, songs[1]);
            mediaPlayer.start();

            mediaPlayer = MediaPlayer.create(this, songs[0]);
        }else{
            card1.flipCard();
            card2.flipCard();
            mediaPlayer.start();
        }

        checkIfEnded();

        cardDisabled = false;
        counter = 0;
    }

    // Checks if game has ended
    private void checkIfEnded(){
        boolean ended = true;
        //int noOfCards = grid.getChildCount();

        for(int child = 0; child < cardOptions; child++){
            CardView card = (CardView)grid.getChildAt(child);
            ended = ended && (card.getVisibility() == View.INVISIBLE ||card.getVisibility() == View.GONE );
        }

        if(ended){
            endScreen.setVisibility(View.VISIBLE);
            replay.setVisibility(View.VISIBLE);
            mediaPlayer = MediaPlayer.create(this, songs[2]);
            mediaPlayer.start();
        }
    }

    // Restarts Game by resetting visibility and hiding all card faces
    public void restartActivity(View v){
        endScreen.setVisibility(View.INVISIBLE);
        replay.setVisibility(View.INVISIBLE);

        counter = 0;
        cardDisabled = false;

        int noOfCards = grid.getChildCount();
        for(int child = 0; child < noOfCards; child++){
            CardView card = (CardView)grid.getChildAt(child);
            card.setVisibility(View.VISIBLE);
            card.hideCard();
        }

        setCards();
    }

    private int generateRandom(int min_n, int max_n){
        int random = (int)(Math.random() * max_n + min_n);
        return random;
    }


}

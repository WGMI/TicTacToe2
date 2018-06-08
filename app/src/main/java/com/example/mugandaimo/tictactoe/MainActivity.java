package com.example.mugandaimo.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView player1View,player2View;
    Button reset;
    Button[][] buttons;

    boolean player1turn = true;

    int player1points;
    int player2points;
    int roundCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1View = findViewById(R.id.player1text);
        player2View = findViewById(R.id.player2text);
        reset = findViewById(R.id.reset);

        buttons = new Button[3][3];

        for(int x=0;x<3;x++){
            for(int y=0;y<3;y++){
                String buttonID = "space_"+x+y;
                int resID = getResources().getIdentifier(buttonID,"id",getPackageName());
                buttons[x][y] = findViewById(resID);
                buttons[x][y].setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(
                !((Button)view).getText().toString().equals("")
                ){
            return;
        }

        if(player1turn){
            ((Button)view).setText("X");
        } else{
            ((Button)view).setText("O");
        }

        roundCount++;

        if(checkForWin()){
            if(player1turn){
                playerWins(1);
            } else{
                playerWins(2);
            }
        } else if(roundCount == 9){
            playerWins(0);
        } else{
            player1turn = !player1turn;
            //if true make it false
        }
    }

    private void playerWins(int i) {
        if(i==1){
            player1points++;
            Toast.makeText(MainActivity.this,"Player 1 Wins!",Toast.LENGTH_SHORT).show();
            updatePoints();
            resetBoard();
        } else if(i==2){
            player2points++;
            Toast.makeText(MainActivity.this,"Player 2 Wins!",Toast.LENGTH_SHORT).show();
            updatePoints();
            resetBoard();
        } else if(i==0){
            Toast.makeText(MainActivity.this,"Draw!",Toast.LENGTH_SHORT).show();
            resetBoard();
        }
    }

    private void resetBoard() {
        for(int x=0;x<3;x++){
            for(int y=0;y<3;y++){
                buttons[x][y].setText("");
            }
        }

        roundCount = 0;
        player1turn = true;
    }

    private void updatePoints() {
        player1View.setText("Player 1: " + player1points);
        player2View.setText("Player 2: " + player1points);
    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];
        for(int x=0;x<3;x++){
            for(int y=0;y<3;y++){
                field[x][y] = buttons[x][y].getText().toString();
            }
        }

        for(int x=0;x<3;x++){
            if(
                    field[x][0].equals(field[x][1]) &&
                            field[x][0].equals(field[x][2]) &&
                            !field[x][0].equals("")
                    ){
                return true;
            }
        }

        for(int x=0;x<3;x++){
            if(
                    field[0][x].equals(field[1][x]) &&
                            field[0][x].equals(field[2][x]) &&
                            !field[0][x].equals("")
                    ){
                return true;
            }
        }

        if(
                field[0][2].equals(field[1][1]) &&
                        field[0][2].equals(field[2][0]) &&
                        !field[0][2].equals("")
                ){
            return true;
        }

        if(
                field[0][0].equals(field[1][1]) &&
                        field[0][0].equals(field[2][2]) &&
                        !field[0][0].equals("")
                ){
            return true;
        }

        return false;
    }
}

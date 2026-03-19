package me.docs.japuszko;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    boolean gra = false;
    int appleIndex = 4;
    int gameTime = 10000;
    int swapTicks = 0;
    int points = 0;
    boolean clicked = false;
    boolean trap = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start = findViewById(R.id.button);
        TextView pointText = findViewById(R.id.pointcount);
        TextView time = findViewById(R.id.timecount);
        ArrayList<ImageView> zdjecia = new ArrayList<>();
        zdjecia.add(findViewById(R.id.imageView1));
        zdjecia.add(findViewById(R.id.imageView2));
        zdjecia.add(findViewById(R.id.imageView3));
        zdjecia.add(findViewById(R.id.imageView4));
        zdjecia.add(findViewById(R.id.imageView5));
        zdjecia.add(findViewById(R.id.imageView6));
        zdjecia.add(findViewById(R.id.imageView7));
        zdjecia.add(findViewById(R.id.imageView8));
        zdjecia.add(findViewById(R.id.imageView9));

        for(ImageView i:zdjecia){
            i.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = zdjecia.indexOf(i);
                    if(index==appleIndex && !clicked){
                        clicked = true;
                        if(trap){
                            points--;
                        } else{
                            points++;
                        }
                        pointText.setText(String.valueOf(points));
                    }
                }
            });
        }
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!gra){
                    gra = true;
                    points = 0;
                    gameTime = 10000;
                    countDownTimer = new CountDownTimer(10000,100) {
                        @Override
                        public void onFinish() {

                        }

                        @Override
                        public void onTick(long l) {
                            gameTime-=100;
                            time.setText(String.valueOf((double) gameTime / 1000));
                            if(gameTime==0){
                                gra = false;
                            }
                            if(swapTicks != 0){
                                swapTicks--;
                            } else{
                                swapTicks = 5;
                                clicked = false;
                                trap = false;
                                appleIndex = (int) Math.floor(Math.random() * 9);
                                if(Math.random() <= 0.2){
                                    trap = true;
                                }
                                for(ImageView i:zdjecia){
                                    i.setVisibility(INVISIBLE);
                                }
                                if(trap){
                                    zdjecia.get(appleIndex).setImageDrawable(getDrawable(R.drawable.evil_japuszko));
                                } else{
                                    zdjecia.get(appleIndex).setImageDrawable(getDrawable(R.drawable.japuszko));
                                }
                                zdjecia.get(appleIndex).setVisibility(VISIBLE);
                            }
                        }
                    };
                    countDownTimer.start();
                }
            }
        });

    }
}
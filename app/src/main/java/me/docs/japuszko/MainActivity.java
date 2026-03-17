package me.docs.japuszko;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    boolean gra = false;
    int appleIndex = 4;
    float gameTime = 10;
    boolean trap = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start = findViewById(R.id.button);
        TextView points = findViewById(R.id.pointcount);
        TextView time = findViewById(R.id.timecount);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!gra){
                    gra = true;
                    countDownTimer = new CountDownTimer(1000 * (long) gameTime,100) {
                        @Override
                        public void onFinish() {

                        }

                        @Override
                        public void onTick(long l) {
                            gameTime-=0.1;
                            time.setText(String.valueOf(Math.floor(gameTime * 10)/10));
                        }
                    };
                    countDownTimer.start();
                }
            }
        });
    }
}
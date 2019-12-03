package com.example.androidpractice5;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.os.Message;
import android.telephony.MbmsStreamingSession;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = (TextView) findViewById(R.id.txtContent);
        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                textView.setText(msg.arg1 + "");
            }
        };
        final Runnable myWorker = new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while (progress <= 100) {
                    Message msg = new Message();
                    msg.arg1 = progress;
                    handler.sendMessage(msg);
                    progress = progress + 10;
                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Message msg = handler.obtainMessage();
                msg.arg1 = -1;
                handler.sendMessage(msg);
            }
        };
        Button button = (Button)findViewById(R.id.btnStart);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Thread workThread = new Thread(null,myWorker,"WorkThread");
                    workThread.start();
                }
            });
            final TextView textView1 = (TextView)findViewById(R.id.txtContent);
            Handler handler1 = new Handler();
            handler1.post(new Runnable() {
                @Override
                public void run() {
                    textView1.setText("0");
                }
            });
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

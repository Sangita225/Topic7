package com.example.topic7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintStream;

public class MainActivity extends AppCompatActivity {

    private EditText etword,etmeaning;
    private Button btnadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etword = findViewById(R.id.etword);
        etmeaning = findViewById(R.id.etmeaning);
        btnadd = findViewById(R.id.btnadd);


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                save();
            }
        });
    }
        private void save(){
            try {
                PrintStream printStream=new PrintStream(openFileOutput("words.txt",MODE_PRIVATE | MODE_APPEND));
                printStream.println(etword.getText().toString()+"->"+ etmeaning.getText().toString());
                Toast.makeText(this, "Saved to"+getFilesDir(),Toast.LENGTH_SHORT).show();


            }catch (IOException e){
                Log.d("Dictionary app","Error:"+e.toString());
                e.printStackTrace();
            }

        }


    }


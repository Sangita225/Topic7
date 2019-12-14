package com.example.topic7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DisplayActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lstDictionary;
    private Map<String,String>dicionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);


        lstDictionary=findViewById(R.id.lstview);
        dicionary=new HashMap<>();
        readFromFile();
        ArrayAdapter adapter=new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(dicionary.keySet())
        );
lstDictionary.setAdapter(adapter);
lstDictionary.setOnItemClickListener(this);

    }

    private void readFromFile(){
        try{
            FileInputStream fos=openFileInput("words.txt");
            InputStreamReader isr=new InputStreamReader(fos);
            BufferedReader br=new BufferedReader(isr);
            String line="";
            while((line=br.readLine())!=null){
                String[]parts=line.split("->");
                dicionary.put(parts[0],parts[1]);

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //getting the current position
        String key=parent.getItemAtPosition(position).toString();

        //getting the meaning of current position key
        String meaning=dicionary.get(key);

        Intent intent=new Intent(DisplayActivity.this,MeaningActivity.class);
        intent.putExtra("meaning",meaning);
        startActivity(intent);



    }
}



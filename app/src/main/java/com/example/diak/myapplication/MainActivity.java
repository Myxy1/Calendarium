package com.example.diak.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText name,date;
    private RadioButton rb1,rb2;
    private String nev,szuletesnap,nem,ido_most,eredmeny;
    private Button save,karacsony,szulinap,reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.name);

        SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        nev = sharedPreferences.getString("name","");
        name.setText(nev);

        szuletesnap = sharedPreferences.getString("date","");
        date = (EditText)findViewById(R.id.birthday);
        date.setText(szuletesnap);

        szuletesnap = date.getText().toString();
        rb1 = (RadioButton)findViewById(R.id.radio0);
        rb2 = (RadioButton)findViewById(R.id.radio1);
        DateFormat df = new SimpleDateFormat("MM.dd");
        ido_most = df.format(Calendar.getInstance().getTime());


        save = (Button)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().length() == 0 || date.getText().length() == 0){
                    Toast.makeText(MainActivity.this,"Please fill the form below and press Save!",Toast.LENGTH_SHORT).show();
                }else{
                    nev = name.getText().toString();
                    szuletesnap = date.getText().toString();
                    if (rb1.isChecked()){
                        nem = "Male";
                    }else{
                        nem = "Female";
                    }
                    SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("name", name.getText().toString());
                    editor.putString("date", date.getText().toString());
                    editor.commit();
                    Toast.makeText(MainActivity.this,"Your data saved!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText(null);
                date.setText(null);
                rb1.setChecked(false);
                rb2.setChecked(false);
            }
        });

        karacsony = (Button)findViewById(R.id.karacsony);
        karacsony.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                if (ido_most.equals("12.24") || ido_most.toString().equals("12.25") || ido_most.toString().equals("12.26")){
                    eredmeny = "Yes!! Merry Christmas " + name.getText().toString() + "!!!:)";
                }else{
                    eredmeny = "No, I'm sorry " + name.getText().toString() + " :(";
                }
                alertDialogBuilder
                        .setTitle("Is it Christmas?")
                        .setMessage(eredmeny)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        szulinap = (Button)findViewById(R.id.szulinap);
        szulinap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().length() == 0 || date.getText().length() == 0) {
                    Toast.makeText(MainActivity.this, "Please fill the form below and press Save!", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                    if (date.getText().toString().equals(ido_most)) {
                        eredmeny = "Yes!!! Happy Birthday " + name.getText().toString() + " !!!:)";
                    } else {
                        eredmeny = "No, I'm sorry " + name.getText().toString() + " :(";
                    }
                    alertDialogBuilder
                            .setTitle("Is it Birthday?")
                            .setMessage(eredmeny)
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
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
            if (name.getText().length() == 0 || date.getText().length() == 0) {
                Toast.makeText(MainActivity.this, "Please fill the form below and press Save!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Name:" + name.getText().toString() + ",Date of birth:" + date.getText().toString() + "Gender:" + nem, Toast.LENGTH_LONG).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.example.spalanie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Button btn,btnexit;
    ToggleButton tblicznik;
    EditText etbiezacy,etpoprzedni,etpaliwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);
        etbiezacy=findViewById(R.id.etbiezacy);
        etpoprzedni=findViewById(R.id.etpoprzedni);
        etpaliwo=findViewById(R.id.etpaliwo);
        btn=findViewById(R.id.btn);
        tblicznik=findViewById(R.id.tblicznik);
        etbiezacy.setHint("Licznik główny aktualnie w km");
        etpoprzedni.setHint("Licznik główny poprzednio w km");
        tblicznik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tblicznik.isChecked()) {
                    etpoprzedni.setVisibility(View.INVISIBLE);
                    etbiezacy.setHint("Licznik dzienny w km");
                }
                else {
                    etpoprzedni.setVisibility(View.VISIBLE);
                    etbiezacy.setHint("Licznik główny aktualnie w km");
                    etpoprzedni.setHint("Licznik główny poprzednio w km");
                }
                tv.setText("");
                etbiezacy.setText("");
                etpoprzedni.setText("");
                etpaliwo.setText("");
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double licznik=0,paliwo=0,spalanie=0;
                if(tblicznik.isChecked()){
                    try{
                        licznik=Double.parseDouble(etbiezacy.getText().toString());
                        paliwo=Double.parseDouble(etpaliwo.getText().toString());
                        spalanie=Math.round((paliwo/licznik)*100);
                        tv.setText("Spalanie: "+spalanie+" l/100 km"+
                                "\r\n\r\nZatankowano: "+paliwo+"\r\n"+
                                "Przejechano: "+licznik);
                    }catch(Exception blad) {
                        Toast.makeText(MainActivity.this,blad.getMessage(),Toast.LENGTH_LONG).show();
                        tv.setText("");
                    }finally {
                        etbiezacy.setText("");
                        etpoprzedni.setText("");
                        etpaliwo.setText("");
                    }
                }
                else{
                    try{
                        licznik=Double.parseDouble(etbiezacy.getText().toString());
                        licznik-=Double.parseDouble(etpoprzedni.getText().toString());
                        paliwo=Double.parseDouble(etpaliwo.getText().toString());
                        spalanie= Math.round((paliwo / licznik)*100);
                        tv.setText("Spalanie: "+spalanie+" l/100 km"+
                                "\r\n\r\nZatankowano: "+paliwo+"\r\n"+
                                "Przejechano: "+licznik);
                    }catch(Exception blad) {
                        Toast.makeText(MainActivity.this,blad.getMessage(),Toast.LENGTH_LONG).show();
                        tv.setText("");
                    }finally {
                        etbiezacy.setText("");
                        etpoprzedni.setText("");
                        etpaliwo.setText("");
                    }
                }
            }
        });
        btnexit=findViewById(R.id.btnexit);
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
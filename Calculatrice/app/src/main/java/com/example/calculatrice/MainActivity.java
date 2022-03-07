package com.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText ec;
    ListView lv;
    List<String> operations;
    ArrayAdapter aad;
    int temp,temp1,temp2;
    char op;
    boolean x = false,isNegative = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lv);
        operations = new ArrayList<>();
        aad = new ArrayAdapter(this, android.R.layout.simple_list_item_1,operations);
        lv.setAdapter(aad);
    }

    public void calc(View view) {
        Button btn = (Button) view;
        ec = findViewById(R.id.ecran);
        ec.setText(ec.getText().toString()+btn.getText());
        temp = Integer.parseInt(ec.getText().toString());

        if(isNegative) {
            temp = temp * -1;
        }
        x = true;
    }

    public void calcOp(View view) {
        Button btn = (Button) view;
        ec = findViewById(R.id.ecran);
        op = btn.getText().charAt(0);
        ec.setText("");
        if(op == '-' && x == false) {
            isNegative = true;
        }

        temp1 = temp;
        x = false;
    }

    public void res(View view) {
            if(x == false) {
                Toast.makeText(this, "le deuxieme nombre n'est pas saisie", Toast.LENGTH_SHORT).show();
                return ;
            }
            temp2 = temp;
            ec.setText("");
            switch (op) {
                case '+' :
                    temp = temp1+temp2;
                        ec.setText(Integer.toString(temp));
                    break;
                case '-' :
                    temp = temp1-temp2;
                    ec.setText(Integer.toString(temp));
                    break;
                case '*' :
                    temp = temp1*temp2;
                    ec.setText(Integer.toString(temp));
                    break;
                case '/' :
                    temp = temp1/temp2;
                    ec.setText(Integer.toString(temp));
                    break;
            }
    }

    public void add (View view){
        operations.add(temp1 + " " + op + " "+temp2+" = " +  temp);
        aad.notifyDataSetChanged();
    }

    public void vider(View view) {
        ec = findViewById(R.id.ecran);
        temp = temp1 = temp2 = 0;
        ec.setText("");
    }




}
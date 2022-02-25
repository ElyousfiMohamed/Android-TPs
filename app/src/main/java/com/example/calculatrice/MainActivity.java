package com.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ec;
    int number;
    int temp;
    char op;
    boolean x = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calc(View view) {
        Button btn = (Button) view;
        ec = findViewById(R.id.ecran);
        ec.setText(ec.getText().toString()+btn.getText());
        number = Integer.parseInt(ec.getText().toString());
        x = true;
    }

    public void calcOp(View view) {
        Button btn = (Button) view;
        ec = findViewById(R.id.ecran);
        op = btn.getText().charAt(0);
        ec.setText("");
        temp = number;
        x = false;
    }

    public void res(View view) {
            if(x == false) {
                Toast.makeText(this, "The second number ?", Toast.LENGTH_SHORT).show();
                return ;
            }
            ec.setText("");
            switch (op) {
                case '+' :
                    int res= temp+number;
                    System.out.println(res);
                    ec.setText(Integer.toString(res));
                    break;
                case '-' :
                    res= temp-number;
                    System.out.println(res);
                    ec.setText(Integer.toString(res));
                    break;
                case '*' :
                    res= temp*number;
                    System.out.println(res);
                    ec.setText(Integer.toString(res));
                    break;
                case '/' :
                    res= temp/number;
                    System.out.println(res);
                    ec.setText(Integer.toString(res));
                    break;
            }
    }




}
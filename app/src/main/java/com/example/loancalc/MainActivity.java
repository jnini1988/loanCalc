package com.example.loancalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText loanAmt,rate, term, fee, minimum;
    private Button calc;
    private TextView monthly, totalIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButton();
    }

    public void addListenerOnButton() {
        loanAmt = (EditText) findViewById(R.id.loanAmount);
        rate = (EditText) findViewById(R.id.interestRate);
        term = (EditText) findViewById(R.id.loanTerm);
        fee = (EditText) findViewById(R.id.loanFee);
        minimum = (EditText) findViewById(R.id.minimumPay);
        monthly = (TextView) findViewById(R.id.monthly_payment);
        totalIn = (TextView) findViewById(R.id.total_interest);

        calc = (Button) findViewById(R.id.button);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loanAmount=loanAmt.getText().toString();
                String interestRate=rate.getText().toString();
                String loanTerm=term.getText().toString();
                String loanFee=fee.getText().toString();
                String minimumPay=minimum.getText().toString();

                double l=Double.parseDouble(loanAmount);
                double r=Double.parseDouble(interestRate);
                int t=Integer.parseInt(loanTerm);
                double f=Double.parseDouble(loanFee);
                double m=Double.parseDouble(minimumPay);

                double P=l+f;
                double interest = (P*Math.pow(1+r,t))/(t*12)-P;
                double month = (P+interest)/(t*12);
                //if monthly estimated payment is less than minimum payment, then minimum payment is paided each month and payment term may be shortened
                if(month<m){
                    month=m;
                }
                monthly.setText(String.valueOf(round(month)));
                totalIn.setText(String.valueOf(round(interest)));
            }
        });

    }

    public double round (double n){
        return Math.round(n*100.0)/100.0;
    }
}
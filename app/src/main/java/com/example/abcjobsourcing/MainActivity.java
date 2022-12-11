package com.example.abcjobsourcing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    String role;
    double rate = 1000;
    double grossSalary = 0.00;
    int workHr;
    double net = 0.00;
    int dep;
    double iTaxdeduction;
    double memfee , ssdeduc, totnet, deductions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    final EditText name = (EditText) findViewById(R.id.cName);
    final Spinner roles = (Spinner) findViewById(R.id.cRole);
    final EditText dependents = (EditText) findViewById(R.id.nDep);
    final EditText hrs = (EditText) findViewById(R.id.nHrs);

    final Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
    final Button btnClear = (Button)  findViewById(R.id.btnClear);

    final TextView res1 = (TextView) findViewById(R.id.result1);
    final TextView gross = (TextView) findViewById(R.id.gSal);
    final TextView net = (TextView) findViewById(R.id.nSal);

    btnSubmit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            role = roles.getSelectedItem().toString();
            res1.setText(role);

            workHr = Integer.parseInt(hrs.getText().toString());
            grossSalary = rate *workHr;
            DecimalFormat df = new DecimalFormat("Php ###,###.00");
            gross.setText("Gross Salary is: " +df.format(grossSalary));

            dep = Integer.parseInt(dependents.getText().toString());
            iTaxdeduction = (grossSalary-(grossSalary * .05 * dep))*.15;
            memfee = (grossSalary * .13);
            ssdeduc = (grossSalary * .0785);
            deductions = iTaxdeduction+memfee+ssdeduc;
            totnet = grossSalary-deductions;
            DecimalFormat dff = new DecimalFormat("Php ###,###.00");
            net.setText(("Net salary is: "+ dff.format(totnet)));
        }
    });

    btnClear.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            roles.setSelection(0);
            res1.setText("No role selected");
            dependents.setText("No. of Dependents");
            hrs.setText("No. of hrs");
            gross.setText("Php 0.00");
            net.setText("Php 0.00");
        }
    });

    }
}
package com.example.abcjobsourcing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
            try {

                // perform the operation

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

                //to hide the keyboard when user began to input
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            } catch (NumberFormatException e) {
                // Get the context for the app
                Context context = getApplicationContext();

                // Set the duration for the toast
                int duration = Toast.LENGTH_SHORT;

                // Set the text for the toast
                CharSequence text = "Please enter valid integers.";

                // Create the toast object
                Toast toast = Toast.makeText(context, text, duration);

                // Show the toast
                toast.show();
            }


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
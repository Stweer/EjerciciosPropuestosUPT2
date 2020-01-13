package com.example.ejerciciospropuestosupt2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String  TAG = "MainActivity";
    private EditText mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDataSetListener;

    EditText edtnombre;
    EditText edtapellido;
    Spinner spinner;
    Spinner spinner2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mDisplayDate = (EditText) findViewById(R.id.edtfecha);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,R.style.Theme_AppCompat_Light_Dialog_MinWidth,mDataSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.show();
            }
        });

        mDataSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateset: mm/dd/yyyy: " + month + "/" + day +"/" + year);
                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };



        edtnombre=findViewById(R.id.txtnombre);
        edtapellido=findViewById(R.id.txtapellido);
        spinner = (Spinner) findViewById(R.id.spngrado);
        spinner2 = (Spinner) findViewById(R.id.spnsexo);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Grado, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.Sexo, android.R.layout.simple_spinner_item);
        spinner2.setAdapter(adapter2);

    }
    public void Enviar(View view){
        Intent intent = new Intent(this, Respuesta.class);
        intent.putExtra("nombre",edtnombre.getText().toString());
        intent.putExtra("apellido",edtapellido.getText().toString());

        intent.putExtra("grado",spinner.getSelectedItem().toString());
        intent.putExtra("sexo",spinner2.getSelectedItem().toString());
        intent.putExtra("fecha",mDisplayDate.getText().toString());

        startActivity(intent);
    }
}

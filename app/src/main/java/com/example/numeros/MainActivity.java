package com.example.numeros;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Integer> descomponEnFactoresPrimos(int valor)
    {
        assert valor>1;

        // Se empieza probando como posible factor primo el 2.
        int factor = 2;
        ArrayList<Integer> factores=new ArrayList<Integer>();

        if(valor == 1){
            factores.add(1);
        }else{
            while (factor <= valor)
            {
                // Mientras es divisible, se añade el factor a la lista de factores primos
                // y se realiza la división.
                while (valor % factor == 0 )
                {
                    factores.add(new Integer(factor));
                    valor = valor/factor;
                }

                // Si no es divisible, se pasa al posible siguiente factor.
                factor++;
            }
        }



        return factores;
    }





    boolean soloPrimos = false;
    int veces = 0;
    int numeroExacto = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final TextView tv = (TextView) findViewById(R.id.tv);
        final NumberPicker np = (NumberPicker) findViewById(R.id.np);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        final RadioButton rb = (RadioButton) findViewById(R.id.radioButton);
        final RadioButton rb2 = (RadioButton) findViewById(R.id.radioButton2);
        final RadioButton rb3 = (RadioButton) findViewById(R.id.radioButton3);
        final RadioButton rb4 = (RadioButton) findViewById(R.id.radioButton4);
        final Switch sw = (Switch) findViewById(R.id.switch1);
        final FrameLayout myFrame = (FrameLayout) findViewById(R.id.asd);
        final EditText min = (EditText) findViewById(R.id.editText);
        final EditText max = (EditText) findViewById(R.id.editText2);
        final EditText ex = (EditText) findViewById(R.id.editText3);
        final String[] values = {"4", "6", "8", "9", "10", "12", "14", "15", "16",
                "18","20","21","22","24","25","26","27","28","30","32","33",
                "34","35","36","38","39","40","42","44","45","46","48", "49",
                "50","51","52","54","55","56","57","58","60","62","63", "64",
                "65","66","68","69","70","72","74","75","76","77","78", "80",
                "81","82","84","85","86","87","88","90","91","92","93", "94",
                "95","96","98","99","100"};

        //TODO: definir el maximo y el minimo del carrusel con el editText



        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioButton:
                        if(sw.isChecked()){
                            tv.setTextColor(Color.parseColor("#ffffff"));
                        }else{
                            tv.setTextColor(Color.parseColor("#000000"));
                        }
                        tv.setText("Seleccione numero");
                        np.setDisplayedValues(null);
                        np.setMinValue(0);
                        np.setMaxValue(values.length-1);
                        np.setDisplayedValues(values);
                        soloPrimos = true;
                        break;
                    case R.id.radioButton2:
                        if(sw.isChecked()){
                            tv.setTextColor(Color.parseColor("#ffffff"));
                        }else{
                            tv.setTextColor(Color.parseColor("#000000"));
                        }
                        tv.setText("Seleccione numero");
                        np.setDisplayedValues(null);
                        np.setMinValue(1);
                        np.setMaxValue(100);
                        soloPrimos = false;
                        break;
                    case R.id.radioButton3:
                        int nmax = 0;
                        int nmin = 0;
                        try{
                            nmax = Integer.parseInt(max.getText().toString());
                            nmin = Integer.parseInt(min.getText().toString());
                        }catch (Exception e){
                            System.out.println("ERROR");
                        }
                        if(max.getText().toString().equals("")){
                            tv.setTextColor(Color.parseColor("#ff0000"));
                            tv.setText("INTRODUZCA ALGÚN VALOR MÁXIMO");
                            np.setDisplayedValues(null);
                            np.setMinValue(0);
                            np.setMaxValue(0);
                        }else {
                            if (nmin > nmax) {
                                tv.setTextColor(Color.parseColor("#ff0000"));
                                tv.setText("INTRODUZCA UN VALOR MÁXIMO MAYOR QUE EL MÍNIMO");
                                np.setDisplayedValues(null);
                                np.setMinValue(0);
                                np.setMaxValue(0);
                            } else {
                                if (min.getText().toString().equals("")) {
                                    np.setDisplayedValues(null);
                                    np.setMinValue(0);
                                    np.setMaxValue(nmax);
                                    soloPrimos = false;
                                } else {
                                    np.setDisplayedValues(null);
                                    np.setMinValue(nmin);
                                    np.setMaxValue(nmax);
                                    soloPrimos = false;
                                }

                            }
                        }
                        break;
                    case R.id.radioButton4:
                        if(ex.getText().toString().equals("")){
                            tv.setTextColor(Color.parseColor("#ff0000"));
                            tv.setText("INTRODUZCA UN NUMERO EXACTO");
                        }else{
                            if(sw.isChecked()){
                                tv.setTextColor(Color.parseColor("#ffffff"));
                            }else{
                                tv.setTextColor(Color.parseColor("#000000"));
                            }
                            tv.setText("Seleccione numero");
                            np.setDisplayedValues(null);
                            np.setMinValue(0);
                            np.setMaxValue(0);
                            numeroExacto = Integer.parseInt(ex.getText().toString());
                            soloPrimos=false;
                            ArrayList<Integer> factores = new ArrayList<Integer>();
                            factores = descomponEnFactoresPrimos(numeroExacto);
                            String resultado = "";
                            int n = 0;
                            for(Integer i : factores){
                                if(n == 0){
                                    resultado = ""+i;
                                }else{
                                    resultado = resultado + ", " + i;
                                }
                                n++;
                            }
                            //Display the newly selected number from picker
                            tv.setText("El numero "+numeroExacto+" descompuesto en numeros primos es :"+resultado);
                        }
                        break;
                }
            }
        });



        tv.setTextColor(Color.parseColor("#000000"));

        //Gets whether the selector wheel wraps when reaching the min/max value.
        np.setWrapSelectorWheel(true);




        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                veces++;
                if(sw.isChecked()){
                    sw.setText("Desactivar modo noche");
                    tv.setTextColor(Color.parseColor("#ffffff"));
                    sw.setTextColor(Color.parseColor("#ffffff"));
                    myFrame.setBackgroundColor(Color.parseColor("#000000"));
                    rb.setTextColor(Color.parseColor("#ffffff"));
                    rb2.setTextColor(Color.parseColor("#ffffff"));
                    rb3.setTextColor(Color.parseColor("#ffffff"));
                    rb4.setTextColor(Color.parseColor("#ffffff"));
                    min.setTextColor(Color.parseColor("#ffffff"));
                    max.setTextColor(Color.parseColor("#ffffff"));
                    ex.setTextColor(Color.parseColor("#ffffff"));
                }else{
                    sw.setText("Activar modo noche");
                    tv.setTextColor(Color.parseColor("#000000"));
                    sw.setTextColor(Color.parseColor("#000000"));
                    myFrame.setBackgroundColor(Color.parseColor("#ffffff"));
                    rb.setTextColor(Color.parseColor("#000000"));
                    rb2.setTextColor(Color.parseColor("#000000"));
                    rb3.setTextColor(Color.parseColor("#000000"));
                    rb4.setTextColor(Color.parseColor("#000000"));
                    min.setTextColor(Color.parseColor("#000000"));
                    max.setTextColor(Color.parseColor("#000000"));
                    ex.setTextColor(Color.parseColor("#000000"));
                }

                if(veces%25 == 0){
                    sw.setText("");
                    tv.setVisibility(View.GONE);
                    rg.setVisibility(View.GONE);
                    np.setVisibility(View.GONE);
                    max.setVisibility(View.GONE);
                    min.setVisibility(View.GONE);
                    ex.setVisibility(View.GONE);
                    myFrame.setBackground(getResources().getDrawable(R.drawable.ayuwoki));

                }else{
                    tv.setVisibility(View.VISIBLE);
                    rg.setVisibility(View.VISIBLE);
                    np.setVisibility(View.VISIBLE);
                    max.setVisibility(View.VISIBLE);
                    min.setVisibility(View.VISIBLE);
                    ex.setVisibility(View.VISIBLE);
                }
            }
        });




        //Set a value change listener for NumberPicker
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, final int newVal){
                if(soloPrimos == true){
                    ArrayList<Integer> factores = new ArrayList<Integer>();
                    factores = descomponEnFactoresPrimos((Integer.parseInt(values[newVal])));
                    String resultado = "";
                    int n = 0;
                    for(Integer i : factores){
                        if(n == 0){
                            resultado = ""+i;
                        }else{
                            resultado = resultado + ", " + i;
                        }
                        n++;
                    }
                    tv.setText("El numero "+values[newVal]+" descompuesto en numeros primos es :"+resultado);
                }else{
                    ArrayList<Integer> factores = new ArrayList<Integer>();
                    factores = descomponEnFactoresPrimos(newVal);
                    String resultado = "";
                    int n = 0;
                    for(Integer i : factores){
                        if(n == 0){
                            resultado = ""+i;
                        }else{
                            resultado = resultado + ", " + i;
                        }
                        n++;
                    }
                    //Display the newly selected number from picker
                    tv.setText("El numero "+newVal+" descompuesto en numeros primos es :"+resultado);
                }
            }
        });
    }
}

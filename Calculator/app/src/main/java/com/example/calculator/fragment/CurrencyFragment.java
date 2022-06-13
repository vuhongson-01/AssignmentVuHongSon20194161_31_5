package com.example.calculator.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.calculator.MainActivity;
import com.example.calculator.R;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class CurrencyFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    View view;

    String input, output;
    Spinner spinner_output, spinner_input;

    boolean dotf = false;
    TextView workingsTV;
    TextView resultsTV;
    String workings = "";
    String currentResult = "";
    int maxCharacter = 20;

    String[] currencyArr;
    int numOfCurrent = 7;
    Hashtable<String, Integer> ci = new Hashtable<String, Integer>();
//    int exchangeUnitTable[][] = new int[numOfCurrent][numOfCurrent];
    String exchangeUnitTable[][] = {{"1", "0.000043", "0.00029", "0.0058", "0.055", "0.0035", "0.000041"},
                                    {"23182.00", "1", "6.71", "134.42", "1279.28", "57.62", "0.95"},
                                    {"3455.41", "0.15", "1", "20.04", "190.68", "8.78", "0.14"},
                                    {"172.44", "0.0074", "0.05", "1", "0.52", "0.43", "0.0071"},
                                    {"18.12", "0.00078", "0.0052", "0.11", "1", "0.045", "0.00074"},
                                    {"402.29", "0.017", "0.12", "2.33", "22.2", "1", "0.016"},
                                    {"24387.46", "1.05", "7.06", "141.39", "1345.80", "60.62", "1"}};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_currency, container, false);
        makeHashCurrency();

        // Inflate the layout for this fragment
        spinner_input = (Spinner) view.findViewById(R.id.spn_category_input);
        spinner_input.setOnItemSelectedListener(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_input = ArrayAdapter.createFromResource(getActivity(),
                R.array.currency_unit, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter_input.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_input.setAdapter(adapter_input);

//      ---------------------------------------------------------------------
        spinner_output = (Spinner) view.findViewById(R.id.spn_category_output);
        spinner_output.setOnItemSelectedListener(this);
        
        ArrayAdapter<CharSequence> adapter_output = ArrayAdapter.createFromResource(getActivity(),
                R.array.currency_unit, android.R.layout.simple_spinner_item);
        adapter_output.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_output.setAdapter(adapter_output);

        workingsTV = (TextView) view.findViewById(R.id.input_currency);
        resultsTV = (TextView) view.findViewById(R.id.output_currency);

        setButtonListener();

        setWorkings("0");
        input = spinner_input.getSelectedItem().toString();
        output = spinner_output.getSelectedItem().toString();


        return view;
    }

    void setButtonListener(){
        Button btn1 = (Button) view.findViewById(R.id.input_1);
        btn1.setOnClickListener(this);
        Button btn2 = (Button) view.findViewById(R.id.input_2);
        btn2.setOnClickListener(this);
        Button btn3 = (Button) view.findViewById(R.id.input_3);
        btn3.setOnClickListener(this);
        Button btn4 = (Button) view.findViewById(R.id.input_4);
        btn4.setOnClickListener(this);
        Button btn5 = (Button) view.findViewById(R.id.input_5);
        btn5.setOnClickListener(this);
        Button btn6 = (Button) view.findViewById(R.id.input_6);
        btn6.setOnClickListener(this);
        Button btn7 = (Button) view.findViewById(R.id.input_7);
        btn7.setOnClickListener(this);
        Button btn8 = (Button) view.findViewById(R.id.input_8);
        btn8.setOnClickListener(this);
        Button btn9 = (Button) view.findViewById(R.id.input_9);
        btn9.setOnClickListener(this);
        Button btn0 = (Button) view.findViewById(R.id.input_0);
        btn0.setOnClickListener(this);
        Button btnC = (Button) view.findViewById(R.id.input_C);
        btnC.setOnClickListener(this);
        Button btnDot = (Button) view.findViewById(R.id.input_dot);
        btnDot.setOnClickListener(this);
        Button btnBS = (Button) view.findViewById(R.id.input_BS);
        btnBS.setOnClickListener(this);
    }

    void makeHashCurrency(){
        currencyArr = getResources().getStringArray(R.array.currency_unit);
        for (int i = 0; i < currencyArr.length; i++){
            ci.put(currencyArr[i], i);
//        System.out.println("Ci: " + ci);
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        input = spinner_input.getSelectedItem().toString();
        output = spinner_output.getSelectedItem().toString();
        convert();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
    private void setWorkings(String givenValue){
        if (workings.length() <= maxCharacter) {
            workings += givenValue;
            workingsTV.setText(workings);
        }
    }

    public void zeroCharacterProcess(){
        if (workings.length() == 1 && workings.charAt(0) == '0'){
            workings = "";
        }
    }
    
    public void numberOnClick(String c) {
        zeroCharacterProcess();
        setWorkings(c);
        if (!workings.equals("0")) convert();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.input_1) numberOnClick("1");
        else if (id == R.id.input_2) numberOnClick("2");
        else if (id == R.id.input_3) numberOnClick("3");
        else if (id == R.id.input_4) numberOnClick("4");
        else if (id == R.id.input_5) numberOnClick("5");
        else if (id == R.id.input_6) numberOnClick("6");
        else if (id == R.id.input_7) numberOnClick("7");
        else if (id == R.id.input_8) numberOnClick("8");
        else if (id == R.id.input_9) numberOnClick("9");
        else if (id == R.id.input_0) numberOnClick("0");

        else if (id == R.id.input_C) clearOnClick();
        else if (id == R.id.input_BS) backSpaceOnClick();
        else if (id == R.id.input_dot) dotOnClick();
    }

    public void dotOnClick() {
        if (!dotf) {
            setWorkings(".");
            convert();
            dotf = true;
        }
    }

    public void clearOnClick() {
        workings = "0";
        dotf = false;
        workingsTV.setText(workings);
        currentResult = "0";
        resultsTV.setText("0");
    }

    public void backSpaceOnClick() {
        if (workings.length() != 0){
            if (workings.length() == 1){
                workings = "";
                setWorkings("0");
                resultsTV.setText("0");
            }
            else {
                if (workings.charAt(workings.length() - 1) == '.'){
                    dotf = false;
                }
                String w = workings.substring(0, workings.length()-1);
                workings = "";
                setWorkings(w);
                convert();
            }
        }
    }

    void convert(){
        // converted
        String input = spinner_input.getSelectedItem().toString();
        String output = spinner_output.getSelectedItem().toString();

        String coef = exchangeUnitTable[ci.get(input).intValue()][ci.get(output).intValue()];

        String cur = workingsTV.getText().toString();

        int dot = findDot(cur);
        int dot2 = findDot(coef);

        int dec1 = 0;
        int dec2 = 0;

        if (dot != -1) {
            cur = standardized(cur, dot);

            int len = cur.length();
            if (dot < len){
                dec1 = len - 1 - dot;
                cur = cur.substring(0, dot) + cur.substring(dot + 1, len);
            }
        }

        if (cur.length() > 1) {
            int j = 0;
            while (j < cur.length() && cur.charAt(j) == '0')
                j++;
            cur = cur.substring(j, cur.length());
        }

        if (dot2 != -1){
            coef = standardized(coef, dot2);

            int len = coef.length();
            if (dot2 < len){
                dec2 = len - 1 - dot2;
                coef = coef.substring(0, dot2) + coef.substring(dot2 + 1, len);
            }
        }

        if (coef.length() > 1) {
            int j = 0;
            while (j < coef.length() && coef.charAt(j) == '0')
                j++;
            coef = coef.substring(j, coef.length());
        }

//        System.out.println("Cur: " + cur + "; " + dec1 + " - " + "Coef: " + coef + "; " + dec2);
        String res = multiplicate(cur, coef);
        int dec = dec1 + dec2;

        if (dec == 0){
            res += ".0";
        }
        else if (!res.equals("0") && dec != 0) {
            int l = res.length();
            if (dec == l) {
                res = "0." + res;
            } else if (dec < l) {
                //12345 2
                res = res.substring(0, l - dec) + "." + res.substring(l - dec, l);
            } else if (dec > l) {
                String pre = "";
                for (int i = 0; i < dec - l; i++)
                    pre += '0';
                pre = "0." + pre;

                res = pre + res;
            }
        }
//        System.out.println("Res: " + res + " - " + "dec: " + dec);

        resultsTV.setText(res);
    }
    int findDot(String cur){
        for (int i = 0; i < cur.length(); i++){
            if (cur.charAt(i) == '.'){
                return i;
            }
        }
        return -1;
    }

    String standardized(String cur, int dot){
        int len = cur.length();
        while (cur.charAt(len - 1) == '0' && cur.charAt(len - 1) != '.') {
            len--;
            cur = cur.substring(0, len);
        }

        if (dot == len - 1){
            cur = cur.substring(0, len-1);
        }

        return cur;
    }

    public int At(String num, int index){       //convert numberic character to number
        if (index < 0) return 0;
        else return (num.charAt(index) - 48);
    }
    public String plus(String num1, String num2){
        String Res = "";
        int i1 = num1.length() - 1;
        int i2 = num2.length() - 1;
        int residual = 0;

        while (true){
            if (i1 < 0 && i2 < 0) break;

            int tmp = At(num1, i1) + At(num2, i2) + residual;
            residual = 0;

            if (tmp < 10){
                Res = Integer.toString(tmp) + Res;
            }
            else {
                Res = Integer.toString((tmp % 10)) + Res;
                tmp = tmp / 10;
                residual = tmp;
            }
            i1--;
            i2--;
        }
        if (residual == 1) Res = "1" + Res;
        return Res;
    }

    public String multiplicate(String num1, String num2){
        String Res = "0";
        for (int i = num2.length() - 1; i >= 0; i--){ // 13 x 19
            String r = "";
            int residual = 0;
            for (int j = num1.length()-1; j >= 0; j--){
                int tmp = At(num2, i) * At(num1, j) + residual;
                r = Integer.toString(tmp % 10) + r;
                residual = tmp / 10;
            }

            if (residual != 0) r = Integer.toString(residual) + r;
            for (int j = 0; j < num2.length() - 1 - i; j++)
                r += "0";
            Res = plus(Res, r);
        }

//        System.out.println("PreRes: " + Res);
        int j = 0;
        if (Res.length() > 1) {
            while (j < Res.length()-1 && Res.charAt(j) == '0') j++;
            Res = Res.substring(j, Res.length());
        }

        return Res;
    }
}

package com.example.calculator.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.calculator.R;
import java.lang.*;
import java.util.Stack;

public class CalculatorFragment extends Fragment implements View.OnClickListener {

    TextView workingsTV;
    TextView resultsTV;
    String workings = "";
    String currentResult = "";
    Stack<String> operatorStack = new Stack<String>();

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_calculator, container, false);
        Button btn1 = (Button) view.findViewById(R.id.btn_1);
        btn1.setOnClickListener(this);
        Button btn2 = (Button) view.findViewById(R.id.btn_2);
        btn2.setOnClickListener(this);
        Button btn3 = (Button) view.findViewById(R.id.btn_3);
        btn3.setOnClickListener(this);
        Button btn4 = (Button) view.findViewById(R.id.btn_4);
        btn4.setOnClickListener(this);
        Button btn5 = (Button) view.findViewById(R.id.btn_5);
        btn5.setOnClickListener(this);
        Button btn6 = (Button) view.findViewById(R.id.btn_6);
        btn6.setOnClickListener(this);
        Button btn7 = (Button) view.findViewById(R.id.btn_7);
        btn7.setOnClickListener(this);
        Button btn8 = (Button) view.findViewById(R.id.btn_8);
        btn8.setOnClickListener(this);
        Button btn9 = (Button) view.findViewById(R.id.btn_9);
        btn9.setOnClickListener(this);
        Button btn0 = (Button) view.findViewById(R.id.btn_0);
        btn0.setOnClickListener(this);
        Button btnReverse = (Button) view.findViewById(R.id.btn_reverseSign);
        btnReverse.setOnClickListener(this);
        Button btnEqual = (Button) view.findViewById(R.id.btn_equalSign);
        btnEqual.setOnClickListener(this);
        Button btnPlus = (Button) view.findViewById(R.id.btn_plusSign);
        btnPlus.setOnClickListener(this);
        Button btnSub = (Button) view.findViewById(R.id.btn_subSign);
        btnSub.setOnClickListener(this);
        Button btnMulti = (Button) view.findViewById(R.id.btn_multipicationSign);
        btnMulti.setOnClickListener(this);
        Button btnDivide = (Button) view.findViewById(R.id.btn_divideSign);
        btnDivide.setOnClickListener(this);
        Button btnCE = (Button) view.findViewById(R.id.btn_CE);
        btnCE.setOnClickListener(this);
        Button btnC = (Button) view.findViewById(R.id.btn_C);
        btnC.setOnClickListener(this);
        Button btnBS = (Button) view.findViewById(R.id.btn_BS);
        btnBS.setOnClickListener(this);

        workingsTV = (TextView) view.findViewById(R.id.workingTextView);
        resultsTV = (TextView) view.findViewById(R.id.resultTextView);
        return view;
    }


    public int isOperator(char x){
        if (x == '+') return 1;
        if (x == '-') return 2;
        if (x == 'x') return 3;
        if (x == '/') return 4;
        if (x == '=') return 5;
        return 0;
    }
    public String transform(String Res){ // from 100000000000000 to 1000000E8
        if (Res.length() >= 14){
            String s = "";
            if (Res.charAt(0) == '-') {
                s = Res.substring(1, 9);
                if (Res.charAt(9) >= '5') s = plus(s,"1");
                s = "-" + s + "E" + Integer.toString(Res.length()-9);
            }
            else {
                s = Res.substring(0, 8);
                if (Res.charAt(8) >= '5') s = plus(s,"1");
                s = s + "E" + Integer.toString(Res.length()-8);
            }
            return s;
        }
        return Res;
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

    public String subtract(String num1, String num2){
        String Res = "";
        int rev = 0;

        //swap (num1,num2) if num1 < num2, 'rev' is flag
        if (num2.length() > num1.length()){
            rev = 1;
            String s = num1;
            num1 = num2;
            num2 = s;

        }
        else if (num1.length() == num2.length()){
            for (int i = 0; i < num1.length(); i++){
                if (num1.compareTo(num2) < 0){
                    rev = 1;
                    String s = num1;
                    num1 = num2;
                    num2 = s;
                    break;
                }
            }
        }

        int i1 = num1.length() - 1;
        int i2 = num2.length() - 1;
        int residual = 0;

        while (true){
            if (i1 < 0 && i2 < 0) break;

            int tmp = At(num1, i1) - At(num2, i2) - residual;
            residual = 0;

            if (tmp >= 0){
                Res = Integer.toString(tmp) + Res;
            }
            else {
                residual = 1;
                tmp += 10;
                Res = Integer.toString(tmp) + Res;
            }
            i1--;
            i2--;
        }

        int i = 0;
        while (Res.charAt(i) == '0' && i < Res.length()-1) i++;
        Res = Res.substring(i, Res.length());

        if (rev == 1) Res = "-" + Res;
        return  Res;
    }

    public int compareTo(String s1, String s2){
        if (s1.length() < s2.length()) return -1;
        if (s1.length() == s2.length())
            return (s1.compareTo(s2));
        if (s1.length() > s2.length()) return 1;
        return 0;
    }

    public String divide(String num1, String num2){
        String Res = "";
        int i = num2.length()-1;
        String nb = num1.substring(0, i+1);

        System.out.println(num1 + " * " + num2 + " * " + nb);
        while (i < num1.length()){  //324 : 8
            if (compareTo(nb, num2) < 0){
                Res += "0";
                System.out.println(Res + " - ");
            } else{
                int t = 0;
                System.out.println(nb + " ~ " + num2);
                while (compareTo(nb, num2) >= 0){
                    nb = subtract(nb, num2);
                    t++;
                }

                Res += Integer.toString(t);
                System.out.println(Res + " -- ");
            }
            i++;
            if (i < num1.length())
                nb += num1.charAt(i);

            // if nb = 0x
            if (!nb.equals("0")) {
                int j = 0;
                while (nb.length() > 0 && nb.charAt(j) == '0' && j < nb.length()-1) j++;
                nb = nb.substring(j, nb.length());
            }
        }
        int j = 0;
        while (Res.charAt(j) == '0') j++;
        Res = Res.substring(j, Res.length());
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

        int j = 0;
        while (Res.charAt(j) == '0') j++;
        Res = Res.substring(j, Res.length());

        return Res;
    }

    public String calculate(String num1, int operator, String num2){
        String Res = "";
        int numOfSubSign = 0;
        if (isOperator(num1.charAt(0)) == 2) {
            numOfSubSign += 1;
            num1 = num1.substring(1, num1.length());
        } //num1 has - sign

        if (isOperator(num2.charAt(0)) == 2) {
            numOfSubSign += 2;
            num2 = num2.substring(1, num2.length());
        }; //num2 has - sign

        if (numOfSubSign == 1 && operator == 1) {                   //-1 + a  (a>0) -> -(1-a)
            operator = 2;
            String s = num1;
            num1 = num2;
            num2 = s;
        }
        else if (numOfSubSign == 1 && operator == 2) operator = 1;  //-1 - a        -> -(1+a)
        else if (numOfSubSign == 2 && operator == 1) operator = 2;  // 1 + (-a)     -> 1-a
        else if (numOfSubSign == 2 && operator == 2) operator = 1;  // 1 - (-a)     -> 1+a

        if (operator == 1){             //+
            Res = plus(num1, num2);
            if (numOfSubSign == 1) Res = "-" + Res;
        }
        else if (operator == 2){        //-
            Res = subtract(num1, num2);
        }
        else if (operator == 3){        //x
            if (num1.equals("0") || num2.equals("0")){
                Res = "0";
            } else {
                Res = multiplicate(num1, num2);
                if (numOfSubSign == 1 || numOfSubSign == 2) Res = "-" + Res;
            }
        }
        else if (operator == 4){        //:
            if (num1.length() < num2.length()){
                Res = "0";
            }
            else if (num1.length() == num2.length() && num1.compareTo(num2) < 0){
                    Res = "0";
            }
            else {
                if (num2.equals("0")) {
                    Res = "INF";
                }
                else {
                    Res = divide(num1, num2);
                    if (numOfSubSign == 1 || numOfSubSign == 2) Res = "-" + Res;
                }
            }
        }
        return Res;
    }

    private void setWorkings(String givenValue){
        if (workings.length() <= 20) {
            workings += givenValue;
            workingsTV.setText(workings);
        }
    }

    public void preCal(String operator){
        if (workings.length() == 1 && isOperator(workings.charAt(0)) > 0){
            workings = "";
            setWorkings("0" + operator);
            while (!operatorStack.empty()) operatorStack.pop();
            operatorStack.push(operator);
            return;
        }
        if (!operatorStack.empty()) {
            if (workings.length() > 0) {
//                123= || 123+456=
                if (isOperator(workings.charAt(workings.length()-1)) == 5){
                    workings = currentResult;
                    if (workings.equals("INF") || workings.equals("-INF")) workings = "0";
                }
                else if (isOperator(workings.charAt(workings.length()-1)) > 0){
//                    123+
                    operatorStack.pop();
                    workings = workings.substring(0, workings.length()-1);
                }
                else {
//                    123+456
                    String num1 = "";
                    String num2 = "";
                    int op = 0;
                    if (workings.charAt(0) == '-') {
                        num2 += '-';
                        workings = workings.substring(1, workings.length());
                    }
                    for (int i = 0; i < workings.length(); i++){
                        if (isOperator(workings.charAt(i)) == 0){
                            num2 += workings.charAt(i);
                        }
                        else {
                            num1 = num2;
                            num2 = "";
                            op = isOperator(workings.charAt(i));
                        }
                    }
//                    System.out.println(num1 + " ~ " + num2);
//                    System.out.println(op);
                    String Res = calculate(num1, op, num2);
                    workings = "";
                    if (!Res.equals("INF")) setWorkings(Res);
                    else setWorkings("0");
                    currentResult = Res;
                    resultsTV.setText(transform(Res));
                }
            } else {
                setWorkings("0");
            }
        }
        else {
            if (workings == ""){
                setWorkings("0");
            }
            else if (workings.charAt(workings.length()-1) == '='){
                workings = workings.substring(0, workings.length()-1);
            }
        }
        setWorkings(operator);
        operatorStack.push(operator);
    }

    private void initTextView() {
        workingsTV = (TextView) getView().findViewById(R.id.workingTextView);
        resultsTV = (TextView) getView().findViewById(R.id.resultTextView);
    }

    public void clearEntryOnClick(View view) {
        if (workings.length() == 0) return;
//      123+
        if (isOperator(workings.charAt(workings.length()-1)) > 0 &&
                isOperator(workings.charAt(workings.length()-1)) <5) {
            workings = "";
            while (!operatorStack.empty()) operatorStack.pop();
            setWorkings("0");
            return;
        }
//      123+456 || 123+456= || 123= || 123, if has [+,-,x,\] then inx > 0
        int inx = -1;
        for (int i = 0; i < workings.length(); i++){
            if (isOperator(workings.charAt(i)) > 0 && isOperator(workings.charAt(i)) < 5 && i > 0){
                inx = i;
                break;
            }
        }

        if (inx == -1){
            workings = "";
            setWorkings("0");
        }
        else{
            workings = workings.substring(0, inx+1);
            setWorkings("");
        }
    }

    public void clearOnClick(View view) {
        workings = "";
        workingsTV.setText(workings);
        currentResult = "0";
        resultsTV.setText("0");

        while (!operatorStack.empty()) operatorStack.pop();
    }

    public void backSpaceOnClick(View view) {
        if (workings.length() != 0){
            if (isOperator(workings.charAt(workings.length()-1)) > 0 &&
                    isOperator(workings.charAt(workings.length()-1)) < 5)
                while (!operatorStack.empty()) operatorStack.pop();

            if (workings.length() == 1){
                workings = "";
                setWorkings("0");
            }
            else {
                String w = workings.substring(0, workings.length()-1);
                workings = "";
                setWorkings(w);
            }
        }
    }

    public void zeroCharacterProcess(){
        if (workings.length() == 1 && workings.charAt(0) == '0'){
            workings = "";
        }
        if (workings.length() > 3 &&
            isOperator(workings.charAt(workings.length()-2)) != 0 &&
            workings.charAt(workings.length()-1) == '0'){
            workings = workings.substring(0, workings.length()-1);
        }
    }

    public void divideOnClick(View view) {
        preCal("/");
    }
    public void multiplicationOnClick(View view){
        preCal("x");
    }
    public void plusOnClick(View view){
        preCal("+");
    }
    public void subOnClick(View view){
        preCal("-");
    }

    public void oneOnClick(View view) {
        zeroCharacterProcess();
        if (workings.length() > 0 && workings.charAt(workings.length()-1) == '=') {
            workings = "";
            while (!operatorStack.empty()) operatorStack.pop();
        }
        setWorkings("1");
    }

    public void twoOnClick(View view) {
        zeroCharacterProcess();
        if (workings.length() > 0 && workings.charAt(workings.length()-1) == '=') {
            workings = "";
            while (!operatorStack.empty()) operatorStack.pop();
        }
        setWorkings("2");
    }

    public void threeOnClick(View view) {
        zeroCharacterProcess();
        if (workings.length() > 0 && workings.charAt(workings.length()-1) == '=') {
            workings = "";
            while (!operatorStack.empty()) operatorStack.pop();
        }
        setWorkings("3");
    }
    public void fourOnClick(View view) {
        zeroCharacterProcess();
        if (workings.length() > 0 && workings.charAt(workings.length()-1) == '=') {
            workings = "";
            while (!operatorStack.empty()) operatorStack.pop();
        }
        setWorkings("4");
    }

    public void fiveOnClick(View view) {
        zeroCharacterProcess();
        if (workings.length() > 0 && workings.charAt(workings.length()-1) == '=') {
            workings = "";
            while (!operatorStack.empty()) operatorStack.pop();
        }
        setWorkings("5");
    }

    public void sixOnClick(View view) {
        zeroCharacterProcess();
        if (workings.length() > 0 && workings.charAt(workings.length()-1) == '=') {
            workings = "";
            while (!operatorStack.empty()) operatorStack.pop();
        }
        setWorkings("6");
    }
    public void sevenOnClick(View view) {
        zeroCharacterProcess();
        if (workings.length() > 0 && workings.charAt(workings.length()-1) == '=') {
            workings = "";
            while (!operatorStack.empty()) operatorStack.pop();
        }
        setWorkings("7");
    }

    public void eightOnClick(View view) {
        zeroCharacterProcess();
        if (workings.length() > 0 && workings.charAt(workings.length()-1) == '=') {
            workings = "";
            while (!operatorStack.empty()) operatorStack.pop();
        }
        setWorkings("8");
    }

    public void nineOnClick(View view) {
        zeroCharacterProcess();
        if (workings.length() > 0 && workings.charAt(workings.length()-1) == '=') {
            workings = "";
            while (!operatorStack.empty()) operatorStack.pop();
        }
        setWorkings("9");
    }

    public void reverseSignOnClick(View view) {
        String curRes = currentResult;
        if (curRes.charAt(0) != '0') {
            if (curRes.charAt(0) == '-') curRes = curRes.substring(1, curRes.length());
            else
                curRes = "-" + curRes;
            currentResult = curRes;
            resultsTV.setText(transform(curRes));
        }
        else
            return;
    }

    public void zeroOnClick(View view) {
        if (workings.length() > 0 && workings.charAt(workings.length()-1) == '=') {
            workings = "";
            while (!operatorStack.empty()) operatorStack.pop();
        }
        if (workings.length() == 0) {
            setWorkings("0");
            return;
        }
        else if (workings.length() == 1 && workings.charAt(0) == '0') return;
        else if (workings.length() > 2 &&
                 isOperator(workings.charAt(workings.length()-2)) != 0 &&
                 workings.charAt(workings.length() - 1) == '0') return;
        setWorkings("0");
    }

    public void equalOnClick(View view) {
        if (workings.length() == 0) return;
//      - (by backspace)
        if (workings.length() == 1 && isOperator(workings.charAt(0)) > 0){
            workings = "";
            setWorkings("0");
            while (!operatorStack.empty()) operatorStack.pop();
            return;
        }
//      123+
        if (isOperator(workings.charAt(workings.length() - 1)) > 0 &&
                isOperator(workings.charAt((workings.length()-1))) < 5){

            currentResult = workings.substring(0, workings.length() - 1);
            resultsTV.setText(transform(currentResult));

            // System.out.println("-");
            workings = workings.substring(0,workings.length()-1);
            setWorkings("=");
            operatorStack.pop();
            return;
        }
//      123 || 123+456 || 123+456=
        String num1 = "";
        String num2 = "";
        int op = 0;
        String w = "";

        if (workings.charAt(0) == '-') {////////////
            num2 += '-';
            w = workings.substring(1, workings.length());
        }
        else w = workings;

        // System.out.println(w+"----");
        for (int i = 0; i < w.length(); i++){
            if (isOperator(w.charAt(i)) == 0){
                num2 += w.charAt(i);
            }
            else if (isOperator(w.charAt(i)) < 5){
                num1 = num2;
                num2 = "";
                op = isOperator(w.charAt(i));
            }
        }

        if (op == 0){   //123
            currentResult = num2;
            resultsTV.setText(transform(num2));

            if (isOperator(w.charAt(w.length()-1)) != 5)
                setWorkings("=");
            // System.out.println("--");
        }
        else {
            String Res = calculate(num1, op, num2);
            if (workings.charAt(workings.length()-1) != '=') {
                setWorkings("=");
                // System.out.println("---");
            }

            currentResult = Res;
            resultsTV.setText(transform(Res));
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        
        if (id == R.id.btn_1) oneOnClick(view);
        else if (id == R.id.btn_2) twoOnClick(view);
        else if (id == R.id.btn_3) threeOnClick(view);
        else if (id == R.id.btn_4) fourOnClick(view);
        else if (id == R.id.btn_5) fiveOnClick(view);
        else if (id == R.id.btn_6) sixOnClick(view);
        else if (id == R.id.btn_7) sevenOnClick(view);
        else if (id == R.id.btn_8) eightOnClick(view);
        else if (id == R.id.btn_9) nineOnClick(view);
        else if (id == R.id.btn_0) zeroOnClick(view);

        else if (id == R.id.btn_equalSign) equalOnClick(view);
        else if (id == R.id.btn_reverseSign) reverseSignOnClick(view);
        else if (id == R.id.btn_plusSign) plusOnClick(view);
        else if (id == R.id.btn_subSign) subOnClick(view);
        else if (id == R.id.btn_divideSign) divideOnClick(view);
        else if (id == R.id.btn_multipicationSign) multiplicationOnClick(view);
        else if (id == R.id.btn_C) clearOnClick(view);
        else if (id == R.id.btn_CE) clearEntryOnClick(view);
        else if (id == R.id.btn_BS) backSpaceOnClick(view);

    }
}

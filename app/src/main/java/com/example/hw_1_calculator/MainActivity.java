package com.example.hw_1_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

     List<String> numbersAndSigns = new ArrayList<>();

     final int maxLengthStringOnScreen = 10;
     final String signs = "√^*✕/÷+-";
     final String END_OF_CALCULATIONS = "###end###";

     private TextView textViewMain;
     private Button btnClearAll;
     private Button btnClearLast;
     private Button btnDegree;
     private Button btnSqrt;
     private Button btnDiv;
     private Button btnMulti;
     private Button btnMinus;
     private Button btnPlus;
     private Button btnEqual;
     private Button btn0;
     private Button btn1;
     private Button btn2;
     private Button btn3;
     private Button btn4;
     private Button btn5;
     private Button btn6;
     private Button btn7;
     private Button btn8;
     private Button btn9;
     private Button btnPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // инициализация объектов классов
         textViewMain=findViewById(R.id.textViewMain);
         btnClearAll=findViewById(R.id.btnClearAll);
         btnClearLast=findViewById(R.id.btnClearLast);
         btnDegree=findViewById(R.id.btnDegree);
         btnSqrt=findViewById(R.id.btnSqrt);
         btnDiv=findViewById(R.id.btnDiv);
         btnMulti=findViewById(R.id.btnMulti);
         btnMinus=findViewById(R.id.btnMinus);
         btnPlus=findViewById(R.id.btnPlus);
         btnEqual=findViewById(R.id.btnEqual);
         btn0=findViewById(R.id.btn0);
         btn1=findViewById(R.id.btn1);
         btn2=findViewById(R.id.btn2);
         btn3=findViewById(R.id.btn3);
         btn4=findViewById(R.id.btn4);
         btn5=findViewById(R.id.btn5);
         btn6=findViewById(R.id.btn6);
         btn7=findViewById(R.id.btn7);
         btn8=findViewById(R.id.btn8);
         btn9=findViewById(R.id.btn9);
         btnPoint=findViewById(R.id.btnPoint);

         //подключение обработчика событий

        btnClearAll.setOnClickListener(this);
        btnClearLast.setOnClickListener(this);
        btnDegree.setOnClickListener(this);
        btnSqrt.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnMulti.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnEqual.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnPoint.setOnClickListener(this);

        //инициализация коллекции
        numbersAndSigns.add("0");
    }


    @Override
    public void onClick(View view) {
        int btn_id= view.getId();
        //При наличии отметки о конце вычислений - делаем очистку коллекции,при повторнном ввводе
        if (numbersAndSigns.size() > 1 && numbersAndSigns.get(1).equals(END_OF_CALCULATIONS)) {
            clearAll();
        }

        switch (btn_id){
            case R.id.btnClearAll: {
                textViewMain.setText("0");
                clearAll();
                break;
            }
            case R.id.btnClearLast: {
                dellLastSymbol();
                break;
            }
            case R.id.btnDegree: {
                addSymbol("^");
                break;
            }
            case R.id.btnSqrt: {
                addSymbol("√");
                break;
            }
            case R.id.btnDiv: {
                addSymbol("÷");
                break;
            }
            case R.id.btnMulti: {
                addSymbol("✕");
                break;
            }
            case R.id.btnMinus: {
                addSymbol("-");
                break;
            }
            case R.id.btnPlus: {
                addSymbol("+");
                break;
            }
            case R.id.btnEqual: {
                calculations();
                break;
            }
            case R.id.btn0: {
                addSymbol("0");
                break;
            }
            case R.id.btn1: {
                addSymbol("1");
                break;
            }
            case R.id.btn2: {
                addSymbol("2");
                break;
            }
            case R.id.btn3: {
                addSymbol("3");
                break;
            }
            case R.id.btn4: {
                addSymbol("4");
                break;
            }
            case R.id.btn5: {
                addSymbol("5");
                break;
            }
            case R.id.btn6: {
                addSymbol("6");
                break;
            }
            case R.id.btn7: {
                addSymbol("7");
                break;
            }
            case R.id.btn8: {
                addSymbol("8");
                break;
            }
            case R.id.btn9: {
                addSymbol("9");
                break;
            }
            case R.id.btnPoint: {
                addSymbol(".");
                break;
            }
        }
        showExpression();
    }



    // очистка коллекции
    private void clearAll() {
        numbersAndSigns.clear();
        numbersAndSigns.add("0");
    }

    //добавление символа по клику
    private void addSymbol(String text){
        int arrSize = numbersAndSigns.size();
        String lastElement = numbersAndSigns.get(arrSize-1);
        if (text.equals(".")) { // если введена "."
            if (!signs.contains(lastElement) && !lastElement.contains(".")) {
                String newNumber = lastElement + text;
                numbersAndSigns.set(arrSize - 1, newNumber);
            }
        } else if (signs.contains(text)) { // если ввели мат. символ
            if (text.equals("√")) {
                if (signs.contains(lastElement) && !lastElement.equals("√")) {
                    numbersAndSigns.add(text);
                } else if ((arrSize == 1 && lastElement.equals("0"))) {
                    numbersAndSigns.set(0, text);
                }
            } else if (!signs.contains(lastElement)) {
                fixLastPoint();
                numbersAndSigns.add(text);
            }
        } else {
            if (signs.contains(lastElement)) {
                numbersAndSigns.add(text);
            } else {
                String newNumber = lastElement + text;
                numbersAndSigns.set(arrSize - 1, checkFirstZero(newNumber));
            }
        }
    }


    //исправление строки при добавление десятичной точки к числу с "5." на "5.0"
    private void fixLastPoint() {
        String lastElement = numbersAndSigns.get(numbersAndSigns.size() - 1);
        int lastElementLength = lastElement.length();
        if (lastElement.charAt(lastElementLength - 1) == ".".charAt(0)) {
            addSymbol("0");
        }
    }

    //устраняем "0" в начале строки
    private String checkFirstZero(String text) {
        StringBuilder newText = new StringBuilder(text);
        if (newText.charAt(0) == '0' && newText.charAt(1) != ".".charAt(0)) {
            newText.deleteCharAt(0);
        }
        return newText.toString();
    }

// функция кнопки удаления последнего символа
    private void dellLastSymbol() {
        int arrLength = numbersAndSigns.size();
        String lastElement = numbersAndSigns.get(arrLength - 1);
        if (arrLength == 1 && lastElement.length() == 1) {
            clearAll();
        } else if (lastElement.length() == 1) {
            numbersAndSigns.remove(arrLength - 1);
        } else if (lastElement.length() > 0) {
            StringBuilder newText = new StringBuilder(lastElement).deleteCharAt(lastElement.length() - 1);
            numbersAndSigns.set(arrLength - 1, newText.toString());
        }
    }

    // разборка коллекции на группы "число-знак-число" или в случае с кв. корнем "знак число"
    private void calculations() {
        removeLastSign();
        int pozSign = -1;
        StringBuilder leftNumber = new StringBuilder("");
        StringBuilder rightNumber = new StringBuilder("");
        StringBuilder sign = new StringBuilder("");
        for (int i = 0; i < signs.length(); i++) {
            sign = new StringBuilder(signs.substring(i, i + 1));
            while (numbersAndSigns.contains(sign.toString())) { // проверка наличия i-го символа мат. действия
                pozSign = numbersAndSigns.indexOf(sign.toString()); // индекс символа мат. действия
                if (sign.toString().equals("√")) {
                    rightNumber = new StringBuilder("0"); // сохранеие элемента справа от знака мат. действия
                    leftNumber = new StringBuilder(numbersAndSigns.get(pozSign + 1)); // сохранение элемента справа от знака мат. действия
                } else {
                    // формирование параметров - число-знак мат. действия-число
                    leftNumber = new StringBuilder(numbersAndSigns.get(pozSign - 1)); // сохранеие элемента справа от знака мат. действия
                    rightNumber = new StringBuilder(numbersAndSigns.get(pozSign + 1)); // сохранение элемента справа от знака мат. действия
                    numbersAndSigns.set(pozSign - 1, ""); //очистка элемента в коллекции слева от знака мат. действия
                }
                numbersAndSigns.set(pozSign + 1, "");  //очистка элемента в коллекции справа от знака мат. действия

                // вызов калькулятора
                double result = new Calculator(sign.toString()).calculations(Double.parseDouble(leftNumber.toString()), Double.parseDouble(rightNumber.toString()));
                numbersAndSigns.set(pozSign, "" + result);

                // удаление пустых строк из коллекции
                removeEmptyElement();
            }
        }
        //добавление в коллекцию отметки об окончании вычислений
        if (numbersAndSigns.size() == 1) {
            numbersAndSigns.add(END_OF_CALCULATIONS);
        }
    }

    // удаление последнего символа, если он является мат. оператором
    private void removeLastSign() {
        while (signs.contains(numbersAndSigns.get(numbersAndSigns.size() - 1))) {
            numbersAndSigns.remove(numbersAndSigns.size() - 1);
        }
    }

    //удаление пустой строки из коллекции
    private void removeEmptyElement() {
        while (numbersAndSigns.contains("")) {
            numbersAndSigns.remove("");
        }
    }

    // вывод содержания коллекции
    private void showExpression() {
        StringBuilder newText = new StringBuilder("");
        for (String numberOrSign : numbersAndSigns) {
            if (!numberOrSign.equals(END_OF_CALCULATIONS)) { // игнорирование последней строки в коллекции об окончании вычислений
                newText.append(numberOrSign);
            }
        }

        // вывод содержания коллекции на экран в зависимости от того, идет ввод данных или вывод результата вычислений на экран
        if (numbersAndSigns.size() > 1 && numbersAndSigns.get(1).equals(END_OF_CALCULATIONS)) { // вывод результата вычислений
            textViewMain.setText(numberRounding(newText.toString()));
        } else { // вывод введенных данных
            if (newText.length() > maxLengthStringOnScreen) {
                newText = new StringBuilder("..." + newText.substring(newText.length() - maxLengthStringOnScreen - 1));
            }
            textViewMain.setText(newText);
        }
    }

    //округление результата вычислений для уменьшения длины строки в окне калькулятора
    private String numberRounding(String numInString) {
        if (numInString.length() > maxLengthStringOnScreen && numInString.contains("E")) {
            BigDecimal bd = new BigDecimal(numInString.substring(0, numInString.indexOf("E")));
            bd = bd.setScale(5, RoundingMode.UP);
            return bd.toString() + numInString.substring(numInString.indexOf("E"));
        }
        return numInString;
    }


}
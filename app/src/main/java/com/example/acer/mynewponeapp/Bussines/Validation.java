package com.example.acer.mynewponeapp.Bussines;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validation {


    public Validation() {

    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public static boolean IsEmptyRegister(String nombre, String direccion, String celphone, String alimento, String dia, String mail,String contraseña) {
        boolean isValid = true;
        if (nombre.isEmpty() || direccion.isEmpty() || celphone.equals(0) || alimento.isEmpty() || dia.equals(0) || contraseña.isEmpty()) {
            isValid = false;
        }
        return isValid;
    }

    public static boolean IsNumeric(String numero) {
        boolean isNumerc = true;
        try {

            Integer retorno = Integer.valueOf(numero);

        } catch (NumberFormatException e) {
            isNumerc = false;
        }
        return isNumerc;
    }


}
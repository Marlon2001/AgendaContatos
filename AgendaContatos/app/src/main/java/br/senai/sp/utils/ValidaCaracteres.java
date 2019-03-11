package br.senai.sp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidaCaracteres{

    public static boolean isValidName(String txtNome){
        String NAME_PATTERN = "[a-zA-Z ]+";
        Pattern patternNAME = Pattern.compile(NAME_PATTERN);
        Matcher matcherNAME = patternNAME.matcher(txtNome);
        if(matcherNAME.matches()){
            return true;
        }
        return false;
    }

    public static boolean isValidEndereco(String txtEndereco){
        String ENDERECO_PATTERN = "[a-zA-Z 0-9-.,]+";
        Pattern patternENDERECO = Pattern.compile(ENDERECO_PATTERN);
        Matcher matcherENDERECO = patternENDERECO.matcher(txtEndereco);
        if(matcherENDERECO.matches()){
            return true;
        }
        return false;
    }

    public static boolean isValidEmail(String txtEmail){
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern patternEMAIL = Pattern.compile(EMAIL_PATTERN);
        Matcher matcherEMAIL = patternEMAIL.matcher(txtEmail);
        if(matcherEMAIL.matches()){
            return true;
        }
        return false;
    }

    public static boolean isValidFone(String txtFone){
        String FONE_PATTERN = "[0-9]{2} ?[9]?[0-9]{4}-?[0-9]{4}";
        Pattern patternFONE = Pattern.compile(FONE_PATTERN);
        Matcher matcherFONE = patternFONE.matcher(txtFone);
        if(matcherFONE.matches()){
            return true;
        }
        return false;
    }
}

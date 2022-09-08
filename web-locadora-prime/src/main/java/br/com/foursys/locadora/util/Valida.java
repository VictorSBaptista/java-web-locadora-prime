package br.com.foursys.locadora.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import br.com.caelum.stella.validation.CPFValidator;

/**
 * Classe responsável por armazenar os métodos de validação de dados
 *
 * @author Victor Baptista
 * @since 17/03/2021
 * @version 1.0
 */
public class Valida {

    /*
     *Método para verificar se o campo é diferente de vazio ou nulo
     */
    public static boolean isEmptyOrNull(String args) {
        return (args.trim().equals("") || args == null);
    }//fim do método isEmptyOrNull

    /*
     *Método para verificar se o campo formatado é diferente de vazio ou nulo 
     */
    public static boolean formattedIsEmptyOrNull(String args) {
        String aux = args.trim().replaceAll("[()-./]", "");
        return (aux.trim().equals("") || aux == null);
    }//fim do método isEmptyOrNull

    /*
     *Método para verificar se o campo é um inteiro  
     */
    public static boolean isInteger(String args) {
        try {
            Integer.parseInt(args);
            return true;
        } catch (Exception e) {
            return false;
        }
    }//fim do método isEmptyOrNull
    
    /*
     *Método para verificar se o campo é um inteiro  
     */
    public static boolean isDouble(String args) {
        try {
            Double.parseDouble(args);
            return true;
        } catch (Exception e) {
            return false;
        }
    }//fim do método isEmptyOrNull
    
    public static boolean isDoubleZero(double num) {
    	if (num <= 0) {
    		return true;
    	}
    	return false;
    }
    
    public static boolean isIntZero(int num) {
    	if (num <= 0) {
    		return true;
    	}
    	return false;
    }
    
    /*
     * Método para verificar se o CPF é válido
     */
    public static boolean isCpfInvalido(String args) {
        CPFValidator validator = new CPFValidator(true);
        try {
            validator.assertValid(args);
            return false;
        } catch (Exception e) {
            return true;
        }
    }
    
    /*
     * Método para verificar se a Data de fundação é válida
     */
    public static boolean isDataInvalida(String args) {
        String formato = "dd/MM/uuuu";
        
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formato).withResolverStyle(ResolverStyle.STRICT);
        
        try {
            LocalDate date = LocalDate.parse(args, dateTimeFormatter);
            return false;
        } catch (DateTimeParseException e) {
            return true;
        }
    }
}

package br.com.serratec.classes;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Utils {
	   static NumberFormat formatarValor = new DecimalFormat ("R$ #,##0.00");

	    public static String doubleToString (Double valor) {
	        return formatarValor.format(valor);
	    }
}

/**
 *
 *  @author Górniak Mateusz S17463
 *
 */

package zad1;


import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calc {

    public Calc(){}

    private static String[] operations = {"multiply", "add", "empty", "minus", "empty", "divide"};

    public String doCalc(String arg) {
        arg = arg.replaceAll("\\s+", " ");
        String[] args = arg.split(" ");
        BigDecimal arg1 = new BigDecimal(args[0]);
        BigDecimal arg2 = new BigDecimal(args[2]);
        int operation = args[1].charAt(0)-42;



        try {
            String oper = operations[operation];
            Class clazz = new Calc().getClass();
            Method calcMethod = clazz.getMethod(oper,arg1.getClass(), arg2.getClass());
            return (String) calcMethod.invoke(new Calc(), arg1, arg2);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    public String multiply(BigDecimal a, BigDecimal b){
        return a.multiply(b).toString();
    }

    public String add(BigDecimal a, BigDecimal b){
        return a.add(b).toString();
    }

//    private String empty(BigDecimal a, BigDecimal b){
//        re
//    }

    public String minus(BigDecimal a, BigDecimal b){
        return a.subtract(b).toString();
    }

    public String divide(BigDecimal a, BigDecimal b){
        return a.divide(b,10, RoundingMode.CEILING).toString();
    }
}
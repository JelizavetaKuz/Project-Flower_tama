public class Utils {
    /**
     * Round to 4 numbers after coma
     * @param number double
     * @return double, rounded
     */
    public static double round(double number){
        double rounded = Math.round(number*1000)/1000.00;
        return rounded;
    }
}

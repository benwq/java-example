import java.math.BigDecimal;
import java.util.function.Function;

public class DerivedFunction implements Function<BigDecimal, BigDecimal> {
    private static final BigDecimal DELTA_X = new BigDecimal(0.000000000000001);
    private Function<BigDecimal, BigDecimal> function;

    public DerivedFunction(Function<BigDecimal, BigDecimal> function) {
        this.function = function;
    }

    @Override
    public BigDecimal apply(BigDecimal x) {
        return (function.apply(x .add(DELTA_X).subtract(function.apply(x))).divide(DELTA_X));
    }

    public static void main(String[] args) {
        //打印函数在x=2处的二阶导
        int [] num1 ,num2;
        String a = "abc";
        boolean c = a.charAt(1)==a.charAt(2);
        char[] aChars = a.toCharArray();

        System.out.println(new DerivedFunction(new DerivedFunction(x ->  new BigDecimal(3).multiply(x.pow(3)).
                add(new BigDecimal(2).multiply(x.pow(2))).
                add(new BigDecimal(1).multiply(x)).
                add(new BigDecimal(1))
        )).apply(new BigDecimal(2)));
    }
}
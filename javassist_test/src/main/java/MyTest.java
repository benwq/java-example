import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author benwq
 * @create 2022/11/1 10:16
 * @desc 二维数组离散程度
 **/

public class MyTest {

    public static List<Point> seatList = new ArrayList<>();
    public static List<Point> selectedSeatList = new ArrayList<>();
    public static List<Point> unSelectSeatList = new ArrayList<>();

    /**
     * @author benwq
     * @create 2022/11/1 10:35
     * @desc 初始化座位列表
     **/
    public static void init() {
        int xLimit = 9;
        int yLimit = 9;
        for (int x = 1; x <= xLimit; x++) {
            for (int y = 1; y <= yLimit; y++) {
                Point p = new Point();
                p.setX(x);
                p.setY(y);
                unSelectSeatList.add(p);
            }
        }
    }

    /**
     * @author benwq
     * @create 2022/11/1 10:35
     * @desc 计算座位离散值
     **/
    public static BigDecimal calcR1(Point p) {
        int x = p.getX();
        int y = p.getY();
        int sumXy = x * y;
        int sumX = x;
        int sumY = y;
        BigDecimal sumXPow = new BigDecimal(x).pow(2);
        BigDecimal sumYPow = new BigDecimal(y).pow(2);
        for (Point sp : selectedSeatList) {
            int tx = sp.getX();
            int ty = sp.getY();
            int xy = tx * ty;
            sumXy += xy;
            sumX += tx;
            sumY += ty;
            BigDecimal xPow = new BigDecimal(tx).pow(2);
            BigDecimal yPow = new BigDecimal(ty).pow(2);
            sumXPow = sumXPow.add(xPow);
            sumYPow = sumYPow.add(yPow);
        }
        int n = selectedSeatList.size() + 1;
        BigDecimal xyF = new BigDecimal(n * sumXy - sumX * sumY);
        BigDecimal xF = sumXPow.multiply(new BigDecimal(n)).subtract(new BigDecimal(sumX).pow(2));
        if (xF.compareTo(BigDecimal.ZERO) != 0)
            xF = sqrt(xF);
        BigDecimal yF = sumYPow.multiply(new BigDecimal(n)).subtract(new BigDecimal(sumY).pow(2));
        if (yF.compareTo(BigDecimal.ZERO) != 0)
            yF = sqrt(yF);
        if (xyF.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
        BigDecimal r = xyF.divide(xF.multiply(yF), 10, RoundingMode.CEILING);
        return r;
    }

    public static BigDecimal calcR2(Point p) {
        int x = p.getX();
        int y = p.getY();
        int sumXy = x * y;
        int sumX = x;
        int sumY = y;
        BigDecimal sumXPow = new BigDecimal(x).pow(2);
        BigDecimal sumYPow = new BigDecimal(y).pow(2);
        for (Point sp : selectedSeatList) {
            int tx = sp.getX();
            int ty = sp.getY();
            int xy = tx * ty;
            sumXy += xy;
            sumX += tx;
            sumY += ty;
            BigDecimal xPow = new BigDecimal(tx).pow(2);
            BigDecimal yPow = new BigDecimal(ty).pow(2);
            sumXPow = sumXPow.add(xPow);
            sumYPow = sumYPow.add(yPow);
        }
        int n = selectedSeatList.size() + 1;
        BigDecimal yF = sumYPow.subtract(new BigDecimal(sumY * sumY).divide(new BigDecimal(n), 10, RoundingMode.CEILING));
        BigDecimal xF = sumXPow.subtract(new BigDecimal(sumX * sumX).divide(new BigDecimal(n), 10, RoundingMode.CEILING));
        BigDecimal xyF = new BigDecimal(sumXy).subtract(new BigDecimal(sumX).multiply(new BigDecimal(sumY).divide(new BigDecimal(n), 10, RoundingMode.CEILING))).pow(2);
        BigDecimal r = yF.subtract(xyF.divide(xF, 10, RoundingMode.CEILING)).divide(new BigDecimal(n - 2), 10, RoundingMode.CEILING);
        if (r.compareTo(BigDecimal.ZERO) > 0)
            r = sqrt(r);
        return r;
    }

    public static Point calcNext() {
//        BigDecimal minR = new BigDecimal(1);
//        Point minP = null;
//        for (Point usp : unSelectSeatList) {
//            BigDecimal r = calcR(usp);
//            if (r.compareTo(minR) <= 0) {
//                minR = r;
//                minP = usp;
//            }
//        }
        BigDecimal maxR = new BigDecimal(0);
        Point maxP = null;
        for (Point usp : unSelectSeatList) {
            BigDecimal r = calcR2(usp);
            if (r.compareTo(maxR) > 0) {
                maxR = r;
                maxP = usp;
            }
        }
        if (maxP != null) {
            boolean addResult = addSelectedSeat(maxP);
            if (addResult) removeUnSelectedSeat(maxP);
        }
        return maxP;
    }

    public static boolean addSelectedSeat(Point p) {
        for (Point sp : selectedSeatList) {
            if (sp.getX().intValue() == p.getX().intValue()
                    && sp.getY().intValue() == p.getY().intValue()) {
                return false;
            }
        }
        selectedSeatList.add(p);
        return true;
    }

    public static boolean removeUnSelectedSeat(Point p) {
        for (Point usp : unSelectSeatList) {
            if (usp.getX().intValue() == p.getX().intValue()
                    && usp.getY().intValue() == p.getY().intValue()) {
                unSelectSeatList.remove(usp);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        init();
        Point p1 = new Point();
        p1.setX(1);
        p1.setY(1);
        Point p2 = new Point();
        p2.setX(9);
        p2.setY(9);
        addSelectedSeat(p1);
        removeUnSelectedSeat(p1);
        addSelectedSeat(p2);
        removeUnSelectedSeat(p2);
        for (int i = 0; i < 50; i++) {
            Point maxP = calcNext();
            if (maxP != null)
                System.out.println("x:" + maxP.getX() + ",y:" + maxP.getY());
        }
    }

    public static BigDecimal sqrt(BigDecimal num) {
        if (num.compareTo(BigDecimal.ZERO) < 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal x = num.divide(new BigDecimal("2"), MathContext.DECIMAL128);
        while (x.subtract(x = sqrtIteration(x, num)).abs().compareTo(new BigDecimal("0.0000000000000000000001")) > 0) ;
        return x;
    }

    private static BigDecimal sqrtIteration(BigDecimal x, BigDecimal n) {
        return x.add(n.divide(x, MathContext.DECIMAL128)).divide(new BigDecimal("2"), MathContext.DECIMAL128);
    }
}


class Point {
    private Integer x;
    private Integer y;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
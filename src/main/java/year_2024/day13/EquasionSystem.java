package year_2024.day13;

import java.math.BigInteger;

public class EquasionSystem {

    private static final BigInteger[] NO_SOLUTION = {BigInteger.ZERO, BigInteger.ZERO};

    BigInteger dX1;
    BigInteger dY1;
    BigInteger dX2;
    BigInteger dY2;
    BigInteger cX;
    BigInteger cY;

    protected BigInteger[] findXAndY() {
        BigInteger y = dX1.multiply(cY).subtract(dY1.multiply(cX)).divide(dX1.multiply(dY2).subtract(dX2.multiply(dY1)));
        BigInteger x = cX.subtract(dX2.multiply(y)).divide(dX1);

        return isValidSolution(x, y) ? new BigInteger[]{x, y} : NO_SOLUTION;
    }

    private boolean isValidSolution(BigInteger x, BigInteger y) {
        return  dX1.multiply(x).add(dX2.multiply(y)).equals(cX)
                &&
                dY1.multiply(x).add(dY2.multiply(y)).equals(cY);
    }
}

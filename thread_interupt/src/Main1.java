import java.math.BigInteger;
/*
thread to calculate powers
* */
public class Main1 {

    public static void main(String[] args) {
        Thread thread = new Thread(new LongComputationTask(new BigInteger("2"), new BigInteger("10")));
        thread.start();
    }

    private static class LongComputationTask implements Runnable {

        private BigInteger base;
        private BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power) {
            this.power = power;
            this.base = base;
        }

        @Override
        public void run() {

            System.out.println(base + "^" + power + " = " + pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;
            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {

                result = result.multiply(base);
            }

            return result;
        }
    }
}


import java.math.BigInteger;

/*
* termination of daemon threads
*threads that are not dependent on termination of the main thread
 */

public class Main3 {

    public static void main(String[] args) {
        Thread thread = new Thread(new Main3.LongComputationTask(new BigInteger("2"), new BigInteger("10")));
        thread.setDaemon(true);
        thread.start();
        thread.interrupt();
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
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("Thread has been prematurely interrupted");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }

            return result;
        }
    }
}

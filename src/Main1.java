
import java.lang.*;

public class Main1 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable(){

            @Override
            public void run() {
                //Code that will run in the new thread
                System.out.println("This thread is called " + Thread.currentThread().getName());

                System.out.println("Current thread priority is " + Thread.currentThread().getPriority());

                throw new RuntimeException("Intentional Exception"); //to test exception

            }
        });
        thread.setName("Misbehaving Thread"); //gives a name to the thread

        //set priority of the thread
        thread.setPriority(Thread.MAX_PRIORITY);
        System.out.println("We are in the thread: " + Thread.currentThread().getName() + " before starting a new thread");
        //start catch exceptions inside threads
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A critical error happened in thread "+ t.getName()
                + " the error is " + e.getMessage());
            }
        });
        //stop catch exceptions inside threads
        thread.start();
        System.out.println("We are in the thread: " + Thread.currentThread().getName() + " after starting a new thread");

        thread.sleep(10000); //do not schedule until after 10 seconds
    }
}

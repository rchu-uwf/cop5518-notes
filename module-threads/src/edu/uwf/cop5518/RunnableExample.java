package edu.uwf.cop5518;


public class RunnableExample implements Runnable {

    @Override
    public void run() {
        System.out.println("I am executing in a thread!");
    }

    public static void main(String[] args) {
        // constructing a job
        RunnableExample job = new RunnableExample();

        // constructing a new thread
        System.out.println("Define a thread.");
        Thread thread = new Thread(job);

        // execute job as a thread
        System.out.println("Schedule the thread. Ready to execute.");
        thread.start();

        // wait for worker thread to terminate
        try {
            System.out.println("Wait here until the thread terminates.");
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Thread has terminated.");

    }
}

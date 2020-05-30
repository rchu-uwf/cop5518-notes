package edu.uwf.cop5518;

public class Job extends Thread {

    private final SharedData _sharedData;
    private final String _name;

    public Job(String name, SharedData newSharedData) {
        _name = name;
        _sharedData = newSharedData;

    }

    @Override
    public void run() {
        for (int i=0; i < _sharedData.getRange(); ++i) {
            System.out.println(_name + " " + i + " " + _sharedData.getValue() + "\n");
            _sharedData.incrementValue();
        }
    }

    public static void main(String[] args) {

        // constructing a shared data object
        SharedData sharedData = new SharedData();

        Job job1 = new Job("threadA", sharedData);
        Job job2 = new Job("threadB", sharedData);

        System.out.println("prethreads " + sharedData.getValue());

        // executing job thread
        job1.start();
        job2.start();

        // wait for worker thread to terminate
        try {
            job1.join();
            job2.join();
        } catch (InterruptedException xcp) {
            System.err.println("Unable to join threads");
        }

        System.out.println("postthreads " + sharedData.getValue());
    }
} // end class
package edu.uwf.cop5518;

import java.util.ArrayList;

public class JobNew extends Thread {

    private final SharedData _sharedData;
    private final String _name;

    public JobNew(String name, SharedData newSharedData) {
        _name = name;
        _sharedData = newSharedData;

    }

    @Override
    public void run() {
        for (int i=0; i < _sharedData.getRange(); ++i) {
            //System.out.println(_name + " " + i + " " + _sharedData.getValue() + "\n");
            _sharedData.incrementValue();
        }
    }

    public static void main(String[] args) {

        int maxRange = Integer.parseInt(args[0]);
        int numThreads = Integer.parseInt(args[1]);

        System.out.println("Max range is " + maxRange + " and number of threads is " + numThreads);

        // constructing a shared data object
        SharedData sharedData = new SharedData(maxRange);

        ArrayList<JobNew> allJobs = new ArrayList(numThreads);
        for (int i=0; i<numThreads; ++i) {
            allJobs.add(new JobNew("Thread "+i,sharedData));
        }

        for (int i=0;i<numThreads;++i){
            allJobs.get(i).start();
        }


        // wait for worker thread to terminate
        try {
            for (int i=0;i<numThreads;++i){
                allJobs.get(i).join();
            }
        } catch (InterruptedException xcp) {
            System.err.println("Unable to join threads");
        }

        System.out.println("postthreads " + sharedData.getValue());
    }
} // end class
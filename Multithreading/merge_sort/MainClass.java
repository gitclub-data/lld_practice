import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

interface MergeSort {
    public void mergeSort(List<Integer> arr);
}

class MergeSortMultithreadedImplementation extends Thread implements MergeSort{

    private Integer numberOfThreads;
    private ThreadPoolExecutor executor;

    public MergeSortMultithreadedImplementation(){
        this.numberOfThreads = 1000;
    }

    public MergeSortMultithreadedImplementation(Integer numberOfThreads){
        this.numberOfThreads = numberOfThreads;
    }

    private void merge(List<Integer> arr, Integer ls, Integer le, Integer rs, Integer re){

        List<Integer> temp = new ArrayList<>();
        Integer start = ls;
        Integer end = re;

        while(ls<=le && rs<=re){
            Integer left = arr.get(ls);
            Integer right = arr.get(rs);
            if(left<=right){
                temp.add(left);
                ls += 1;
            }
            else{
                temp.add(right);
                rs += 1;
            }
        }

        while(ls<=le){
            Integer left = arr.get(ls);
            temp.add(left);
            ls += 1;
        }

        while(rs<=re){
            Integer right = arr.get(rs);
            temp.add(right);
            rs += 1;
        }

        Integer init = 0;
        while(start<=end){
            arr.set(start, temp.get(init));
            start += 1;
            init += 1;
        }

    }

    private void divideAndMerge(List<Integer> arr, Integer start, Integer end){
        if(start<end){
            Integer mid = start + (Integer) (end-start)/2;
            Future<?> f1 = this.executor.submit(()->divideAndMerge(arr, start, mid));
            Future<?> f2 = this.executor.submit(()-> divideAndMerge(arr, mid+1, end));

            try{
                f1.get();
                f2.get();
            }catch(Exception e){
                System.out.println(e);
            }
            
            merge(arr, start, mid, mid+1, end);
        }
    }

    public void mergeSort(List<Integer> arr){
        Integer start = 0;
        Integer end = arr.size();
        this.executor = new ThreadPoolExecutor(
            numberOfThreads, // corePoolSize
            numberOfThreads, // maximumPoolSize
            0L,              // keepAliveTime
            TimeUnit.MILLISECONDS,
            new SynchronousQueue<>(), // No queue: tasks must be handed off directly
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.CallerRunsPolicy() // Runs task in calling thread if pool is full
        );
        divideAndMerge(arr, start, end-1);
        this.executor.shutdown();
    }
}

class MergeSortImplementation implements MergeSort{
    public void merge(List<Integer> arr, Integer ls, Integer le, Integer rs, Integer re){

        List<Integer> temp = new ArrayList<>();
        Integer start = ls;
        Integer end = re;

        while(ls<=le && rs<=re){
            Integer left = arr.get(ls);
            Integer right = arr.get(rs);
            if(left<=right){
                temp.add(left);
                ls += 1;
            }
            else{
                temp.add(right);
                rs += 1;
            }
        }

        while(ls<=le){
            Integer left = arr.get(ls);
            temp.add(left);
            ls += 1;
        }

        while(rs<=re){
            Integer right = arr.get(rs);
            temp.add(right);
            rs += 1;
        }

        Integer init = 0;
        while(start<=end){
            arr.set(start, temp.get(init));
            start += 1;
            init += 1;
        }

    }

    private void divideAndMerge(List<Integer> arr, Integer start, Integer end){
        if(start<end){
            Integer mid = start + (Integer) (end-start)/2;
            divideAndMerge(arr, start, mid);
            divideAndMerge(arr, mid+1, end);
            merge(arr, start, mid, mid+1, end);
        }
    }

    public void mergeSort(List<Integer> arr){
        Integer start = 0;
        Integer end = arr.size();
        divideAndMerge(arr, start, end-1);
    }
}


public class MainClass{
    
    public static List<Integer> preparelist(Integer start, Integer end, Integer howmany){
        Random rand = new Random();
        List<Integer> listOfNumbers = new ArrayList<>();
        for(Integer i=0;i<howmany;i++){
            Integer generatednumber = rand.nextInt(end-start+1)+start;
            listOfNumbers.add(generatednumber);
        }
        return listOfNumbers;
    }

    public static void main(String args[]){
        
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Available cores: " + cores);

        Integer elementscount = 100000;
        List<Integer> arr = preparelist(0, 10, elementscount);
        MergeSort tmergesort = new MergeSortMultithreadedImplementation();
        MergeSort mergesort = new MergeSortImplementation();
        
        // System.out.println(arr);

        long startTime = System.nanoTime();
        mergesort.mergeSort(arr);
        long endTime = System.nanoTime();
        long duration = endTime - startTime; // in nanoseconds
        double seconds = duration / 1_000_000_000.0;
        System.out.println("Non Threaded Execution time: " + seconds + " s");

        // System.out.println(arr);

        arr = preparelist(0, 10, elementscount);

        // System.out.println(arr);
        
        startTime = System.nanoTime();
        tmergesort.mergeSort(arr);
        endTime = System.nanoTime();
        duration = endTime - startTime; // in nanoseconds
        seconds = duration / 1_000_000_000.0;
        System.out.println("Threaded Execution time: " + seconds + " s");

        // System.out.println(arr);
    }
}

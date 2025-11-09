import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class MergeSortImplementation{
    public List<Integer> preparelist(Integer start, Integer end, Integer howmany){
        Random rand = new Random();
        List<Integer> listOfNumbers = new ArrayList<>();
        for(Integer i=0;i<howmany;i++){
            Integer generatednumber = rand.nextInt(end-start+1)+start;
            listOfNumbers.add(generatednumber);
        }
        return listOfNumbers;
    }

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

    public void divideAndMerge(List<Integer> arr, Integer start, Integer end){
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
    public static void main(String args[]){
        
        MergeSortImplementation mergesort = new MergeSortImplementation();
        
        List<Integer> arr = mergesort.preparelist(0, 10, 50);

        System.out.println(arr);

        mergesort.mergeSort(arr);

        System.out.println(arr);
    }
}

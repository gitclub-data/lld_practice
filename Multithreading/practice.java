// Online Java Compiler
// Use this editor to write, compile and run your Java code online

class Main {
    public static Integer count = 0;

    public static synchronized void increment(){
        count+=1;
    }

    public static void main(String[] args) throws Exception {
        
        Thread thread1 = new Thread(()->{
            Integer i = 0;
            while(i<1000){
                increment();
                i+=1;
            } 
        });
        Thread thread2 = new Thread(()->{
            Integer i = 0;
            while(i<1000){
                increment();
                i+=1;
            } 
        });
        
        thread1.start();
        thread2.start();
        
        thread1.join();
        thread2.join();
        
        System.out.println(count);
    }
}
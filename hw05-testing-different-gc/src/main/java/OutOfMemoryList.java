import java.util.LinkedList;
import java.util.List;

public class OutOfMemoryList {

    public static void main(String[] args){
        run();
    }

    public static void run(){
        List<List> memory = new LinkedList();
        int a =0;
        for (int i = 0; i < Integer.MAX_VALUE; i++){
            memory.add(makeBrick());
            System.out.println(i);
            if (a>3){
                memory.remove(a-2);
                a = 0;
            }
            a++;
            //Label_1
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

    public static List makeBrick(){
        List<Integer> brick = new LinkedList<>();
        for (int i = 0; i < 1000; i++)
            brick.add(i);
        return brick;
    }

}

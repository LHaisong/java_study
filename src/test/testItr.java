package test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class testItr {
    public static void main(String[]args){
        List<Integer>list=new ArrayList<>();
        for(int i=0;i<6;i++){
            list.add(i);
        }
        Iterator<Integer> iterator=list.iterator();
        while (iterator.hasNext()){
            int n=iterator.next();
            if(n==2){
                iterator.remove();
            }
        }
    }
}

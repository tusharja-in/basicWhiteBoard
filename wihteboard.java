import java.util.*;
class teacher extends Thread{
    whiteboard w;
    teacher(whiteboard wb){
        w=wb;
    }
    String[] sm={"this is your java class","now goto china ","fauda ","end"};
    
    public void run(){
        for(int i=0;i<sm.length;i++){
            w.write(sm[i]);
            try{sleep(100);}catch(Exception e){}
        }
        
    }
}
class student extends Thread{
    whiteboard w;
    String name;
    student(whiteboard wb,String n){
        w=wb;
        name=n;
    }
    public void run(){
        String line;
        w.attendence();
        do{
        line=w.read();
        System.out.println(name + " reading "+ line);
        try{sleep(100);}catch(Exception e){}
        System.out.flush();
        }while(!line.equals("end"));
    }
}
class whiteboard{
    String text;
    int count=0;
    int noOfStu=0;
    public void attendence(){
        noOfStu++;
    }
    synchronized public void write(String s){
        System.out.println("teacher is writing "+ s);
        while(count!=0)
            try{wait();}catch(Exception e){}
        text=s;
        count=noOfStu;
        notifyAll();
    }
    synchronized public String read(){
        while(count==0){
            try{wait();}catch(Exception e){}
        }
        count--;
        if(count==0){notify();}
        return text;
    }
}
public class wihteboard
{
    public static void main(String[] args) 
    {
        whiteboard wb=new whiteboard();
        teacher t=new teacher(wb);
        student s1=new student(wb,"tj");
        student s2=new student(wb,"yash");
        student s3=new student(wb,"rohan");
        student s4=new student(wb,"subrat");
        t.start();
        s1.start();
        s2.start();
        s3.start();
        s4.start();
    }
}

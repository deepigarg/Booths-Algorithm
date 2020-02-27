import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class Mult {

    static String ac;
    static int sc;
    static String Qn1;
    static String Qn;
    static String mr;
    static String md;
    static int n;

    public static void Multiply(int num1, int num2){

        n=Math.max(Math.min(Integer.toBinaryString(num1).length(),Integer.toBinaryString(0-num1).length()),Math.min(Integer.toBinaryString(num2).length(),Integer.toBinaryString(0-num2).length()));

        sc=n+1;
        md = handle(num1,n);
        mr = handle(num2,n);

        System.out.println("md: "+md);
        System.out.println("mr: "+mr);
        reset();

        while(sc!=0) {
            cycle();
            Qn=mr.substring(mr.length()-1);
        }
        String ansbin = ac+mr;
        int ansDec = Integer.parseInt(ansbin,2);
        if(num1==0 || num2==0 || (num1>=0 && num2>=0) ||(num1<0 && num2<0)){
        }
        else{
            String oneC=Complement1(ansbin);
            ansbin = Integer.toBinaryString(Integer.parseInt(oneC,2)+1);
            ansDec = 0-Integer.parseInt(ansbin,2);
        }
        System.out.println("Product(Binary): "+handle(ansDec,2*n));
        System.out.println("Product(Decimal): "+ansDec);

        Booth.out.println("Result of Multiplication:");
        Booth.out.println("Product(Binary): "+handle(ansDec,2*n));
        Booth.out.println("Product(Decimal): "+ansDec);
        System.out.println();
    }

    public static String Complement1(String in){
        String oneC="";
        for (int i=0;i<in.length();i++){
            if (in.charAt(i)=='0'){
                oneC+="1";
            }else if(in.charAt(i)=='1'){
                oneC+='0';
            }
        }
        return oneC;
    }

    public static String handle(int num,int size){
        String ans;
        if (num<0){
            String bin=Integer.toBinaryString(num);
            ans=bin.substring(bin.length()-(size+1));
        }else{
            ans=new String(new char[(size+1)-Integer.toBinaryString(num).length()]).replace("\0", "0")
                    +Integer.toBinaryString(num);
        }
        return ans;
    }

    public static void reset(){
        ac = String.format("%"+(n+1)+"s", Integer.toBinaryString(0)).replace(' ', '0');
        Qn1="0";
        Qn=mr.substring(mr.length()-1);
    }

    public static void cycle(){
        if ((Qn+Qn1).equals("01")){
            ac = String.format("%"+(n+1)+"s", Integer.toBinaryString(Integer.parseInt(ac,2)+Integer.parseInt(md,2))).replace(' ', '0');

        }else if((Qn+Qn1).equals("00") || (Qn+Qn1).equals("11")){

        }else if ((Qn+Qn1).equals("10")){
            String req=Complement1(md);
            ac = String.format("%"+(n+1)+"s",Integer.toBinaryString(Integer.parseInt(ac,2)+Integer.parseInt(req,2)+1)).replace(' ', '0');
            if(ac.length()>n+1){
                ac = ac.substring(1);
            }
        }
        if(ac.length()>n+1){
            ac = ac.substring(1);
        }
        if(sc>0){
            ashr();
        }
        sc--;
    }

    public static void ashr(){
        Qn1 = mr.substring(mr.length()-1);
        mr = ac.substring(ac.length()-1) + mr.substring(0,mr.length()-1);
        ac = ac.substring(0,1) + ac.substring(0,ac.length()-1);
    }
}

class Div {

    static String ac;
    static int sc;
    static String mr; // divisor
    static String md; //dividend
    static int n;

    public static void Divide(int num1, int num2){

        n=Math.max(Math.min(Integer.toBinaryString(num1).length(),Integer.toBinaryString(0-num1).length()),Math.min(Integer.toBinaryString(num2).length(),Integer.toBinaryString(0-num2).length()));

        sc=n+1; //sc=count
        if(num1<0){
            md=handle(0-num1);
        }else{
            md=handle(num1);
        }
        if (num2<0){
            mr=handle(0-num2);
        }else{
            mr=handle(num2);
        }

        System.out.println("dividend: "+md);
        System.out.println("divisor: "+mr);
        ac = String.format("%"+(n+1)+"s", Integer.toBinaryString(0)).replace(' ', '0');

        while(sc!=0) {
            cycle();
        }
        int quo =0;
        int rem =0;
        if (num2==0){
            System.out.println("Error: Cannot Divide by Zero.");
            System.exit(0);
        }else if(num1==0){
            quo = 0;
            rem = 0;
        }else{
            if (num1<0){
                if (num2<0){
                    quo = Integer.parseInt(md,2);
                }else{
                    quo = 0 - Integer.parseInt(md,2);
                }
                rem = 0 - Integer.parseInt(ac,2);
            }else{
                if (num2<0){
                    quo = 0 - Integer.parseInt(md,2);
                }else{
                    quo = Integer.parseInt(md,2);
                }
                rem = Integer.parseInt(ac,2);
            }
        }
        System.out.println("Quotient(Binary): "+handle(quo));
        System.out.println("Remainder(Binary): "+handle(rem));
        System.out.println("Quotient(Decimal): "+quo);
        System.out.println("Remainder(Decimal): "+rem);

        Booth.out.println("\nResult of Division:");
        Booth.out.println("Quotient(Binary): "+handle(quo));
        Booth.out.println("Remainder(Binary): "+handle(rem));
        Booth.out.println("Quotient(Decimal): "+quo);
        Booth.out.println("Remainder(Decimal): "+rem);
        Booth.out.close();
    }

    public static String handle(int num){
        String ans;
        if (num<0){
            String bin=Integer.toBinaryString(num);
            ans=bin.substring(bin.length()-(n+1));
        }else{
            ans=new String(new char[(n+1)-Integer.toBinaryString(num).length()]).replace("\0", "0")
                    +Integer.toBinaryString(num);
        }
        return ans;
    }

    public static void cycle(){
        ashl();

        int o=Integer.parseInt(ac,2);
        int t=Integer.parseInt(mr,2);
        int diff=o-t;
        String d=handle(o-t);
        ac=d;
        if (ac.charAt(0)=='1'){
            md=md.substring(0,md.length()-1)+"0";
            ac = String.format("%"+(n+1)+"s", Integer.toBinaryString(Integer.parseInt(ac,2)+Integer.parseInt(mr,2))).replace(' ', '0');
            if (ac.length()>(n+1)){
                ac=ac.substring(ac.length()-(n+1));
            }
        }else{
            md=md.substring(0,md.length()-1)+"1";
        }
        sc--;
    }

    public static void ashl(){
        ac = ac.substring(1)+md.substring(0,1);
        md = md.substring(1)+"0";
    }
}

public class Booth{
    public static PrintWriter out;

    static {
        try {
            out = new PrintWriter(new FileWriter("output.txt"));
        } catch (IOException e) {
            System.out.println("An error occured while writing to file.");
        }
    }

    public static void main(String args[]){
        Mult m = new Mult();
        Div d = new Div();
        Scanner scan = new Scanner(System.in);
        System.out.println("This is implementation of Booth's algorithm for multiplication and division.");
        System.out.println("Enter two numbers in separate lines, the program shows the result of multiplication followed by result of division taking the first number as dividend and second number as divisor.");
        System.out.println("Please begin.");
        int n1 = scan.nextInt();
        int n2 = scan.nextInt();
        m.Multiply(n1,n2);
        d.Divide(n1,n2);
        System.out.println("\nThe results are printed to the system file- 'output.txt'");
    }
}
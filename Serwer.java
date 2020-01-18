
import java.io.*;
import java.net.*;
import java.util.regex.Pattern;

/*
This is code of basic server - little bit more complex than echo server.
Client send line or lines with numbers,seperated by space, and our server returns sum.
There are some securities:
-bad line (ex."123 abcd 23 235\r\n")
-line without terminator
-empty line
It works also with multiple numbers (ex."123 90 100 235 .... \r\n").
Server can serves 5 clients and then will shut down.
 */



public class Serwer {




   private static byte [] actioner(String str){
    //on input we get one long String
       String arr[]=str.split(" ");//splitting by spaces
       Integer sum=new Integer(0);
       for(int i=0;i<arr.length;++i){
           if(Pattern.matches("\\d+",arr[i])&&arr.length!=0){
               sum+=Integer.parseInt(arr[i]);

           }
           else return "ERROR\r\n".getBytes();//returns ERROR message

       }

           return (sum +"\r\n").getBytes();//returns sum converted to bytes


   }


   private final static int port = 2019;

    public static void main(String [] args)throws IOException {
        InputStream in ;
        OutputStream out ;
        Socket socket ;


        //main server driver

        final ServerSocket serverSocket = new ServerSocket(port);



        byte arr[] = new byte[1024 * 1024 * 16];//quite big bufor
        //Our server can serves 5 user.
        //Then we must to start it again

        int numberOfActions=0;

        while(numberOfActions<5) {
            //There is whole server's action

            numberOfActions++;
            socket = serverSocket.accept();
            in = socket.getInputStream();
            out = socket.getOutputStream();



            String str;
            int il=1;

            while (il > 0) {

                il = in.read(arr, 0, arr.length);


                str = "";
                //to avoid str+=str[i] I decided to use concat
                for (int i = 0; i < il; ++i) {
                    char ch=(char)arr[i];//casted to char
                    str=str.concat(String.valueOf(ch));
                    //str = str + (char) arr[i];//This is possibly faster,but very risky.
                }


                //splitting on lines
                String strings[] = str.split("\\r\\n");//appropriate line terminator.Other OS may has other line terminator. Watch out!

                //java split method in 'special' cases can split string in...empty string.
                //so I decided to use
                int k;
                if (strings[strings.length - 1].equals("")) k = strings.length - 1;
                else k = strings.length;

                for (int i = 0; i < k; ++i) {

                    byte[] byt = actioner(strings[i]);
                    out.write(byt, 0, byt.length);


                }


            }



            in.close();
            out.close();
            socket.close();



        }
            serverSocket.close();



    }
}
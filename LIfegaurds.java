import java.util.*;
import java.io.*;

public class LIfegaurds {
   public static void main(String args[]) throws IOException{
           int x, y, z, temp;
           int covered =0;
           int maxCovered =0 ;
           BufferedReader reader = new BufferedReader(new FileReader("2.in"));
           PrintWriter writer = new PrintWriter(new BufferedWriter(new PrintWriter("2.out")));
           int N = Integer.parseInt(reader.readLine());
           int[] startArray = new int[N];
           int [] endArray = new int[N];

           for (x=0; x<N; x++){
                   StringTokenizer token = new StringTokenizer(reader.readLine());
                   startArray[x] = Integer.parseInt(token.nextToken());
                   endArray[x] = Integer.parseInt(token.nextToken());
           }
           int[] interval = new int[1000000000];

           for(x=0; x<N; x++) {
                   for (y = 0; y < N; y++) {
                           if (y!=x) {
                                   for (temp = startArray[y];
                                        temp < endArray[y]; temp++)
                                           interval[temp]++;
                           }
                   }
                   covered = 0;
                   for (int i = 0; i < 1000000000; i++) {
                           if (interval[i] > 0) {
                                   covered++;
                           }
                   }
                   if (covered > maxCovered) {
                           maxCovered = covered;
                   }
                   Arrays.fill(interval, 0);

/*                   for (z = 0; z < 1000000000; z++) {
                           interval[z] = 0;
                   }*/
           }
           writer.println(maxCovered);
           writer.close();
           reader.close();
   }
}

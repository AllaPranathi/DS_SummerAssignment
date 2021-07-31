import java.util.*;
import java.io.*;

public class LIfegaurds {
   public static void main(String args[]) throws IOException{
           int x, y, z, temp;
           int covered =0;
           int maxCovered =0 ;
           int maximum = 0;
           BufferedReader reader = new BufferedReader(new FileReader("4.in"));
           PrintWriter writer = new PrintWriter(new BufferedWriter(new PrintWriter("4.out")));
           int N = Integer.parseInt(reader.readLine());
           int[] startArray = new int[N];
           int [] endArray = new int[N];

           for (x=0; x<N; x++){
                   StringTokenizer token = new StringTokenizer(reader.readLine());
                   startArray[x] = Integer.parseInt(token.nextToken());
                   endArray[x] = Integer.parseInt(token.nextToken());
                   maximum = Math.max(maximum, endArray[x]);
           }
           //int max = Arrays.ma;
           int[] interval = new int[maximum];

           for(x=0; x<N; x++) {
                   for (y = 0; y < N; y++) {
                           if (y!=x) {
                                   for (temp = startArray[y];
                                        temp < endArray[y]; temp++)
                                           interval[temp]++;
                           }
                   }
                   covered = 0;
                   for (int i = 0; i < maximum; i++) {
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

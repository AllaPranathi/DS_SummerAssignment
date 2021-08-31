package lifeguards;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class LifeguardsMain {
    public static void main(String[] args) {
        try {
            ClassLoader classLoader = LifeguardsMain.class.getClassLoader();
            List<Path> testFiles = Files.walk(new File(classLoader.getResource("testcases/inputs").getFile()).toPath())
                    .filter(Files::isRegularFile).toList();
            for(Path filePath:testFiles) {
                Scanner sc = new Scanner(filePath);

                //Process each testcase
                int n = Integer.parseInt(sc.nextLine());
                System.out.println(n);
                List<TimeInterval> timeIntervals = new ArrayList<>();

                //Create list of time intervals
                for(int i=0;i<n;i++) {
                    TimeInterval timeInterval = new TimeInterval(sc.nextInt(), sc.nextInt());
                    timeIntervals.add(timeInterval);
                }

                //Sort time intervals based on start time point
                timeIntervals.sort(Comparator.comparingInt(o -> o.l));

                int totalSpanningTime = 0, maxTimeSoFar = 0;

                //Calculate total time spanned by all lifeguards
                for(TimeInterval timeInterval:timeIntervals) {
                    if (timeInterval.r>maxTimeSoFar) {
                        totalSpanningTime+= timeInterval.r - Math.max(maxTimeSoFar,timeInterval.l);
                        maxTimeSoFar = timeInterval.r;
                    }
                }

                //Append a dummy interval to process last interval
                TimeInterval dummyTimeInterval = new TimeInterval(timeIntervals.get(n-1).r,0);
                timeIntervals.add(dummyTimeInterval);

                timeIntervals.forEach(timeInterval -> System.out.println(timeInterval.l + " "+ timeInterval.r));

                //To hold the minimum time contributed by the lifeguard to be fired
                int minTimeToRemove=totalSpanningTime;

                //Calculate the least time interval contributed
                maxTimeSoFar = 0;
                for(int i=0;i<n;i++){
                    TimeInterval currentTimeInterval = timeIntervals.get(i);
                    int currentLifeguardTime = Math.min(timeIntervals.get(i+1).l, currentTimeInterval.r)
                            -Math.max(currentTimeInterval.l,maxTimeSoFar);
                    minTimeToRemove = Math.min(minTimeToRemove,currentLifeguardTime);
                    maxTimeSoFar = Math.max(maxTimeSoFar, currentTimeInterval.r);
                }

                //If minTimeToRemove<0, it means one or more of the lifeguards had zero impact
                int maxTimeWithLifeguardRemoval = minTimeToRemove>0?totalSpanningTime-minTimeToRemove:totalSpanningTime;
                String fileName = filePath.getFileName().toString();

                //Write to output
                File outputFile = new File(filePath.getParent().getParent()  + "/"+
                        fileName.substring(0,fileName.length()-3) + ".out");
                outputFile.createNewFile();
                BufferedWriter output = new BufferedWriter(new FileWriter(outputFile));
                output.write(String.valueOf(maxTimeWithLifeguardRemoval));
                output.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

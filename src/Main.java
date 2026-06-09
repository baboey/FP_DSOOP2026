import graph.DS;
import java.io.*;

public class Main {
    DS graph = new DS();

    public void main() {
        readFile(1);

        graph.printVertex();
        graph.findVertex(1).printEdge();
    }

    // readFile(mode)
    // Mode 1: time priority
    // Mode 2: low risk priority
    // Mode 3: distance priority
    void readFile(int mode) {
        FileReader      fr;
        BufferedReader  br;
        String path     = "../data/dataset.csv",
               ln       = null,
               delimiter = ",";
        int src, dst, 
            weight = 0; // Needs to be initialized for some reason
                        //  idk :/

        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);
            while ((ln = br.readLine()) != null) {
                String[] values = ln.split(delimiter);
                src     = Integer.parseInt(values[0]);
                dst     = Integer.parseInt(values[1]);
                graph.addVertex(src);
                graph.addVertex(dst);
                if      (mode == 1) // time
                    weight = Integer.parseInt(values[2]);
                else if (mode == 2) // risk
                    weight = Integer.parseInt(values[3]);
                else if (mode == 3) // distance
                    weight = Integer.parseInt(values[4]);
                graph.addEdge(src, dst, weight);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

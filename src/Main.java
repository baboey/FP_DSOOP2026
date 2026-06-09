import graph.DS;
import java.io.*;

public class Main {
    DS graph = new DS();

    public void main() {
        generateDataset(25,50,5);
        readDataset(1);
        graph.printVertex();
        graph.findVertex(24).printEdge();
        return;
    }

    // readFile(mode)
    // Mode 1: time priority
    // Mode 2: low risk priority
    // Mode 3: distance priority
    void readDataset(int mode) {
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

            br.readLine(); // skips first line
            while ((ln = br.readLine()) != null) {
                // reads CSV line-by-line
                String[] values = ln.split(delimiter);
                src     = Integer.parseInt(values[0]);
                dst     = Integer.parseInt(values[1]);
                graph.addVertex(src);
                graph.addVertex(dst);

                // Only uses one factor to deter the weight
                if      (mode == 1) // weight as time
                    weight = Integer.parseInt(values[2]);
                else if (mode == 2) // ~ risk
                    weight = Integer.parseInt(values[3]);
                else if (mode == 3) // ~ distance
                    weight = Integer.parseInt(values[4]);
                graph.addEdge(src, dst, weight);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // n for number, r for range
    void generateDataset(int nvertex, int nedge, int rweight) {
        FileWriter      fw;
        BufferedWriter  bw;
        String path     = "../data/dataset.csv";
        String src, dst, pdst, weight;
        int rand = 0;
        nedge = (int) (nedge/nvertex); // totally madeup number for max edge on each vertex

        try {
            fw = new FileWriter(path);
            bw = new BufferedWriter(fw);

            bw.write("src,dst,time,risk,distance\n");
            for (int i = 0; i < nvertex; i ++) {
                src = Integer.toString(i);

                pdst = null;
                for (int k = 0; k < nedge; ++k) {
                    rand = (int)(Math.random() * nvertex);
                    dst = Integer.toString(rand);

                    // prevents duplicates
                    if (src == dst || dst == pdst) { 
                        --k;
                        continue;
                    }
                    pdst = dst;

                    bw.append(src);
                    bw.append("," + dst);
                    for (int j = 0; j < 3; j++) {
                        rand = (int)(Math.random() * rweight);
                        weight = Integer.toString(rand);
                        bw.append("," + weight);
                    }
                    bw.append("\n");
                }
            } 
            bw.close(); // never forget ts
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
}

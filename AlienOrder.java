import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// Задача 6. Порядок букв, в котором данный список слов уже отсортирован.
public class AlienOrder {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        String[] words = new String[n];
        for (int i = 0; i < n; i++)
            words[i] = in.readLine();

        boolean[][] before = new boolean[k][k];
        int[] indeg = new int[k];
        boolean possible = true;

        for (int i = 0; i + 1 < n && possible; i++) {
            String left = words[i];
            String right = words[i + 1];
            int len = Math.min(left.length(), right.length());
            boolean differ = false;

            for (int j = 0; j < len; j++) {
                int a = left.charAt(j) - 'a';
                int b = right.charAt(j) - 'a';
                if (a == b)
                    continue;
                if (!before[a][b]) {
                    before[a][b] = true;
                    indeg[b]++;
                }
                differ = true;
                break;
            }

            if (!differ && left.length() > right.length())
                possible = false;
        }

        if (!possible) {
            out.println("IMPOSSIBLE");
            out.flush();
            return;
        }

        List<Integer>[] next = new ArrayList[k];
        for (int i = 0; i < k; i++)
            next[i] = new ArrayList<>();
        for (int a = 0; a < k; a++) {
            for (int b = 0; b < k; b++) {
                if (before[a][b])
                    next[a].add(b);
            }
        }

        PriorityQueue<Integer> ready = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            if (indeg[i] == 0)
                ready.add(i);
        }

        StringBuilder order = new StringBuilder();
        while (!ready.isEmpty()) {
            int v = ready.poll();
            order.append((char) ('a' + v));
            for (int to : next[v]) {
                indeg[to]--;
                if (indeg[to] == 0)
                    ready.add(to);
            }
        }

        if (order.length() < k)
            out.println("IMPOSSIBLE");
        else
            out.println(order);
        out.flush();
    }
}

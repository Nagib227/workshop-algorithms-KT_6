import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

// Задача 3. Порядок этапов. Если вариантов несколько — берём лексикографически меньший.
public class Stages {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Integer>[] next = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            next[i] = new ArrayList<>();

        int[] indeg = new int[n + 1];
        Set<Long> seen = new HashSet<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long key = ((long) a << 32) | b;
            if (!seen.add(key))
                continue;
            next[a].add(b);
            indeg[b]++;
        }

        PriorityQueue<Integer> ready = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            if (indeg[i] == 0)
                ready.add(i);
        }

        int[] order = new int[n];
        int count = 0;
        while (!ready.isEmpty()) {
            int v = ready.poll();
            order[count++] = v;
            for (int to : next[v]) {
                indeg[to]--;
                if (indeg[to] == 0)
                    ready.add(to);
            }
        }

        if (count < n) {
            out.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (i > 0)
                    sb.append(' ');
                sb.append(order[i]);
            }
            out.println(sb);
        }
        out.flush();
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Задача 5. Можно ли разложить людей по двум группам согласно утверждениям.
public class Teams {
    static int[] parent;
    static int[] rel;
    static int[] rank;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        rel = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 1; i <= n; i++)
            parent[i] = i;

        boolean ok = true;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(in.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            if (ok && !agree(u, v, r))
                ok = false;

            if (sb.length() > 0)
                sb.append('\n');
            sb.append(ok ? "YES" : "NO");
        }

        out.println(sb);
        out.flush();
    }

    // rel[x] — 0, если x в той же группе, что и предок, и 1, если в другой
    static int find(int x) {
        if (parent[x] != x) {
            int root = find(parent[x]);
            rel[x] ^= rel[parent[x]];
            parent[x] = root;
        }
        return parent[x];
    }

    static boolean agree(int a, int b, int r) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb)
            return (rel[a] ^ rel[b]) == r;

        if (rank[pa] < rank[pb]) {
            int temp = pa;
            pa = pb;
            pb = temp;
        }
        parent[pb] = pa;
        rel[pb] = rel[a] ^ rel[b] ^ r;
        if (rank[pa] == rank[pb])
            rank[pa]++;
        return true;
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Задача 1. Развернуть односвязный список, не создавая новых узлов.
public class ReverseList {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(in.readLine().trim());
        StringTokenizer st = new StringTokenizer(in.readLine());
        ListNode head = new ListNode(Integer.parseInt(st.nextToken()));
        ListNode tail = head;
        for (int i = 1; i < n; i++) {
            tail.next = new ListNode(Integer.parseInt(st.nextToken()));
            tail = tail.next;
        }

        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        head = prev;

        StringBuilder sb = new StringBuilder();
        for (ListNode node = head; node != null; node = node.next) {
            if (sb.length() > 0)
                sb.append(' ');
            sb.append(node.value);
        }
        out.println(sb);
        out.flush();
    }
}

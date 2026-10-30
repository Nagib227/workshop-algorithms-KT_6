import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Задача 4. В каждом полном блоке из k узлов перевернуть связи. Хвост короче k не трогать.
public class ReverseGroups {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        ListNode head = new ListNode(Integer.parseInt(st.nextToken()));
        ListNode tail = head;
        for (int i = 1; i < n; i++) {
            tail.next = new ListNode(Integer.parseInt(st.nextToken()));
            tail = tail.next;
        }

        if (k > 1)
            head = reverseBlocks(head, k);

        StringBuilder sb = new StringBuilder();
        for (ListNode node = head; node != null; node = node.next) {
            if (sb.length() > 0)
                sb.append(' ');
            sb.append(node.value);
        }
        out.println(sb);
        out.flush();
    }

    private static ListNode reverseBlocks(ListNode head, int k) {
        ListNode newHead = null;
        ListNode prevTail = null;
        ListNode start = head;

        while (start != null) {
            ListNode check = start;
            int count = 0;
            while (check != null && count < k) {
                check = check.next;
                count++;
            }

            if (count < k) {
                if (prevTail != null)
                    prevTail.next = start;
                else
                    newHead = start;
                break;
            }

            ListNode prev = null;
            ListNode cur = start;
            for (int i = 0; i < k; i++) {
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }

            if (newHead == null)
                newHead = prev;
            if (prevTail != null)
                prevTail.next = prev;

            prevTail = start;
            start = cur;
        }

        return newHead;
    }
}

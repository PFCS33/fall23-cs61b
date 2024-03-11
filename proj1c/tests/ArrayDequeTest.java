import deque.ArrayDeque;
import deque.Deque;
import deque.LinkedListDeque;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class ArrayDequeTest {
    @Test
    public void testEquals(){
        Deque<Integer> dq1 = new ArrayDeque<>();
        dq1.addFirst(1);
        dq1.addFirst(2);
        dq1.addFirst(5);

        Deque<Integer> dq2 = new ArrayDeque<>();
        dq2.addFirst(1);
        dq2.addFirst(2);
        dq2.addFirst(5);

        Deque<Integer> dq3 = new ArrayDeque<>();
        dq3.addFirst(1);
        dq3.addFirst(3);
        dq3.addFirst(5);
        assertThat(dq1).isEqualTo(dq2);
        assertThat(dq1).isNotEqualTo(dq3);
    }

    @Test
    public void testToString(){
        Deque<Integer> dq1 = new ArrayDeque<>();
        dq1.addFirst(1);
        dq1.addFirst(2);
        dq1.addFirst(5);
        assertThat(dq1.toString()).isEqualTo("[5, 2, 1]");

    }
}

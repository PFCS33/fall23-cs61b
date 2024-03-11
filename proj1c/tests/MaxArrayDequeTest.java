import deque.ArrayDeque;
import org.junit.jupiter.api.*;
import deque.Deque;
import deque.MaxArrayDeque;
import java.util.Comparator;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDequeTest {
    static private class IntComparator implements Comparator<Integer>{
        public int compare(Integer a, Integer b)
        {
            if(a==null)
                return b;
            else if(b==null)
                return a;
            else
                return a-b;
        }
    }
    static private class IntComparator2 implements Comparator<Integer>{
        public int compare(Integer a, Integer b)
        {
            if(a==null)
                return b;
            else if(b==null)
                return a;
            else
                return b-a;
        }
    }

    static private class StringComparator implements Comparator<String>{
        public int compare(String a, String b)
        {
            if(a==null)
                return -1;
            else if(b==null)
                return 1;
            else
            {
                return a.compareTo(b);
            }

        }
    }

    @Test
    public void intTest(){
        Comparator<Integer> c= new IntComparator();
        Comparator<Integer> c2 = new IntComparator2();
        MaxArrayDeque<Integer> dq1 = new MaxArrayDeque<>(c);
        dq1.addFirst(1);
        dq1.addFirst(2);
        dq1.addFirst(5);
        dq1.addFirst(10);
        dq1.addFirst(-1);
        dq1.addFirst(8);
        assertThat(dq1.max()).isEqualTo(10);
        assertThat(dq1.max(c2)).isEqualTo(-1);
    }

    @Test
    public void TestString() {
        Comparator<String> c= new StringComparator();
        MaxArrayDeque<String> dq1 = new MaxArrayDeque<>(c);
        dq1.addFirst("aaa");
        dq1.addFirst("aab");
        dq1.addFirst("abb");
        assertThat(dq1.max()).isEqualTo("abb");
        dq1.addFirst("z");
        assertThat(dq1.max()).isEqualTo("z");
//        dq1.addFirst(-1);
//        dq1.addFirst(8);
    }

}

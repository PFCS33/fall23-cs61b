import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

     @Test
     @DisplayName("LinkedListDeque has no fields besides nodes and primitives")
     void noNonTrivialFields() {
         Class<?> nodeClass = NodeChecker.getNodeClass(LinkedListDeque.class, true);
         List<Field> badFields = Reflection.getFields(LinkedListDeque.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(nodeClass) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not nodes or primitives").that(badFields).isEmpty();
     }

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque<String> lld1 = new LinkedListDeque<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque<String> lld1 = new LinkedListDeque<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque<Integer> lld1 = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    // Below, you'll write your own tests for LinkedListDeque.
    @Test
    public void isEmptyTest(){
         Deque<Integer> lld1 = new LinkedListDeque<>();

         boolean isEmpty1 = lld1.isEmpty();
         lld1.addFirst(1);
         boolean isEmpty2 = lld1.isEmpty();

        assertThat(isEmpty1).isTrue();
        assertThat(isEmpty2).isFalse();
    }

    @Test
    public void sizeTest(){
         Deque<Integer> lld1 = new LinkedListDeque<>();
         int size1 = lld1.size();
         lld1.addFirst(1);
         lld1.addLast(1);
         int size2 = lld1.size();
         assertThat(size1).isEqualTo(0);
         assertThat(size2).isEqualTo(2);
    }

    @Test
    public void getTest(){
         Deque<Integer> lld1 = new LinkedListDeque<>();
         lld1.addFirst(22); // [22]
         lld1.addFirst(11); // [11, 22]
         lld1.addLast(33);  // [11, 22, 33]
         lld1.addLast(44);  // [11, 22, 33, 44]
        Integer item1 = lld1.get(0);
        Integer item2 = lld1.get(2);
        Integer item3 = lld1.get(3);
        Integer item4 = lld1.get(4);
        Integer item5 = lld1.get(-2);
        assertThat(item1).isEqualTo(11);
        assertThat(item2).isEqualTo(33);
        assertThat(item3).isEqualTo(44);
        assertThat(item4).isNull();
        assertThat(item5).isNull();
    }
    @Test
    public void getRecursiveTest(){
        Deque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(22); // [22]
        lld1.addFirst(11); // [11, 22]
        lld1.addLast(33);  // [11, 22, 33]
        lld1.addLast(44);  // [11, 22, 33, 44]
        Integer item1 = lld1.getRecursive(0);
        Integer item2 = lld1.getRecursive(2);
        Integer item3 = lld1.getRecursive(3);
        Integer item4 = lld1.getRecursive(4);
        Integer item5 = lld1.getRecursive(-2);
        assertThat(item1).isEqualTo(11);
        assertThat(item2).isEqualTo(33);
        assertThat(item3).isEqualTo(44);
        assertThat(item4).isNull();
        assertThat(item5).isNull();
    }

    @Test
    public void removeFirstTest(){
         Deque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(22); // [22]
        lld1.addFirst(11); // [11, 22]
        Integer item1 = lld1.removeFirst();
        Integer item2 = lld1.removeFirst();
        Integer item3 = lld1.removeFirst();
        assertThat(item1).isEqualTo(11);
        assertThat(item2).isEqualTo(22);
        assertThat(item3).isNull();
    }

    @Test
    public void removeLastTest(){
        Deque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(22); // [22]
        lld1.addFirst(11); // [11, 22]
        Integer item1 = lld1.removeLast();
        Integer item2 = lld1.removeLast();
        Integer item3 = lld1.removeLast();
        assertThat(item1).isEqualTo(22);
        assertThat(item2).isEqualTo(11);
        assertThat(item3).isNull();
    }
}
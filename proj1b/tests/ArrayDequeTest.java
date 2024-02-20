import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {

     @Test
     @DisplayName("ArrayDeque has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

     @Test
    public void addFirstTest(){
        Deque<Integer> ad1 = new ArrayDeque<>();

        ad1.addFirst(11);
        ad1.addFirst(22);
        ad1.addFirst(33);
         ad1.addFirst(44);
         ad1.addFirst(55);
         ad1.addFirst(66);
         ad1.addFirst(77);
         ad1.addFirst(88);
         ad1.addFirst(99);
         ad1.addFirst(100);

     }
    @Test
    public void addLastTest(){
        Deque<Integer> ad1 = new ArrayDeque<>();

        ad1.addLast(11);
        ad1.addLast(22);
        ad1.addLast(33);
        ad1.addLast(44);
        ad1.addLast(55);
        ad1.addLast(66);
        ad1.addLast(77);
        ad1.addLast(88);
        ad1.addLast(99);
        ad1.addLast(100);
    }

    @Test
    public void getTest(){
        Deque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(22); // [22]
        lld1.addFirst(11); // [11, 22]
        lld1.addLast(33);  // [11, 22, 33]
        lld1.addLast(44);  // [11, 22, 33, 44]
        lld1.addLast(55);
        lld1.addLast(66);
        lld1.addLast(77);
        lld1.addLast(88);
        Integer item1 = lld1.get(0);
        Integer item2 = lld1.get(2);
        Integer item3 = lld1.get(3);
        Integer item4 = lld1.get(10);
        Integer item5 = lld1.get(-2);
        Integer item6 = lld1.get(7);
        assertThat(item1).isEqualTo(11);
        assertThat(item2).isEqualTo(33);
        assertThat(item3).isEqualTo(44);
        assertThat(item4).isNull();
        assertThat(item5).isNull();
        assertThat(item6).isEqualTo(88);
    }

    @Test
    public void toListTest(){
        Deque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(22); // [22]
        lld1.addFirst(11); // [11, 22]
        lld1.addLast(33);  // [11, 22, 33]
        lld1.addLast(44);  // [11, 22, 33, 44]
        lld1.addLast(55);
        lld1.addLast(66);
        lld1.addLast(77);
        lld1.addLast(88);
        lld1.addLast(99);
        assertThat(lld1.toList()).containsExactly(11, 22, 33,44,55,66,77,88,99).inOrder();
    }
    @Test
    public void removeFirstTest(){
        Deque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(22); // [22]
        lld1.addFirst(11); // [11, 22]
        lld1.addLast(33);  // [11, 22, 33]
        lld1.addLast(44);  // [11, 22, 33, 44]
        lld1.addLast(55);
        lld1.addLast(66);
        lld1.addLast(77);
        lld1.addLast(88);
        lld1.addLast(99);
        Integer item1 = lld1.removeFirst();
        Integer item2 = lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        Integer item3 = lld1.removeFirst();
        assertThat(item1).isEqualTo(11);
        assertThat(item2).isEqualTo(22);
        assertThat(item3).isEqualTo(66);
    }
    @Test
    public void removeLastTest(){
        Deque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(22); // [22]
        lld1.addFirst(11); // [11, 22]
        lld1.addLast(33);  // [11, 22, 33]
        lld1.addLast(44);  // [11, 22, 33, 44]
        lld1.addLast(55);
        lld1.addLast(66);
        lld1.addLast(77);
        lld1.addLast(88);
        lld1.addLast(99);
        Integer item1 = lld1.removeLast();
        Integer item2 = lld1.removeLast();
        lld1.removeLast();
        lld1.removeLast();
        lld1.removeLast();
        Integer item3 = lld1.removeLast();
        assertThat(item1).isEqualTo(99);
        assertThat(item2).isEqualTo(88);
        assertThat(item3).isEqualTo(44);
    }
}

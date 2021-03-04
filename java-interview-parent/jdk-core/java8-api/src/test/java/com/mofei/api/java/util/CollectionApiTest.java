package com.mofei.api.java.util;

import com.mofei.api.Person;
import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author com.mofei
 * @version 2020/3/25 23:50
 */
public class CollectionApiTest {

    @Test
    public void testCollectionRemoveIf(){
        Collection<String> c  = new ArrayList<>();
        c.add("a");
        c.add("b");
        c.add("c");
        boolean b = c.removeIf(x -> x.equals("b"));
        c.forEach(System.out::println);
    }

    @Test
    public void testPersonRemoveIf() {
        Collection<Person> collection = new ArrayList();
        collection.add(new Person("张三", 22, "男"));
        collection.add(new Person("李四", 19, "女"));
        collection.add(new Person("王五", 34, "男"));
        collection.add(new Person("赵六", 30, "男"));
        collection.add(new Person("田七", 25, "女"));

        boolean b = collection.removeIf(x -> x.getAge() > 30);

        System.out.println("collection = " + collection.toString());
    }

    /**
     * boolean retainAll(Collection<?> c)
     */
    @Test
    public void testRetainAll() {
        Collection<String> c  = new ArrayList<>();
        c.add("a");
        c.add("b");
        c.add("c");

        Collection<String> c1  = new ArrayList<>();
        c1.add("a");
        c1.add("b");

        boolean b = c.retainAll(c1);
        System.out.println("b = " + b);


        //集合转换数组
        String[] strings = c.toArray(new String[0]);
        //集合转列表
        List<String> strings1 = Arrays.asList(strings);
        //数组转流对象
        Stream<String> stream = Arrays.stream(strings);
        List<String> collect = stream.collect(Collectors.toList());
        //比较字符串中的int数组的数值大小
        Arrays.sort(strings, Comparator.comparingInt(Integer::valueOf));

        boolean empty = c.isEmpty();
        System.out.println("empty = " + empty);
        c.forEach(System.out::println);
        c1.forEach(System.out::println);
    }

    @Test
    public void testIsEmpty() {
        Collection<String> c = new ArrayList<>();
        c.add("a");
        c.add("b");
    }
}
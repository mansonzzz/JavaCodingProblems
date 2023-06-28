package com.m.jcp.tips.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangtian1
 * <ul>
 *  <li>总结：
 *      <ul>
 *          <li>1. <? extends A> 通配 A 的子类，禁止 add，可以 get 为 A</li>
 *          <li>2. <? super A> 通配 A 的父类，可以 add A 的子类对象，可以 get 为 Object</li>
 *      </ul>
 *  </li>
 *  <li>按需选取语法：
 *      <ul>
 *          <li>通配符 ? 如果不做任何约束，不能 add，只能取出 Object 强转</li>
 *          <li>通配符 <? extends A> 只接受 A 的子类，但不确定是哪个子类，get 出来可以向上转为 A</li>
 *          <li>通配符 <? super A> 只接受 A 的父类，单不确定是哪个父类，只能 add A 或 A 的子类（因为可以直接向上转型）</li>
 *      </ul>
 *  </li>
 * </ul>
 */
public class Main {
    public static void main(String[] args) {
        test001Polymorphism();
        test002GenericsV1();
        test002GenericsV2();
        test002GenericsV3();
    }

    private static void test001Polymorphism() {
        // V1 Student 向上转型为 Person
        Student student = new Student();
        Person person = student;

        // V2 数组协变
        Student[] students = new Student[5];
        Person[] people = students;
        people[0] = new Student();
        // ArrayStoreException at runtime 不能存放 Person 的其他子类
        // people[1] = new Teacher();
    }

    /**
     * 问号通配符（List<?>）可以接纳任意类型的 List，
     */
    private static void test002GenericsV1() {
        List<Student> students = new ArrayList<>();
        students.add(new Student());
        // 编译错误，List 不具备协变能力
        // List<Person> people = students;

        List<?> objs = students;
        // 编译器不知道 objs 里存放的是什么类型，所以不能添加任何元素
        // objs.add(new Student()); // 编译错误
        // objs.add(new Person()); // 编译错误
        // 取出来的元素只能是 Object，因为向上转型为 Object
        Object o = objs.get(0);
        assert o == null;
        // objs 里的元素在编译器被类型擦除了，但是在运行时元素还是能知道自己的实际类型
        assert o instanceof Student;
        assert o instanceof Object;
    }

    /**
     * 只能 get 是因为 List<? extends Person> 无法确定具体类型，所以只能向上转型为 Person
     * <p>
     * <ul>
     *     <li>1. 如果 List<? extends Person> people 实际类型是 List<Student> people，那么我们 add(new ClassLeader()) 是没有问题的。</li>
     *     <li>2. 但如果 List<? extends Person> people 实际类型是List<ClassLeader> people，那么我们 add(new Student()) 肯定是编译不过的。</li>
     * </ul>
     */
    private static void test002GenericsV2() {
        List<Student> students = new ArrayList<>();
        students.add(new Student());
        List<? extends Person> people = students;
        // people.add(new Person()); // 编译错误，无法确定具体类型
        // people.add(new Student()); // 编译错误
        Person person = people.get(0);
        assert person instanceof Person;
    }

    /**
     * 1. 如果 List<? super Student> students 实际是 List<Person> 那么 Student 和 ClassLeader 都可以正常向上转型添加进去
     * <p>
     * 2. 如果 Student 有两个父类，除了 extends Person 外还 implements 了 child 接口
     * <ul>
     *     <li>2.1 假设 List<? super Student> students 实际是 List<Person> 那么 Person 可以正常添加</li>
     *     <li>2.2 假设 List<? super Student> students 实际是 List<child> 那么 Person 无法添加，因为他们是平级关系</li>
     * </ul>
     */
    private static void test002GenericsV3() {
        List<Person> people = new ArrayList<>();
        people.add(new ClassLeader());
        List<? super Student> students = people;

        // List<? super Student> 的实际类型一定是 Student 的父类型，所以继承自 Student 的子类都可以通过向上转型添加进 students
        students.add(new Student());
        students.add(new ClassLeader());

        // students.add(new Teacher());
        // students.add(new Person()); // 编译错误，student 可能有多个父类
        Object o = students.get(0);
        assert o instanceof Person;
        assert o instanceof Student;
    }
}

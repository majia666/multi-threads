package com.majia.concurrentpackage.atomic.atomicreferenceandstampedreference;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {

    public static void main(String[] args) {

        AtomicReference<Simple> atomicReference = new AtomicReference<Simple>(new Simple("majia", 18));

        System.out.println(atomicReference.get());

        Simple a = new Simple("a", 13);

        boolean b = atomicReference.compareAndSet(a,a);

        System.out.println(b);
    }

    static class Simple {
        private String name;
        private int age;

        public Simple(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Simple{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}

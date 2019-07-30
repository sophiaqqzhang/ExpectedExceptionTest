package com.exception.ExpectedExceptionTest;

class Debug {
    private static void f1() {
        System.out.println("one");
        System.out.println("two");
        System.out.println("three");
    }

    private static void data() {
        System.out.println("1+1");
        System.out.println("2");
        System.out.println("3");
    }

    private static void f2() {
        for (char c = 0; c < 128; c++) {
            if (Character.isLowerCase(c))
                System.out.println("value:" + (int) c + "character:" + c);
        }
        f1();
    }

    public static void main(String[] args) {
        f1();
        f2();
        data();
    }
}

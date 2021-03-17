package com.mofei.oom;

/**
 * @author mofei
 * @date 2021/3/11 23:25
 */
public class JavaOverStackFlowDemo {
    public static void main(String[] args) {
        println();
    }

    private static void println() {
        println();
    }
}

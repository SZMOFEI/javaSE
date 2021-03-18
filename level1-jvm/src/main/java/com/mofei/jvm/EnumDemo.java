package com.mofei.jvm;

/**
 * @author mofei
 * @date 2021/3/7 23:23
 */
public enum EnumDemo {
    ONE(1, "赵"), TWO(2, "奇"), THREE(3, "卫"), FOUR(4, "宋"), FIVE(5, "李");
    private Integer code;
    private String name;

    EnumDemo() {
    }

    EnumDemo(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static void main(String[] args) {
        String name = EnumDemo.getCodeBy(EnumDemo.TWO.getCode());
        System.out.println("name = " + name);
    }

    public static String getCodeBy(int code) {
        EnumDemo[] values = EnumDemo.values();
        for (EnumDemo value : values) {
            if (value.getCode().equals(code)) {
                return value.getName();
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

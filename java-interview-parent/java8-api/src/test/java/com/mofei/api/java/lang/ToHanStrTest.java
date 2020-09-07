package com.mofei.api.java.lang;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author com.mofei
 * @date 2020/3/29 18:53
 */
public class ToHanStrTest {


    private String[] hanStr = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖", "拾"};
    private String[] unitStr = {"十", "百", "千", "万"};

    /**
     * 测试把一个输入的double 转成整数的小数的数组
     */
    @Test
    public void testDivide() {
        double str = 34.509;
        String[] nums = devideNum(str);
        Assert.assertEquals(2, nums.length);
        Assert.assertEquals("34", nums[0]);
        Assert.assertEquals("51.0", nums[1]);
    }

    @Test
    public void testToHanStr() {
        String numStr = "113060";
        String s = toHanstr(numStr);
        Assert.assertEquals("叁千零陆十0", s);
    }

    private String[] devideNum(double str) {
        long zheng = (long) str;
        double xiao = Math.round((str - zheng) * 100);
        return new String[]{zheng + "", xiao + ""};
    }

    private String toHanstr(String numStr) {
        StringBuffer result = new StringBuffer();
        char[] chars = numStr.toCharArray();
        int length = chars.length;
        for (int i = 0; i < chars.length; i++) {
            int i1 = chars[i] - 48;
            //处理尾数全部是0的情况， 比如3400 就是三千四百 ,而并不是三千四百零零
            boolean matches = numStr.substring(i, length).matches("[0]+");
            if (matches) {
                return result.toString();
            }
            if (i != chars.length - 1 && i1 != 0) {
                //比如你1000 1 是第4位 ， 那么就是 2
                //是不是千万和万之间
                int i2 = length - i;//当前数字的位数
                if (i2 > 5 && i2 < 9) {
                    result.append(hanStr[i1]);
                } else if (i2 == 5) {
                    result.append(hanStr[i]).append(unitStr[length - 2 - i]);
                } else {
                    //不是最后一位并且不是零
                    result.append(hanStr[i1]).append(unitStr[length - 2 - i]);
                }
            } else {
                result.append(hanStr[i1]);
            }
        }
        return result.toString();
    }

    @Test
    public void testSubString() {
        String s = "3400";
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int i1 = chars[i] - 48;

            String substring = s.substring(i, chars.length);

            boolean matches = s.substring(i, chars.length).matches("[0]+");
            if (matches) {
                System.out.println("substring = " + substring);
            }

        }
    }
}

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class bigInter {
    public static void main(String[] args)
    {
        //大数相加
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the length of number1: ");
        int lengthOfNumber1 = input.nextInt();
        System.out.println("Please enter the length of number2: ");
        int lengthOfNumber2 = input.nextInt();
        //输入链表长度

        List<Integer> number1 = new LinkedList<Integer>();
        List<Integer> number2 = new LinkedList<Integer>();
        //定义链表

        for(int i = 0; i < lengthOfNumber1; i++)
        {
            number1.add(i);
        }
        for(int i = 0; i < lengthOfNumber2; i++)
        {
            number2.add(i);
        }
        //利用循环，基于定义的链表长度，把大数压进链表

        int bigLength = Math.max(lengthOfNumber1, lengthOfNumber2);
        //取链表长度中最大的值


        if(lengthOfNumber1 < lengthOfNumber2)
        {
            for(int i = 1; i <= (lengthOfNumber2 - lengthOfNumber1); i++)
            {
                number1.add(0);
            }
        }
        if(lengthOfNumber2 < lengthOfNumber1)
        {
            for(int i = 1; i <= (lengthOfNumber1 - lengthOfNumber2); i++)
            {
                number2.add(0);
            }
        }
        //将链表长度较小的链表后补零

        System.out.println("bigNumber1:"+number1);
        System.out.println("bigNumber2:"+number2);
        //打印两个链表

        /*Scanner input = new Scanner(System.in);
        System.out.println("Please enter two numbers(String): \nnumber1 is: ");
        String number1 = input.next();
        System.out.println("number2 is: ");
        String number2 = input.next();*/
        List<Integer> m = addMethod(number1, number2, bigLength);//三个含参
        int count = 0;
        for(int i = m.size()-1; i >= 0; i--)
        {
            //因为是顺着放进去的，所以应该倒着取出来。
            System.out.print(m.get(i));
            count++;
            if(count % 3 == 0)
            {
                System.out.print(" ");
            }
        }
    }
    public static List<Integer> addMethod(List<Integer> num1, List<Integer> num2, int n) {//写链表相加的方法，三个含参
        List<Integer> sum = new LinkedList<Integer>();
        int tensDigits = 0; //存储十位上的数
        for(int i = 0; i < n; i++)
        {
            int temp;
            if(tensDigits > 0)
            {
                temp = num1.get(i) + num2.get(i) + tensDigits;
                //当十位上有数字是就应该加上
                // 获取num1的每一位加上获取num2的每一位加上定义的十位数字，以表示进位
                tensDigits = 0;
            }
            else
            {
                temp = num1.get(i) + num2.get(i);
                //获取num1的每一位加上获取num2的每一位，不用进位
            }
            //每处理两位，向链表里添加一次数
            if(temp >= 10)
            {//如果两位相加之不是个位数
                int remainder = temp % 10;//把每次求余的结果添加到链表里
                int consult = temp / 10;
                sum.add(remainder);
                tensDigits = consult;//迭代定义的十位数字等于求余后的商
                if(i == (n - 1))
                { //当加到最后一位时，应该把十位也放进结果里
                    sum.add(tensDigits);
                }
            }
            else
            {
                //如果两位相加后是个位数
                //直接加入链表当中
                sum.add(temp);
            }
        }
        return sum;//把链表作为返回值
    }
    public static List<Integer> creat(String number) /*throws Exception*/ {//暂时没用到
        LinkedList<Byte> list = new LinkedList<Byte>();
        //首先要判断输入进来的integer是否是一个合理的字符串
        //把一串字符串当成一串数，做一个模板
        String mod = "[+|-]*(//d)*";
        //正则0 - 无穷个, /d小写数字,/是转义符,第一个斜杠表示
        //要把第二个斜杠当成真正的斜杠
        Pattern pt = Pattern.compile(mod);
        //模式匹配,判断是否是一个正常的表达式
        Matcher match = pt.matcher(number);
        //测试进来的字符串与模板是否匹配
        String strNumber = null;
        //把以下不同的无论是加号开始还是减号开始保存到字符串中
        if(!match.find())
        //如果在输入的字符串中，不能够找到符合模板的内容
        {
            System.out.println("wrong input");
            //throw new Exception("wrong input");
            //抛出异常
        }
        else
        {//如果在输入的字符串中，能够找到符合模板的内容
            if(mod.startsWith("+") || mod.startsWith("-"))
            {
                strNumber = number.substring(1 , number.length() - 1);
                //从一个位置开始到一个位置结束
            }
            else
            {
                strNumber = number.substring(0 , number.length() - 1);
            }
        }
        //把除了符号位所有以外的数放到list里面
        char[] strArray = strNumber.toCharArray();
        //把字符串放到数组当中
        for(int i = 0; i < strArray.length-1; i++)
        {
            //把list里面加入所有产生的内容
            list.add((byte) (strArray[i] - '0'));
        }
        return null;
    }
}
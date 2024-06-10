import java.util.*;

public class MoneyConverter {
    String intStr;
    String fracStr;
    String num;
    String result ="";
    Scanner in;
    float fl;
    boolean conZero;
    int flag =0;
    private static final String[] CN_NUMBERS = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"}; 
    private static final String[] CN_UNITS = {"圆", "拾", "佰", "仟"};  
    private static final String[] CN_BIG_UNITS = {"", "万", "亿", "兆"};  
    private static final String[] CN_Min_UNITs = {"分","角"};
    public void inputStr(){
        do {
            System.out.print("请输入金额: ");
            in = new Scanner(System.in);
            num = in.next();
            try {
                float fl = Float.parseFloat(num);
                if (fl < 0) {
                    System.out.println("金额不能小于0,请重新输入!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("非法输入,金额必须是数字: " );
            }
        } while (true); // 循环直到输入有效
        intfun(num);
    }
    public String intfun(String num){//打印整数部分数值
        if(num.equals("0")){
            System.out.print("零圆整");
            result = "零圆整";
            return result;
        }

        //先进行一下预处理，去除前导零（零按位置分，分为前导零，中间零和末尾零）
        num = num.replaceFirst("^0+", "");
        if(num.contains(".") == false){
            intStr = num;
            fracStr = null;
        }
        else{
            String []s = num.split("\\.");
            intStr = s[0];//整数部分
            //System.out.println("s[0]="+s[0]);
            fracStr = s[1];//小数部分
            //System.out.println("s[1]="+s[1]);
        }

        for(int i=0;i<intStr.length();i++){
            switch (intStr.charAt(i)) {
                case '0':
                    for(;i < intStr.length() && intStr.charAt(i) == '0';i++){
                        if((intStr.length()-i == 5 || intStr.length()-i == 9) && flag == 0){
                            result +=  print1(intStr,i);//中途遍历到了万位或者亿位的零要打印数位
                        } 
                    }
                    i = i - 1;//无论是以第一种条件跳出还是第二个条件，此时i=i-1对应的字符是连续零的最后一个‘0’
                    if(i == intStr.length() -1){
                        if(fracStr == null){
                            result += print1(intStr, i);
                            /*System.out.println();
                            System.out.println(result);*/
                            return result;
                        }
                        else{
                            result += CN_UNITS[0];
                            System.out.print(CN_UNITS[0]);
                            fracfun(fracStr);
                            /*System.out.println();
                            System.out.println(result);*/
                            return result;
                        }
                    }
                    if(intStr.length() - i == 5 || intStr.length() - i == 9){}//连续零如果以万位或者亿位为终止可以不打印零。而只打印数位，前面亦已经执行过
                    else{result +="零";System.out.print("零");}
                    break;
                case '1':
                    if((intStr.length()-i == 2 && intStr.length() == 2) || (intStr.length()-i == 6 && intStr.length() == 6)
                    || (intStr.length()-i == 10 && intStr.length() == 10)){ //十元，十万元，十亿元省略数字

                    }
                    else{
                        result += CN_NUMBERS[1];
                        System.out.print(CN_NUMBERS[1]);
                        
                    }  
                    result += print1(intStr, i);
                    break;
                case '2':
                    result += CN_NUMBERS[2];
                    System.out.print(CN_NUMBERS[2]);
                    
                    result += print1(intStr, i);
                    break;
                case '3':
                    result += CN_NUMBERS[3];
                    System.out.print(CN_NUMBERS[3]);
     
                    result += print1(intStr, i);
                    break;
                case '4':
                    result += CN_NUMBERS[4];
                    System.out.print(CN_NUMBERS[4]);
                    
                    result += print1(intStr, i);
                    break;
                case '5':
                    result += CN_NUMBERS[5];
                    System.out.print(CN_NUMBERS[5]);
                    
                    result += print1(intStr, i);
                    break;
                case '6':
                    result += CN_NUMBERS[6];
                    System.out.print(CN_NUMBERS[6]);
                    
                    result += print1(intStr, i);
                    break;
                case '7':
                    result += CN_NUMBERS[7];
                    System.out.print(CN_NUMBERS[7]);
                    
                    result += print1(intStr, i);
                    break;
                case '8':
                    result += CN_NUMBERS[8];
                    System.out.print(CN_NUMBERS[8]);
                    
                    result += print1(intStr, i);
                    break;
                case '9':
                    result += CN_NUMBERS[9];
                    System.out.print(CN_NUMBERS[9]);
                    
                    result += print1(intStr, i);
                    break;
                case '.':
                    break;
                default:
                    System.out.print("Error");
                    break;
            }
        }
        fracfun(fracStr);//不是以零终结整数部分，代码执行到这
        /*in.close();
        System.out.println();
        System.out.println(result);*/
        return result;
    }

    public void fracfun(String fracStr){//打印小数部分数值
        if(fracStr != null){
            for(int i=0;i<fracStr.length();i++){
                switch (fracStr.charAt(i)) {
                    case '0':
                    for(;i < fracStr.length() && fracStr.charAt(i) == '0';i++){}
                    i = i - 1;//确保跳出循环是最后一个零
                    if(fracStr.length() - i == 1){}//末尾零不打印
                    else{
                        System.out.print(CN_NUMBERS[0]);
                        result += CN_NUMBERS[0];
                        //result += print2(fracStr, i);//数位不需要打印，打印零即可
                    } 
                    break;
                case '1':
                    result += CN_NUMBERS[1];
                    System.out.print(CN_NUMBERS[1]);
                    
                    result += print2(fracStr, i);
                    break;
                case '2':
                    result += CN_NUMBERS[2];
                    System.out.print(CN_NUMBERS[2]);
                    
                    result += print2(fracStr, i);
                    break;
                case '3':
                    result += CN_NUMBERS[3];
                    System.out.print(CN_NUMBERS[3]);
                    
                    result += print2(fracStr, i);
                    break;
                case '4':
                    result += CN_NUMBERS[4];
                    System.out.print(CN_NUMBERS[4]);
                    
                    result += print2(fracStr, i);
                    break;
                case '5':
                    result += CN_NUMBERS[5];
                    System.out.print(CN_NUMBERS[5]);
                    
                    result += print2(fracStr, i);
                    break;
                case '6':
                    result += CN_NUMBERS[6];
                    System.out.print(CN_NUMBERS[6]);
                    
                    result += print2(fracStr, i);
                    break;
                case '7':
                    result += CN_NUMBERS[7];
                    System.out.print(CN_NUMBERS[7]);
                    
                    result += print2(fracStr, i);
                    break;
                case '8':
                    result += CN_NUMBERS[8];
                    System.out.print(CN_NUMBERS[8]);
                    
                    result += print2(fracStr, i);
                    break;
                case '9':
                    result += CN_NUMBERS[9];
                    System.out.print(CN_NUMBERS[9]);
                    
                    result += print2(fracStr, i);
                    break;
                default:
                    System.out.print("Error");
                    break;
                }
            }
        }
    }

    public String print1(String intstr,int i){//打印整数部分数位
        switch (intStr.length()-i) {//intStr.length()-i表示处于从右往左第几位
            case 1:
                if(fracStr != null){
                    System.out.print(CN_UNITS[0]);
                    return CN_UNITS[0];
                }
                else if(fracStr == null){
                    System.out.print(CN_UNITS[0]+"整");//没有小数部分，打印整
                    return CN_UNITS[0]+"整";
                }
                return CN_UNITS[0];
            case 2:
            case 6:
            case 10:
                System.out.print(CN_UNITS[1]);
                return CN_UNITS[1];
            case 3:
            case 7:
            case 11:
                System.out.print(CN_UNITS[2]);
                return CN_UNITS[2];
            case 4:
            case 8:
            case 12:
                System.out.print(CN_UNITS[3]);
                return CN_UNITS[3];
            case 5:
                System.out.print(CN_BIG_UNITS[1]);
                return CN_BIG_UNITS[1];
            case 9:
                System.out.print(CN_BIG_UNITS[2]);
                return CN_BIG_UNITS[2];
            default:
            System.out.println("Error");
                break;
        }
        return "";
    }

    public String print2(String fracStr,int i){//打印小数部分数位
        switch (fracStr.length()-i) {
            case 1:
                System.out.print(CN_Min_UNITs[0]);
                return CN_Min_UNITs[0];
            case 2:
                System.out.print(CN_Min_UNITs[1]);
                return CN_Min_UNITs[1];
            default:
                break;
        }
        return "";
    }
    
}

class Convert{
    public static void main(String[] args) {
        MoneyConverter moneyConverter = new MoneyConverter();
        String result = moneyConverter.intfun("100810001100.01");
        System.out.println();
        System.out.println("result="+result);
    }
}



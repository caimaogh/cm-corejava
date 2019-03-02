/**
 * @Author: CaiMao
 * @Description
 * @Date: created in 2019/2/25 21:10
 */
public class CalculateDemo {

    CalculateDemo next;
    int index;

    public static void main(String[] args){

    }

    public static int calculate(char[] expr) throws Exception{
        int result = 0;
        int addNum = 0;
        int leftPar = 0;
        int rightPar = 0;
        int operNum = 0;
        for(char a : expr){
            if(a=='+'){
                addNum++;
            }else if(a=='('){
                leftPar++;
            }else if(a==')'){
                rightPar++;
            }else{
                operNum++;
            }
        }
        if(addNum==operNum){
            throw new Exception("single operand");
        }else if(leftPar != rightPar){
            throw new Exception("not paired parentheses");
        }else if(operNum<1){
            throw new Exception("no operator defined");
        }
        return result;

    }
}

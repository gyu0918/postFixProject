package day0228;


import java.util.*;

public class Main {

    public boolean checkNum(char a) {
        if (a < 48 || a > 57) {
            return false;
        }
        return true;
    }

    public int operatorToNum(char a) {
        //연산자 우선순위
        // *,/ > +,- > (        순서로 우선순위 정해짐
        // 3   >  2   > 1
        if (a == '*' || a == '/') {
            return 3;
        } else if (a == '+' || a == '-') {
            return 2;
        } else if (a == '(') {
            return 1;
        } else {
            return 0;
        }
    }

    public void stackPopPop(Stack<Character> stack, List<Character> result) {
        while (!stack.isEmpty()) {
            char temp = stack.pop();
            if (temp == '(') {
                break;
            }
            result.add(temp);
        }
    }

    public static void main(String[] args) {
        String a = "2+3*4/(2-3)+10";
//        String a = "1+2*(3-4)/5";
        Main main = new Main();

        Stack<Character> stack = new Stack<>();

        //결과 값
        List<Character> result = new ArrayList<>();


        boolean flag = false;

        for (int i = 0; i < a.length(); i++) {
            if (main.checkNum(a.charAt(i))) {
                result.add(a.charAt(i));
                flag = true;
                continue;
            }
            //숫자가 아닌 연산자 나왔을 경우
            if (flag == true) {
                result.add('S');
                flag = false;
                i--;
                continue;
            }//100 이렇게 3자리 숫자 구분할려고 뒤에 S를 붙인다 .
            //연산자 숫자로 변환 부분
            int strOp = main.operatorToNum(a.charAt(i));
            if (stack.isEmpty()) {
                stack.push(a.charAt(i));
                flag = false;
                continue;
            }
            int stackOp = main.operatorToNum(stack.peek());

            //우선순위 처리 숫자 비교 부분
            if (a.charAt(i) == '(') {
                stack.push(a.charAt(i));
            } else if (a.charAt(i) == ')') {
                main.stackPopPop(stack, result);  // '(' 이게 나올떄까지 pop하는것

            } else if (strOp > stackOp) {
                stack.push(a.charAt(i));
            } else if (strOp <= stackOp) {
                while (true) {
                    if (stack.isEmpty()) {
                        break ;
                    }
                    int num = main.operatorToNum(stack.peek());
                    if (strOp > num) {
                        break;
                    }
                    result.add(stack.pop());
                }
//                   result.add(stack.pop());
                stack.push(a.charAt(i));
            }


        }
        result.add('S');

        //남아 있는 스텍의 연선자들을 넣어준다.

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        for (Character c : result) {
            System.out.print(c);
        }

        //계산하는 식!!
        //우선 자리수 생각해서 넣어준다. 배열에 넣어준다.
       // result를 생각해서 해보자

    }
}

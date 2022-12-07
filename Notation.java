
public class Notation {
    public static int precedence (char character)
    {
        if (character=='+'||character=='-')
            return 1;
        else if(character=='*'||character=='/')
            return 2;
        else if (character=='^')
            return 3;
        else
            return -1;
    }

    public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException{
        //initializing stack
        MyStack <String> stack= new MyStack<String>();
        MyQueue <String>postfix= new MyQueue<String>();

        for (int i=0; i<infix.length();i++)
        {

            if (Character.isDigit(infix.charAt(i))||Character.isLetter(infix.charAt(i)))

                postfix.enqueue(Character.toString(infix.charAt(i)));


            else if (infix.charAt(i)=='(') {
                stack.push(Character.toString(infix.charAt(i)));

            } else if (infix.charAt(i)=='+'||infix.charAt(i)=='-'||infix.charAt(i)=='*'||infix.charAt(i)=='/'||infix.charAt(i)=='^') {
                while ((!stack.isEmpty())&&precedence(stack.top().charAt(0))>=precedence(infix.charAt(i)))
                {
                    stack.push(stack.pop());
                }
                stack.push(Character.toString(infix.charAt(i)));
            }
            else if (infix.charAt(i)==')')
            {
                if(stack.isEmpty())
                    throw new InvalidNotationFormatException();
                while (stack.top().charAt(0)!='(')
                {
                    if(stack.size()==1&&stack.top().charAt(0)!='(')
                        throw new InvalidNotationFormatException();
                    postfix.enqueue(stack.pop());
                }
                stack.pop();

            }

        }

        while (!stack.isEmpty())
        {
            postfix.enqueue(stack.pop());
        }
        return postfix.toString();
    }
    public static String convertPostfixToInfix(String postfix)throws InvalidNotationFormatException
        {
            MyStack <String> stack= new MyStack<String>();
           String resultedInfix;
            for (int i=0; i<postfix.length(); i++)
            {
                String infix="";
                String valueOne=null;
                String valueTwo=null;
                char character=postfix.charAt(i);
                if(Character.isDigit(character)||Character.isLetter(character))
                {
                    stack.push(String.valueOf(character));
                }
                if(character=='+'||character=='-'||character=='*'||character=='/'||character=='^')
                {
                    if(stack.size()<2)
                        throw new InvalidNotationFormatException();
                    else
                    {
                        valueOne= String.valueOf(stack.pop());
                        valueTwo=String.valueOf(stack.pop());
                        infix="("+valueTwo+character+valueOne+")";
                        stack.push(infix);
                    }

                }
            }

            if (stack.size()!=1)
                throw new InvalidNotationFormatException();
            resultedInfix=stack.pop();
            return resultedInfix;

        }
        public static double evaluatePostfixExpression(String postfix) throws InvalidNotationFormatException
        {
            MyStack <String> stack= new MyStack<String>();
            double result=0.0;
            String resultedValue="";
            for (int i=0; i<postfix.length(); i++) {
                char character=postfix.charAt(i);
                if (Character.isDigit(character)||character=='(')
                {
                     stack.push(String.valueOf(character));
                }
                else if (character=='+'||character=='-'||character=='*'||character=='/'||character=='^')
                {
                    if(stack.size()<2)
                        throw new InvalidNotationFormatException();

                    else
                    {
                        String valueOne= String.valueOf(stack.pop());
                        String valueTwo=String.valueOf(stack.pop());

                       if (character=='+')
                       {
                           double temp=Double.parseDouble(valueTwo)+ Double.parseDouble(valueOne);
                           result=temp;
                       }
                        else if (character=='-')
                        {
                            double temp=Double.parseDouble(valueTwo)- Double.parseDouble(valueOne);
                            result=temp;
                        }
                       else if (character=='*')
                       {
                           double temp=Double.parseDouble(valueTwo)*Double.parseDouble(valueOne);
                           result=temp;
                       }
                       else if (character=='/')
                       {
                           double temp=Double.parseDouble(valueTwo)/ Double.parseDouble(valueOne);
                           result=temp;
                       }
                       else if (character=='^')
                       {
                           double temp=Math.pow(Double.parseDouble(valueTwo), Double.parseDouble(valueOne));
                           result=temp;
                       }
                       stack.push(String.valueOf(result));
                    }
                }

            }

            if (stack.size()!=1)
                throw new InvalidNotationFormatException();

            resultedValue=stack.pop();
            return Double.parseDouble(resultedValue);
        }

}

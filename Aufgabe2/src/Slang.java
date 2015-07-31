import java.util.Stack;

public class Slang {

    /*fg: Stack for the operations */
    private static Stack<Value> myStack;

    //Class for StringValue
    private static class StringValue implements Value {
        String s;
        public StringValue(String s){
            this.s = s;
        }
        public String toS(){
            return s;
        }
        public int toI(){
            return 0;
        }
    }
    //Class for IntValue
    private static class IntValue implements Value {
        int i;
        public IntValue(int i){
            this.i = i;
        }
        public String toS(){
            return Integer.toString(i);
        }
        public int toI(){
            return i;
        }
    }
    /*/fg*/

    public static void main(String[] args) {

        /*fg: Stack initialization */
        myStack = new Stack<Value>();
        /*/fg*/

        AtomScanner sc = null;

        try {
            sc = new AtomScanner(System.in);
        } catch (LexerException ex) {
            System.out.println(ex);
            return;
        }

        while (sc.hasNext()) {
            if (sc.hasNextString()) {
                /*fg:
                 * default:{ System.out.println("STRING: " + sc.nextString());} */
                myStack.push(new StringValue(sc.nextString()));
                /*/fg*/
             }
            else if (sc.hasNextInteger()) {
                /*fg:
                 * default: System.out.println("INTEGER: " + sc.nextInteger());*/
                myStack.push(new IntValue(sc.nextInteger()));
                /*/fg*/
            }
            else if (sc.hasNextAtom()) {
                /*fg:
                 * default: System.out.println("ATOM: " + sc.nextAtom());*/
                switch(sc.nextAtom()) {
                    case ".s": printStack(); break;
                    case "prin": f_prin(); break;
                    case "print": f_print(); break;
                    case "sub": f_sub(); break;
                    case "add": f_add(); break;
                    case "div": f_div(); break;
                    case "mod": f_mod(); break;
                    default: //throw error
                }
                /*/fg*/
            }
            else {
                System.out.println("UNKNOWN: " + sc.next());
            }
        }
    }

    /*fg: Atom functions */
    private static void printStack(){
        Stack<Value> tmpStack = new Stack<Value>();
        System.out.println("-- Top of Stack: ");
        while(!myStack.empty()){
            tmpStack.push(myStack.peek());
            System.out.println(myStack.pop().toS());
        }
        System.out.println("-- Bottom");
        while(!tmpStack.empty()){
            myStack.push(tmpStack.pop());
        }
    }

    private static void f_prin(){
        System.out.print(myStack.pop().toS());
    }
    private static void f_print(){
        System.out.println(myStack.pop().toS());
    }
    private static void f_sub(){
        myStack.push(new IntValue(myStack.pop().toI() - myStack.pop().toI()));
    }
    private static void f_add(){
        myStack.push(new IntValue(myStack.pop().toI() + myStack.pop().toI()));
    }
    private static void f_div(){
        myStack.push(new IntValue(myStack.pop().toI() / myStack.pop().toI()));
    }
    private static void f_mod(){
        myStack.push(new IntValue(myStack.pop().toI() % myStack.pop().toI()));
    }
    private static void f_read(){
    }
    /*/fg*/

}

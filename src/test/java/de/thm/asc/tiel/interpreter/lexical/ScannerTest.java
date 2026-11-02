package de.thm.asc.tiel.interpreter.lexical;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScannerTest {

    @Test
    void scansVariableDeclarationAndFunctionCall() {
        var tokens = new Scanner("""
                var answer = 12.5;
                print(answer);
                """).scan();

        assertTokenTypes(tokens, List.of(
                TokenType.VAR,
                TokenType.IDENTIFIER,
                TokenType.EQUAL,
                TokenType.NUMBER,
                TokenType.SEMICOLON,
                TokenType.IDENTIFIER,
                TokenType.LEFT_PAREN,
                TokenType.IDENTIFIER,
                TokenType.RIGHT_PAREN,
                TokenType.SEMICOLON,
                TokenType.EOF
        ));

        assertEquals("answer", tokens.get(1).value());
        assertEquals(12.5, tokens.get(3).value());
        //assertEquals(TokenType.SEMICOLON,tokens.get(4).type());
    }

    @Test
    void skipsLineComments() {
        var tokens = new Scanner("""
                var x = 1; // ignore this
                return x;
                """).scan();

        assertTokenTypes(tokens, List.of(
                TokenType.VAR,
                TokenType.IDENTIFIER,
                TokenType.EQUAL,
                TokenType.NUMBER,
                TokenType.SEMICOLON,
                TokenType.RETURN,
                TokenType.IDENTIFIER,
                TokenType.SEMICOLON,
                TokenType.EOF
        ));
    }

    @Test
    void scansMultilineString() {
        var tokens = new Scanner("""
                var message = "hello
                world";
                print(message);
                """).scan();

        assertTokenTypes(tokens, List.of(
                TokenType.VAR,
                TokenType.IDENTIFIER,
                TokenType.EQUAL,
                TokenType.STRING,
                TokenType.SEMICOLON,
                TokenType.IDENTIFIER,
                TokenType.LEFT_PAREN,
                TokenType.IDENTIFIER,
                TokenType.RIGHT_PAREN,
                TokenType.SEMICOLON,
                TokenType.EOF
        ));

        assertEquals("hello\nworld", tokens.get(3).value());
    }

    @Test
    void scansSimpleFunction() {
        var tokens = new Scanner("""
                fun test(flag) {
                    if not flag and true or false {
                        return 1 != 2;
                    }
                }
                """).scan();

        assertTokenTypes(tokens, List.of(
                TokenType.FUN,
                TokenType.IDENTIFIER,
                TokenType.LEFT_PAREN,
                TokenType.IDENTIFIER,
                TokenType.RIGHT_PAREN,
                TokenType.LEFT_BRACE,
                TokenType.IF,
                TokenType.NOT,
                TokenType.IDENTIFIER,
                TokenType.AND,
                TokenType.TRUE,
                TokenType.OR,
                TokenType.FALSE,
                TokenType.LEFT_BRACE,
                TokenType.RETURN,
                TokenType.NUMBER,
                TokenType.NOT_EQUAL,
                TokenType.NUMBER,
                TokenType.SEMICOLON,
                TokenType.RIGHT_BRACE,
                TokenType.RIGHT_BRACE,
                TokenType.EOF
        ));
    }
    @Test
    void scanIfStmt(){
        var tokens= new Scanner("""
                var test = true;
                if (test == true) {
                    print("true");
                }
                else{
                    print("false");
                }
        """).scan();
        assertTokenTypes(tokens,List.of(
                TokenType.VAR,
                TokenType.IDENTIFIER,
                TokenType.EQUAL,
                TokenType.TRUE,
                TokenType.SEMICOLON,
                TokenType.IF,
                TokenType.LEFT_PAREN,
                TokenType.IDENTIFIER,
                TokenType.EQUAL_EQUAL,
                TokenType.TRUE,
                TokenType.RIGHT_PAREN,
                TokenType.LEFT_BRACE,
                TokenType.IDENTIFIER,
                TokenType.LEFT_PAREN,
                TokenType.STRING,
                TokenType.RIGHT_PAREN,
                TokenType.SEMICOLON,
                TokenType.RIGHT_BRACE,
                TokenType.ELSE,
                TokenType.LEFT_BRACE,
                TokenType.IDENTIFIER,
                TokenType.LEFT_PAREN,
                TokenType.STRING,
                TokenType.RIGHT_PAREN,
                TokenType.SEMICOLON,
                TokenType.RIGHT_BRACE,
                TokenType.EOF
        ));
        //if
        assertEquals(TokenType.IF,tokens.get(5).type());
        assertEquals(null,tokens.get(5).value());
        assertEquals("if",tokens.get(5).lexeme());
        //for string "true"
        assertEquals("true",tokens.get(14).value());
        System.out.println(tokens.get(14));
        assertEquals(TokenType.STRING,tokens.get(14).type());


    }

    @Test
    void scanArrayDecl(){
        var tokens= new Scanner("""
                var numbers = [10, 20, 30, 40];
                print(numbers[0]);
                """).scan();
        assertTokenTypes(tokens,List.of(
                TokenType.VAR,
                TokenType.IDENTIFIER,
                TokenType.EQUAL,
                TokenType.LEFT_BRACKET,
                TokenType.NUMBER,
                TokenType.COMMA,
                TokenType.NUMBER,
                TokenType.COMMA,
                TokenType.NUMBER,
                TokenType.COMMA,
                TokenType.NUMBER,
                TokenType.RIGHT_BRACKET,
                TokenType.SEMICOLON,
                TokenType.IDENTIFIER,
                TokenType.LEFT_PAREN,
                TokenType.IDENTIFIER,
                TokenType.LEFT_BRACKET,
                TokenType.NUMBER,
                TokenType.RIGHT_BRACKET,
                TokenType.RIGHT_PAREN,
                TokenType.SEMICOLON,
                TokenType.EOF
        ));
//        System.out.println(tokens.get(4));
//        System.out.println(tokens.get(5));
//        System.out.println(tokens.get(6));
        assertEquals(10.0,tokens.get(4).value());
        assertEquals(TokenType.NUMBER,tokens.get(4).type());
    }
    @Test
    void scanTwoDimArray(){
        var tokens=new Scanner("""
                print([[1, 2], [3, 4]][1][1]);
                """).scan();
        assertTokenTypes(tokens,List.of(
                TokenType.IDENTIFIER,
                TokenType.LEFT_PAREN,
                TokenType.LEFT_BRACKET,
                TokenType.LEFT_BRACKET,
                TokenType.NUMBER,
                TokenType.COMMA,
                TokenType.NUMBER,
                TokenType.RIGHT_BRACKET,
                TokenType.COMMA,
                TokenType.LEFT_BRACKET,
                TokenType.NUMBER,
                TokenType.COMMA,
                TokenType.NUMBER,
                TokenType.RIGHT_BRACKET,
                TokenType.RIGHT_BRACKET,
                TokenType.LEFT_BRACKET,
                TokenType.NUMBER,
                TokenType.RIGHT_BRACKET,
                TokenType.LEFT_BRACKET,
                TokenType.NUMBER,
                TokenType.RIGHT_BRACKET,
                TokenType.RIGHT_PAREN,
                TokenType.SEMICOLON,
                TokenType.EOF

        ));
//        System.out.println(tokens.get(1));
//        System.out.println(tokens.get(2));
//        System.out.println(tokens.get(4));
        assertEquals(TokenType.LEFT_PAREN,tokens.get(1).type());
        assertEquals(1.0,tokens.get(4).value());
        assertEquals(TokenType.LEFT_BRACKET,tokens.get(2).type());

    }
    //Tests for classes
    @Test
    public void scanClassAndThis(){
        var tokens = new Scanner("class Counter{this.value=1;}").scan();
//        System.out.println(tokens.get(0));
//        System.out.println(tokens.get(1));
        assertEquals(TokenType.CLASS,tokens.get(0).type());
        assertEquals(TokenType.IDENTIFIER,tokens.get(1).type());
        assertEquals(TokenType.LEFT_BRACE,tokens.get(2).type());
        assertEquals(TokenType.THIS,tokens.get(3).type());
    }
    @Test
    public void scanDot(){
        var tokens = new Scanner("a.value;").scan();
        assertEquals(TokenType.DOT,tokens.get(1).type());
    }

    private static void assertTokenTypes(List<Token> tokens, List<TokenType> expectedTypes) {
        assertEquals(expectedTypes.size(), tokens.size());

        for (var i = 0; i < expectedTypes.size(); i++) {
            assertEquals(expectedTypes.get(i), tokens.get(i).type(), "token index " + i);
        }
    }
}

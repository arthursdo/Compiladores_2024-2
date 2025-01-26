PARSER_BEGIN(LugosiParser)
import java.io.*;

public class LugosiParser {
    public static void main(String[] args) throws ParseException,IOException {
        try {
            LugosiParser parser = new LugosiParser(new FileInputStream(args[0]));
            parser.LugosiParser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

PARSER_END(LugosiParser)

SKIP : {
    " " 
    | "\t" 
    | "\n" 
    | "\r" 
}

TOKEN : { 
   <VOID: "void">
 | <MAIN: "main">
 | <LET: "let">
 | <FLOAT: "float">
 | <BOOL: "bool">
 | <IF: "if">
 | <WHILE: "while">
 | <DO: "do">
 | <READIO: "readIO">
 | <PRINTIO: "printIO">
 | <RETURN: "return">
 | <TRUE: "true">
 | <FALSE: "false">
 | <MINUS: "-">
 | <DIV: "/">
 | <AND: "&&">
 | <OR: "||">
 | <LT: "<">
 | <GT: ">">
 | <EQUALS: "=="> 
}
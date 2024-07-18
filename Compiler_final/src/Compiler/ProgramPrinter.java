package Compiler;

import gen.japyListener;
import gen.japyParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Stack;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProgramPrinter implements japyListener {
    //Phase1
    static int INDETATION_LEVEL = 0;

    private void addToOutput(String line) {
        output.add(line);
    }

    public String indentation(int levelIndent) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < levelIndent; i++) {
            output.append("    ");
        }
        return output.toString();
    }

    private final Stack<BreakContinue> breakContinueStack = new Stack<>();
    private final ArrayList<String> output = new ArrayList<>();
    //****//****//****//****//****//****//****//****//****

    //Phase2//
    ArrayList<Scope> Scopes = new ArrayList<>();
    static Stack<String> WhereScope = new Stack<String>();
    static int elifcounter = 1;
    static int loop_cond = 1;
    private Scope foundScope;
    Map<String, Scope> scopeMap = new HashMap<>(); // To store scopes by their name

    //****//****//****//****//****//****//****//****//****

    //Phase3
    public ArrayList<String> definedErrors = new ArrayList<>();
    public ArrayList<String> returnErrors = new ArrayList<>();
    public ArrayList<String> methodParametersErrors = new ArrayList<>();
    public ArrayList<String> arrayErrors = new ArrayList<>();
    public ArrayList<String> typeErrors = new ArrayList<>();
    public ArrayList<String> inheritloopsErrors = new ArrayList<>();
    public ArrayList<String> notDefinedErrors = new ArrayList<>();

    public String definedErrors(int error, int line, int column, String name) {
        String type;
        if (error == 102) {
            type = "Function";
        } else if (error == 104) {
            type = "Field";
        } else {
            type = "Class";
        }
        return "Error" + error + ": in line [" + line + ":" + column + "], " + type + " " + name + " has been defined already";
    }

    public String returnErrors(int error, int line, int column, String name) {
        return "Error" + "210" + ": in line [" + line + ":" + column + "], ReturnType of this method must be " + name;
    }


    public String methodParametersErrors(int error, int line, int column, String name) {
        return "Error" + error + ": in line [" + line + ":" + column + "], function " + name + "Parameters type/count dont match  ";
    }

    public String arrayErrors(int error, int line, int column, String name) {
        return "Error" + error + ": in line [" + line + ":" + column + "], array " + name + " index are out of range/not int";
    }

    public String typeErrors(int error, int line, int column) {
        return "Error" + error + " in line [" + line + ":" + column + "], " + "rvalue type does not match the lvalue";
    }

    public String inheritloopsErrors(int error, int line, int column) {
        return "Error" + error + " in line [" + line + ":" + column + "], " + "loop in inheritence";
    }

    public String notDefinedErrors(int error, int line, int column, String name) {
        return "Error" + error + " in line [" + line + ":" + column + "], field/var: " + name + " not defiend";
    }

    public String fieldRedefinition(String name, int line, int column) {
        return "field_" + name + "_" + line + "_" + column;
    }

    public String methodRedefinition(String name, int line, int column) {
        return "function_" + name + "_" + line + "_" + column;
    }

    public String classRedefinition(String name, int line, int column) {
        return "class_" + name + "_" + line + "_" + column;
    }

    public static boolean isStringLiteral(String str) {
        return str.startsWith("\"") && str.endsWith("\"");
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return str.contains(".");
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isBoolean(String str) {
        return "true".equalsIgnoreCase(str) || "false".equalsIgnoreCase(str);
    }

    public static String extractArrayName(String input) {
        // Find the index of '['
        int index = input.indexOf('[');

        // If '[' exists, extract the substring before it
        if (index != -1) {
            return input.substring(0, index);
        } else {
            // If '[' does not exist, throw an exception or handle as needed
            throw new IllegalArgumentException("Invalid array format: " + input);
        }
    }

    public static String extractMethodName(String input) {
        String regex = "(?:this\\.)?(\\w+)(?:\\([^)]*\\))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }

    public static int countArgumentsInFunction(String input) {
        // Define the regex pattern to match function calls with optional arguments
        String regex = "\\b\\w+\\s*\\(([^)]*)\\)";

        // Compile the pattern
        Pattern pattern = Pattern.compile(regex);

        // Initialize total argument count
        int totalArguments = 0;

        // Create a matcher for the input string
        Matcher matcher = pattern.matcher(input);

        // Iterate through matches
        while (matcher.find()) {
            // Extract the arguments part
            String argumentsPart = matcher.group(1).trim();

            // If arguments part is not empty, split arguments by commas and count them
            if (!argumentsPart.isEmpty()) {
                String[] arguments = argumentsPart.split(",");
                totalArguments += arguments.length;
            }
        }

        return totalArguments;
    }

    public static Integer extractNumber(String input) {
        // Define the regex pattern to match a number inside square brackets
        String regex = "\\[(\\d+)\\]";

        // Compile the pattern
        Pattern pattern = Pattern.compile(regex);

        // Create a matcher for the input string
        Matcher matcher = pattern.matcher(input);

        // Check if the pattern is found
        if (matcher.find()) {
            // Extract the number string
            String numberStr = matcher.group(1);
            try {
                // Attempt to parse the number string to an integer
                return Integer.parseInt(numberStr);
            } catch (NumberFormatException e) {
                // If parsing fails (numberStr is not a valid integer), return null
                return null;
            }
        } else {
            // If no number is found, return null
            return null;
        }
    }
    //****//****//****//****//****//****//****//****//****


    @Override
    public void enterProgram(japyParser.ProgramContext ctx) {
        //PHASE2
        WhereScope.push("Program");
        Scope programScope = new Scope(ctx.start.getLine(), ctx.stop.getLine(), "Program", Types.Program, "");
        Scopes.add(programScope);
        //****//****//****//****//****//****//****//****//****
    }

    @Override
    public void exitProgram(japyParser.ProgramContext ctx) {
        //PHASE1
        int linenum = 1;
        System.out.print("PHASE1: \n \n");
        for (String line : output) {
            System.out.print(linenum + " ");
            System.out.println(line);
            linenum++;
        }
        System.out.print("END OF PHASE1 \n \n");

        //****//****//****//****//****//****//****//****//****

        //PHASE2
//                System.out.print("PHASE2: \n \n");
//        WhereScope.pop();
//        for (Scope scope : Scopes) {
//            if (scope.getType().equals(Types.Program)) {
//                System.out.println(scope.toString());
//
//            }
//        }
//        for (Scope scope : Scopes) {
//            if (scope.getType().equals(Types.Class)) {
//                System.out.println(scope.toString());
//
//            }
//        }
//        for (Scope scope : Scopes) {
//            if (scope.getType().equals(Types.Function)) {
//                System.out.println(scope.toString());
//
//            }
//        }
//        for (Scope scope : Scopes) {
//            if (!scope.getType().equals(Types.Function) && !scope.getType().equals(Types.Class) && !scope.getType().equals(Types.Program)) {
//                System.out.println(scope.toString());
//
//            }
//        }
//        System.out.print("END OF PHASE2 \n \n");
//
        //****//****//****//****//****//****//****//****//****
        //PHASE3:
        for (Scope scope : Scopes) {
            if (scope.getType() == Types.Class && scope.hasCircularInheritance(scopeMap)) {
                inheritloopsErrors.add(inheritloopsErrors(300, scope.getSline(), 0));
            }
        }
        System.out.print("PHASE3: \n \n");
        for (String line : definedErrors) {
            System.out.println(line);
        }
        for (String line : returnErrors) {
            System.out.println(line);
        }
        for (String line : methodParametersErrors) {
            System.out.println(line);
        }
        for (String line : arrayErrors) {
            System.out.println(line);
        }
        for (String line : typeErrors) {
            System.out.println(line);
        }
        for (String line : inheritloopsErrors) {
            System.out.println(line);
        }
        for (String line : notDefinedErrors) {
            System.out.println(line);
        }
// Phase 3: Check for circular inheritance

        for (String error : inheritloopsErrors) {
            System.out.println(error);
        }
        System.out.print("END OF PHASE3 \n \n");

        //****//****//****//****//****//****//****//****//****


    }

    @Override
    public void enterEntryClassDeclaration(japyParser.EntryClassDeclarationContext ctx) {

    }

    @Override
    public void exitEntryClassDeclaration(japyParser.EntryClassDeclarationContext ctx) {
        //System.out.println("</class>");
    }

    @Override
    public void enterFieldDeclaration(japyParser.FieldDeclarationContext ctx) {
        ArrayList<String> addnames = new ArrayList<>();
        String accessModifier = ctx.access_modifier() != null ? ctx.access_modifier().getText() : "";
        String fieldType = ctx.japyType().getText();
        String fieldName = ctx.ID(0).getText();
        StringBuilder additionalFieldName = new StringBuilder();
//        System.out.println(indentation(INDETATION_LEVEL) + fieldName + ": (field, " + accessModifier + ", " + fieldType + ")");
        for (int i = 1; i < ctx.ID().size(); i++) {
            additionalFieldName.append(ctx.ID(i).getText()).append(", ");
            addnames.add(ctx.ID(i).getText());
        }

        addToOutput(indentation(INDETATION_LEVEL) + fieldName + (additionalFieldName.length() > 0 ? ", " + additionalFieldName.toString() : "") + ": (field, " + accessModifier + ", " + fieldType + ")");
        //****//****//****//****//****//****//****//****//****

        //PHASE2
        String key = "field_" + fieldName;
        String val = "(name: " + fieldName + ")(" + "accessModifier: " + accessModifier + ")(type: " + fieldType + ")";


        Scope foundScope = null;
        String TopOfStack = WhereScope.peek();
        for (Scope scope : Scopes) {
            if (scope.getName().equals(TopOfStack)) {
                foundScope = scope;
                break;
            }
        }
        if (foundScope.fieldExists(fieldName)) {
            key = fieldRedefinition(fieldName, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
            definedErrors.add(definedErrors(104, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), fieldName));
        }
        SymbolItems value = new SymbolItems(ctx.start.getLine(), val);
        foundScope.insert(key, value);


        for (String ff : addnames) {
            key = "field_" + ff;
            val = "(name: " + fieldName + ")(" + "accessModifier: " + accessModifier + ")(type: " + fieldType + ")";
            value = new SymbolItems(ctx.start.getLine(), val);
            if (foundScope.fieldExists(ff)) {
                key = fieldRedefinition(ff, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
                definedErrors.add(definedErrors(104, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ff));

            }
            foundScope.insert(key, value);

            //****//****//****//****//****//****//****//****//****

            //PHASE3


            //****//****//****//****//****//****//****//****//****


        }
    }

    @Override
    public void enterAccess_modifier(japyParser.Access_modifierContext ctx) {

    }

    @Override
    public void exitFieldDeclaration(japyParser.FieldDeclarationContext ctx) {

    }

    @Override
    public void exitAccess_modifier(japyParser.Access_modifierContext ctx) {

    }

    @Override
    public void enterMethodDeclaration(japyParser.MethodDeclarationContext ctx) {
        //PHASE1
        String accessModifier = ctx.access_modifier() != null ? ctx.access_modifier().getText() : "public";
        String methodName = ctx.methodName.getText();
        String returnType = ctx.t.getText();
        StringBuilder params = new StringBuilder();
        if (ctx.param1 != null) {
            params.append("(")
                    .append(ctx.param1.getText()).append(", ").append(ctx.typeP1.getText()).append(")");

            for (int i = 0; i < ctx.japyType().size() - 2; i++) {
                params.append(", (").append(ctx.param2.getText())
                        .append(", ").append(ctx.typeP2.getText()).append(")");
            }
        }

        addToOutput(indentation(INDETATION_LEVEL) + "<function '" + methodName + "', " + accessModifier + ", parameters: [" + params + "]>");
        INDETATION_LEVEL++;
        //****//****//****//****//****//****//****//****//****

        //PHASE2
        StringBuilder Params = new StringBuilder();
        if (ctx.param1 != null) {
            Params.append("(index: 0 ),(").append("name: " + ctx.param1.getText() + "),(type: ")
                    .append(ctx.typeP1.getText()).append(")");

            for (int i = 2; i < ctx.japyType().size(); i++) {
                Params.append("[(index: " + (i - 1) + "),(").append("name: " + ctx.param2.getText() + "),(type: ")
                        .append(ctx.typeP2.getText()).append(")]");
            }

        }

        String key = "function_" + methodName;
        String val = "(name: " + methodName + ")(" + "accessModifier: " + accessModifier + ")(return: " + returnType + ")\n" + "Parameters[" + Params + "]";
        SymbolItems value = new SymbolItems(ctx.start.getLine(), val);
        value.setMethodParametersCount(ctx.japyType().size() - 1);
        Scope foundScope = null;
        String TopOfStack = WhereScope.peek();
        for (Scope scope : Scopes) {
            if (scope.getName().equals(TopOfStack)) {
                foundScope = scope;
                break;
            }
        }
//PHASE3
        if (foundScope.methodExists(methodName)) {
            key = methodRedefinition(methodName, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
            definedErrors.add(definedErrors(102, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), methodName));
            methodName = "function_" + methodName + "_" + ctx.getStart().getLine() + "_" + ctx.getStart().getCharPositionInLine();
        }
        foundScope.insert(key, value);
        Scope functionScope = new Scope(ctx.start.getLine(), ctx.stop.getLine(), methodName, Types.Function, returnType);
        for (Scope scope : Scopes) {
            if (scope.name.equals(WhereScope.peek())) {
                scope.setChild(functionScope);
                functionScope.setParent(scope);
            }
        }

        //****//****//****//****//****//****//****//****//****

        WhereScope.push(methodName);
        Scopes.add(functionScope);
        //****//****//****//****//****//****//****//****//****


    }

    @Override
    public void exitMethodDeclaration(japyParser.MethodDeclarationContext ctx) {
        String returnType;
        if (ctx.t != null) {
            returnType = ctx.t.getText();
        } else {
            returnType = " ";
        }
        //PHASE1
        String actualReturnType = ctx.s.s1.s6 != null ? ctx.s.s1.s6.expression().getText() : " ";

        INDETATION_LEVEL--;
        addToOutput(indentation(INDETATION_LEVEL) + "</function return (" + actualReturnType + ", " + returnType + ")>");

        //****//****//****//****//****//****//****//****//****

        //PHASE2
        WhereScope.pop();
        //****//****//****//****//****//****//****//****//****


    }

    @Override
    public void enterClosedStatement(japyParser.ClosedStatementContext ctx) {
        //PHASE2
        if (ctx.parent instanceof japyParser.ClosedConditionalContext) {
            japyParser.ClosedConditionalContext conditionalContext = (japyParser.ClosedConditionalContext) ctx.parent;
            String conditionalType;
            Types type;
            if (conditionalContext.ifStat == ctx) {
                conditionalType = "if";
                elifcounter = 1;
                type = Types.If;
                //phase1
                addToOutput(indentation(INDETATION_LEVEL) + "<if condition: <" + conditionalContext.ifExp.getText() + ">>");
                INDETATION_LEVEL++;
                //
            } else if (conditionalContext.elifStat != null && elifcounter < conditionalContext.expression().size()) {
                conditionalType = "elif";
                elifcounter++;
                type = Types.Elif;
                WhereScope.pop();
                //phase1
                addToOutput(indentation(INDETATION_LEVEL - 1) + "<elif condition: <" + conditionalContext.elifExp.getText() + ">>");
                //
            } else if (conditionalContext.elseStmt == ctx) {
                WhereScope.pop();
                conditionalType = "else";
                type = Types.Else;

                //phase1
                addToOutput(indentation(INDETATION_LEVEL - 1) + "<else>");
                //
            } else {
                WhereScope.pop();
                conditionalType = "unknown";
                type = Types.Else;
            }
//       System.out.println("Entering " + conditionalType + " block at line " + ctx.start.getLine());
            String name = conditionalType + loop_cond;
            loop_cond++;
            Scope ifScope = new Scope(ctx.start.getLine() - 1, ctx.stop.getLine(), name, type, "");
            for (Scope scope : Scopes) {
                if (scope.name.equals(WhereScope.peek())) {
                    scope.setChild(ifScope);
                    ifScope.setParent(scope);
                }
            }
            WhereScope.push(name);
            Scopes.add(ifScope);
        } else if (ctx.parent instanceof japyParser.StatementClosedLoopContext) {
            japyParser.StatementClosedLoopContext whileContext = (japyParser.StatementClosedLoopContext) ctx.parent;
            String conditionalType = "while";
            Types type = Types.While;
//    System.out.println("Entering " + conditionalType + " block at line " + ctx.start.getLine());
            String name = conditionalType + loop_cond;
            loop_cond++;
            Scope whileScope = new Scope(ctx.start.getLine(), ctx.stop.getLine(), name, type, "");
            for (Scope scope : Scopes) {
                if (scope.name.equals(WhereScope.peek())) {
                    scope.setChild(whileScope);
                    whileScope.setParent(scope);
                }
            }
            WhereScope.push(name);
            Scopes.add(whileScope);
        }
        //****//****//****//****//****//****//****//****//****

    }

    @Override
    public void exitClosedStatement(japyParser.ClosedStatementContext ctx) {

    }

    @Override
    public void enterClosedConditional(japyParser.ClosedConditionalContext ctx) {


    }

    @Override
    public void exitClosedConditional(japyParser.ClosedConditionalContext ctx) {

        //PHASE1
        INDETATION_LEVEL--;
        addToOutput(indentation(INDETATION_LEVEL) + "</else>");


        //****//****//****//****//****//****//****//****//****
        WhereScope.pop();

//        boolean hasElse = ctx.elseStmt != null;
//
//        if (hasElse) {
//            addToOutput(indentation(INDETATION_LEVEL) + "</else>");
//        } else {
//            addToOutput(indentation(INDETATION_LEVEL) + "</if>");
//            //addToOutput(indentation(INDETATION_LEVEL) + "<else>");
//            INDETATION_LEVEL++;
//        }


//      for (int i = 0; i < ctx.elifExp().size(); i++) {
//        addToOutput("<elif condition: <" + ctx.elifExp(i).getText() + ">>");
//        INDETATION_LEVEL++;
//        INDETATION_LEVEL--;
//      }


    }

    @Override
    public void enterOpenConditional(japyParser.OpenConditionalContext ctx) {
        addToOutput(indentation(INDETATION_LEVEL) + "<if condition: <" + ctx.ifExp.getText() + ">>");
        INDETATION_LEVEL++;

//        Types type = Types.If; // Assuming it's an "if" block; adjust as per your grammar structure
//
//        String name = "if" + loop_cond; // Adjust loop_cond as needed
//        loop_cond++;
//        Scope ifScope = new Scope(ctx.start.getLine(), ctx.stop.getLine(), name, type);
//        WhereScope.push(name);
//        Scopes.add(ifScope);
    }

    @Override
    public void exitOpenConditional(japyParser.OpenConditionalContext ctx) {
        //PHASE1
        INDETATION_LEVEL--;
        addToOutput(indentation(INDETATION_LEVEL) + "</if>");


        //PHASE2
        WhereScope.pop();
    }

    @Override
    public void enterOpenStatement(japyParser.OpenStatementContext ctx) {

        //PHASE2
        String conditionalType;
        Types type;
        conditionalType = "if";
        type = Types.If;
        String name = conditionalType + loop_cond;
        loop_cond++;
        Scope ifScope = new Scope(ctx.start.getLine() - 1, ctx.stop.getLine(), name, type, "");
        for (Scope scope : Scopes) {
            if (scope.name.equals(WhereScope.peek())) {
                scope.setChild(ifScope);
                ifScope.setParent(scope);
            }
        }
        WhereScope.push(name);
        Scopes.add(ifScope);
        //****//****//****//****//****//****//****//****//****

    }

    @Override
    public void exitOpenStatement(japyParser.OpenStatementContext ctx) {

    }

    @Override
    public void enterStatement(japyParser.StatementContext ctx) {

    }

    @Override
    public void enterStatementVarDef(japyParser.StatementVarDefContext ctx) {
        //PHASE1
        String varName = ctx.i1.getText();
        String varValue = ctx.expression(0).getText();
        addToOutput(indentation(INDETATION_LEVEL) + varValue + " -> (" + varName + ", var )");
        //****//****//****//****//****//****//****//****//****

        //PHASE2
        String key = "var_" + varName;
        String val = "(name: " + varName + ")(" + "first appearance: " + ctx.start.getLine() + ")";
        SymbolItems value = new SymbolItems(ctx.start.getLine(), val);

        String type = null;
        if (isDouble(varValue)) {
            type = "double";
            value.setVariableType(type);
        } else if (isBoolean(varValue)) {
            type = "bool";
            value.setVariableType(type);

        }
        if (isStringLiteral(varValue)) {
            type = "string";
            value.setVariableType(type);

        }
        if (isInteger(varValue)) {
            type = "int";
            value.setVariableType(type);

        } else if (varValue.charAt(varValue.length() - 1) == ']') {
            if (extractNumber(varValue) != null) {
                type = "array";
                value.setVariableType(type);
                value.setArrayIndexCount(extractNumber(varValue));
            } else {
                arrayErrors.add(arrayErrors(410, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), varName));
            }
        }
        Scope foundScope = null;
        String TopOfStack = WhereScope.peek();
        for (Scope scope : Scopes) {
            if (scope.getName().equals(TopOfStack)) {
                foundScope = scope;
                break;
            }
        }
        foundScope.insert(key, value);
        //****//****//****//****//****//****//****//****//****
        //PHASE3
        int count;
        if (varValue.charAt(varValue.length() - 1) == ')') {
            count = countArgumentsInFunction(varValue);

            for (Scope scope : Scopes) {
                if (scope.name.equals(WhereScope.peek())) {
                    Scope classScope = scope.getParentUntilClass();

                    if (classScope.searchFunctionInCurrentScope(extractMethodName(varValue)).getMethodParametersCount() != count) {
                        methodParametersErrors.add(methodParametersErrors(310, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), extractMethodName(varValue)));
                    }
                }
            }


        }

        //****//****//****//****//****//****//****//****//****


    }

    @Override
    public void exitStatement(japyParser.StatementContext ctx) {

    }

    @Override
    public void exitStatementVarDef(japyParser.StatementVarDefContext ctx) {

    }

    @Override
    public void enterStatementBlock(japyParser.StatementBlockContext ctx) {

    }

    @Override
    public void exitStatementBlock(japyParser.StatementBlockContext ctx) {

    }

    @Override
    public void enterStatementContinue(japyParser.StatementContinueContext ctx) {
        BreakContinue top = breakContinueStack.peek();
        int line = top.getSline();
        addToOutput(indentation(INDETATION_LEVEL) + "goto " + line);
    }

    @Override
    public void exitStatementContinue(japyParser.StatementContinueContext ctx) {

    }

    @Override
    public void enterStatementBreak(japyParser.StatementBreakContext ctx) {
        //PHASE1
        BreakContinue top = breakContinueStack.peek();
        int line = top.getEline();
        addToOutput(indentation(INDETATION_LEVEL) + "goto ");
    }

    @Override
    public void exitStatementBreak(japyParser.StatementBreakContext ctx) {

    }

    @Override
    public void enterStatementReturn(japyParser.StatementReturnContext ctx) {
        //PHASE3
        String exp = ctx.e.getText();
        String exptype;
        String actualReturnType = "";
        if (isBoolean(exp)) {
            exptype = "bool";
        } else if (isStringLiteral(exp)) {
            exptype = "string";
        } else if (isDouble(exp)) {
            exptype = "double";
        } else if (isInteger(exp)) {
            exptype = "int";
        } else {
// for (Scope scope : Scopes) {
//            if (scope.name.equals(WhereScope.peek())) {
//                SymbolItems obj = scope.hashtable.get("var_"+exp);
//                if (obj != null) {
//                    String value = obj.value;
//                    int startIndex = value.indexOf("return:") + "return:".length();
//                    int endIndex = value.indexOf(")", startIndex);
//                    if (startIndex != -1 && endIndex != -1) {
//                        exptype= value.substring(startIndex, endIndex).trim();
//                    }
//                }
//            }
//        }
            exptype = "var";
        }
        for (Scope scope : Scopes) {
            if (scope.name.equals(WhereScope.peek())) {
                actualReturnType = scope.getReturnType();
                break;
            }
        }
        if (!exptype.equals(actualReturnType) && exptype != "var") {
            int column = ctx.start.getCharPositionInLine();
            returnErrors.add(returnErrors(210, ctx.start.getLine(), column, actualReturnType));
        }

        //****//****//****//****//****//****//****//****//****

    }

    @Override
    public void exitStatementReturn(japyParser.StatementReturnContext ctx) {
        //PHASE1
        String returnValue = ctx.e.getText();
        addToOutput(indentation(INDETATION_LEVEL) + "return " + returnValue);
    }

    @Override
    public void exitStatementClosedLoop(japyParser.StatementClosedLoopContext ctx) {
        //PHASE1
        INDETATION_LEVEL--;
        addToOutput(indentation(INDETATION_LEVEL) + "</while>");
        for (int i = 0; i < output.size(); i++) {
            String str = output.get(i);
            if (str.trim().equals("goto")) {
                output.set(i, str + ((output.size()) + 1));

            }
        }
        breakContinueStack.pop();
        //PHASE2
        WhereScope.pop();

    }

    @Override
    public void enterStatementOpenLoop(japyParser.StatementOpenLoopContext ctx) {
        //PHASE1

        addToOutput(indentation(INDETATION_LEVEL) + "<while condition: <" + ctx.e.getText() + ">>");
        INDETATION_LEVEL++;
        BreakContinue brk = new BreakContinue(output.size());
        breakContinueStack.push(brk);
    }

    @Override
    public void enterStatementClosedLoop(japyParser.StatementClosedLoopContext ctx) {
        //PHASE1
        addToOutput(indentation(INDETATION_LEVEL) + "<while condition: <" + ctx.e.getText() + ">>");
        INDETATION_LEVEL++;
        BreakContinue brk = new BreakContinue(output.size());
        breakContinueStack.push(brk);
    }

    @Override
    public void exitStatementOpenLoop(japyParser.StatementOpenLoopContext ctx) {
        //PHASE1
        INDETATION_LEVEL--;
        addToOutput(indentation(INDETATION_LEVEL) + "</while>");
        breakContinueStack.pop();
        BreakContinue top = breakContinueStack.peek();
        top.setEline(output.size());
        breakContinueStack.pop();
        //PHASE2
        WhereScope.pop();

    }

    @Override
    public void enterStatementWrite(japyParser.StatementWriteContext ctx) {

    }

    @Override
    public void enterStatementAssignment(japyParser.StatementAssignmentContext ctx) {
        //PHASE1
        String varValue = ctx.expression(1).getText();
        String varName = ctx.expression(0).getText();
        addToOutput(indentation(INDETATION_LEVEL) + varValue + " -> " + varName);

        //PHASE3
        for (Scope scope : Scopes) {
            if (scope.name.equals(WhereScope.peek())) {
                if (scope.hashtable.get("var_" + varName) != null) {
                    String type = scope.hashtable.get("var_" + varName).variableType;
                    String type2 = null;
                    if (isBoolean(varValue)) {
                        type2 = "bool";
                    } else if (isStringLiteral(varValue)) {
                        type2 = "string";
                    } else if (isDouble(varValue)) {
                        type2 = "double";
                    } else if (isInteger(varValue)) {
                        type2 = "int";
                    }
                    if (type2 != type)
                        typeErrors.add(typeErrors(510, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()));

                } else if (!varName.contains("[") && !varName.startsWith("this.") && scope.getParent().hashtable.get("var_" + varName) == null && scope.getParent().getParent().hashtable.get("var_" + varName) == null) {
                    notDefinedErrors.add(notDefinedErrors(610, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), varName));
                }
                if (varName.contains("[")) {
                    Scope currentScope = scope;
                    String name = extractArrayName(varName);
                    int orgIndex;
                    while (currentScope.getParent() != null) {

                        if (currentScope.hashtable.get("var_" + name) != null) {
                            if (currentScope.hashtable.get("var_" + name).variableType == "array") break;
                        }
                        currentScope = currentScope.getParent();
                    }
                    orgIndex = currentScope.hashtable.get("var_" + name).getArrayIndexCount();
                    if (extractNumber(varName) != null) {
                        if (orgIndex <= extractNumber(varName))
                            arrayErrors.add(arrayErrors(410, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), varName));
                    }
                    if (extractNumber(varName) == null) {
                        arrayErrors.add(arrayErrors(410, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), varName));

                    }
                }

            }

        }


        //****//****//****//****//****//****//****//****//****
    }

    @Override
    public void exitStatementAssignment(japyParser.StatementAssignmentContext ctx) {

    }

    @Override
    public void exitStatementWrite(japyParser.StatementWriteContext ctx) {
        //PHASE1
        String message = ctx.e.getText();
        addToOutput(indentation(INDETATION_LEVEL) + "sout(" + message + ")");
    }

    @Override
    public void enterStatementInc(japyParser.StatementIncContext ctx) {
        //PHASE1
        String varName = ctx.expression().getText();
        addToOutput(indentation(INDETATION_LEVEL) + "1 + " + varName + " -> " + varName);
    }

    @Override
    public void exitStatementInc(japyParser.StatementIncContext ctx) {

    }

    @Override
    public void enterStatementDec(japyParser.StatementDecContext ctx) {
        //PHASE1
        String varName = ctx.expression().getText();
        addToOutput(indentation(INDETATION_LEVEL) + varName + "- 1 " + " -> " + varName);
    }

    @Override
    public void exitStatementDec(japyParser.StatementDecContext ctx) {

    }

    @Override
    public void enterExpression(japyParser.ExpressionContext ctx) {

    }

    @Override
    public void enterExpressionOr(japyParser.ExpressionOrContext ctx) {

    }

    @Override
    public void exitExpression(japyParser.ExpressionContext ctx) {

    }

    @Override
    public void enterExpressionOrTemp(japyParser.ExpressionOrTempContext ctx) {

    }

    @Override
    public void exitExpressionOr(japyParser.ExpressionOrContext ctx) {

    }

    @Override
    public void exitExpressionOrTemp(japyParser.ExpressionOrTempContext ctx) {

    }

    @Override
    public void enterExpressionAnd(japyParser.ExpressionAndContext ctx) {

    }

    @Override
    public void exitExpressionAnd(japyParser.ExpressionAndContext ctx) {

    }

    @Override
    public void enterExpressionAndTemp(japyParser.ExpressionAndTempContext ctx) {

    }

    @Override
    public void exitExpressionAndTemp(japyParser.ExpressionAndTempContext ctx) {

    }

    @Override
    public void enterExpressionEq(japyParser.ExpressionEqContext ctx) {

    }

    @Override
    public void exitExpressionEq(japyParser.ExpressionEqContext ctx) {

    }

    @Override
    public void enterExpressionEqTemp(japyParser.ExpressionEqTempContext ctx) {

    }

    @Override
    public void exitExpressionEqTemp(japyParser.ExpressionEqTempContext ctx) {

    }

    @Override
    public void enterClassDeclaration(japyParser.ClassDeclarationContext ctx) {
        //PHASE1
        String className = ctx.className.getText();
        String classParent = ctx.classParent != null ? ctx.classParent.getText() : null;
        String accessModifier = ctx.access_modifier() != null ? ctx.access_modifier().getText() : "default";

        addToOutput(indentation(INDETATION_LEVEL) + "<class '" + className + "'" + (accessModifier.equals("default") ? "" : ", " + accessModifier) + (classParent != null ? ", inherits '" + classParent + "'" : "") + ">");
        INDETATION_LEVEL++;
        //****//****//****//****//****//****//****//****//****

        //PHASE2
        String key = "class_" + className;
        String val = "(name: " + className + ")(" + "accessModifier: " + accessModifier + ")" + (classParent != null ? "(inherits: class_" + classParent + ")" : "") + (ctx.getParent() instanceof japyParser.EntryClassDeclarationContext ? "(main)" : "");
        SymbolItems value = new SymbolItems(ctx.start.getLine(), val);
        Scope foundScope = null;
        String TopOfStack = WhereScope.peek();
        for (Scope scope : Scopes) {
            if (scope.getName().equals(TopOfStack)) {
                foundScope = scope;
                break;
            }
        }
        if (foundScope.classExists(className)) {
            key = classRedefinition(className, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
            definedErrors.add(definedErrors(100, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), className));
            className = "class_" + className + "_" + "_" + ctx.getStart().getLine() + "_" + ctx.getStart().getCharPositionInLine();
        }
        foundScope.insert(key, value);
        //////
        Scope classScope = new Scope(ctx.start.getLine(), ctx.stop.getLine(), className, Types.Class, "");
        for (Scope scope : Scopes) {
            if (scope.name.equals(WhereScope.peek())) {
                scope.setChild(classScope);
                classScope.setParent(scope);
            }
        }
        WhereScope.push(className);
        Scopes.add(classScope);
        classScope.setParentClassName(classParent);
        scopeMap.put(className, classScope); // Add to scopeMap
        //****//****//****//****//****//****//****//****//****

    }

    @Override
    public void exitClassDeclaration(japyParser.ClassDeclarationContext ctx) {
        addToOutput(indentation(INDETATION_LEVEL - 1) + "</class> \n");
        INDETATION_LEVEL--;
        WhereScope.pop();


    }

    @Override
    public void enterExpressionCmp(japyParser.ExpressionCmpContext ctx) {

    }

    @Override
    public void exitExpressionCmp(japyParser.ExpressionCmpContext ctx) {

    }

    @Override
    public void enterExpressionCmpTemp(japyParser.ExpressionCmpTempContext ctx) {

    }

    @Override
    public void exitExpressionCmpTemp(japyParser.ExpressionCmpTempContext ctx) {

    }

    @Override
    public void enterExpressionAdd(japyParser.ExpressionAddContext ctx) {

    }

    @Override
    public void exitExpressionAdd(japyParser.ExpressionAddContext ctx) {

    }

    @Override
    public void enterExpressionAddTemp(japyParser.ExpressionAddTempContext ctx) {

    }

    @Override
    public void exitExpressionAddTemp(japyParser.ExpressionAddTempContext ctx) {

    }

    @Override
    public void enterExpressionMultMod(japyParser.ExpressionMultModContext ctx) {

    }

    @Override
    public void exitExpressionMultMod(japyParser.ExpressionMultModContext ctx) {

    }

    @Override
    public void enterExpressionMultModTemp(japyParser.ExpressionMultModTempContext ctx) {

    }

    @Override
    public void exitExpressionMultModTemp(japyParser.ExpressionMultModTempContext ctx) {

    }

    @Override
    public void enterExpressionUnary(japyParser.ExpressionUnaryContext ctx) {

    }

    @Override
    public void exitExpressionUnary(japyParser.ExpressionUnaryContext ctx) {

    }

    @Override
    public void enterExpressionMethods(japyParser.ExpressionMethodsContext ctx) {

    }

    @Override
    public void exitExpressionMethods(japyParser.ExpressionMethodsContext ctx) {

    }

    @Override
    public void enterExpressionMethodsTemp(japyParser.ExpressionMethodsTempContext ctx) {

    }

    @Override
    public void exitExpressionMethodsTemp(japyParser.ExpressionMethodsTempContext ctx) {

    }

    @Override
    public void enterExpressionOther(japyParser.ExpressionOtherContext ctx) {

    }

    @Override
    public void exitExpressionOther(japyParser.ExpressionOtherContext ctx) {

    }

    @Override
    public void enterJapyType(japyParser.JapyTypeContext ctx) {

    }

    @Override
    public void exitJapyType(japyParser.JapyTypeContext ctx) {

    }

    @Override
    public void enterSingleType(japyParser.SingleTypeContext ctx) {

    }

    @Override
    public void exitSingleType(japyParser.SingleTypeContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {

    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }
}

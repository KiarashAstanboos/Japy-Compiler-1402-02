package Compiler;

import java.sql.Struct;

public class SymbolItems {
    int line;
    String value;
    int methodParametersCount;
    String arrayType;
    int arrayIndexCount;
    String variableType;

    public String getVariableType() {
        return variableType;
    }

    public void setVariableType(String variableType) {
        this.variableType = variableType;
    }

    public int getMethodParametersCount() {
        return methodParametersCount;
    }

    public int getArrayIndexCount() {
        return arrayIndexCount;
    }

    public String getArrayType() {
        return arrayType;
    }

    public void setMethodParametersCount(int methodParametersCount) {
        this.methodParametersCount = methodParametersCount;
    }

    public void setArrayIndexCount(int arrayIndexCount) {
        this.arrayIndexCount = arrayIndexCount;
    }

    public void setArrayType(String arrayType) {
        this.arrayType = arrayType;
    }

    public SymbolItems(int line, String name) {
        setLine(line);
        setValue(name);
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getValue() {
        return value;
    }

    public int getLine() {
        return line;
    }

    @Override
    public String toString() {
        return value;
    }
}

package Compiler;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class Scope {
    int Sline;
    int Eline;
    String name;
    Types type;
    String returnType;
    Hashtable<String, SymbolItems> hashtable = new Hashtable<>();
    Scope parent;
    Scope child;
    String parentClassName = null;

    public Scope(int line, int end, String name, Types type, String returnType) {
        setSline(line);
        setEline(end);
        setName(name);
        setType(type);
        setReturnType(returnType);
    }

    public void setParentClassName(String parentClassName) {
        this.parentClassName = parentClassName;
    }

    public String getParentClassName() {
        return parentClassName;
    }

    public boolean hasCircularInheritance(Map<String, Scope> scopes) {
        Set<String> visited = new HashSet<>();
        String currentClass = this.name;
        while (currentClass != null) {
            if (visited.contains(currentClass)) {
                return true; //
            }
            visited.add(currentClass);
            Scope parentScope = scopes.get(currentClass);
            if (parentScope != null) {
                currentClass = parentScope.getParentClassName();
            } else {
                currentClass = null;
            }
        }
        return false;
    }

    public void setChild(Scope child) {
        this.child = child;
    }

    public void setParent(Scope parent) {
        this.parent = parent;
    }

    public Scope getChild() {
        return child;
    }

    public Scope getParent() {
        return parent;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setSline(int sline) {
        this.Sline = sline;
    }

    public void setEline(int sline) {
        this.Eline = sline;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public int getSline() {
        return Sline;
    }

    public int getEline() {
        return Eline;
    }

    public String getName() {
        return name;
    }

    public Types getType() {
        return type;
    }

    public Hashtable<String, SymbolItems> getHashtable() {
        return hashtable;
    }

    public void insert(String key, SymbolItems value) {
        hashtable.put(key, value);
    }

    public SymbolItems lookup(String key) {
        return hashtable.get(key);
    }

    public SymbolItems searchFunctionInCurrentScope(String functionName) {
        return lookup("function_" + functionName);
    }

    @Override
    public String toString() {
        return "------------- SCOPE TYPE: " + this.type + "-----" + getName() + " :(" + getSline() + "," + getEline() + ") -------------\n" +
                (printItems() == "" ? "!NO KEY FOUND!\n" : printItems()) +
                "-----------------------------------------\n";
    }

    public String printItems() {
        String itemsStr = "";
        for (Map.Entry<String, SymbolItems> entry : hashtable.entrySet()) {
            itemsStr += "Key = " + entry.getKey() + " | Value = " + entry.getValue()
                    + "\n";
        }
        return itemsStr;
    }

    public boolean methodExists(String methodName) {
        for (Map.Entry<String, SymbolItems> entry : hashtable.entrySet()) {
            if (entry.getKey().startsWith("function_")) {
                String funcName = entry.getKey().substring("function_".length());
                if (funcName.equals(methodName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean fieldExists(String fieldName) {
        for (Map.Entry<String, SymbolItems> entry : hashtable.entrySet()) {
            if (entry.getKey().startsWith("field_")) {
                String fName = entry.getKey().substring("field_".length());
                if (fName.equals(fieldName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean classExists(String className) {
        for (Map.Entry<String, SymbolItems> entry : hashtable.entrySet()) {
            if (entry.getKey().startsWith("class_")) {
                String fName = entry.getKey().substring("class_".length());
                if (fName.equals(className)) {
                    return true;
                }
            }
        }
        return false;

    }

    public Scope getParentUntilClass() {
        Scope currentScope = this.parent; // Start with the immediate parent
        while (currentScope != null && currentScope.type != Types.Class) {
            currentScope = currentScope.parent;
        }
        return currentScope;
    }

}


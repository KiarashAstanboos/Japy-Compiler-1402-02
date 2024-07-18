package Compiler;

import gen.japyParser;

public class BreakContinue {
    int Sline;
    int Eline;
    public BreakContinue(int Sline)
    {

        setSLine(Sline);
    }



    public void setSLine(int line) {
        this.Sline = line;
    }

    public int getEline() {
        return Eline;
    }

    public void setEline(int eline) {
        Eline = eline;
    }

    public int getSline() {
        return Sline;
    }

}

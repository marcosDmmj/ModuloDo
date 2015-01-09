package com.br.ksg.classesBasicas;

/*
 * Created by Marcos Denis on 08/01/2015.
 */
public class DoisInt implements Comparable<DoisInt>{
    private int i;
    private int ponto;

    public DoisInt(int i, int ponto) {
        this.i = i;
        this.ponto = ponto;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public int compareTo(DoisInt doisInt) {
        if (this.ponto > doisInt.ponto)
            return -1;
        else
            if (this.ponto < doisInt.ponto)
                return 1;
        return 0;
    }
}

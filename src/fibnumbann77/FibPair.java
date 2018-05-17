/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibnumbann77;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class FibPair implements Serializable{
    private int n;
    private int value;

    public FibPair(int n, int value) {
        this.n = n;
        this.value = value;
    }

    public int getN() {
        return n;
    }

    public int getValue() {
        return value;
    }
    
}

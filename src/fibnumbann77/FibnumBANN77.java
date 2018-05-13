/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibnumbann77;

/**
 *
 * @author Gubics Andrea
 */
public class FibnumBANN77 {

    public static int computeFN(int N){
        if(N < 1){
            throw new IllegalArgumentException("Argument must be greater than zero");
        }
        if(N == 1 || N == 2){
            return 1;
        }
        return computeFN(N-1) + computeFN(N-2);
    }
    
}

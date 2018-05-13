/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibnumbann77;

import java.util.List;

/**
 *
 * @author user
 */
public interface FibonacciRepository {
    void save(FibPair pair);
    List<FibPair> load();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibnumbann77;

import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gubics Andrea
 */
public class FibnumBANN77Test {

    @Test
    public void testComputeFN1(){       
        assertThat(FibnumBANN77.computeFN(1), is(1));  
    }
    
    @Test
    public void testComputeFn2(){
         assertThat(FibnumBANN77.computeFN(2), is(1));
    }
    
    @Test
    public void computeFN3(){
         assertThat(FibnumBANN77.computeFN(3), is(2));
    }
    
    @Test
    public void computeFN5(){
         assertThat(FibnumBANN77.computeFN(5), is(5));
    }
    
    @Test
    public void computeFN9(){
         assertThat(FibnumBANN77.computeFN(9), is(34));
    }
    
    @Test
    public void computeFN4(){
         assertThat(FibnumBANN77.computeFN(4), is(3));
    }
    
    @Test
    public void computeFN6(){
         assertThat(FibnumBANN77.computeFN(6), is(8));
    }
    
    @Test
    public void computeFN7(){
         assertThat(FibnumBANN77.computeFN(9), is(13));
    }
    
}

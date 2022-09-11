
import com.mycompany.functionalprogramming.exercise2_1.Function;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author carlos
 */
public class Tests {
    
    static <T, U, V> Function<Function<U, V>, Function<Function<T, U>, Function<T, V>>> compose(){
        return f -> (g -> (x -> (f.apply(g.apply(x)))));
    }
    
    @Test
    public void testHigherCompose(){
        Function<Double, Double> half = x -> x/2;
        Function<Double, Double> triple = x-> x*3;
        assertEquals(15.0,Tests.<Double, Double, Double>compose().apply(triple).apply(half).apply(10.0));
    }
    
}

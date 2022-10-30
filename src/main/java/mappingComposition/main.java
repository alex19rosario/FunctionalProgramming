package mappingComposition;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static loops.CollectionUtilities.list;
import static mappingComposition.Map.map;

public class main {

    public static void main(String[] args){

        ArrayList<Double> salaries = (ArrayList<Double>) list(650.0, 500.0, 850.0, 630.0, 750.0, 950.0, 1500.0, 2000.0, 1350.0, 1300.0);
        Function<Double, Double> addDouble = s -> s*2;
        Function<Double, Double> addBono = s -> s*1.5;
        Function<Double, Double> removeTaxes = s -> s*0.82;

        //WHITOUT USING MAPPING COMPOSITION
        List<Double> salariesWithDouble = map(salaries, addDouble);
        List<Double> salariesWithBono = map(salariesWithDouble, addBono);
        List<Double> salariesWithTaxes = map(salariesWithBono, removeTaxes);

        //WITH MAPPING COMPOSITION
        List<Double> finalSalaries = map(salaries, addDouble.andThen(addBono).andThen(removeTaxes));
    }
}

package conditions;

import com.mycompany.functionalprogramming.exercise2_1.Function;

import static conditions.Case.cond;
import static conditions.Case.mcase;


public class main {

    static Function<Integer, Result<String>> integerChecker = x -> cond(
        mcase(() -> Result.success("negative")),
        mcase(() -> x > 0, () -> Result.failure("positve")),
        mcase(() -> x == 0, () -> Result.failure("zero"))
    );



    public static void main(String[] args){

    }
}

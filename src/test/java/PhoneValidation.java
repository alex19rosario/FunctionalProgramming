import PhoneValidation.Effect;
import PhoneValidation.Result;
import com.mycompany.functionalprogramming.exercise2_1.Function;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

public class PhoneValidation {
    static Pattern phonePattern = Pattern.compile("^[0-9]{3}+-[0-9]{3}+-[0-9]{4}$");
    static Function<String, Result<String>> phoneChecker = s -> s == null?
            Result.failure("Phone must not be null"):
            s.length() == 0?
                    Result.failure("Phone must not be empty"):
                    phonePattern.matcher(s).matches()?
                            Result.success(s):
                            Result.failure("Phone "+ s + " is invalid");

    static Effect<String> success = s -> System.out.println("The number " + s + " has been registered");

    static Effect<String> failure = s -> System.err.println("Error message logged: "+ s);

    @Test
    public void nullScenario(){

    }

}

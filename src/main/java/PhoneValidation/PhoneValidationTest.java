package PhoneValidation;

import com.mycompany.functionalprogramming.exercise2_1.Function;

import java.util.regex.Pattern;

public class PhoneValidationTest {

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

    public static void main(String... args){
        phoneChecker.apply("165151").bind(success, failure);
        phoneChecker.apply(null).bind(success, failure);
        phoneChecker.apply("").bind(success, failure);
        phoneChecker.apply("829-784-2714").bind(success, failure);
    }



}

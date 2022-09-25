package com.mycompany.functionalprogramming.exercise2_1;

import java.util.regex.Pattern;

public class EmailValidation2 {

    static Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

    static Function<String, Result<String>> emailChecker = s -> s == null?
            Result.failure("Email must not be null"):
            s.length() == 0?
                    Result.failure("Email must not be empty"):
                    emailPattern.matcher(s).matches()?
                            Result.success(s):
                            Result.failure("email " + s + " is invalid");
    static Effect<String> success = s -> System.out.println("Mail sent to " + s);
    static Effect<String> failure = s -> System.err.println("Error message logged: " + s);

    public static void main(String... args){
        emailChecker.apply("this.is@my.email").bind(success, failure);
        emailChecker.apply(null).bind(success, failure);
        emailChecker.apply("").bind(success, failure);
        emailChecker.apply("alexrosario@gmail.com").bind(success, failure);
    }


}
@FunctionalInterface
interface Effect<T>{
    void apply(T t);
}
@FunctionalInterface
interface Result<T> {
    void bind(Effect<T> success, Effect<String> failure);

    public static <T> Result<T> failure(String message){
        return new Failure<>(message);
    }

    public static <T> Result<T> success(T value){
        return new Success<>(value);
    }
}

class Success<T> implements Result<T> {

    private final T value;

    public Success(T t){
        value = t;
    }

    @Override
    public void bind(Effect<T> success, Effect<String> failure) {
        success.apply(value);
    }
}

class Failure<T> implements Result<T>{

    private final String errorMessage;

    public Failure(String s){
        errorMessage = s;
    }

    @Override
    public void bind(Effect<T> success, Effect<String> failure) {
        failure.apply(errorMessage);
    }

}

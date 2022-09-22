package com.mycompany.functionalprogramming.exercise2_1;

import java.util.regex.Pattern;

public class EmailValidation {

    static Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

    static Function<String, Result> emailChecker = s -> s == null?
            new Result.Failure("email must be not null"):
            s.length() == 0?
                    new Result.Failure("email must not be empty"):
                    emailPattern.matcher(s).matches()?
                            new Result.Succes():
                            new Result.Failure("email " + s + " is invalid.");

    public static void logError(String s){
        System.err.println("Error message logged: " + s);
    }

    public static void sendVerification(String s){
        System.out.println("Mail sent to " + s);
    }

    static Executable validate(String s){
        Result result = emailChecker.apply(s);
        return (result instanceof Result.Succes)?
                () -> sendVerification(s):
                () -> logError(((Result.Failure)result).getMessage());
    }

    public static void main(String[] args){
        validate("this.is@my.email").exec();
        validate(null).exec();
        validate("").exec();
        validate("john.doe@acme.com").exec();
    }
}

interface Result{

    public class Succes implements Result{}
    public class Failure implements Result{
        private final String errormessage;

        public Failure(String s){
            this.errormessage = s;
        }

        public String getMessage(){
            return errormessage;
        }
    }
}

interface Executable{
    void exec();
}

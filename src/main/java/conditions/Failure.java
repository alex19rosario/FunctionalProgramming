package conditions;

import java.util.function.Consumer;

public class Failure<T> implements Result<T>{

    private final String errormessage;

    public Failure(String s){
        errormessage = s;
    }
    @Override
    public void bind(Consumer<T> success, Consumer<String> failure) {
        failure.accept(errormessage);
    }
}

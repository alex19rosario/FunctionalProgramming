package conditions;

import java.util.function.Consumer;

public class Success<T> implements Result<T>{

    private final T value;

    public Success(T t){
        value = t;
    }
    @Override
    public void bind(Consumer<T> success, Consumer<String> failure) {
        success.accept(value);
    }
}

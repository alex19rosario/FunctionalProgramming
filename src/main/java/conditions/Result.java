package conditions;

import java.util.function.Consumer;

public interface Result<T> {

    void bind (Consumer<T> success, Consumer<String> failure);

    public static<T> Result<T> failure(String message){
        return new Failure<>(message);
    }

    public static<T> Result<T> success(T value){
        return new Success<>(value);
    }
}

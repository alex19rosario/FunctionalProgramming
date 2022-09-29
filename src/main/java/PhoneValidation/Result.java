package PhoneValidation;

@FunctionalInterface
public interface Result<T> {

    void bind(Effect<T> success, Effect<String> failure);

    public static <T> Result<T> failure(String message){

        return new Failure<>(message);
    }

    public static <T> Result<T> success(T value){
        return new Success<>(value);
    }
}

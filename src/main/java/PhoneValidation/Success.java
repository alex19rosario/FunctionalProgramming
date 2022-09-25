package PhoneValidation;

public class Success<T> implements Result<T> {

    private final T value;

    public Success(T t){
        value =t;
    }

    @Override
    public void bind(Effect<T> success, Effect<String> failure) {
        success.apply(value);
    }
}

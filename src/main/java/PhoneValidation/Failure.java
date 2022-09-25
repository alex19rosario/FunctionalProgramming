package PhoneValidation;

public class Failure<T> implements Result<T>{
    private final String errorMessage;

    public Failure(String s){
        errorMessage = s;
    }

    @Override
    public void bind(Effect<T> success, Effect<String> failure) {
        failure.apply(errorMessage);
    }

}

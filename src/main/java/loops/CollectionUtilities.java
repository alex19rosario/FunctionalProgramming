package loops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionUtilities {

    public static <T> List<T> list(){
        return Collections.emptyList();
    }

    public static <T> List<T> list(T t){
        return Collections.singletonList(t);
    }

    public static <T> List<T> list(List<T> ts){
        return Collections.unmodifiableList(new ArrayList<>(ts));
    }

    public static <T> List<T> list(T... t){
        return Collections.unmodifiableList(Arrays.asList(Arrays.copyOf(t, t.length)));
    }
}

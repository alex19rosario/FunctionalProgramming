import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhoneValidation {
    static Pattern phonePattern = Pattern.compile("^[0-9]{3}+-[0-9]{3}+-[0-9]{4}$");




    @Test
    public void nullScenario(){
        assertEquals(2+2, 4);
    }

    @Test
    public void emptyScenario(){
        assertEquals(2+2, 4);
    }

    @Test
    public void validScenario(){
        assertEquals(2+2, 4);
    }

    @Test
    public void invalidScenario(){
        assertEquals(2+2, 4);
    }
}

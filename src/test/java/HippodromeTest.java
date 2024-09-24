import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    public void constructor_NullListParamIllegalArgumentException() {
        String message = "Horses cannot be null.";
        List<Horse> listHorses = null;

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(listHorses));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void constructor_EmptyListParamIllegalArgumentException() {
        String message = "Horses cannot be empty.";
        List<Horse> listHorses = new ArrayList<>();

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(listHorses));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void getHorses_Test() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            horses.add(new Horse("Horse" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertNotNull(hippodrome.getHorses());
        assertEquals(10, hippodrome.getHorses().size());
        assertEquals("Horse0", hippodrome.getHorses().get(0).getName());
        assertEquals("Horse5", hippodrome.getHorses().get(5).getName());
        assertEquals("Horse9", hippodrome.getHorses().get(9).getName());
    }

    @Test
    void move_Test() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        for (Horse hors : horses) {
            Mockito.verify(hors, Mockito.times(1)).move();
        }
    }

    @Test
    void getWinner_Test() {
        Hippodrome hippodrome = new Hippodrome(List.of(
                new Horse("Name1",1,10),
                new Horse("Name2",1,20),
                new Horse("Name3",1,30)
        ) );

        assertEquals("Name3",hippodrome.getWinner().getName());
    }
}
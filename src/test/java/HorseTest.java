import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;


class HorseTest {

    @Test
    public void constructor_NullNameParamIllegalArgumentException() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 5, 5));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "  ", "\n", "\n\n", "\t", "\t\t", "\t \t"})
    public void constructor_EmptyNameParamIllegalArgumentException(String name) {
        String message = "Name cannot be blank.";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 5, 5));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void constructor_NegativeSpeedParamIllegalArgumentException() {
        String message = "Speed cannot be negative.";
        String name = "TestName";
        double speed = -5;
        double distance = 5;

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void constructor_NegativeDistanceParamIllegalArgumentException() {
        String message = "Distance cannot be negative.";
        String name = "TestName";
        double speed = 5;
        double distance = -5;

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void getName_Test() {
        String name = "TestName";
        double speed = 5;
        double distance = 5;
        Horse horse = new Horse(name, speed, distance);

        String actualName = horse.getName();

        assertEquals(name, actualName);
    }

    @Test
    void getSpeed_Test() {
        String name = "TestName";
        double speed = 5;
        double distance = 5;
        Horse horse = new Horse(name, speed, distance);

        double actualSpeed = horse.getSpeed();

        assertEquals(speed, actualSpeed);
    }

    @Test
    void getDistance_Test() {
        String name = "TestName";
        double speed = 5;
        double distance = 5;
        Horse horse = new Horse(name, speed, distance);

        double actualDistance = horse.getDistance();

        assertEquals(distance, actualDistance);

    }

    @Test
    void move_Test() {
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {

            Horse horse = new Horse("TestName", 5, 5);

            horse.move();

            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.3, 0.5, 0.7})
    public void moveCalculate_Test(double fakeRandomValue) {
        double min = 0.2;
        double max = 0.9;
        String name = "TestName";
        double speed = 5;
        double distance = 5;
        Horse horse = new Horse(name, speed, distance);
        double expectedDistance = distance + speed * fakeRandomValue;

        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(min, max)).thenReturn(fakeRandomValue);

            horse.move();
        }
        assertEquals(expectedDistance,horse.getDistance());

    }
}
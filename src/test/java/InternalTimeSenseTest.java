import org.example.InternalTimeSense;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class InternalTimeSenseTest {

    @Test
    public void testInternal() throws InterruptedException {
        InternalTimeSense timeSense = new InternalTimeSense();

        // // Тестовый пример 1: пользователь нажимает Enter для раннего нажатия
        long startTime = System.currentTimeMillis();
        InputStream mockInput = new ByteArrayInputStream("\n".getBytes());
        System.setIn(mockInput);
        timeSense.internal();
        long endTime = System.currentTimeMillis();
        assertTrue(endTime - startTime < 60000);

        // Тестовый пример 2: пользователь нажимает Enter слишком поздно
        startTime = System.currentTimeMillis();
        Thread.sleep(62000);
        mockInput = new ByteArrayInputStream("\n".getBytes());
        System.setIn(mockInput);
        timeSense.internal();
        endTime = System.currentTimeMillis();
        assertTrue(endTime - startTime > 61000 && endTime - startTime < 63000);

        // Тестовый пример 3: пользователь вводит неверный ввод, затем правильный ввод
        startTime = System.currentTimeMillis();
        mockInput = new ByteArrayInputStream("foo\n\n".getBytes());
        System.setIn(mockInput);
        timeSense.internal();
        endTime = System.currentTimeMillis();
        assertTrue(endTime - startTime < 60000);
    }
}

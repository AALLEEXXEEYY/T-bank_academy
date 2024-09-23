package tbank.academy.vicilica;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MainTest {

    @Test
    public void testGetFilenameForTheme_Animals() {
        String result = Main.getFilenameForTheme("animals");
        assertEquals("src\\main\\resources\\animals.txt", result);
    }

    @Test
    public void testGetFilenameForTheme_Fruits() {
        String result = Main.getFilenameForTheme("fruits");
        assertEquals("src\\main\\resources\\fruits.txt", result);
    }

    @Test
    public void testGetFilenameForTheme_Cities() {
        String result = Main.getFilenameForTheme("cities");
        assertEquals("src\\main\\resources\\cities.txt", result);
    }

    @Test
    public void testGetFilenameForTheme_InvalidTheme() {
        String result = Main.getFilenameForTheme("invalidTheme");
        assertNull(result, "An invalid theme should return null.");
    }

    @Test
    public void testChoiceLevel_ValidJuniorInput() {
        Scanner mockScanner = Mockito.mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("junior");
        String level = Main.choiceLevel(mockScanner);
        assertEquals("junior", level);
    }

    @Test
    public void testChoiceLevel_ValidMiddleInput() {
        Scanner mockScanner = Mockito.mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("middle");
        String level = Main.choiceLevel(mockScanner);
        assertEquals("middle", level);
    }

    @Test
    public void testChoiceLevel_ValidSeniorInput() {
        Scanner mockScanner = Mockito.mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("senior");
        String level = Main.choiceLevel(mockScanner);
        assertEquals("senior", level);
    }
}
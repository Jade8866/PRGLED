package ch.hslu.prg.ledmuster.g3_08;

import ch.hslu.prg.ledboard.proxy.BoardService;
import ch.hslu.prg.ledboard.proxy.Led;
import ch.hslu.prg.ledboard.proxy.LedColor;

public class ClientApp {

    public static void main(String[] args) {
        BoardService service = new BoardService();
        createRunningLight(service);
    }

    /**
     * Creates a row of LEDs divided into four sections with different colors:
     * - The first 8 LEDs (from the left) are yellow.
     * - The next 8 LEDs are blue.
     * - The next 8 LEDs are red.
     * - The last 8 LEDs (from the right) are green.
     *
     * Then, it turns on all LEDs to verify the sections.
     *
     * @param service BoardService instance controlling the LEDs
     */
    private static void createRunningLight(BoardService service) {
        int cols = 32;  // Total number of LEDs in the row

        // Step 1: Add a row of LEDs to the board without specifying a color (default red)
        service.add(1, LedColor.RED);  // Adds a row at index 0 with default red color

        // Step 2: Get the LED array
        Led[][] leds = service.getAllLeds();
      /*  if (leds == null || leds.length == 0 || leds[0].length < cols) {
            System.out.println("Error: LED array is not properly initialized.");
            return;
        }

        // Step 3: Divide the row into four 8-LED sections and assign colors
        for (int col = 0; col < cols; col++) {
            if (col < 8) {
                // First section (leftmost 8 LEDs) - Yellow
                service.replace(leds[0][col], LedColor.YELLOW);
            } else if (col < 16) {
                // Second section - Blue
                service.replace(leds[0][col], LedColor.BLUE);
            } else if (col < 24) {
                // Third section - Red (already red by default, so this step is optional)
                service.replace(leds[0][col], LedColor.RED);
            } else {
                // Fourth section (rightmost 8 LEDs) - Green
                service.replace(leds[0][col], LedColor.GREEN);
            }
        }

        // Step 4: Turn on all LEDs to verify the setup
        for (int col = 0; col < cols; col++) {
            leds[0][col].turnOn();
        }

        // Pause for verification
        service.pauseExecution(2000);  // Pause for 2 seconds to view the LED arrangement
    }*/
}
}

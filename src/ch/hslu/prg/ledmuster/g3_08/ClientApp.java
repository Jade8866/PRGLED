	package ch.hslu.prg.ledmuster.g3_08;

	import ch.hslu.prg.ledboard.proxy.BoardService;
	import ch.hslu.prg.ledboard.proxy.Led;
	import ch.hslu.prg.ledboard.proxy.LedColor;
	import java.util.Scanner;

	public class ClientApp {

		public static void main(String[] args) {
			BoardService service = new BoardService();
			ledsOnOff(service);
		}

		private static void ledsOnOff(BoardService service) {
			// Define the colors in a fixed sequence
			LedColor[] colors = {LedColor.YELLOW, LedColor.BLUE, LedColor.RED, LedColor.GREEN};
			
			int maxRows = service.MAX_ROWS;
			int cols = 8; // Assuming 8 columns for LEDs, adjust if needed
			
			Scanner sc = new Scanner(System.in); // Scanner object for user input
			int rows;

			// Prompt the user for the number of rows
			do {
				System.out.print("Enter the number of rows for LEDs (1-" + maxRows + "): ");
				rows = sc.nextInt();
			} while (rows <= 0 || rows > maxRows);

			sc.close(); // Close Scanner after use

			Led[][] leds = service.getAllLeds();
			if (leds == null || leds.length > maxRows) {
				System.out.println("Error: LED array size does not match the expected number of rows.");
				return;
			}

			// Variables to track color states for synchronization checks
			int xYellow = 0, xBlue = 0, xRed = 0, xGreen = 0;
			int yYellow = 0, yBlue = 0, yRed = 0, yGreen = 0;

			// Cycle through the rows
			for (int row = 0; row < rows; row++) {
				int colorIndex = 0;

				// Cycle through columns in the row
				for (int col = 0; col < cols; col++) {
					LedColor currentColor = colors[colorIndex];

					// Ensure that the row and column indices are valid
				  /*  if (row >= leds.length || col >= leds[row].length) {
						System.out.println("Error: Invalid row or column index. Row: " + row + ", Column: " + col);
						continue;
					}*/

					// Add LED to the specified row with the current color
					service.add(row+1, currentColor);
					 leds = service.getAllLeds();
					// Turn on the LED at the specified row and column
					leds[row][col].turnOn();
					System.out.println("LED at row " + (row + 1) + ", column " + col + " set to color: " + currentColor);

					// Track color state changes for specific indices
					if (col == 1) {
						if (currentColor == LedColor.YELLOW) {
							xYellow++;
							service.replace(leds[row][col], LedColor.YELLOW);
						} else if (currentColor == LedColor.BLUE) {
							xBlue++;
							service.replace(leds[row][col], LedColor.BLUE);
						} else if (currentColor == LedColor.RED) {
							xRed++;
							service.replace(leds[row][col], LedColor.RED);
						} else if (currentColor == LedColor.GREEN) {
							xGreen++;
							service.replace(leds[row][col], LedColor.GREEN);
						}
					}
					
					if (col == 15 || col == 32) {
						if (currentColor == LedColor.YELLOW) {
							yYellow++;
							service.replace(leds[row][col], LedColor.YELLOW);
						} else if (currentColor == LedColor.BLUE) {
							yBlue++;
							service.replace(leds[row][col], LedColor.BLUE);
						} else if (currentColor == LedColor.RED) {
							yRed++;
							service.replace(leds[row][col], LedColor.RED);
						} else if (currentColor == LedColor.GREEN) {
							yGreen++;
							service.replace(leds[row][col], LedColor.GREEN);
						}
					}

					// Check synchronization at specific indices
					if (col == 1 || col == 15 || col == 32) {
						if (xYellow == yYellow && xBlue == yBlue && xRed == yRed && xGreen == yGreen) {
							System.out.println("LEDs are in sync at index " + col + ", proceeding normally.");
						} else {
							System.out.println("LEDs out of sync at index " + col + ", adjusting sequence.");
							colorIndex = (colorIndex + 1) % colors.length; // Shift the color sequence
						}
					}

					// Move to the next color in the fixed sequence
					colorIndex = (colorIndex + 1) % colors.length;
				}
			}

			// Pause for verification of LEDs
			service.pauseExecution(2000);
		}
	}

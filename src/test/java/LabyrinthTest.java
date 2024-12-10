import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LabyrinthTest {
	private String readFile(String filePath) throws IOException {
		return new String(Files.readAllBytes(Paths.get(filePath)));
	}

	private static Stream<Object[]> testCases() throws IOException {
		String testCasesDir = "src/test/cses_testfiles/";

		return Files.list(Paths.get(testCasesDir))
				.filter(path -> path.toString().endsWith(".in"))
				.map(path -> new Object[]{path.toString(), path.toString().replace(".in", ".out")})
				.filter(pair -> Files.exists(Paths.get(pair[1].toString())))
				.toList()
				.stream();
	}

	@ParameterizedTest(name = "Test {index}: {0}")
	@MethodSource("testCases")
	void testFromFiles(String inputFile, String outputFile) throws IOException {
		String input = readFile(inputFile);
		String expectedOutput = readFile(outputFile).trim();

		String[] lines = input.split("\n");
		String[] dimensions = lines[0].split(" ");
		int height = Integer.parseInt(dimensions[0]);
		int width = Integer.parseInt(dimensions[1]);

		char[][] maze = new char[height][width];
		int startX = -1, startY = -1, endX = -1, endY = -1;

		for (int i = 0; i < height; i++) {
			maze[i] = lines[i + 1].toCharArray();
			for (int j = 0; j < width; j++) {
				if (maze[i][j] == 'A') {
					startX = i;
					startY = j;
				} else if (maze[i][j] == 'B') {
					endX = i;
					endY = j;
				}
			}
		}

		String actualOutput = LabyrinthSolver.solve(maze, height, width, startX, startY, endX, endY);

		assertEquals(expectedOutput, actualOutput);
	}
}

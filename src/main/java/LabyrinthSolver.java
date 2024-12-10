import java.util.*;

public class LabyrinthSolver {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int height = scanner.nextInt();
		int width = scanner.nextInt();
		scanner.nextLine();


		char[][] maze = new char[height][width];
		int startX = -1, startY = -1, endX = -1, endY = -1;

		for (int i = 0; i < height; i++) {
			maze[i] = scanner.nextLine().toCharArray();
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

		String result = solve(maze, height, width, startX, startY, endX, endY);
		System.out.println(result);
	}

	static String solve(char[][] maze, int height, int width, int startX, int startY, int endX, int endY) {
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		char[] dir = {'L', 'R', 'U', 'D'};

		boolean[][] visited = new boolean[height][width];
		int[][] parent = new int[height * width][2];
		Queue<int[]> queue = new LinkedList<>();

		queue.add(new int[]{startX, startY});
		visited[startX][startY] = true;

		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int x = current[0];
			int y = current[1];

			if (x == endX && y == endY) {
				return constructPath(parent, startX, startY, endX, endY, width, dx, dy, dir);
			}

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx >= 0 && nx < height && ny >= 0 && ny < width && maze[nx][ny] != '#' && !visited[nx][ny]) {
					visited[nx][ny] = true;
					queue.add(new int[]{nx, ny});
					parent[nx * width + ny] = new int[]{x, y};
				}
			}
		}

		return "NO";
	}

	private static String constructPath(int[][] parent, int startX, int startY, int endX, int endY, int width,
										int[] dx, int[] dy, char[] dir) {
		StringBuilder path = new StringBuilder();
		int x = endX, y = endY;

		while (x != startX || y != startY) {
			int[] prev = parent[x * width + y];
			int px = prev[0];
			int py = prev[1];

			for (int i = 0; i < 4; i++) {
				if (x == px + dx[i] && y == py + dy[i]) {
					path.append(dir[i]);
					break;
				}
			}

			x = px;
			y = py;
		}

		path.reverse();
		return "YES\n" + path.length() + "\n" + path;
	}
}

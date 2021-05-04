/** #backtracking #brute-force */
import java.util.Scanner;

class TheSultanSuccessors {
  public static boolean check(int[][] board, int row, int col) {
    // check Vertical
    for (int i = 0; i < row; i++) {
      if (board[i][col] > 0) {
        return false;
      }
    }
    // check Main diagonal
    for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
      if (board[i][j] > 0) {
        return false;
      }
    }
    // check Secondary diagonal
    for (int i = row, j = col; j < board.length && i >= 0; i--, j++) {
      if (board[i][j] > 0) {
        return false;
      }
    }
    return true;
  }

  public static void NQueen(int[][] board, int row, Score score) {
    if (row == board.length) {
      score.maxScore = Math.max(score.currentScore, score.maxScore);
      return;
    }
    for (int j = 0; j < board.length; j++) {
      // check whether the queen can be placed on chessboard
      if (check(board, row, j)) {
        // place this queen in board[row][j]
        board[row][j] *= -1;
        score.currentScore += board[row][j];
        NQueen(board, row + 1, score);
        // backtracking
        score.currentScore -= board[row][j];
        board[row][j] *= -1;
      }
    }
  }

  public static void main(String[] args) {
    int N = 8;
    Scanner sc = new Scanner(System.in);
    int k = sc.nextInt();
    for (int t = 0; t < k; t++) {
      // input
      int[][] board = new int[N][N];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          board[i][j] = sc.nextInt();
          board[i][j] *= -1;
        }
      }
      // place 8 queens to get max scores
      Score score = new Score(0, 0);
      NQueen(board, 0, score);
      System.out.println(String.format("%5d", score.maxScore));
    }
  }
}

class Score {
  int currentScore;
  int maxScore;

  public Score(int currentScore, int maxScore) {
    this.currentScore = currentScore;
    this.maxScore = maxScore;
  }
}

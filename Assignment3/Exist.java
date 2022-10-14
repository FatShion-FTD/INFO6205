package Assignment3;

import java.util.*;

public class Exist {
    final static int[] DIRS = new int[] { -1, 0, 1, 0, -1 };

    public boolean exist(char[][] board, String word) {
        boolean res = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                res |= dfs(i, j, 0, board, word);
            }
        }
        return res;
    }

    public boolean dfs(int row, int col, int index, char[][] board, String word) {
        if (index == word.length())
            return true;
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length)
            return false;

        boolean res = false;
        if (board[row][col] == word.charAt(index)) {
            char ori = board[row][col];
            board[row][col] = '!';
            for (int i = 1; i < DIRS.length; i++) {
                int newRow = row + DIRS[i - 1], newCol = col + DIRS[i];
                res |= dfs(newRow, newCol, index + 1, board, word);
            }
            board[row][col] = ori;
        }
        return res;
    }
}

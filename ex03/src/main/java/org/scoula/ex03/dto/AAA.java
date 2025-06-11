package org.scoula.ex03.dto;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class AAA {
    static class State {
        int x, y, k, dis;
        State(int x, int y, int k, int dis) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.dis = dis;
        }
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();
        sc.nextLine();

        int[][] map = new int[N][M];
        boolean[][][] visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            String nextLine = sc.nextLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = nextLine.charAt(j) - '0';
            }
        }

        State start = new State(0, 0, K, 1);
        Queue<State> q = new LinkedList<>();
        visited[0][0][0] = true;
        q.offer(start);

        while (!q.isEmpty()) {
            State cur = q.poll();

            if (cur.x == N - 1 && cur.y == M - 1) {
                System.out.println(cur.dis);
                return;
            }

            for (int k = 0; k < 4; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

//                if (visited[nx][ny]) continue;

                //갈 수 있는 공간이라면
                if (map[nx][ny] == 0 && !visited[nx][ny][0]) {
                    q.offer(new State(nx, ny, cur.k, cur.dis + 1));
                    visited[nx][ny][0] = true;
                } else if (map[nx][ny] == 1 && cur.k > 0 && !visited[nx][ny][1] ) {
                    q.offer(new State(nx, ny, cur.k - 1, cur.dis + 1));
                    visited[nx][ny][1] = true;
                }

            }
        }

        System.out.println(-1);
    }
}

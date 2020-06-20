import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Main {
    public static int[][] randomgap(){
        Random r = new Random();
        int[][] ex = new int [100][2];
        for (int i = 0; i < 100; i++) {
            ex[i][0] = i;
            ex[i][1] = i * 4 + 10 + (int)r.nextGaussian();
        }
        return ex;
    }
    static int[][] yeje = randomgap();
    // fx = 4x + 10;
    public static double fx(int x[]) {
        int gap_return = 0;
        for (int i = 0; i < 10; i++) {
            int gap = x[0] * yeje[i][0] + x[1] - yeje[i][1];
            gap_return += gap*gap;
        }
        return (double)1/(double)gap_return;
    }

    public static int[][] init() {
        Random r = new Random();
        int[][] arr = new int[4][2];
        for(int i=0; i<4; i++) {
            for (int j = 0; j < 2; j++) {
                arr[i][j] = r.nextInt(32);
                System.out.printf("%d ", arr[i][j]);
            }
            if(i < 3) System.out.print(", ");
        }
        System.out.println();

        return arr;
    }

    public static int[][] selection(int[][] x) {
        double sum = 0;
        double[] f = new double[4];
        for(int i=0; i<4; i++) {
            f[i] = fx(x[i]);
            sum += f[i];
        }

        double[] ratio = new double[x.length];
        for(int i=0; i<x.length; i++) {
            if(i==0) ratio[i] = f[i] / sum;
            else ratio[i] = ratio[i-1] + f[i] / sum;
        }

        int[][] sx = new int[4][2];
        Random r = new Random();
        for(int i=0; i<x.length; i++) {
            double p = r.nextDouble();
            if(p < ratio[0]) {
                sx[i][0] = x[0][0]; sx[i][1] = x[0][1];
            }
            else if(p < ratio[1]) {
                sx[i][0] = x[1][0]; sx[i][1] = x[1][1];
            }
            else if(p < ratio[2]) {
                sx[i][0] = x[2][0]; sx[i][1] = x[2][1];
            }
            else {
                sx[i][0] = x[3][0]; sx[i][1] = x[3][1];
            }
        }

        return sx;
    }

    public static String int2String(String x) {
        return String.format("%8s", x).replace(' ', '0');
    }

    public static String[][] crossOver(int[][] x) {
        String[][] arr = new String[4][2];
        for(int i=0; i<2; i++) {
            for (int j = 0; j < 4; j+=2) {
                String bit1 = int2String(Integer.toBinaryString(x[j][i]));
                String bit2 = int2String(Integer.toBinaryString(x[j+1][i]));

                arr[j][i] = bit1.substring(0, 2) + bit2.substring(2, 5);
                arr[j+1][i] = bit2.substring(0, 2) + bit1.substring(2, 5);
            }
        }

        return arr;
    }

    public static int invert(String x) {
        Random r = new Random();
        int a = Integer.parseInt(x, 2);
        for(int i=0; i<x.length(); i++) {
            double p = (double)1/ (double)16;
            if(r.nextDouble() < p) {
                a = 1 << i ^ a;
            }
        }
        return a;
    }

    public static int[][] mutation(String[][] x) {
        int[][] arr = new int[x.length][2];
        for (int i=0; i<x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                arr[i][j] = invert(x[i][j]);
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[][] x = init();
        double max_fx = 0;
        int[] answer = new int [2];
        for(int i=0; i<10000; i++) {
            int[][] sx = selection(x);
            String[][] cx = crossOver(sx);
            int[][] mx = mutation(cx);

            double[] f = new double[mx.length];
            for(int j = 0; j <mx.length; j++) {
                f[j] = fx(mx[j]);
                max_fx = Math.max(f[j], max_fx);
                if(max_fx == f[j]){
                    answer[0] = mx[j][0];
                    answer[1] = mx[j][1];
                }

            }
            System.out.println();
            System.out.println(answer[0] + " " + answer[1]);
            System.out.println(max_fx);
        }
        System.out.println("f(x) = " + answer[0] + "x +" + answer[1]);
    }
}
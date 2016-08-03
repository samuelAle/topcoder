package com.samuelale.practice;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

/**
 * This class implements a topcoder challenge to solve a simple problem of
 * determining the areas of every rectangle leftover from a larger rectangle
 * that has been patched with other rectangles....
 */
public class grafixMask {
    private static final int height = 400;
    private static final int width = 600;

    // create the representation of the map filled with zeros
    private List<Pixel> unvisitedPixels = new ArrayList<>();

    public int[] sortedAreas(String[] rectangles) {
        List<Integer> result = new ArrayList<>();

        // create a list representing every pixel in the image
        for(int i=0; i<height; i++) {
            for (int j = 0; j < width; j++) {
                unvisitedPixels.add(new Pixel(i,j));
            }
        }

        // for each rectangle in the arg, remove its pixels from the image
        for(String rect : rectangles) {
            List<Integer> points = Arrays.stream(rect.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            for(int row=points.get(0); row<=points.get(2); row++) {
                for(int col=points.get(1); col<=points.get(3); col++) {
                    unvisitedPixels.remove(new Pixel(row, col));
                }
            }
        }


        while(!unvisitedPixels.isEmpty()) {
            Pixel pix = unvisitedPixels.get(0);
            result.add(tracePixels(pix.row, pix.col));
        }

        int[] res = new int[result.size()];
        int count = 0;
        for(Integer i : result) {
            res[count] = i;
            count++;
        }
        return res;
    }

    /**
     * Check's the current pixel at row/col and returns 0 if it is out of bounds,
     * has been checked already, or is part of a rectangle.
     * After checking that pixel, the function is called again recursively
     * checking the surrounding pixels
     * @return the number
     */
    private int tracePixels(int row, int col) {
        if(!isLegit(row, col)) return 0;
        visitPixel(row, col);

        Queue<Pixel> queue = new ArrayBlockingQueue<>(10);
        if(isLegit(row+1, col)) queue.add(new Pixel(row+1, col));
        if(isLegit(row, col+1)) queue.add(new Pixel(row, col+1));
        if(isLegit(row-1, col)) queue.add(new Pixel(row-1, col));
        if(isLegit(row, col-1)) queue.add(new Pixel(row, col-1));
//        queue.add(new Pixel(row+1, col+1));
//        queue.add(new Pixel(row-1, col-1));
//        queue.add(new Pixel(row+1, col-1));
//        queue.add(new Pixel(row-1, col+1));

        int res = 1;
        while(!queue.isEmpty()) {
            Pixel pix = queue.remove();
            row = pix.row;
            col = pix.col;
            if(isLegit(row, col)) {
                res++;
                visitPixel(row, col);
                if(isLegit(row+1, col)) queue.add(new Pixel(row+1, col));
                if(isLegit(row, col+1)) queue.add(new Pixel(row, col+1));
                if(isLegit(row-1, col)) queue.add(new Pixel(row-1, col));
                if(isLegit(row, col-1)) queue.add(new Pixel(row, col-1));
//                queue.add(new Pixel(row+1, col+1));
//                queue.add(new Pixel(row-1, col-1));
//                queue.add(new Pixel(row+1, col-1));
//                queue.add(new Pixel(row-1, col+1));
            }
        }

        return res;
    }

    private boolean isLegit(int row, int col) {
        // the pixel isn't legit if it has already been visited or if it doesn't exist
        return !unvisitedPixels.contains(new Pixel(row, col));
    }

    private void visitPixel(int row, int col) {
        unvisitedPixels.remove(new Pixel(row, col));
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new grafixMask().sortedAreas(new String[]{
                "48 192 351 207", "48 392 351 407", "120 52 135 547", "260 52 275 547"})));
    }

    private class Pixel {
        int row;
        int col;

        Pixel(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int hashCode() {
            long bits = java.lang.Double.doubleToLongBits(row);
            bits ^= java.lang.Double.doubleToLongBits(col) * 31;
            return (((int) bits) ^ ((int) (bits >> 32)));
        }

        @Override
        public boolean equals(Object obj) {
            return (obj instanceof Pixel) && (((Pixel)obj).row == row && ((Pixel)obj).col == col);
        }
    }
}























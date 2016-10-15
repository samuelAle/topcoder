package com.samuelale.practice;
public class FlowerBed {

    public static void main(String[] args) {
        System.out.println(flowerBed(new int[]{
                0,0,1,0,0,0,0,1
        }, 3));
    }

    /**
     * Determine whether you can plant x number of flowers in a flowerbed.
     * A flowerbed may not have 2 flowers next to each other.
     * @param flowerbed the flowerbed represented by an int array.
     *                  1 represents a planted flower, 0 represents a vacant spot
     * @param flowers the number of flowers to plant
     * @return true if you can plant the requested number of flowers in the flowerbed
     */
    public static boolean flowerBed(int[] flowerbed, int flowers) {
        // handle base cases
        if(flowerbed.length == 0) return false;
        if(flowerbed.length == 1) return flowerbed[0] == 0 && flowers <= 1;

        // make the flowerbed start and end with a 1 if necessary
        if(flowerbed[0] == 0 && flowerbed[1] == 0) {
            flowerbed[0] = 1;
            flowers--;
        }
        if(flowerbed[flowerbed.length-1] == 0 && flowerbed[flowerbed.length-2] == 0) {
            flowerbed[flowerbed.length-1] = 1;
            flowers--;
        }

        // now we are handling flowerbeds that are at least 3 in length
        int zeroCount = 0;
        for(int i=0; i<flowerbed.length; i++) {
            if(flowerbed[i]==0) {
                zeroCount++;
            } else if(zeroCount > 2) {
                //?: 1 ??? 1
                //2: 1 00 1 - 0
                //3: 1 000 1 - 1
                //4: 1 0000 1 - 1
                //5: 1 00000 1 - 2
                //6: 1 000000 1 - 2
                flowers -= (zeroCount-1)/2;
                zeroCount = 0;
            }
        }

        System.out.println(flowers);
        return flowers <= 0;
    }
}

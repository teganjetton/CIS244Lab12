/*
 * Tegan Jetton
 * CIS 244 IK
 * 05/11/23
 * Lab 12
 * This is two solutions to the bin packing problem, using
 * first fit and next fit. The program asks the user how many
 * items must be packed and the size of the bins, creates random
 * numbers to place in the bins, then calls the respective methods
 * to sort the random numbers into bins.
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Iterator;
import java.util.List;

public class BinPacker{

	int capacity;
	int[] itemsToPack = new int[100];
	int binNumber;
	int totalNumBins;
	static ArrayList<Integer> items = new ArrayList<>();
	
	
	public static void main(String args[]) {

		int pos = 0;
		int number = 0;
		int count = 1;
		int upperBound = 0;
		Scanner sc = new Scanner(System.in);

		System.out.println("how many items are to be packed? ");
		number = sc.nextInt();

		System.out.println("enter the bin capacity? ");
		upperBound = sc.nextInt();
		
		// always close your Scanner :)
		sc.close();
		
		while (count <= number) {
			pos = getRandomValue(upperBound);
			items.add(pos);
			count++;
		}

		System.out.println("-----------------------------");
		System.out.println("items to pack: \n");
		print();
		
		// Call firstFit and print results
		int index = 1;
		ArrayList<ArrayList<Integer>> bins1 = firstFit(upperBound, items);
		System.out.println("\nFirst Fit Sorted Bins");
		System.out.println("---------------------");
		for (List<Integer> bin: bins1) {
			System.out.println("Bin "+index+": "+bin);
			index++;
		}
		
		// Call nextFit and print results
		index=1;
		ArrayList<ArrayList<Integer>> bins2 = nextFit(upperBound, items);
		System.out.println("\nNext Fit Sorted Bins");
		System.out.println("---------------------");
		for (List<Integer> bin: bins2) {
			System.out.println("Bin "+index+": "+bin);
			index++;
		}
	}

	// create random values less than the upper bound of the bin
  	public static int getRandomValue(int ub) {
  		int element = 0;
		int upperBound = ub;
		int lowerBound = 1;
		Random r = new Random();
		element = r.nextInt(upperBound-lowerBound)+1;
		return element;
	}
  	
  	
	
	// first fit method
	public static ArrayList<ArrayList<Integer>> firstFit(int upper, ArrayList<Integer> items){
		int ub = upper;
		ArrayList<ArrayList<Integer>> bins1 = new ArrayList<>();
		// go through each item
		for(int item : items) {
			boolean isPlaced = false;
			// go through each bin
			for(ArrayList<Integer> bin : bins1) {
				// if the sum of the all the bin items + the new item is less than the upper
				// bound, add the item
				if (bin.stream().mapToInt(Integer::intValue).sum() + item <= ub) {
					bin.add(item);
					isPlaced=true;
					break;
				}
			}
				// if it didn't fit in any of the bins, make a new bin
				if(!isPlaced) {
					ArrayList<Integer> newBin = new ArrayList<>();
					newBin.add(item);
					bins1.add(newBin);
				}
			}
		return bins1;
	}
			
	// nextFit method
	public static ArrayList<ArrayList<Integer>> nextFit(int upper, ArrayList<Integer> items){
		int ub = upper;
		ArrayList<ArrayList<Integer>> bins2 = new ArrayList<>();
		ArrayList<Integer> bin = new ArrayList<>();
		// go through each item
		for(int item : items) {
			// if the sum of the all the bin items + the new item is less than the upper
			// bound, add the item
			if (bin.stream().mapToInt(Integer::intValue).sum() + item <= ub) {
				bin.add(item);
			}
			// otherwise, we'll add that bin to bins2 (done with that one now), make
			// a new bin, and add the item
			else {
				bins2.add(bin);
				bin = new ArrayList<>();
				bin.add(item);
			}
		}
		// not graceful, but it would miss adding the last bin bc it broke out of the loop
		if(!bin.isEmpty()) {
			bins2.add(bin);
		}
		return bins2;
	}
  	

public static void print() {
		for (int i = 0; i < items.size(); ++i)
			System.out.print(items.get(i) + " ");

		System.out.println("\n-----------------------------");
	}
}

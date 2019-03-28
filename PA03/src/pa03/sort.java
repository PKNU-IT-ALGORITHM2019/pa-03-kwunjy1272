package pa03;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.io.IOException;

public class sort {
	static int n;
	static int[] arr;
	static int[] A;
	static double[][] time = new double[7][6];

	static public void main(String[] args) throws IOException {
		String[] sort = { "bubble", "select", "Insert", "Merge", "quick1", "quick2", "quick3" };
		System.out.println("           Random1000 Reverse1000 Random10000 Reverse10000 Random100000 Reverse100000");
		print();
		//quick1 overflow
		for (int i = 0; i < 7; i++) {
			System.out.print(sort[i] + "   ");
			for (int j = 0; j < 6; j++) {
				System.out.print(time[i][j] + "s 	");
			}
			System.out.println("");
		}
	}

	public static void init(int x) {
		if (x == 0 || x == 1)
			n = 1000;
		else if (x == 2 || x == 3)
			n = 10000;
		else
			n = 100000;
		Random ran = new Random();
		arr = new int[n];
		if (x % 2 == 1) {
			for (int i = 0; i < n; i++) {
				arr[i] = n - i;
			}
		} else {
			for (int i = 0; i < n; i++)
				arr[i] = ran.nextInt(n);
		}
	}

	public static void print() {
		for (int j = 0; j < 6; j++) {
			long start = System.currentTimeMillis();
			if (j % 2 == 0) {
				for (int k = 0; k < 10; k++) {
					init(j);
					bubbleSort(n - 1);
				}
			} else {
				init(j);
				bubbleSort(n - 1);
			}
			long end = System.currentTimeMillis();
			double t = (end - start) / 1000.0;
			time[0][j] = t;
		}
		for (int j = 0; j < 6; j++) {
			long start = System.currentTimeMillis();
			if (j % 2 == 0) {
				for (int k = 0; k < 10; k++) {
					init(j);
					selectionSort(n - 1);
				}
			} else {
				init(j);
				selectionSort(n - 1);
			}
			long end = System.currentTimeMillis();
			double t = (end - start) / 1000.0;
			time[1][j] = t;
			// System.out.print(t + "s ");
		}
		for (int j = 0; j < 6; j++) {
			long start = System.currentTimeMillis();
			if (j % 2 == 0) {
				for (int k = 0; k < 10; k++) {
					init(j);
					insertionSort(n - 1);
				}
			} else {
				init(j);
				insertionSort(n - 1);
			}
			long end = System.currentTimeMillis();
			double t = (end - start) / 1000.0;
			time[2][j] = t;
		}
		for (int j = 0; j < 6; j++) {
			long start = System.currentTimeMillis();
			if (j % 2 == 0) {
				for (int k = 0; k < 10; k++) {
					init(j);
					mergeSort(0, n - 1);
				}
			} else {
				init(j);
				mergeSort(0, n - 1);
			}
			long end = System.currentTimeMillis();
			double t = (end - start) / 1000.0;
			time[3][j] = t;
		}
		System.out.print("\nquick1    ");
		for (int j = 0; j < 6; j++) {
			long start = System.currentTimeMillis();
			if (j % 2 == 0) {
				for (int k = 0; k < 10; k++) {
					init(j);
					quickSort_LP(0, n - 1);
				}
			} else {
				init(j);
				quickSort_LP(0, n - 1);
			}
			long end = System.currentTimeMillis();
			double t = (end - start) / 1000.0;
			System.out.print(t+"   ");
			time[4][j] = t;
		}
		for (int j = 0; j < 6; j++) {
			long start = System.currentTimeMillis();
			if (j % 2 == 0) {
				init(j);
				for (int k = 0; k < 10; k++) {
					quickSort_MP( 0, n - 1);
				}
			} else {
				init(j);
				quickSort_MP(0, n - 1);
				}
			long end = System.currentTimeMillis();
			double t = (end - start) / 1000.0;
			time[5][j] = t;
			}
		for (int j = 0; j < 6; j++) {
			long start = System.currentTimeMillis();
			if (j % 2 == 0) {
				for (int k = 0; k < 10; k++) {
					init(j);
					quickSort_RP(0, n - 1);
				}
			} else {
				init(j);
				quickSort_RP(0, n - 1);
			}
			long end = System.currentTimeMillis();
			double t = (end - start) / 1000.0;
			time[6][j] = t;
		}
	}

	public static void bubbleSort(int r) {
		for (int i = r; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (arr[j] > arr[j + 1])
					swap(j, j + 1);
			}
		}
	}

	public static void selectionSort(int r) {

		for (int i = r; i > 0; i--) {
			int max = arr[i], x = i;
			for (int j = 0; j <= i; j++) {
				if (max < arr[j]) {
					max = arr[j];
					x = j;
				}
			}
			if (max > arr[i]) {
				swap(i, x);
			}
		}
	}

	public static void insertionSort(int r) {
		for (int i = 1; i <= r; i++) {
			int tmp = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > tmp) {
				arr[j + 1] = arr[j];
				arr[j] = tmp;
				j--;
			}
		}
	}

	public static void mergeSort(int p, int r) {
		if (p < r) {
			int q = p + (r - p) / 2;
			mergeSort(p, q);
			mergeSort(q + 1, r);
			merge(p, q, r);
		}
	}

	public static void merge(int p, int q, int r) {
		A = new int[n];
		int i = p, j = q + 1, k = p;
		while (i <= q && j <= r) {
			if (arr[i] <= arr[j])
				A[k++] = arr[i++];
			else
				A[k++] = arr[j++];
		}
		while (i <= q) {
			A[k++] = arr[i++];
		}
		while (j <= r) {
			A[k++] = arr[j++];
		}
		for (int c = p; c <= r; c++) {
			arr[c] = A[c];
		}
	}

	public static void swap(int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

	public static int partition(int p, int r) {

		int x = arr[r];
		int i = p - 1;

		for (int j = p; j < r; j++) {
			if (arr[j] < x) {
				i++;
				int tmp = arr[j];
				arr[j] = arr[i];
				arr[i] = tmp;
			}
		}
		swap(i + 1, r);
		return i + 1;
	}

	public static void quickSort_LP(int p, int r) {
		if (p < r) {
			int q = partition(p, r);
			quickSort_LP(p, q - 1);
			quickSort_LP(q + 1, r);
		}
	}

	public static void threeSort(int f, int m, int l) {
		if (arr[f] > arr[m])
			swap(f, m);
		if (arr[m] > arr[l])
			swap(m, l);
		if (arr[f] > arr[m])
			swap(f, m);
	}

	public static void quickSort_MP(int f, int r) {
		int i, j, pivot, mid = f + (r - f) / 2;
		threeSort(f, mid, r);
		if (r - f + 1 > 3) {
			pivot = arr[mid];
			swap(mid, r - 1);
			i = f;
			j = r - 1;
			while (true) {
				while (arr[++i] < pivot && i < r)
					;
				while (arr[--j] > pivot && f < j)
					;
				if (i >= j)
					break;
				swap(i, j);
			}
			swap(i, r - 1);
			quickSort_MP(f, i - 1);
			quickSort_MP(i + 1, r);
		}
	}

	public static int partition2(int f, int r) {
		Random random = new Random();
		int index = f + random.nextInt(r - f + 1);
		int pivot = arr[index];
		swap(r, index);
		int i = f - 1;

		for (int j = f; j < r; j++) {
			if (arr[j] < pivot) {
				i++;
				swap(i, j);
			}
		}
		swap(i + 1, r);
		return i + 1;
	}

	public static void quickSort_RP(int f, int r) {
		if (f < r) {
			int q = partition2(f, r);
			quickSort_RP(f, q - 1);
			quickSort_RP(q + 1, r);
		}
	}
}

import java.util.Scanner;

public class Sample {

	public static void main(String[] args) {
		int value = 0;
		
		for (int i=0; i<10; i++) {
		value += 1;
		System.out.println(value);
		}
		Scanner sca = new Scanner(System.in);
		
		int a = sca.nextInt();
		System.out.println(a);
		
	}

}

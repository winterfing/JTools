package tools.fibonacci;


import java.math.BigDecimal;

/**
 * 
 * @author Administrator
 * 斐波那契数列——f0 = 0	f1 = 1 fn = fn-1 + fn-2
 *
 */
public class FibonacciTest {
	
	static BigDecimal f0 = new BigDecimal(0);
	
	static BigDecimal f1 = new BigDecimal(1);
	
	static BigDecimal fn;
	
	static BigDecimal [] arr = new BigDecimal [100];

	/**
	 * 
	 * @param args
	 * println会在每行输出后加一个转行符
	 */
	public static void main(String[] args) {
		int n = 2 ;
		arr[0] = f0; 
		System.out.println("斐波那契数列:");
		System.out.print("f0 = "+f0.toString()+" ; ");
		arr[1] = f1;
		System.out.print("f1 = "+f1.toString());
		while (n < 100) {
			
			arr[n] = arr[n-1].add(arr[n-2]);
			fn = arr[n];
			System.out.print("f"+n+ " = "+fn.toString()+" ; ");
			n++;
		}
		
	}
	
}

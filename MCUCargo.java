import java.util.Scanner;

public class MCUCargo {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int numItems = sc.nextInt();
		
		long totalItemFeeWithoutLocalDeliv = 0;
		
		// just to count totalLocalDeliv
		long totalWeightFee = 0;
		
		
		for(int i=0;i<numItems;i++) {
			// get the data for an item
			String status = sc.next();
			int p = sc.nextInt();
			int l = sc.nextInt();
			int t = sc.nextInt();
			int w = sc.nextInt();
			
			long weightFee = w * 12;
			if(p > 40 || l > 40 || t > 40) {
				weightFee = p * l * t / 400;
			}
			
			long taxFee = 0;
			if(status.equals("new")) {
				taxFee = w * 5;
			}
			
			long interShippingFee = 0;
			if(w % 7 == 0) {
				interShippingFee = w / 7 * 50;
			}else {
				// add one extra for remainder
				interShippingFee = ((w / 7)+1) * 50;
			}
			
			long itemFeeWithoutLocalDeliv = weightFee + taxFee + interShippingFee;
			
			// add the fee to total
			totalItemFeeWithoutLocalDeliv += itemFeeWithoutLocalDeliv;
			// update total weight fee
			totalWeightFee += weightFee;
			
		}
		
		int distance = sc.nextInt();
		
		long totalFee = totalItemFeeWithoutLocalDeliv + (totalWeightFee * distance / 1000);

		System.out.println(totalFee);
		
		sc.close();
	}
}

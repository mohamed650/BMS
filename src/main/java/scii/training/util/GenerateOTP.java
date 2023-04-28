package scii.training.util;

public class GenerateOTP {

    public String generateOtp(int length){
        String numericString = "0123456789";
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<length; i++) {
			int index = (int) (numericString.length() * Math.random());
			sb.append(numericString.charAt(index));
		}
		return sb.toString();
    }
}

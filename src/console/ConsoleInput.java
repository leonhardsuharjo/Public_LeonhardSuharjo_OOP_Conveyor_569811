package console;

import exceptions.InvalidRuleException;
import java.util.Scanner;

public class ConsoleInput {
	private final Scanner scanner = new Scanner(System.in);

	

	public int readInt(String prompt, int min, int max) throws InvalidRuleException {
		System.out.print(prompt);
		return parseInt(scanner.nextLine().trim(), min, max);
	}

	public double readDouble(String prompt, double min, double max) throws InvalidRuleException {
		System.out.print(prompt);
		String text = scanner.nextLine().trim();
		double value;
		try {
			value = Double.parseDouble(text);
		} catch (NumberFormatException exception) {
			throw new InvalidRuleException("\"" + text + "\" is not a number.");
		}
		if (value < min || value > max) {
			throw new InvalidRuleException(value + " is outside the allowed range [" + min + ", " + max + "].");
		}
		return value;
	}

	private String readOption(String prompt, String allowed) throws InvalidRuleException {
		System.out.print(prompt);
		String text = scanner.nextLine().trim().toUpperCase();
		if (text.length() != 1 || !allowed.contains(text)) {
			throw new InvalidRuleException("\"" + text + "\" is not one of: " + allowed);
		}
		return text;
	}
	
	private int parseInt(String text, int min, int max) throws InvalidRuleException {
		int value;
		try {
			value = Integer.parseInt(text);
		} catch (NumberFormatException exception) {
			throw new InvalidRuleException("\"" + text + "\" is not a whole number.");
		}
		if (value < min || value > max) {
			throw new InvalidRuleException(value + " is outside the allowed range [" + min + ", " + max + "].");
		}
		return value;
	}

	public int askInt(String prompt, int min, int max) {
		while (true) {
			try {
				return readInt(prompt, min, max);
			} catch (InvalidRuleException exception) {
				System.out.println("Invalid input: " + exception.getMessage() + " Try again.");
			}
		}
	}

	public double askDouble(String prompt, double min, double max) {
		while (true) {
			try {
				return readDouble(prompt, min, max);
			} catch (InvalidRuleException exception) {
				System.out.println("Invalid input: " + exception.getMessage() + " Try again.");
			}
		}
	}
	

	public String askOption(String prompt, String allowed) {
		while (true) {
			try {
				return readOption(prompt, allowed);
			} catch (InvalidRuleException exception) {
				System.out.println("Invalid input: " + exception.getMessage() + " Try again.");
			}
		}
	}

	public Integer askOptionalInt(String prompt, int min, int max) {
		while (true) {
			System.out.print(prompt);
			String text = scanner.nextLine().trim();
			if (text.isEmpty()) {
				return null;
			}
			try {
				return parseInt(text, min, max);
			} catch (InvalidRuleException exception) {
				System.out.println("Invalid input: " + exception.getMessage() + " Try again.");
			}
		}
	}
	
	public Double askOptionalDouble(String prompt, double min, double max) {
	    while (true) {
	        System.out.print(prompt);
	        String text = scanner.nextLine().trim();
	        if (text.isEmpty()) {
	            return null;                              
	        }
	        try {
	            double value = Double.parseDouble(text);
	            if (value < min || value > max) {
	                throw new InvalidRuleException(value + " is outside the allowed range [" + min + ", " + max + "].");
	            }
	            return value;
	        } catch (NumberFormatException exception) {
	            System.out.println("Invalid input: \"" + text + "\" is not a number. Try again.");
	        } catch (InvalidRuleException exception) {
	            System.out.println("Invalid input: " + exception.getMessage() + " Try again.");
	        }
	    }
	}

	public String askText(String prompt) { 
		System.out.print(prompt);
		return scanner.nextLine().trim();
	}
}

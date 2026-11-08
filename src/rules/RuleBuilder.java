package rules;

import console.ConsoleInput;

public class RuleBuilder {
	private final ConsoleInput input;

	public RuleBuilder(ConsoleInput input) {
		this.input = input;
	}

	public Rule buildRuleFromUser() {
		Rule rule = new Rule();
		System.out.println("--- Build a rule: IF <all conditions match> THEN <action> ---");

		boolean adding = true;
		while (adding) { 
			int conditionChoice = input.askInt("Add condition: 1) Fragile package  2) Minimum weight  3) No more conditions > ", 1, 3);
			if (conditionChoice == 1) {
				rule.addCondition(new FragileCondition());
			} else if (conditionChoice == 2) {
				int minimum = input.askInt("Minimum weight that triggers the rule (1-100) > ", 1, 100);
				rule.addCondition(new MinimumWeightCondition(minimum));
			} else {
				adding = false; 
			}
		}

		int actionChoice = input.askInt("Action: 1) Warn (you type the message)  2) Reject (remove from belt) > ", 1, 2);
		if (actionChoice == 1) {
			String message = input.askText("Warning message (Enter = default) > ");
			rule.addAction(message.isEmpty() ? new WarnAction() : new WarnAction(message));
		} else {
			rule.addAction(new RejectAction());
		}
		return rule;
	}
}

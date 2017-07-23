import java.util.LinkedHashMap;
import java.util.Map;


public class Rule {

	public static final String AND = "and";
	public static final String OR = "or";
	
	private int ruleNum;
	private String[] antecedentVariable;
	private String[] antecedentValue;
	private String consequentVariable;
	private String consequentValue;
	private String operator;
	
	public Rule(int num, String anteVar, String anteVal, String conseVar, String conseVal)
	{
		ruleNum = num;
		antecedentVariable = new String[]{anteVar};
		antecedentValue = new String[]{anteVal};
		consequentVariable = conseVar;
		consequentValue = conseVal;
		operator = "null";
	}
	
	//2 antecedent
	public Rule(int num, String anteVar1, String anteVal1, String ope, String anteVar2, String anteVal2,
			String conseVar, String conseVal)
	{
		ruleNum = num;
		antecedentVariable = new String[]{anteVar1, anteVar2};
		antecedentValue = new String[]{anteVal1, anteVal2};
		consequentVariable = conseVar;
		consequentValue = conseVal;
		operator = ope;
	}
	
	public Map<String,Double> evaluation(Map<String,Map<String,Double>> membershipData)
	{
		//get the antecedent values
		double[] membershipArray = new double[antecedentVariable.length];
		for(int i=0; i<membershipArray.length; i++)
		{
			Map<String,Double> anteVarMap = membershipData.get(antecedentVariable[i]);
			membershipArray[i] = anteVarMap.get(antecedentValue[i]);
		}
		
		double outputMembership;
		//evaluate the operator
		switch(operator)
		{
			case Rule.AND:
				outputMembership =  Math.min(membershipArray[0],membershipArray[1]);
				break;
			case Rule.OR:
				outputMembership = Math.max(membershipArray[0],membershipArray[1]);
				break;
			default:
				outputMembership = membershipArray[0];
				break;
		}
		
		Map<String,Double> outputMap = new LinkedHashMap<>();
		outputMap.put(consequentValue, outputMembership);
		return outputMap;
	}
	
	@Override
	public String toString()
	{
		String myString = String.format("RULE %d\nIF %s IS %s", 
				ruleNum, antecedentVariable[0], antecedentValue[0]);
		
		for(int i=1; i<antecedentVariable.length; i++)
		{
			myString = String.format("%s\n%s %s IS %s", 
					myString, operator, antecedentVariable[i], antecedentValue[i]);
		}
		
		myString = String.format("%s\nTHEN %s IS %s", 
				myString, consequentVariable, consequentValue);
		
		return myString;
	}
	
	//get set
	public String[] getAntecedentVariable()
	{
		return antecedentVariable;
	}
	
	public void setAntecedentVariable(String... anteVar)
	{
		antecedentVariable = anteVar;
	}
	
	public String[] getAntecedentValue()
	{
		return antecedentValue;
	}
	
	public void setAntecedentValue(String... anteVal)
	{
		antecedentValue = anteVal;
	}
	
	public String getConsequentVariable()
	{
		return consequentVariable;
	}
	
	public void setConsequentVariable(String conseVar)
	{
		consequentVariable = conseVar;
	}
	
	public String getConsequentValue()
	{
		return consequentValue;
	}
	
	public void setConsequentValue(String conseVal)
	{
		consequentValue = conseVal;
	}
	
	public String getOperator()
	{
		return operator;
	}
	
	public void setOperator(String ope)
	{
		operator = ope;
	}
}

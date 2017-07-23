import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class FuzzyFunction {

	private String linguisticVariable;
	//private FuzzySet[] fuzzySetArray;
	private Map<String,FuzzySet> fuzzySetMap;
	
	public FuzzyFunction(String variable, Map<String,FuzzySet> map)
	{
		linguisticVariable = variable;
		fuzzySetMap = map;
	}
	
	public Map<String,Double> fuzzification(double input)
	{
		Map<String,Double> membershipValues = new LinkedHashMap<>();
		
		for(Map.Entry<String,FuzzySet> entry : fuzzySetMap.entrySet())
		{
			String linguisticValue = entry.getKey();
			FuzzySet myFuzzySet = entry.getValue();
			//check if the particular fuzzy set will get activated
			if(input>=myFuzzySet.getLowerBound() && input<=myFuzzySet.getUpperBound())
			{
				membershipValues.put(linguisticValue, myFuzzySet.calculateMembership(input));
			}
			else
			{
				membershipValues.put(linguisticValue, 0.0);
			}
		}
		
		return membershipValues;
	}
	
	public double defuzzification(Map<String,Double> outputMembership)
	{
		List<FuzzySet> outputFuzzySetList = new ArrayList<>();
		//add in activated clipped fuzzy set
		for(Map.Entry<String,Double> entry : outputMembership.entrySet())
		{
			String linguisticValue = entry.getKey();
			double membership = entry.getValue();
			if(membership == 1) //member 1 means the whole fuzzy set
			{
				outputFuzzySetList.add(fuzzySetMap.get(linguisticValue));
			}
			else if(membership > 0)
			{
				FuzzySet outputFuzzySet = fuzzySetMap.get(linguisticValue) //clipped fuzzy set
						.calculateOutputFuzzySet(membership);
				outputFuzzySetList.add(outputFuzzySet);
			}
		}
		
		//use center of sums to calculate the crisp value
		double centroidAreaSum = 0.0;
		double areaSum = 0.0;
		for(FuzzySet fuzzySet : outputFuzzySetList)
		{
			double area = fuzzySet.calculateArea();
			centroidAreaSum += fuzzySet.calculateCentroid()*area;
			areaSum += area;
		}
		
		double crisp = centroidAreaSum/areaSum;
		return crisp;
	}
	
	//get set
	public String getLinguisticVariable()
	{
		return linguisticVariable;
	}
	
	public void setLinguisticVariable(String variable)
	{
		linguisticVariable = variable;
	}
	
	/*public FuzzySet[] getFuzzySetArray()
	{
		return fuzzySetArray;
	}
	
	public void setFuzzySetArray(FuzzySet[] set)
	{
		fuzzySetArray = set;
	}*/
}

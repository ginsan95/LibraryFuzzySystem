import java.util.ArrayList;
import java.util.List;


public class FuzzySet {

	private String linguisticValue;
	private FitVector[] fitVectorArray;
	private double lowerBound, upperBound;
	
	public FuzzySet(String value, FitVector[] vector)
	{
		linguisticValue = value;
		fitVectorArray = vector;
		lowerBound = fitVectorArray[0].getX(0); //first vector x1
		upperBound = fitVectorArray[fitVectorArray.length-1].getX(1); //last vector x2
	}
	
	public double calculateMembership(double input)
	{
		for(FitVector vector : fitVectorArray)
		{
			double membership = vector.calculateY(input);
			if(membership>=0)
			{
				return membership;
			}
		}
		return -1.0; //negative indicate not in bound
	}
	
	public FuzzySet calculateOutputFuzzySet(double membership)
	{
		//get the cutting point
		List<Double> newXList = new ArrayList<>();
		for(FitVector vector : fitVectorArray)
		{
			if(!vector.isHorizontalLine())
			{
				newXList.add(vector.calculateX(membership));
			}
		}
		
		//if more than 2 intersection means it need 3 vectors
		FitVector[] outputFitVectorArray;
		if(newXList.size()>=2)
		{
			outputFitVectorArray = new FitVector[3];
			outputFitVectorArray[0] = fitVectorArray[0].getChangeLength(newXList.get(0), membership);
			outputFitVectorArray[1] = new FitVector(newXList.get(0),membership,newXList.get(1),membership);
			outputFitVectorArray[2] = fitVectorArray[fitVectorArray.length-1]
					.getChangeLength(newXList.get(1), membership);
		}
		//if intersection is < upper bound 
		//else if(newXList.get(1)<fitVectorArray[fitVectorArray.length-1].getCoordinate(1)[0]) 
		else //right side only
		{
			outputFitVectorArray = new FitVector[2];
			outputFitVectorArray[0] = new FitVector(fitVectorArray[0].getX(0),
					membership,newXList.get(0),membership);
			outputFitVectorArray[1] = fitVectorArray[fitVectorArray.length-1]
					.getChangeLength(newXList.get(0), membership);
		}
		
		return new FuzzySet(linguisticValue, outputFitVectorArray);
	}
	
	/*public double calculateCentroid()
	{
		double coordinateSum = 0.0;
		double membershipSum = 0.0;
		for(int i=0; i<fitVectorArray.length; i++)
		{
			coordinateSum += fitVectorArray[i].getX(0)* fitVectorArray[i].getY(0);
			membershipSum += fitVectorArray[i].getY(0);
		}
		
		//last coordinate
		coordinateSum += fitVectorArray[fitVectorArray.length-1].getX(1)
				* fitVectorArray[fitVectorArray.length-1].getY(1);
		membershipSum += fitVectorArray[fitVectorArray.length-1].getY(1);
		
		double center = coordinateSum/membershipSum;
		return center;
	}*/
	
	public double calculateCentroid()
	{
		double integrationXSum = 0.0;
		double areaSum = 0.0;
		for(int i=0; i<fitVectorArray.length; i++)
		{
			integrationXSum += fitVectorArray[i].calculateIntegration(1);
			areaSum += fitVectorArray[i].calculateIntegration(0);
		}
		double center = integrationXSum/areaSum;
		return center;
	}
	
	public static void main(String[] args)
	{
		/*FuzzySet servicePoorFuzzySet = new FuzzySet("poor", new FitVector[]{
				new FitVector(0,0.1,1.2,0.1), new FitVector(1.2,0.1,1.88,0.4),
				new FitVector(1.88,0.4,4.08,0.4), new FitVector(4.08,0.4,5,0)
		});*/
		
		FuzzySet servicePoorFuzzySet = new FuzzySet("poor", new FitVector[]{
				new FitVector(0,0,1,0.3), new FitVector(1,0.3,3.6,0.3),
				new FitVector(3.6,0.3,4,0.5), new FitVector(4,0.5,5.5,0.5),
				new FitVector(5.5,0.5,6,1), new FitVector(6,1,7,1), new FitVector(7,1,8,0)
		});
		
		System.out.println(servicePoorFuzzySet.calculateCentroid());
	}
	
	/*public double calculateArea()
	{
		double area = 0.0;
		for(FitVector vector : fitVectorArray)
		{
			area += vector.calculateArea();
		}
		return area;
	}*/
	
	public double calculateArea()
	{
		double area = 0.0;
		for(FitVector vector : fitVectorArray)
		{
			area += vector.calculateIntegration(0);
		}
		return area;
	}
	
	//get set
	public String getLinguisticValue()
	{
		return linguisticValue;
	}
	
	public void setLinguisticValue(String value)
	{
		linguisticValue = value;
	}
	
	public FitVector[] getFitVectorArray()
	{
		return fitVectorArray;
	}
	
	public void setFitVectorArray(FitVector[] vector)
	{
		fitVectorArray = vector;
	}
	
	public double getLowerBound()
	{
		return lowerBound;
	}
	
	public double getUpperBound()
	{
		return upperBound;
	}
}

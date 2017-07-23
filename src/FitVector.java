
public class FitVector {

	//Each vector is represented as a straight line using the formula y=mx+c
	private double x1, y1, x2, y2;
	private double m, c; //m is gradient, c is constant
	
	public FitVector(double x1, double y1, double x2, double y2)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		defineEquation();
	}
	
	private void defineEquation()
	{
		m = (y1-y2)/(x1-x2);
		c = y1 - m*x1;
	}
	
	public double calculateX(double y)
	{
		//check if the input value is in bound of the fir vector
		if(y>=0 && y<=1)
		{
			return (y-c)/m;
		}
		else
		{
			return 0.0;
		}
	}
	
	public double calculateY(double x)
	{
		//check if the input value is in bound of the fir vector
		if(x>=x1 && x<=x2)
		{
			return m*x+c;
		}
		else
		{
			return -1.0;
		}
	}
	
	public double[] calculateCoordinate(double x, double y)
	{
		return new double[]{calculateX(y), calculateY(x)};
	}
	
	public double calculateArea()
	{
		double area;
		if(m==0) //square
		{
			area = (x1-x2) * y1;
		}
		else //triangle
		{
			area = 0.5 * (x1-x2) * (y1-y2);
		}
		return Math.abs(area);
	}
	
	public double calculateIntegration(int xAmt) //xAmt is the amount of x for integration
	{
		double integrationX1 = m/(2+xAmt)*Math.pow(x1, 2+xAmt) + c/(1+xAmt)*Math.pow(x1, 1+xAmt);
		double integrationX2 = m/(2+xAmt)*Math.pow(x2, 2+xAmt) + c/(1+xAmt)*Math.pow(x2, 1+xAmt);
		return Math.abs(integrationX2-integrationX1);
	}
	
	public boolean isHorizontalLine()
	{
		return (y1==y2);
	}
	
	public FitVector getChangeLength(double newX, double newY)
	{
		if(newX>x1)
		{
			return new FitVector(x1,y1,newX,newY);
		}
		else
		{
			return new FitVector(newX,newY,x2,y2);
		}
	}
	
	public double[] getCoordinate(int point)
	{
		double[] coordinate = new double[2];
		coordinate[0] = getX(point);
		coordinate[1] = getY(point);
		return coordinate;
	}
	
	public double getX(int point)
	{
		switch(point)
		{
			case 0:
				return x1;
			default:
				return x2;
		}
	}
	
	public double getY(int point)
	{
		switch(point)
		{
			case 0:
				return y1;
			default:
				return y2;
		}
	}
	
	public double getGradient()
	{
		return m;
	}
	
	public double getConstant()
	{
		return c;
	}
}

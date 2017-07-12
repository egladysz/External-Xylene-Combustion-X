package excx.movement;

public class Vector {
	private double xValue;
	private double yValue;
	
	
	public Vector(double magX, double  magY){
		setXMagnitude(magX);
		setYMagnitude(magY);
	}
	
	public void setMagnitude(double mag){
		double x = Math.cos(Math.atan2(yValue, xValue))*mag;
		double y = Math.sin(Math.atan2(yValue, xValue))*mag;
		xValue = x;
		yValue = y;
	}
	
	public void setXMagnitude(double mag){
		this.xValue = mag;
	}
	public void setYMagnitude(double mag){
		this.yValue = mag;
	}
	public void setAngle(double ang, boolean degrees){
		double magnitude = Math.sqrt(xValue*xValue+yValue*yValue);
		xValue = Math.cos(degrees? Math.toRadians(ang):ang)*magnitude;
		yValue = Math.sin(degrees? Math.toRadians(ang):ang)*magnitude;
		
	}
	public double getMagnitude(){
		return Math.sqrt(xValue*xValue+yValue*yValue);
	}
	public double getXMagnitude(){
		return xValue;
	}
	public double getYMagnitude(){
		return yValue;
	}
	public double getAngleDegrees(){
		return Math.toDegrees(getAngleRadians());
	}
	public double getAngleRadians(){
		return Math.atan2(yValue, xValue);
	}
	public Object clone(){
		return new Vector(this.getXMagnitude(),this.getYMagnitude());
	}
	public String toString(){
		return "X: " + xValue + " Y: " + yValue + " Angle: " + Math.toDegrees(Math.atan2(yValue, xValue));
		
	}
}

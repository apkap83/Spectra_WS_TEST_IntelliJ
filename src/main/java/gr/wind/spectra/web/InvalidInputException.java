package gr.wind.spectra.web;

public class InvalidInputException extends Exception
{
	private String errorDetails;

	public InvalidInputException(String reason, String errorDetails)
	{
		super(reason);
		this.errorDetails = errorDetails;
	}

	public String getFaultInfo()
	{
		return errorDetails;
	}
}

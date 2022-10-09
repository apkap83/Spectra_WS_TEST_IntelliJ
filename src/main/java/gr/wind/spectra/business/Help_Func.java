package gr.wind.spectra.business;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gr.wind.spectra.web.InvalidInputException;

public class Help_Func
{
	public final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	// Logger instance
	private final Logger logger = LogManager.getLogger(gr.wind.spectra.business.Help_Func.class.getName());

	public String now()
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		return sdf.format(cal.getTime());
	}

	public boolean PropertiesDBFileModified() throws IOException
	{
		// Properties file
		File databasePropertiesFile = new File(
				"/opt/glassfish5/glassfish/domains/domain1/lib/classes/test_database.properties");

		// Its modification time
		long modTimeOfPropertiesFile_LongFormat = databasePropertiesFile.lastModified();
		String modTimeOfPropertiesFile = String.valueOf(modTimeOfPropertiesFile_LongFormat);

		String modTimeWritten = "";
		// File that contains the latest mod time
		File file = new File(
				"/opt/glassfish5/glassfish/domains/domain1/logs/ModificationTimeFor_database_ForTestEnv.properties");
		Scanner sc = null;
		try
		{
			sc = new Scanner(file);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		while (sc.hasNextLine())
		{
			modTimeWritten = sc.nextLine();
		}

		modTimeWritten = modTimeWritten.replace("\n", "").replace("\r", "");

		// Mod time is the same
		if (modTimeOfPropertiesFile.equals(modTimeWritten))
		{
			//System.out.println("modTimeOfPropertiesFile = " + modTimeOfPropertiesFile);
			//System.out.println("modTimeWritten = " + modTimeWritten);
			//System.out.println("FALSE");
			return false;
		} else // Mod time is different!
		{
			//System.out.println("modTimeOfPropertiesFile = " + modTimeOfPropertiesFile);
			//System.out.println("modTimeWritten = " + modTimeWritten);
			//System.out.println("TRUE");
			//  Update modification time that is written in file
			logger.info("Database properties files was modified!");
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					"/opt/glassfish5/glassfish/domains/domain1/logs/ModificationTimeFor_database_ForTestEnv.properties"));
			try
			{
				writer.write(modTimeOfPropertiesFile);
				writer.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}

			return true;
		}

	}

	public String columnsToInsertStatement(String[] columns)
	{
		int numOfFields = columns.length;

		String mystring = " (`";

		for (int i = 0; i < numOfFields; i++)
		{
			mystring += columns[i] + "`";

			if (i < numOfFields - 1)
			{
				mystring += ", `";
			} else
			{
				mystring += ") VALUES";
			}
		}

		return mystring;
	}

	public String valuesToInsertStatement(String[] values)

	{
		int numOfFields = values.length;

		String mystring = " (";

		for (int i = 0; i < numOfFields; i++)
		{
			mystring += "?";

			if (i < numOfFields - 1)
			{
				mystring += ", ";
			} else
			{
				mystring += ")";
			}
		}

		return mystring;
	}

	public String getTimeStamp()
	{
		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);

		String Output = ts.toString() + " ";
		return Output;
	}

	public String assignSimilarANDPredicates(String[] predicates, String[] values)
	{

		int numOfFields = predicates.length;
		String mystring = "";
		for (int i = 0; i < numOfFields; i++)
		{
			// "OltElementName="+ OltElementName + "
			if (i < numOfFields - 1)
			{
				mystring += predicates[i] + "='" + values[i] + "' AND ";
			} else
			{
				mystring += predicates[i] + "='" + values[i] + "'";
			}
		}

		return mystring;
	}

	public void validateDateTimeFormat(String fieldName, String dateInput) throws ParseException, InvalidInputException
	{
		try
		{
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateInput);
		} catch (ParseException e)
		{
			throw new InvalidInputException(
					"The date field " + fieldName + " is not in expected format \"YYYY-MM-dd HH:mm:ss\"", "Error 208");
		}

	}

	public void validateAgainstPredefinedValues(String fieldName, String fieldValue, String[] values)
			throws InvalidInputException
	{
		boolean found = false;
		for (String preValue : values)
		{
			if (preValue.equals(fieldValue))
			{
				found = true;
			}
		}

		if (!found)
		{
			throw new InvalidInputException(
					"The accepted values of field '" + fieldName + "' are: " + String.join(", ", values), "Error 180");
		}

	}

	public void validateDelimitedValues(String fieldName, String fieldValue, String delimiter, String[] acceptedValues)
			throws InvalidInputException
	{
		// Split with pipe (|) delimiter
		delimiter = "\\|";
		String[] allDelimitedFields = fieldValue.split(delimiter);

		for (String item : allDelimitedFields)
		{
			boolean foundInArray = false;

			for (String acceptedVal : acceptedValues)
			{
				if (item.equals(acceptedVal))
				{
					foundInArray = true;
				}
			}

			if (!foundInArray)
			{
				throw new InvalidInputException("The accepted values of field '" + fieldName
						+ "' are not validated against any combination of Voice, Data, IPTV alone or with pipe (|) delimiter",
						"Error 181");
			}

		}
	}

	public void validateIntegerOrEmptyValue(String fieldName, String valueOfField) throws InvalidInputException
	{
		if (valueOfField.equals(""))
		{
		} else
		{
			try
			{
				Integer.parseInt(valueOfField);
			} catch (NumberFormatException e)
			{
				e.printStackTrace();
				throw new InvalidInputException("Expected Integer or empty value for field '" + fieldName + "'",
						"Error 305");
			}
		}

	}

	public ArrayList<String> hierarchyStringToANDPredicates(String hierarchySelected)
	{
		// hierarchySelected = FTTX=1|OLTElementName=Tolis

		String SQLExpression = "";
		String technology;
		ArrayList<String> elementHierarchy = new ArrayList<String>();

		String[] parts = hierarchySelected.split("->");

		// Pick first element e.g FTTX
		technology = parts[0].split("=")[0];
		elementHierarchy.add(technology);
		int numOfParts = parts.length;

		for (int i = 0; i < numOfParts; i++)
		{
			if (i == 0)
			{
				continue;
			} else
			{
				if (i < numOfParts - 1)
				{
					String[] keyValuePair = parts[i].split("=");
					SQLExpression = SQLExpression + keyValuePair[0] + " = " + "'" + keyValuePair[1] + "'" + " AND ";
				} else
				{
					String[] keyValuePair = parts[i].split("=");
					SQLExpression = SQLExpression + keyValuePair[0] + " = " + "'" + keyValuePair[1] + "'";
				}
			}

		}

		elementHierarchy.add(SQLExpression);
		return elementHierarchy;
	}

	public String columnsWithCommas(String[] columns)
	{
		int numOfColumns = columns.length;
		String myString = "";
		for (int i = 0; i < numOfColumns; i++)
		{
			if (i < numOfColumns - 1)
			{
				myString += columns[i] + ",";
			} else
			{
				myString += columns[i];
			}
		}
		return myString;
	}

	public boolean hierarchyHasMultipleSelections(String Hierarchy)
	{
		String[] initialParts = Hierarchy.split("\\|");
		if (initialParts.length > 1)
		{
			return true;
		} else
		{
			return false;
		}
	}

	// FTTX=1->OLTElementName=ATHOKRDLOLT01->OltSlot=1|OltSlot=2&&OLT=1->OLTElementName=ATHOKRDLOLT01->OltSlot=3|OltSlot=4
	public java.util.List<String> getHierarchySelections(String Hierarchy)
	{

		java.util.List<String> myList = new ArrayList<String>();
		String[] initialParts = Hierarchy.split("%"); // FTTX=1->OLTElementName=ATHOKRDLOLT01->OltSlot=1|OltSlot=2 AND
														// OLT=1->OLTElementName=ATHOKRDLOLT01->OltSlot=3|OltSlot=4
		if (initialParts.length > 1)
		{

			// System.out.println("Part I " + initialParts[0]);
			// System.out.println("Part II " + initialParts[1]);
			for (int i = 0; i < initialParts.length; i++)
			{
				String UniqueHierarchy = "";
				String[] items = initialParts[i].split("->"); // FTTX=1 AND OLTElementName=ATHOKRDLOLT01 AND
																// OltSlot=1|OltSlot=2

				// System.out.println("Part 1 " + items[0]);
				// System.out.println("Part 2 " + items[1]);
				// System.out.println("Part 3 " + items[2]);

				int itemsNum = items.length;

				String[] multipleItems = items[itemsNum - 1].split("\\|"); // OltSlot=1 AND OltSlot=2

				// System.out.println("Part 4 " + multipleItems[0]);
				// System.out.println("Part 5 " + multipleItems[1]);

				for (int j = 0; j < items.length - 1; j++)
				{
					UniqueHierarchy += items[j] + "->";
					// System.out.println("Part 6 " + UniqueHierarchy);
				}

				for (int k = 0; k < multipleItems.length; k++)
				{
					// System.out.println("Part 7 " + UniqueHierarchy + multipleItems[k]);
					myList.add(UniqueHierarchy + multipleItems[k]);
				}

			}

		} else
		{
			String UniqueHierarchy = "";
			String[] items = Hierarchy.split("->"); // FTTX=1 AND OLTElementName=ATHOKRDLOLT01 AND OltSlot=1|OltSlot=2

			// System.out.println("Part 1 " + items[0]);
			// System.out.println("Part 2 " + items[1]);
			// System.out.println("Part 3 " + items[2]);

			int itemsNum = items.length;

			String[] multipleItems = items[itemsNum - 1].split("\\|"); // OltSlot=1 AND OltSlot=2

			// System.out.println("Part 4 " + multipleItems[0]);
			// System.out.println("Part 5 " + multipleItems[1]);

			for (int j = 0; j < items.length - 1; j++)
			{
				UniqueHierarchy += items[j] + "->";
				// System.out.println("Part 6 " + UniqueHierarchy);
			}

			for (int k = 0; k < multipleItems.length; k++)
			{
				// System.out.println("Part 7 " + UniqueHierarchy + multipleItems[k]);
				myList.add(UniqueHierarchy + multipleItems[k]);
			}
		}

		return myList;
	}

	public String hierarchyToPredicate(String input)
	{
		String myPredicate = "";
		String[] initialParts = input.split("->");

		if (initialParts.length == 1)
		{
			return "1 = 1";
		}

		for (int i = 1; i < initialParts.length; i++)
		{
			String[] secondaryParts = initialParts[i].split("=");

			if (i < initialParts.length - 1)
			{
				myPredicate += secondaryParts[0] + " = '" + secondaryParts[1] + "' AND ";
			} else
			{
				myPredicate += secondaryParts[0] + " = '" + secondaryParts[1] + "'";
			}
		}
		return myPredicate;
	}

	public String[] hierarchyKeys(String input)
	{
		String[] initialParts = input.split("->");

		ArrayList<String> myArrayList = new ArrayList<String>();

		if (initialParts.length == 1)
		{
			return new String[] {};
		}

		for (int i = 1; i < initialParts.length; i++)
		{
			String[] secondaryParts = initialParts[i].split("=");

			myArrayList.add(secondaryParts[0]);
		}

		return myArrayList.toArray(new String[0]);
	}

	public String[] hierarchyValues(String input)
	{
		String[] initialParts = input.split("->");

		ArrayList<String> myArrayList = new ArrayList<String>();

		if (initialParts.length == 1)
		{
			return new String[] {};
		}

		for (int i = 1; i < initialParts.length; i++)
		{
			String[] secondaryParts = initialParts[i].split("=");

			myArrayList.add(secondaryParts[1]);
		}

		return myArrayList.toArray(new String[0]);
	}

	public String[] hierarchyStringTypes(String input)
	{
		String[] initialParts = input.split("->");

		ArrayList<String> myArrayList = new ArrayList<String>();

		if (initialParts.length == 1)
		{
			return new String[] {};
		}

		for (int i = 1; i < initialParts.length; i++)
		{
			initialParts[i].split("=");

			myArrayList.add("String");
		}

		return myArrayList.toArray(new String[0]);
	}

	public String getRootHierarchyNode(String input)
	{
		String[] initialParts = input.split("->");

		// Only 1 elements without "->"
		if (initialParts.length == 1)
		{
			return input;
		} else
		{
			String[] secondaryParts = initialParts[0].split("=");
			return secondaryParts[0];
		}
	}

	public ArrayList<String> splitHierarchy(String Hierarchy)
	{

		return null;
	}

	public String conCatHierarchy(String[] nodeNames, String[] nodeValues, String[] hierarchyFullPathList)
	{
		String UniqueCharSequence = "->";
		int numOfFields = nodeNames.length;
		String mystring = "";

		if (numOfFields < 1)
		{
			return "None";
		} else if (numOfFields == 1)
		{
			return nodeNames[0] + UniqueCharSequence + hierarchyFullPathList[0] + "=";
		} else if (numOfFields > 1)
		{
			for (int i = 0; i < numOfFields; i++)
			{
				if (i == 0)
				{
					mystring += nodeNames[i] + UniqueCharSequence;
				} else if (i < numOfFields - 1)
				{
					mystring += nodeNames[i] + "=" + nodeValues[i] + UniqueCharSequence;
				} else
				{
					if (i == hierarchyFullPathList.length)
					{
						mystring += nodeNames[i] + "=" + nodeValues[i];
					} else
					{
						mystring += nodeNames[i] + "=" + nodeValues[i] + UniqueCharSequence + hierarchyFullPathList[i]
								+ "=";
					}
				}
			}
		}

		return mystring;
	}

	/*
	 * Checks format of hierarchy that has key=value pairs (after root element)
	 */
	public void checkHierarchyFormatKeyValuePairs(String hierarchy) throws InvalidInputException
	{
		String[] hierarchyItems = hierarchy.split("->");

		for (int i = 0; i < hierarchyItems.length; i++)
		{
			if (i == 0)
			{
				continue;
			}
			String[] keyValuePair = hierarchyItems[i].split("=");
			if (keyValuePair.length == 2)
			{

			} else
			{
				throw new InvalidInputException("Error Hierarchy format provided (must be Key=Value pairs)",
						"Error 170");
			}
		}
	}

	public String replaceHierarchyForSubscribersAffected(String hierarchy, String[] subsHierarchy)
			throws InvalidInputException
	{
		String[] hierarchyItems = hierarchy.split("->");
		String rootElement = "";
		String outputHierarchy = "";

		for (int i = 0; i < hierarchyItems.length; i++)
		{
			if (i == 0)
			{
				rootElement = hierarchyItems[0];
				continue;
			} // root element

			String[] keyValuePair = hierarchyItems[i].split("=");

			if (keyValuePair.length == 2)
			{
				if (keyValuePair[0].equals(subsHierarchy[i - 1]))
				{
					if (i < hierarchyItems.length - 1)
					{
						outputHierarchy += keyValuePair[0] + "=" + keyValuePair[1] + "->";
					} else
					{
						outputHierarchy += keyValuePair[0] + "=" + keyValuePair[1];
					}
				} else
				{
					if (i < hierarchyItems.length - 1)
					{
						outputHierarchy += subsHierarchy[i - 1] + "=" + keyValuePair[1] + "->";
					} else
					{
						outputHierarchy += subsHierarchy[i - 1] + "=" + keyValuePair[1];
					}
				}
			} else
			{
				throw new InvalidInputException("Error Hierarchy format provided (must be Key=Value pairs)",
						"Error 170");
			}
		}

		outputHierarchy = rootElement + "->" + outputHierarchy;

		// System.out.println("Output: " + outputHierarchy);
		return outputHierarchy;
	}

	public void validateNotEmpty(String fieldName, String value) throws InvalidInputException
	{
		if (value.isEmpty() || value.equals("?"))
		{
			throw new InvalidInputException("The required field '" + fieldName + "' is empty", "Error 158");
		}
	}

	public boolean checkIfEmpty(String fieldName, String value) throws InvalidInputException
	{
		boolean emptiness = false;

		if (value.isEmpty() || value.equals("?"))
		{
			emptiness = true;
		} else
		{
			emptiness = false;
		}
		return emptiness;
	}

	public void checkColumnsOfHierarchyVSFullHierarchy(String hierarchy, String fullHierarchyFromDB)
			throws InvalidInputException
	{
		String[] hierarchyItems = hierarchy.split("->");
		String[] fullHierarchyFromDBSplit = fullHierarchyFromDB.split("->");

		for (int i = 0; i < hierarchyItems.length; i++)
		{
			if (i == 0)
			{
				continue;
			}

			String[] keyValuePair = hierarchyItems[i].split("=");

			if (keyValuePair.length == 2)
			{
				if (!keyValuePair[0].equals(fullHierarchyFromDBSplit[i - 1]))
				{
					throw new InvalidInputException(
							"Wrong column '" + keyValuePair[0] + "' in hierarchy format provided", "Error 203");
				}
			} else
			{
				throw new InvalidInputException("Error Hierarchy format provided (must be Key=Value pairs)",
						"Error 170");
			}
		}
	}

	// "LLU,VDSL_LLU,VPU" --> "->NGA_TYPE=LLU->NGA_TYPE=VDSL_LLU->NGA_TYPE=VPU
	public String ngaTypesToSqlInFormat(String ngaTypes)
	{
		String output = "AND `NGA_TYPE` IN (";
		String[] allNgaTypes = ngaTypes.split(",");

		for (String ngaType : allNgaTypes)
		{
			output += "'" + ngaType + "',";
		}

		// Remove last comma character
		output = output.substring(0, output.length() - 1);

		output += ")";

		return output;
	}

	public String generateANDPredicateQuestionMarks(String predicateColumns[])
	{
		String output = "";

		if (predicateColumns.length != 0)
		{
			for (int i = 0; i < predicateColumns.length; i++)
			{
				if (i < predicateColumns.length - 1)
				{
					output = output + "`" + predicateColumns[i] + "` = ? AND ";
				} else
				{
					output = output + "`" + predicateColumns[i] + "` = ?";
				}
			}
		} else
		{
			output = "1 = 1";
		}
		return output;
	}

	public String generateCommaPredicateQuestionMarks(String predicateColumns[])
	{
		String output = "";
		for (int i = 0; i < predicateColumns.length; i++)
		{
			if (i < predicateColumns.length - 1)
			{
				output = output + "`" + predicateColumns[i] + "` = ?, ";
			} else
			{
				output = output + "`" + predicateColumns[i] + "` = ?";
			}
		}

		return output;
	}

	/**
	 * Checks if hierarchyGiven start with: Cabinet_Code or Wind_FTTX or
	 * FTTC_Location_Element
	 *
	 * @param hierarchyGiven
	 * @return
	 */

	public String declineSubmissionOnCertainHierarchyLevels(List<String> hierarchiesSubmitted)
			throws InvalidInputException
	{
		/**
		 * Blacklisted for Submission Hierarchies 1.
		 * 1. Wind_FTTX->OltElementName=×××××->OltRackNo=×××××->OltSubRackNo=×××××->OltSlot=×××××->OltPort=×××××->Onu=×××××
		 * 2. FTTC_Location_Element->SiteName=AKADIMIAS
		 */
		ArrayList<String> hierarchyBlackList1 = new ArrayList<String>();
		hierarchyBlackList1.add("Wind_FTTX");
		hierarchyBlackList1.add("OltElementName");
		hierarchyBlackList1.add("OltRackNo");
		hierarchyBlackList1.add("OltSubRackNo");
		hierarchyBlackList1.add("OltSlot");
		hierarchyBlackList1.add("OltPort");
		hierarchyBlackList1.add("Onu");

		ArrayList<String> hierarchyBlackList2 = new ArrayList<String>();
		hierarchyBlackList2.add("FTTC_Location_Element");
		hierarchyBlackList2.add("SiteName");

		Help_Func hf = new Help_Func();

		//		System.out.println("hierarchiesSubmitted = " + Arrays.toString(hierarchiesSubmitted.toArray()));
		for (int i = 0; i < hierarchiesSubmitted.size(); i++)
		{
			ArrayList<String> hierarchyLevelSubmitted = new ArrayList<String>();

			// Check Hierarchy Format Key_Value Pairs
			hf.checkHierarchyFormatKeyValuePairs(hierarchiesSubmitted.get(i).toString());

			// FTTX=1->OLTElementName=ATHOKRDLOLT01->OltSlot=1
			// Split it on '->'
			String[] hierarchiesSubmittedSplitted = hierarchiesSubmitted.get(i).split("->");

			// foreach element delimited by '->'
			for (int j = 0; j < hierarchiesSubmittedSplitted.length; j++)
			{
				// FTTX=1->OLTElementName=ATHOKRDLOLT01->OltSlot=1
				// Split it on '='
				String[] keyValuePairs = hierarchiesSubmittedSplitted[j].split("=");
				hierarchyLevelSubmitted.add(keyValuePairs[0]);
			}

			//			System.out.println("hierarchyLevelSubmitted = " + Arrays.toString(hierarchyLevelSubmitted.toArray()));

			if (hierarchyBlackList1.equals(hierarchyLevelSubmitted)
					|| hierarchyBlackList2.equals(hierarchyLevelSubmitted))
			{
				throw new InvalidInputException(
						"You cannot submit incident at this specific hierarchy level - Please choose one level Up or Down from this level",
						"Error 447");
			}

		}

		return null;
	}

	public void main(String[] args) throws InvalidInputException
	{
		// "LLU,VDSL_LLU,VPU" --> "->NGA_TYPE=LLU->NGA_TYPE=VDSL_LLU->NGA_TYPE=VPU

	}

	// public static void main(String[] args)
	// {

	// System.out.println(Help_Func.ReplaceHierarchyForSubscribersAffected("FTTX->OltElementName=LAROAKDMOLT01->OltSlot=1->OltPort=0->Onu=0->ElementName=LAROAKDMOFLND010H11",
	// new String[]
	// {"OltElementName","OltSlot","OltPort","Onu","ActiveElement","Slot"}));

	// HierarchyStringToANDPredicates("FTTX=1->OLTElementName=ATHOKRDLOLT01->OltSlot=4");
	// System.out.println("Starting...");
	// GetHierarchySelections("FTTX=1->OLTElementName=ATHOKRDLOLT01->OltSlot=1|OltSlot=2%OLT=1->OLTElementName=ATHOKRDLOLT01->OltSlot=3|OltSlot=4");
	// FTTX=1->OLTElementName=ATHOKRDLOLT01->OltSlot=1
	// String out =
	// HierarchyToPredicate("FTTX=1->OLTElementName=ATHOKRDLOLT01->OltSlot=1");
	// System.out.println(out);
	// System.out.println(Help_Func.HierarchyToPredicate("FTTX->OltElementName=ak->something=3"));

	/*
	 * ArrayList<String> nodeNamesArrayList = new ArrayList<String>();
	 * ArrayList<String> nodeValuesArrayList = new ArrayList<String>();
	 *
	 * nodeNamesArrayList.add("FTTX"); nodeValuesArrayList.add("1");
	 *
	 * nodeNamesArrayList.add("OltElementName");
	 * nodeValuesArrayList.add("Somethin1");
	 *
	 * nodeNamesArrayList.add("OltSlot"); nodeValuesArrayList.add("Something2");
	 */
	// FTTX->OltElementName=LAROAKDMOLT01->OltSlot=1->OltPort=0->Onu=0->ElementName=LAROAKDMOFLND010H11->Slot=4
	// String[] hierarchyFullPathList = {"OltElementName", "OltSlot", "OltPort",
	// "Onu", "ElementName", "Slot"};
	// String[] nodeNamesArrayList =
	// {"FTTX","OltElementName","OltSlot","OltPort","Onu","ElementName", "Slot"};
	// String[] nodeValuesArrayList =
	// {"1","LAROAKDMOLT01","1","0","0","LAROAKDMOFLND010H11", "5"};
	// System.out.println(Help_Func.ConCatHierarchy(nodeNamesArrayList,
	// nodeValuesArrayList, hierarchyFullPathList));

	// }

}

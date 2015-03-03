/**
 * 
 * Name:Matthew Vollkommer
 * Code comment:
 * You will start with the LastnameFirstname-AssignmentE BlueJ project. Use the PrintAllMoviesWithLoop 
 * project as a guide for this assignment. Carefully study the PrintAllMoviesWithLoop project to see the 
 * steps for creating and using a ReadFile object to read from a file. Copying and modifying what you will 
 * need from the PrintAllMoviesWithLoop project, in the PayrollPreparation class create a method called 
 * printEmployeeData() that will do the following, all within the printEmployeeData () method:
 * 1. Create a new ReadFile object that will read the file Payroll.txt. Call your ReadFile object something 
 *  appropriate (reader or myReader, etc.)
 * 2. Use the ReadFile object to read in the first line of Payroll.txt. 
 *  (Each line contains an employeeID, name, number of hours worked, payRate per hour, and payroll type: 
 *  either an H for hourly or an S for salaried. Open the file in NotePad to see exactly what is inside.)
 *  Note that payRate and any dollar amount in your program will have to be of type double, rather than int (integer). Example:
 *  private double payRate;
 * 3. Put in a while loop that will keep looping until you have hit the end of file (just like in PrintAllMoviesWithLoop).
 * 4. Within the loop, pull off the relevant fields from the input line. (See how it is done in PrintAllMoviesWithLoops.)
 * 5. Ignore the hourly employees – don’t print anything out for them. To determine which are salaried employees, use the following if statement:
 * if(payrollType.equals(“S”))
 *  because = = does not work for comparing Strings or comparing any objects because the comparison is done with memory pointers, 
 *  not object values. It only works for primitives (like int, double, boolean). To compare Strings, we use the String method called equals.
 *  ??1
 *  Still within the loop, calculate straight pay (hours * payRate), print out a line for each of the salaried employees (only) with the employeeID, the name, the hours worked and pay amount.
 * 6. At the end of the loop, read the next line of data.
 * 7. After the loop, when all the employees in the payroll.txt file have been processed, print out a “trailer” line that says “No More Salaried Employees.”
 * 8. Test your program to make sure it is correct. Run it yourself. Then try the tester.
 * 9. Put your name and a comment about how well the program works or does not where indicated.
 * 10. Exit BlueJ. Make sure you have named the BlueJ folder correctly. Zip it up. And submit it properly to the eLC Assignment.
 * 
 * 
 * 
 * 
 * PayrollPreparation. Assignment E. 
 * 
 * @author Dr. Janine E. Aronson & Matthew Vollkommer
 * @version 20140225
 * 
 * Your work involves reading in a file, working with the data and printing out
 *   relevant output. Do your work in method printEmployeeData.
 * 
 */
public class PayrollPreparation
{

    /**
     * Constructor for objects of class PayrollPreparation
     */
    public PayrollPreparation()
    {
        // No instance variables.
        
    }

    /**
     * .printEmployeeData. All work is to be done here.
     *    There are no fields (instance variables/attributes/global variables).
     * 
     * Read the data line by line and print out the relevant data with computations.
     * 
     * Dr. Janine E. Aronson Solution to Assignment E:
     */
    public void printEmployeeData()
    {
 
        // define a new (local) ReadFile object and connect it up to the payroll.txt file.
        ReadFile reader;
        // Store it as "reader" create a new Readfile object and connect it up to the payroll.txt file.
        // Carry on from there, following the instructions in the assignment.
        //1. Create a new ReadFile object that will read the file Payroll.txt. Call your ReadFile object something appropriate (reader or myReader, etc.)
        //2. Use the ReadFile object to read in the first line of Payroll.txt. (Each line contains an employeeID, name, number of hours worked, 
        //payRate per hour, and payroll type: either an H for hourly or an S for salaried. Open the file in NotePad to see exactly what is inside.)
        //Note that payRate and any dollar amount in your program will have to be of type double, rather than int (integer). Example:private double payRate;
         reader = new ReadFile("Payroll.txt");

        reader.setSeparator(",");

        reader.readInputLine();
        int employeeID;
        String employeeName;
        double hoursWorked, payRate, straightPay;
        String payrollType;
        
        //3. Put in a while loop that will keep looping until you have hit the end of file (just like in PrintAllMoviesWithLoop).
        // The reader.eof will equal false until there are no more lines to read on the file.
        // Then eof will equal true, i.e. when there are no more lines.  

        while(!reader.eof())   
        // As long as End-Of-File is false, that is, we have not run out of Employees 
        // (i.e. reader tried to read and 
        // did not hit the end of the file (that is, got a non-existent line, 
        // pull the values for that movie from 
        // the text line just read by the "reader", print values out, read another line, 
        // and if we still haven't hit the end of the file, do it all again.  
        {
         //4. Within the loop, pull off the relevant fields from the input line. (See how it is done in PrintAllMoviesWithLoops.)
            employeeID = reader.getIntField(1);
            employeeName = reader.getStringField(2);
            hoursWorked = reader.getDoubleField(3);
            payRate = reader.getDoubleField(4);
            payrollType = reader.getStringField(5);
            
           // 5. Ignore the hourly employees – don’t print anything out for them. To determine which are salaried employees, use the following if statement:
            
           if(payrollType.equals("S")){
               
           //print out a line for each of the salaried employees (only) with the employeeID, the name, the hours worked and pay amount
           //Still within the loop, calculate straight pay (hours * payRate),
           straightPay = hoursWorked*payRate;
           System.out.println(employeeID + "           " + employeeName + "   " + hoursWorked + "   "  +straightPay);
            }
            //6. At the end of the loop, read the next line of data.
            reader.readInputLine();
        }    
        //7. After the loop, when all the employees in the payroll.txt file have been processed, print out a “trailer” line that says “No More Salaried Employees.”
        System.out.println("No More Salaried Employees");
        
    }
}

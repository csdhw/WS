package WS_pkg;
import java.io.*;
import java.util.*;


public class rebalance {

	public static void main(String[] args) {
		if (args.length != 1) //no input file path provided
        {
			System.out.println("Please provide input file path.");
        }
        else
        {
        	File f = new File(args[0]);
            if (!f.exists())
            	System.out.println("Provided file not exist."); //input file not exist
            else
            {
            	List<Holding> clientHoldings = new ArrayList<Holding>();
            	double dTotalInvest = Parser.inputParser(args[0], clientHoldings); //parse the input file

                if (clientHoldings.size() == 0 || dTotalInvest == -1)  //the whole input file is invalid if there is one error
                	 System.out.println("Error in input file. Please see log.");
                else
                {
                    String sInstr = "";
                    String sTemp;
                    for (Iterator<Holding> hold = clientHoldings.iterator();hold.hasNext();) //concatenate the output instruction string
                    {
                        sTemp = hold.next().processInstruction(dTotalInvest);
                        if (sTemp != "")
                            sInstr = sInstr + sTemp + ";";
                    }
                    System.out.println(sInstr);
                }
            }
        }
	}

}

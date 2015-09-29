package WS_pkg;
import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Parser {
	/// <summary>
    /// read the input file line by line 
    /// </summary>
    /// <param name="sPath">file path of the input file</param>
    /// <param name="dTotalInvest">output parameter return the client total investment amount</param>
    /// <returns>List of Holding object</returns>
	public static double inputParser(String sPath, List<Holding> clientHoldings)
    {
        List<String> err = new ArrayList<String>();
        String[] data;
        int iIndex = -1;
        String line;
        Holding clientHolding;
        
        try
        {
	        File fInput = new File(sPath);
	        FileInputStream fInputStream = new FileInputStream(fInput);
	        BufferedReader br = new BufferedReader(new InputStreamReader(fInputStream));
	        double dTotalInvest = 0;
	
	        //read input file
	        while ((line=br.readLine()) != null)
	        {
	            iIndex += 1;
	            if (iIndex > 0) //skip the first header line
	            {
	                if (!line.isEmpty()) //if not empty line
	                {
	                    try
	                    {
	                        data = line.split(",");
	                        clientHolding = new Holding();
	                        clientHolding.Ticker = data[0];
	                        clientHolding.TargetAlloc = Double.parseDouble(data[1].toString()) / 100;
	                        clientHolding.ActualAlloc = Double.parseDouble(data[2].toString()) / 100;
	                        clientHolding.ShareOwn = Integer.parseInt(data[3].toString());
	                        clientHolding.SharePrice = Double.parseDouble(data[4].toString());
	                        clientHolding.processMarketValue();
	                        dTotalInvest = dTotalInvest + (clientHolding.MarketValue);
	                        clientHoldings.add(clientHolding);
	                    }
	                    catch (Exception e)
	                    {
	                        err.add("Line:" + iIndex + "-Invalid input format.");
	                    }
	                }
	            }
	        }
	        br.close();
	
	        //empty file
	        if (err.size() == 0 && clientHoldings.size() == 0) 
	            err.add("Empty file");
	
	        //if there is one line of error the whole input file is invalid
	        if (err.size() > 0) 
	        {
	        	clientHoldings.clear();
	
	            //write error to log file
	        	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	      	    Date date = new Date();
	      	    
      	    	File fout = new File("./ErrLog.txt");
				FileOutputStream fos = new FileOutputStream(fout);
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	      	
	        	bw.write("Process datetime: " + dateFormat.format(date));
	        	bw.newLine();
	        	for (Iterator<String> i = err.iterator();i.hasNext();)
	        	{
	        		bw.write(i.next());
	        		bw.newLine();
	        	}
	  	    
	      	    bw.close();
	        }
	        return dTotalInvest;
        }
        catch (Exception e)
  	    {
        	return -1;
  	    }
        
    }
}

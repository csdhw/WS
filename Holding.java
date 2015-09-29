package WS_pkg;

/// <summary>
/// hold each investment information
/// </summary>
public class Holding {
	/// <summary>
    /// attributes of each investment
    /// </summary>
	public String Ticker;
	public double TargetAlloc;
	public double ActualAlloc;
	public int ShareOwn;
	public double SharePrice;
	public double MarketValue;
	public double TargetMarketValue;
	public int TargetShare;
	public String Instruction;
	public int ShareDiff;
	
	/// <summary>
    /// calculate the market value
    /// </summary>
	public void processMarketValue()
	{
		MarketValue = ShareOwn * SharePrice;
	}
	
	/// <summary>
    /// determine the instruction
    /// </summary>
    /// <param name="totalInvest">client total investment</param>
    /// <returns>instruction string</returns>
	 public String processInstruction(double totalInvest)
     {
         TargetMarketValue = totalInvest * TargetAlloc;
         TargetShare = (int)Math.floor(TargetMarketValue / SharePrice);
         ShareDiff = Math.abs(TargetShare - ShareOwn);
         if (TargetShare == ShareOwn) //current investment already on target
             Instruction = "";
         else
         {
             Instruction = TargetShare > ShareOwn ? "Buy" : "Sell";
             Instruction = Instruction + " " + ShareDiff + " shares of " + Ticker;
         }
         return Instruction;
     }
		
}

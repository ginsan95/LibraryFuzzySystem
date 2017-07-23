import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextArea;


public class KnowledgeBase {

	private List<Rule> ruleList = new ArrayList<>();
	private List<Rule> firedRuleList = new ArrayList<>();
	
	public void addRule(Rule rule)
	{
		ruleList.add(rule);
	}
	
	public void removeRule(int index)
	{
		ruleList.remove(index);
	}
	
	public Map<String,Double> evaluation(Map<String,Map<String,Double>> membershipData)
	{
		Map<String,Double> finalOutputMembership = new LinkedHashMap<>();
		firedRuleList.clear();
		
		for(Rule rule : ruleList) //check each rules
		{
			Map<String,Double> outputMembership = rule.evaluation(membershipData);
			for(String key : outputMembership.keySet()) //only 1 data
			{
				if(!finalOutputMembership.containsKey(key))
				{
					finalOutputMembership.put(key, outputMembership.get(key));
				}
				else
				{
					double membership1 = outputMembership.get(key);
					double membership2 = finalOutputMembership.get(key);
					finalOutputMembership.put(key, Math.max(membership1, membership2));
				}
				
				//check which rule fired for display
				if(outputMembership.get(key)>0)
				{
					firedRuleList.add(rule);
				}
			}
		}
		
		return finalOutputMembership;
	}
	
	//get set
	public List<Rule> getRuleList()
	{
		return ruleList;
	}
	
	public void setRuleList(List<Rule> rules)
	{
		ruleList = rules;
	}
	
	public List<Rule> getFiredRuleList()
	{
		return firedRuleList;
	}
	
	public void setFiredRuleList(List<Rule> rules)
	{
		firedRuleList = rules;
	}
}

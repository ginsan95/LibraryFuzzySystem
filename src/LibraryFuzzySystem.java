import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


public class LibraryFuzzySystem {

	private FuzzyFunction[] inputFuzzyFunction;
	private KnowledgeBase myKnowledgeBase;
	private FuzzyFunction coldnessFuzzyFunction; //output
	
	private JFrame frmSunwayLibraryColdness;
	private JSpinner weatherSpinner, airConSpinner;
	private JLabel coldnessHotLabel, coldnessAveLabel ,coldnessColdLabel, coldnessLevelLabel;
	JTextArea rulesTextArea;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibraryFuzzySystem window = new LibraryFuzzySystem();
					window.frmSunwayLibraryColdness.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LibraryFuzzySystem() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		initializeFuzzySystem();
		
		frmSunwayLibraryColdness = new JFrame();
		frmSunwayLibraryColdness.setTitle("Sunway Library Coldness Fuzzy System");
		frmSunwayLibraryColdness.setBounds(100, 100, 488, 478);
		frmSunwayLibraryColdness.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSunwayLibraryColdness.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Weather");
		lblNewLabel.setBounds(10, 11, 97, 14);
		frmSunwayLibraryColdness.getContentPane().add(lblNewLabel);
		
		JComboBox weatherComboBox = new JComboBox();
		String[] weatherComboBoxData = {"Raining (22-27)", "Cloudy (25-30)", "Sunny (28-34)"};
		weatherComboBox.setModel(new DefaultComboBoxModel(weatherComboBoxData));
		weatherComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent item) {
				String value = (String)item.getItem();
				if(value.equals(weatherComboBoxData[0]))
					weatherSpinner.setModel(new SpinnerNumberModel(22.0, 22.0, 27.0, 1.0));
				else if(value.equals(weatherComboBoxData[1]))
					weatherSpinner.setModel(new SpinnerNumberModel(25.0, 25.0, 30.0, 1.0));
				else if(value.equals(weatherComboBoxData[2]))
					weatherSpinner.setModel(new SpinnerNumberModel(28.0, 28.0, 34.0, 1.0));
			}
		});
		weatherComboBox.setSelectedIndex(0);
		weatherComboBox.setBounds(10, 32, 135, 20);
		frmSunwayLibraryColdness.getContentPane().add(weatherComboBox);
		
		weatherSpinner = new JSpinner();
		weatherSpinner.setModel(new SpinnerNumberModel(22.0, 22.0, 27.0, 1.0));
		weatherSpinner.setBounds(10, 63, 78, 20);
		frmSunwayLibraryColdness.getContentPane().add(weatherSpinner);
		
		JLabel lblAirconTemperature = new JLabel("Air-con temperature");
		lblAirconTemperature.setBounds(222, 11, 128, 14);
		frmSunwayLibraryColdness.getContentPane().add(lblAirconTemperature);
		
		JComboBox airConComboBox = new JComboBox();
		String[] airConComboBoxData = {"Low (16-20)", "Average (18-23)", "High (21-26)"};
		airConComboBox.setModel(new DefaultComboBoxModel(airConComboBoxData));
		airConComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent item) {
				String value = (String)item.getItem();
				if(value.equals(airConComboBoxData[0]))
					airConSpinner.setModel(new SpinnerNumberModel(16.0, 16.0, 20.0, 1.0));
				else if(value.equals(airConComboBoxData[1]))
					airConSpinner.setModel(new SpinnerNumberModel(18.0, 18.0, 23.0, 1.0));
				else if(value.equals(airConComboBoxData[2]))
					airConSpinner.setModel(new SpinnerNumberModel(21.0, 21.0, 26.0, 1.0));
			}
		});
		airConComboBox.setSelectedIndex(0);
		airConComboBox.setBounds(222, 32, 135, 20);
		frmSunwayLibraryColdness.getContentPane().add(airConComboBox);
		
		airConSpinner = new JSpinner();
		airConSpinner.setModel(new SpinnerNumberModel(16.0, 16.0, 20.0, 1.0));
		airConSpinner.setBounds(222, 63, 78, 20);
		frmSunwayLibraryColdness.getContentPane().add(airConSpinner);
		
		JLabel lblNewLabel_1 = new JLabel("Library coldness level");
		lblNewLabel_1.setBounds(10, 258, 162, 14);
		frmSunwayLibraryColdness.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u00B0C");
		lblNewLabel_2.setBounds(98, 66, 26, 14);
		frmSunwayLibraryColdness.getContentPane().add(lblNewLabel_2);
		
		JLabel label = new JLabel("\u00B0C");
		label.setBounds(310, 66, 26, 14);
		frmSunwayLibraryColdness.getContentPane().add(label);
		
		JLabel lblNewLabel_3 = new JLabel("Hot (%)");
		lblNewLabel_3.setBounds(10, 289, 44, 14);
		frmSunwayLibraryColdness.getContentPane().add(lblNewLabel_3);
		
		Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
		coldnessHotLabel = new JLabel("");
		coldnessHotLabel.setBounds(64, 283, 60, 20);
		coldnessHotLabel.setBorder(border);
		frmSunwayLibraryColdness.getContentPane().add(coldnessHotLabel);
		
		JLabel lblAverage = new JLabel("Average (%)");
		lblAverage.setBounds(157, 289, 75, 14);
		frmSunwayLibraryColdness.getContentPane().add(lblAverage);
		
		coldnessAveLabel = new JLabel("");
		coldnessAveLabel.setBounds(242, 283, 60, 20);
		coldnessAveLabel.setBorder(border);
		frmSunwayLibraryColdness.getContentPane().add(coldnessAveLabel);
		
		JLabel lblCold = new JLabel("Cold (%)");
		lblCold.setBounds(332, 289, 60, 14);
		frmSunwayLibraryColdness.getContentPane().add(lblCold);
		
		coldnessColdLabel = new JLabel("");
		coldnessColdLabel.setBounds(402, 283, 60, 20);
		coldnessColdLabel.setBorder(border);
		frmSunwayLibraryColdness.getContentPane().add(coldnessColdLabel);
		
		JLabel lavel_2 = new JLabel("Coldness level (Scale : 1-Hottest to 10-Coldest)");
		lavel_2.setBounds(10, 333, 355, 14);
		frmSunwayLibraryColdness.getContentPane().add(lavel_2);
		
		coldnessLevelLabel = new JLabel("");
		coldnessLevelLabel.setBounds(10, 358, 120, 20);
		coldnessLevelLabel.setBorder(border);
		frmSunwayLibraryColdness.getContentPane().add(coldnessLevelLabel);
		
		JButton fuzzyButton = new JButton("Fuzzy");
		fuzzyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fuzzy();
			}
		});
		fuzzyButton.setBounds(10, 398, 105, 30);
		frmSunwayLibraryColdness.getContentPane().add(fuzzyButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 134, 355, 101);
		frmSunwayLibraryColdness.getContentPane().add(scrollPane);
		
		rulesTextArea = new JTextArea();
		scrollPane.setViewportView(rulesTextArea);
		
		JLabel lblNewLabel_4 = new JLabel("Rules fired");
		lblNewLabel_4.setBounds(10, 109, 114, 14);
		frmSunwayLibraryColdness.getContentPane().add(lblNewLabel_4);
	}
	
	
	private void initializeFuzzySystem()
	{
		/**** Define fuzzy functions ****/
		inputFuzzyFunction = new FuzzyFunction[2];
		//Input
		//Weather
		Map<String,FuzzySet> weatherFuzzySet = new LinkedHashMap<>();
		//raining
		FuzzySet weatherRainFuzzySet = new FuzzySet("raining", new FitVector[]{
				new FitVector(22,1,25,1), new FitVector(25,1,27,0)
		});
		weatherFuzzySet.put(weatherRainFuzzySet.getLinguisticValue(), weatherRainFuzzySet);
		//cloudy
		FuzzySet weatherCloudyFuzzySet = new FuzzySet("cloudy", new FitVector[]{
				new FitVector(25,0,27.5,1), new FitVector(27.5,1,30,0)
		});
		weatherFuzzySet.put(weatherCloudyFuzzySet.getLinguisticValue(), weatherCloudyFuzzySet);
		//sunny
		FuzzySet weatherSunnyFuzzySet = new FuzzySet("sunny", new FitVector[]{
				new FitVector(28,0,30,1), new FitVector(30,1,34,1)
		});
		weatherFuzzySet.put(weatherSunnyFuzzySet.getLinguisticValue(), weatherSunnyFuzzySet);
		inputFuzzyFunction[0] = new FuzzyFunction("weather", weatherFuzzySet);
		
		//Air-con temperature
		Map<String,FuzzySet> airConFuzzySet = new LinkedHashMap<>();
		//low
		FuzzySet airConLowFuzzySet = new FuzzySet("low", new FitVector[]{
				new FitVector(16,1,18,1), new FitVector(18,1,20,0)
		});
		airConFuzzySet.put(airConLowFuzzySet.getLinguisticValue(), airConLowFuzzySet);
		//average
		FuzzySet airConAveFuzzySet = new FuzzySet("average", new FitVector[]{
				new FitVector(18,0,20,1), new FitVector(20,1,21,1), new FitVector(20,1,23,0)
		});
		airConFuzzySet.put(airConAveFuzzySet.getLinguisticValue(), airConAveFuzzySet);
		//high
		FuzzySet airConHighFuzzySet = new FuzzySet("high", new FitVector[]{
				new FitVector(21,0,24,1), new FitVector(24,1,26,1)
		});
		airConFuzzySet.put(airConHighFuzzySet.getLinguisticValue(), airConHighFuzzySet);
		inputFuzzyFunction[1] = new FuzzyFunction("air-con temperature", airConFuzzySet);
		
		//Output
		//Coldness level
		Map<String,FuzzySet> coldnessFuzzySet = new LinkedHashMap<>();
		//hot
		FuzzySet coldnessHotFuzzySet = new FuzzySet("hot", new FitVector[]{
				new FitVector(1,1,3,1), new FitVector(3,1,5,0)
		});
		coldnessFuzzySet.put(coldnessHotFuzzySet.getLinguisticValue(), coldnessHotFuzzySet);
		//average
		FuzzySet coldnessAveFuzzySet = new FuzzySet("average", new FitVector[]{
				new FitVector(3,0,5.5,1), new FitVector(5.5,1,8,0)
		});
		coldnessFuzzySet.put(coldnessAveFuzzySet.getLinguisticValue(), coldnessAveFuzzySet);
		//cold
		FuzzySet coldnessColdFuzzySet = new FuzzySet("cold", new FitVector[]{
				new FitVector(6,0,8,1), new FitVector(8,1,10,1)
		});
		coldnessFuzzySet.put(coldnessColdFuzzySet.getLinguisticValue(), coldnessColdFuzzySet);
		coldnessFuzzyFunction = new FuzzyFunction("coldness level", coldnessFuzzySet);
		
		
		/**** Define rules ****/
		myKnowledgeBase = new KnowledgeBase();
		myKnowledgeBase.addRule(new Rule(1,"weather", "sunny",
				Rule.AND, "air-con temperature", "high",
				"coldness level", "hot"));
		myKnowledgeBase.addRule(new Rule(2,"weather", "sunny",
				Rule.AND, "air-con temperature", "average",
				"coldness level", "average"));
		myKnowledgeBase.addRule(new Rule(3,"weather", "cloudy",
				Rule.AND, "air-con temperature", "high",
				"coldness level", "average"));
		myKnowledgeBase.addRule(new Rule(4,"weather", "cloudy",
				Rule.AND, "air-con temperature", "average",
				"coldness level", "average"));
		myKnowledgeBase.addRule(new Rule(5,"weather", "raining",
				Rule.OR, "air-con temperature", "low",
				"coldness level", "cold"));
	}
	
	//perform fuzzy system
	private void fuzzy()
	{
		//inputs
		double inputWeather = (double)weatherSpinner.getValue();
		double inputAirConTemp = (double)airConSpinner.getValue();
		double[] input = {inputWeather, inputAirConTemp};
		
		//membership values for knowledge base
		Map<String,Map<String,Double>> membershipData = new LinkedHashMap<>();
		
		//get membership
		for(int i=0; i<inputFuzzyFunction.length; i++)
		{
			membershipData.put(inputFuzzyFunction[i].getLinguisticVariable(),
					inputFuzzyFunction[i].fuzzification(input[i]));
		}
		
		System.out.println(membershipData);
		
		//evaluate membership by knowledge base
		Map<String,Double> outputMembership = myKnowledgeBase.evaluation(membershipData);
		//System.out.println("coldness level : " + outputMembership);
		
		//display the rules fired
		String ruleString = "";
		for(Rule rule : myKnowledgeBase.getFiredRuleList())
		{
			ruleString += rule.toString() + "\n\n";
		}
		rulesTextArea.setText(ruleString);
		rulesTextArea.setCaretPosition(0);
		
		//display the percentage of each coldness level
		coldnessHotLabel.setText(String.format("%.2f", outputMembership.get("hot")*100));
		coldnessAveLabel.setText(String.format("%.2f", outputMembership.get("average")*100));
		coldnessColdLabel.setText(String.format("%.2f", outputMembership.get("cold")*100));
		
		//get output
		double output = coldnessFuzzyFunction.defuzzification(outputMembership);
		coldnessLevelLabel.setText(String.format("%.2f", output));
		//System.out.println("Output : "+output);
	}
}

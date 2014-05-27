/**
 * 
 * This file is part of the CarParkSimulator Project, written as 
 * part of the assessment for INB370, semester 1, 2014. 
 *
 * CarParkSimulator
 * asgn2Simulators 
 * 20/04/2014
 * 
 */
package asgn2Simulators;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;

import org.jfree.chart.*;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author hogan
 * 
 */
@SuppressWarnings("serial")
public class GUISimulator extends JFrame implements Runnable, ActionListener {

	// Variables
	// private JFreeChart carParkChart;\
	private int editBoxLength = 8;
	private int editBoxHeight = 20;
	private int textBoxHeight = 20;

	JFrame guiCarPark = new JFrame();
	JPanel pnlInput = new JPanel();
	JPanel pnlParkChart = createParkPanel();

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public GUISimulator(String arg0) throws HeadlessException {
		super(arg0);
		System.out.println("Doing");
	}

	private ActionListener actStart() {
		System.out.println("Button pressed");
		run();
		return null;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		new GUISimulator();

	}

	private JFreeChart createChart(XYDataset dataset, String title) {
		System.out.println("Create Chart");
		JFreeChart chart = ChartFactory.createXYLineChart(title, "Time",
				"Vehicles", dataset);

		chart.setBackgroundPaint(Color.WHITE);

		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.LIGHT_GRAY);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		plot.setDomainCrosshairVisible(true);
		plot.setRangeCrosshairVisible(true);

		return chart;
	}

	private static XYSeriesCollection createDataset() {
		XYSeries s1 = new XYSeries("MotorCars");
		s1.add(0, 0);
		s1.add(1, 10);
		s1.add(2, 15);
		s1.add(3, 18);

		XYSeries s2 = new XYSeries("SmallCars");
		s2.add(0, 0);
		s2.add(1, 0);
		s2.add(2, 5);
		s2.add(3, 4);

		XYSeries s3 = new XYSeries("MotorCycles");
		s3.add(0, 0);
		s3.add(1, 2);
		s3.add(2, 11);
		s3.add(3, 3);

		XYSeriesCollection plot = new XYSeriesCollection();

		plot.addSeries(s1);
		plot.addSeries(s2);
		plot.addSeries(s3);

		// System.out.println(dataset);

		return plot;
	}

	public JPanel createParkPanel() {
		JFreeChart carParkChart = createChart(createDataset(),
				"Cars in the Park");
		ChartPanel chartPanel = new ChartPanel(carParkChart);
		chartPanel.setFillZoomRectangle(true);
		chartPanel.setMouseWheelEnabled(true);
		return chartPanel;
	}

	public GUISimulator() {

		// Exit program code
		guiCarPark.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// GUI setup
		guiCarPark.setTitle("Car Park Simulator");
		guiCarPark.setSize(1000, 800);

		// Resize to fit its components
		// guiCarPark.pack();

		// Make the simulation visible
		guiCarPark.setVisible(true);

		// Centre the GUI
		guiCarPark.setLocationRelativeTo(null);

		// Panel
		pnlInput.setBorder(new TitledBorder(
				new LineBorder(Color.BLACK, 1, true), "Car Park"));

		// Text and Edit boxes
		// Max Car Spaces
		JLabel lblMaxCarSpaces = new JLabel("Max Car Spaces:");
		lblMaxCarSpaces.setPreferredSize(new Dimension(100, textBoxHeight));

		JTextField edtMaxCarSpaces = new JTextField(
				Integer.toString(Constants.DEFAULT_MAX_CAR_SPACES),
				editBoxLength);
		edtMaxCarSpaces.setPreferredSize(new Dimension(editBoxLength,
				editBoxHeight));

		// Max small Car Spaces
		JLabel lblMaxSmallSpaces = new JLabel("Max Small Car Spaces:");
		lblMaxSmallSpaces.setPreferredSize(new Dimension(135, textBoxHeight));

		JTextField edtMaxSmallSpaces = new JTextField(
				Integer.toString(Constants.DEFAULT_MAX_SMALL_CAR_SPACES),
				editBoxLength);
		edtMaxSmallSpaces.setPreferredSize(new Dimension(editBoxLength,
				editBoxHeight));

		// Max MotorCycle Spaces Spaces
		JLabel lblMaxMotorCycleSpaces = new JLabel("Max MotorCycle Spaces:");
		lblMaxMotorCycleSpaces.setPreferredSize(new Dimension(145,
				textBoxHeight));

		JTextField edtMaxMotorCycleSpaces = new JTextField(
				Integer.toString(Constants.DEFAULT_MAX_MOTORCYCLE_SPACES),
				editBoxLength);
		edtMaxMotorCycleSpaces.setPreferredSize(new Dimension(editBoxLength,
				editBoxHeight));

		// Max Queue Size
		JLabel lblMaxQueueSize = new JLabel("Max Queue Size:");
		lblMaxQueueSize.setPreferredSize(new Dimension(100, textBoxHeight));

		JTextField edtMaxQueueSize = new JTextField(
				Integer.toString(Constants.DEFAULT_MAX_QUEUE_SIZE),
				editBoxLength);
		edtMaxQueueSize.setPreferredSize(new Dimension(editBoxLength,
				editBoxHeight));

		// Random Constants
		// Random seed
		JLabel lblRandomSeed = new JLabel("Random Seed:");
		lblRandomSeed.setPreferredSize(new Dimension(90, textBoxHeight));

		JTextField edtRandomSeed = new JTextField(
				Integer.toString(Constants.DEFAULT_SEED), editBoxLength);
		edtRandomSeed.setPreferredSize(new Dimension(editBoxLength,
				editBoxHeight));

		// Default Car Probability
		JLabel lblCarProb = new JLabel("Car Probability:");
		lblCarProb.setPreferredSize(new Dimension(90, textBoxHeight));

		JTextField edtCarProb = new JTextField(
				Double.toString(Constants.DEFAULT_CAR_PROB), editBoxLength);
		edtCarProb
				.setPreferredSize(new Dimension(editBoxLength, editBoxHeight));

		// Default Small Car Probability
		JLabel lblSmallCarProb = new JLabel("Small Car Probability:");
		lblSmallCarProb.setPreferredSize(new Dimension(135, textBoxHeight));

		JTextField edtSmallCarProb = new JTextField(
				Double.toString(Constants.DEFAULT_SMALL_CAR_PROB),
				editBoxLength);
		edtSmallCarProb.setPreferredSize(new Dimension(editBoxLength,
				editBoxHeight));

		// MotorCycle Probability
		JLabel lblMotorCycleProb = new JLabel("MotorCycle Probability:");
		lblMotorCycleProb.setPreferredSize(new Dimension(135, textBoxHeight));

		JTextField edtMotorCycleProb = new JTextField(
				Double.toString(Constants.DEFAULT_MOTORCYCLE_PROB),
				editBoxLength);
		edtMotorCycleProb.setPreferredSize(new Dimension(editBoxLength,
				editBoxHeight));

		// Intended Stay Time (Mean)
		JLabel lblIntendedStayMean = new JLabel("Intended Stay Time (Mean):");
		lblIntendedStayMean.setPreferredSize(new Dimension(155, textBoxHeight));

		JTextField edtIntendedStayMean = new JTextField(
				Double.toString(Constants.DEFAULT_INTENDED_STAY_MEAN),
				editBoxLength);
		edtIntendedStayMean.setPreferredSize(new Dimension(editBoxLength,
				editBoxHeight));

		// Add the text and edit boxes to the panel
		pnlInput.add(lblMaxCarSpaces);
		pnlInput.add(edtMaxCarSpaces);
		pnlInput.add(lblMaxSmallSpaces);
		pnlInput.add(edtMaxSmallSpaces);
		pnlInput.add(lblMaxMotorCycleSpaces);
		pnlInput.add(edtMaxMotorCycleSpaces);
		pnlInput.add(lblMaxQueueSize);
		pnlInput.add(edtMaxQueueSize);

		pnlInput.add(lblRandomSeed);
		pnlInput.add(edtRandomSeed);
		pnlInput.add(lblCarProb);
		pnlInput.add(edtCarProb);
		pnlInput.add(lblSmallCarProb);
		pnlInput.add(edtSmallCarProb);
		pnlInput.add(lblMotorCycleProb);
		pnlInput.add(edtMotorCycleProb);
		pnlInput.add(lblIntendedStayMean);
		pnlInput.add(edtIntendedStayMean);

		pnlInput.setVisible(true);
		pnlInput.setPreferredSize(new Dimension(400, 120));

		// Start button
		JButton btnstart = new JButton("Start");
		btnstart.addActionListener(actStart());
		btnstart.setActionCommand("Run");

		pnlInput.add(btnstart);

		// Chart
		pnlParkChart.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2,
				true), "Chart"));
		pnlParkChart.setVisible(true);
		pnlParkChart.setPreferredSize(new Dimension(400, 650));

		// GUI panels

	}

	public void actionPerformed(ActionEvent e) {
		if ("Run".equals(e.getActionCommand()))
			;
		{
			System.out.println("Button");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */

	@Override
	public void run() {
		System.out.println("Running the program.");
		guiCarPark.add(pnlInput, BorderLayout.NORTH);
		guiCarPark.add(pnlParkChart, BorderLayout.SOUTH);

	}

}

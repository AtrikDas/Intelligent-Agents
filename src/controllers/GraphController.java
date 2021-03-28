package controllers;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import entities.Constants;
import entities.StateModel;
import entities.UtilityModel;

public class GraphController {
	
private static final long serialVersionUID = 1L;
	
	
	public static XYSeries[][] states;  
	
	
	public GraphController(final StateModel[][] env) {
		
		states = new XYSeries[Constants.WIDTH][Constants.HEIGHT];    
		
		for (int row =0;row< Constants.WIDTH;row++ ) {
    			for (int col = 0; col<Constants.HEIGHT;col++) {
    			states[row][col] = new XYSeries(col + "," + row);								
    			}
		}
	}
	
	public static void AddIterationDataToDataset(UtilityModel[][] curUtilityArr, int iteration, final StateModel[][] env) {    
        for (int row =0;row< Constants.WIDTH;row++ ) {
        		for (int col = 0; col<Constants.HEIGHT;col++) {
        			String utility = String.format("%.8g", curUtilityArr[col][row].getUtility());
        			double data = Double.parseDouble(utility);
        			states[row][col].add(iteration, data);
        		}
        }     
    }
	
	public static JPanel CreatePanel(final StateModel[][] env) {
		
		JPanel panel = new JPanel();
		
		XYSeriesCollection dataset = new XYSeriesCollection();
        
        for (int row =0;row< Constants.WIDTH;row++ ) {
    			for (int col = 0; col<Constants.HEIGHT;col++) {
    				if (env[col][row].getIsWall()) {
    					
    				} else {
    					dataset.addSeries(states[row][col]);
    				}
    			
    			}
        }	
		
		JFreeChart chart = ChartFactory.createXYLineChart(
                "Utilities per state against iterations",
                "Iterations",
                "Utility",
                dataset, 
                PlotOrientation.VERTICAL,
                true,
                true,
                false
                );
        
        ChartPanel chartPanel = new ChartPanel(chart);
        
        chartPanel.setPreferredSize(new Dimension(800, 450));
       
        panel.add(chartPanel);
        
        chart.getXYPlot().setRenderer(new XYSplineRenderer());
        
        XYPlot plot = (XYPlot)chart.getPlot();
        XYLineAndShapeRenderer rend = (XYLineAndShapeRenderer)plot.getRenderer();
        
        
        int series = 0;
        for (int row =0;row< Constants.WIDTH;row++ ) {
    			for (int col = 0; col<Constants.HEIGHT;col++) {
    				if (env[col][row].getIsWall()) {
    					
    				} else {
    					rend.setSeriesShapesVisible(series, false);
    					series++;
    				}
    			
    			}
        }	

        

        return panel;
	}

}

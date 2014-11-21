package rmosmenu;

import java.awt.Font;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a pie chart using 
 * data from a {@link DefaultPieDataset}.
 */
public class pie extends ApplicationFrame {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Connection con;
	static Statement stmt = null;
	static PreparedStatement prepareStmt;
	

    /**
     * Default constructor.
     *
     * @param title  the frame title.
     */ 
    public pie() {
        super("Pie Chart by Weight");
        setContentPane(createDemoPanel());
        RefineryUtilities.centerFrameOnScreen(this);
        pack();
        setVisible(true);
    }

    /**
     * Creates a sample dataset.
     * 
     * @return A sample dataset.
     */
    public static PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        System.out.println("here");
        
        
        try{
    		Class.forName("com.mysql.jdbc.Driver");
    		con = DriverManager.getConnection(
    				"jdbc:mysql://localhost/ecorecycledb", "root", "");
    		stmt = (Statement)con.createStatement();
    		//if(con)
    		System.out.println(con);
    		ResultSet rs;
    		String query = "SELECT machine_id,SUM(total_weight) FROM statistics GROUP BY machine_id;";
    		prepareStmt = (PreparedStatement) con.prepareStatement(query);
    		System.out.println(prepareStmt);
    		//DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    		rs = prepareStmt.executeQuery();
    		
    		
    		ArrayList <Integer> c= new ArrayList<Integer>();
    		ArrayList <Integer> tweight= new ArrayList<Integer>();
    		
    		
    		
    		while(rs.next())
    		{
    		System.out.println(rs.getInt(1));
    			c.add(rs.getInt(1));
    			
    			System.out.println(rs.getInt(2));
    			tweight.add(rs.getInt(2));
    			
    			
    			
    		}
        
        for(int i =0;i<c.size();i++)
        {
        	
        	dataset.setValue(c.get(i), tweight.get(i));
        
        }
        }
        catch (Exception e){
        	e.printStackTrace();
        }    
        //finally
       // {
        	 return dataset;
        //}
   
  
               
    }
    
    /**
     * Creates a chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return A chart.
     */
    private static JFreeChart createChart(PieDataset dataset) {
        
        JFreeChart chart = ChartFactory.createPieChart(
            "Pie Chart by Total Weight",  // chart title
            dataset,             // data
            true,               // include legend
            true,
            false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
        return chart;
        
    }
    
    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     * 
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {

        pie demo = new pie();
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
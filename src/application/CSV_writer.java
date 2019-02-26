package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.util.Pair;

public class CSV_writer {
	
	
    protected ArrayList<Pair<Double, Double>> diffraction_values;

    public CSV_writer(ArrayList<Pair<Double, Double>> values) throws IOException {
		this.diffraction_values = values;
		write_file();
	}
    
    public void write_file() throws IOException {
    	File file = new File("test_values.csv");
    	FileWriter fw = new FileWriter(file);
    	BufferedWriter bw = new BufferedWriter(fw);
    	
    	bw.write("X,Y");
    	bw.newLine();
    	for (int i = 0; i < diffraction_values.size(); ++i) {
    		bw.write(Double.toString(diffraction_values.get(i).getKey()) + "," + 
    				Double.toString(diffraction_values.get(i).getValue()));
    		bw.newLine();
    	}
    	bw.close();
    	fw.close();
    }
}

package application;


import java.util.ArrayList;
import javafx.util.Pair;

/**
 * Template class for a light diffraction experiment.
 * Contains common behavior / data between all types of apertures.
 */

public abstract class Aperture {
    /**
     * Determines the resolution of the resulting profile. Increasing this number will
     * increase the size of diffraction_values as well.
     */
    protected static int RESOLUTION_CONSTANT = 40;

    /**
     * Number of orders of magnitude out from center to calculate
     */
    protected static int MAX_DIFFRACTION_ORDER = 3;

    /**
     * Width / Diameter of slit aperture - D
     */
    protected double slit_size;

    /**
     * Distance between the object and the screen - L
     */
    protected double screen_distance;

    /**
     * Light wavelength - λ
     */
    protected double wavelength;

    /**
     * Absolute value of x-value extreme for intensity profile
     * Used to ensure that a sufficient number of x-values are calculated to include
     * MAX_DIFFRACTION_ORDER maxima in the plot
     */
    protected double border_x_value;

    /**
     * Array of MAX_DIFFRACTION_ORDER minima locations
     */
    protected double[] minima;

   /**
    * Array of MAX_DIFFRACTION_ORDER maxima locations
    */
    protected double[] maxima;

    /**
     * List of pairs representing coordinates on a diffraction central-line intensity
     * profile. Each x-value represents an angle in radians. Each y-value represents
     * the light intensity value at that angle. The size of diffraction_values
     * depends on RESOLUTION_CONSTANT and MAX_DIFFRACTION_ORDER.
     */
    protected ArrayList<Pair<Double, Double>> diffraction_values;

    /**
     * Mandatory constructor. Assigns data and generates calculation of minima and maxima values
     * @param slit_size
     * @param screen_distance
     * @param wavelength
     * @param border_x_value
     */
    protected Aperture(double slit_size, double screen_distance, double wavelength, double border_x_value){
        this.slit_size = slit_size;
        this.screen_distance = screen_distance;
        this.wavelength = wavelength;
        this.border_x_value = border_x_value;
        this.diffraction_values = new ArrayList<Pair<Double, Double>>();
        this.maxima = new double[MAX_DIFFRACTION_ORDER];
        this.minima = new double[MAX_DIFFRACTION_ORDER];

        calculate_minima();
        calculate_maxima();
    }

    /**
     * Helper method that populates diffraction_values.
     * Will calculate intensity for a number of x-values on the intensity profile
     * In the range [-border_x_value, border_x_value], incrementing by λ/(D*RESOLUTION_CONSTANT)
     */
    protected void fill_diffraction_values() {
        // Fill diffraction values with (x,y) pairs representing coordinates and intensities
        // on a diffraction central-line intensity profile
        double increment = wavelength / (slit_size * RESOLUTION_CONSTANT);
        for (double i = -1 * border_x_value; i <= border_x_value; i += increment) {
            diffraction_values.add(new Pair<Double, Double>(i, calculate_intensity(i)));
        }
    }

    /**
     * Helper method that calculates a light intensity for a given angle in radians,
     ** representing an x-value on the central-line intensity profile plot. Method implemented
     * in subclasses, as calculation varies depending on type of aperture.
     * @param angle
     * @return
     */
    protected abstract double calculate_intensity(double angle);

    /**
     * Helper method to calculate the Beta value used in the central-line
     * profile formula.
     * β = (𝜋 * (𝐷 or d) * sin θ) / λ
     * @param theta
     * @return
     */
    protected double calculate_beta(double theta, double d){
        return (Math.PI * d * Math.sin(theta)) / wavelength;
    }

    /**
     * Helper method that implements sinc trig function, which appears in several
     * calculations
     * sinc ^ 2(α) = (sin(α) / α) ^ 2
     * @param value
     * @return
     */
    protected double calc_sinc_squared(double value){
        return Math.pow((Math.sin(value) / value), 2);
    }

    /**
     * Helper method that calculates MAX_DIFFRACTION_ORDER minima locations.
     * Implemented in subclasses as calculation varies between apertures
     */
    protected abstract void calculate_minima();

    /**
     * Helper method that calculates MAX_DIFFRACTION_ORDER maxima locations.
     * Implemented in subclasses as calculation varies between apertures
     */
    protected abstract void calculate_maxima();
    
    /**
     * @return ArrayList of intensity values 
     */
    public ArrayList<Pair<Double, Double>> get_values(){
    	return diffraction_values;
    }
}
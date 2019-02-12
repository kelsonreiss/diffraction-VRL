/**
 * Author: Kelson Reiss
 * Last Updated: 2/12/2019
 *
 * This class defines single slit diffraction. Receiving several user-inputted parameters,
 * it calculates the intensity profile for the diffraction.
 */

import java.lang.*;

public class SingleSlit{

    // Number of minima / maxima values to calculate on either side
    // of the central maximum intensity.
    private static final int DIFFRACTION_ORDERS = 4;

    // Width of slit aperture
    private double slit_width;

    // Light wavelength
    private double wavelength;

    // Distance between the object and the screen
    private double screen_distance;

    // Stores a list of pairs, with each pair being a minimum value
    // and its corresponding intensity value. Will be double the size of DIFFRACTION_ORDERS,
    // with the first half to the left of 0, and the second half to the right.
    private ArrayList<Pair<Double, Double>> diffraction_values;

    /**
     * Constructor for Single Slit diffraction experiment. Must accept
     * three inputs to determine the parameters of the experiment.
     * @param slit_width
     * @param wavelength
     * @param screen_distance
     */
    SingleSlit(double slit_width, double wavelength, double screen_distance) {
        this.slit_width = slit_width;
        this.wavelength = wavelength;
        this.screen_distance = screen_distance;
        this.diffraction_values = new ArrayList<Pair<Double, Double>>(DIFFRACTION_ORDERS * 2);
    }

    /**
     * Helper method to calculate the Beta value used in the central-line
     * profile formula.
     * β = (𝜋𝐷 sin θ) / λ
     * @param theta
     * @return
     */
    private double calculate_beta(double theta){
        return (Math.PI * slit_width * Math.sin(theta)) / wavelength;
    }
}

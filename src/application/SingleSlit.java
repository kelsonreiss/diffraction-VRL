package application;
/**
 * This class defines single slit diffraction. Receiving several user-inputted parameters,
 * it calculates the intensity profile for the diffraction.
 */

public class SingleSlit extends Aperture{

    /**
     * Constructor for Single Slit diffraction experiment. Must accept
     * three inputs to determine the parameters of the experiment.
     * @param slit_width
     * @param wavelength
     * @param screen_distance
     */
    SingleSlit(double slit_width, double wavelength, double screen_distance) {
        super(slit_width, screen_distance, wavelength,
                // For a Single Slit Aperture, minima values calculated by = m*λ*L/D. m here is MAX_DIFFRACTION_ORDER
                (MAX_DIFFRACTION_ORDER * wavelength * screen_distance) / slit_width);
        fill_diffraction_values();
    }

    /**
     * Helper method that calculates MAX_DIFFRACTION_ORDER minima locations.
     * For a single slit aperture, minima can be calculated via the formula:
     * x_min = m*λ*L/D, where m=±1, ±2, … m± MAX_DIFFRACTION_ORDER
     */
    @Override
    protected void calculate_minima(){
        for (int i = 0; i < MAX_DIFFRACTION_ORDER;  i++){
            minima[i] = ((i+1) * wavelength * screen_distance) / slit_size;
        }
    }

    /**
     * Helper method that calculates MAX_DIFFRACTION_ORDER maxima locations.
     * For a single slit aperture, maxima can be calculated via the formula:
     * x_max = L*(m+1/2)*λ/D, where m=±1, ±2 … m± MAX_DIFFRACTION_ORDER
     */
    @Override
    protected void calculate_maxima(){
        for (int i = 0; i < MAX_DIFFRACTION_ORDER;  i++){
            maxima[i] = (((i+1.5) * wavelength * screen_distance) / slit_size);
        }
    }

    /**
     * Helper method that calculates a light intensity for a given angle in radians,
     * representing an x-value on the central-line intensity profile plot.
     * Intensity is calculated by: sinc ^ 2(β) = (sin(β) / β) ^ 2
     * @param angle
     * @return
     */
    @Override
    protected double calculate_intensity(double angle){
        return super.calc_sinc_squared(calculate_beta(angle, slit_size));
    }
}
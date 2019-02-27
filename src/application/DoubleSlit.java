package application;

/**
 * This class defines double slit diffraction. Receiving several user-inputted parameters,
 * it calculates the intensity profile for the diffraction pattern.
 */

public class DoubleSlit extends Aperture{

    // Distance between two slits - d
    private double slit_seperation;

    DoubleSlit(double slit_width, double wavelength, double screen_distance, double slit_seperation){
        super(slit_width, screen_distance, wavelength,
                // For a Single Slit Aperture, minima values calculated by = m+.5*λ*L/D.
                // m here is MAX_DIFFRACTION_ORDER
                ((.5 + MAX_DIFFRACTION_ORDER) * wavelength * screen_distance) / slit_width);
        this.slit_seperation = slit_seperation;
    }

    /**
     * Helper method that calculates MAX_DIFFRACTION_ORDER maxima locations.
     * For a double slit aperture, maxima can be calculated via the formula:
     * x_max = L*(m)*λ/D, where m=±1, ±2 … m± MAX_DIFFRACTION_ORDER
     */
    @Override
    protected void calculate_maxima() {
        for (int i = 0; i < MAX_DIFFRACTION_ORDER;  i++){
            maxima[i] = ((i+1) * wavelength * screen_distance) / slit_size;
        }
    }

    /**
     * Helper method that calculates MAX_DIFFRACTION_ORDER minima locations.
     * For a double slit aperture, minima can be calculated via the formula:
     * x_min = (m+.5)*λ*L/D, where m=±1, ±2, … m± MAX_DIFFRACTION_ORDER
     */
    @Override
    protected void calculate_minima() {
        for (int i = 0; i < MAX_DIFFRACTION_ORDER;  i++){
            minima[i] = (((i+1.5) * wavelength * screen_distance) / slit_size);
        }
    }

    /**
     * Helper method that calculates a light intensity for a given angle in radians,
     * representing an x-value on the central-line intensity profile plot.
     * Intensity is calculated by: 4 * cos(β)^2 * sinc(α)^2
     * β = (𝜋d sin θ) / λ
     * α = (2𝜋D sin θ) / λ
     * @param angle
     * @return
     */
    @Override
    protected double calculate_intensity(double angle) {
    	// If the angle is 0, intensity should be 1.0
    	if (Math.abs(angle) < 0.0000000001) {
    		return 1.0;
    	}
        return 4 * Math.pow(Math.cos(calculate_beta(angle, slit_seperation)),2)
                * calc_sinc_squared(calculate_beta(angle, 2 * slit_size));
    }
}


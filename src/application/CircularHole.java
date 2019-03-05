package application;

import org.apache.commons.math3.special.BesselJ;

/**
 * Class representing a circular hold diffraction experiment. Implements Aperture class, which contains
 * behavior and data common in several experiments.
 */
public class CircularHole extends Aperture {

    private static final double[] MAGNITUDES = {1.22, 2.23, 3.24, 4.24, 5.24};

    /**
     * Mandatory constructor for Circular Hole experiment
     * @param diameter
     * @param wavelenth
     * @param screen_distance
     */
    public CircularHole(double diameter, double wavelenth, double screen_distance){
        super(diameter, screen_distance, wavelenth,
                // Max minima value is given by m*λ*L/D)
                (MAGNITUDES[MAX_DIFFRACTION_ORDER] * wavelenth * screen_distance) / diameter);
        fill_diffraction_values();
    }


    /**
     * Formula for circular hole diffraction intensity given by Airy pattern:
     * ((2 * J1(β)) / β)^2
     * J1 = first order Bessel Function
     * β =  𝜋𝐷 sin θ / λ
     * @param angle
     * @return
     */
    @Override
    protected double calculate_intensity(double angle) {
        double beta = calculate_beta(angle, slit_size);
        return Math.pow(((2 * calculate_bessel(beta)) / beta), 2);
    }

    private double calculate_bessel(double beta) {
		BesselJ bessel_calculator = new BesselJ(1);
		// Call Bessel function on absolute value of beta 
		return bessel_calculator.value(Math.abs(beta));
	}

	/**
     * 
     * Circular hole minima equation expressed as:  m λ L/ D
     * In this context, "minima" are the radii of the dark fringes at different orders
     */
    @Override
    protected void calculate_minima() {
        for (int i = 0; i < MAX_DIFFRACTION_ORDER; ++i){
            minima[i] = (MAGNITUDES[i] * wavelength * screen_distance) / slit_size;
        }
    }

    /**
     * Helper method that calculates MAX_DIFFRACTION_ORDER maxima locations.
     * For a single slit aperture, maxima can be calculated via the formula:
     * x_max = L*(m+1/2)*λ/D, where m=±1, ±2 … m± MAX_DIFFRACTION_ORDER
     */
	@Override
	protected void calculate_maxima() {
		for (int i = 0; i < MAX_DIFFRACTION_ORDER; ++i){
            maxima[i] = ((MAGNITUDES[i]+.5) * wavelength * screen_distance) / slit_size;
        }
	}
}
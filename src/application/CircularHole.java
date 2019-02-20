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
        super(diameter, wavelenth, screen_distance,
                // Max minima value is given by m*λ*L/D)
                (MAGNITUDES[MAX_DIFFRACTION_ORDER] * wavelenth * screen_distance) / diameter);
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
		return bessel_calculator.value(beta);
	}


	/**
     * (TODO): DOUBLE CHECK THIS EQUATION
     * (TODO: Handle magnitudes greater than 5
     * Circular hole minima equation expressed as:  m λ L/ D
     * In this context, "minima" are the radii of the dark fringes at different orders
     */
    @Override
    protected void calculate_minima() {
        for (int i = 0; i < MAX_DIFFRACTION_ORDER; ++i){
            minima[i] = (MAGNITUDES[i] * wavelength * screen_distance) / slit_size;
        }
    }
}

package primitives;

/**
 * class Material represents a Material for geometric bodies
 */
public class Material {
    public Double3 kD = Double3.ZERO;
    public Double3 kS = Double3.ZERO;
    /**
     * shininess of Material
     */
    public int nShininess = 0;

    /**
     * Setter of kD of Material with Double3
     *
     * @param kD of Material
     * @return Material object
     */
    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Setter of kD of Material with Double
     *
     * @param kD of Material
     * @return Material object
     */
    public Material setkD(Double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * Setter of kS of Material with Double3
     *
     * @param kS of Material
     * @return Material object
     */
    public Material setkS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Setter of kS of Material with Double
     *
     * @param kS of Material
     * @return Material object
     */
    public Material setkS(Double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * Setter of nShininess of Material
     *
     * @param nShininess of Material
     * @return Material object
     */
    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}

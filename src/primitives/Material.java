package primitives;

/**
 * class Material represents a Material for geometric bodies
 */
public class Material {
    public Double3 kD = Double3.ZERO;
    public Double3 kS = Double3.ZERO;

    /**
     * transparency
     */
    public Double3 kT = Double3.ZERO;

    /**
     * reflection
     */
    public Double3 kR = Double3.ZERO;

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
    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Setter of kD of Material with Double
     *
     * @param kD of Material
     * @return Material object
     */
    public Material setKd(Double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * Setter of kS of Material with Double3
     *
     * @param kS of Material
     * @return Material object
     */
    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Setter of kS of Material with Double
     *
     * @param kS of Material
     * @return Material object
     */
    public Material setKs(Double kS) {
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

    /**
     * setter of transparency
     * @param kT transparency of material
     * @return Material object
     */
    public Material setKt(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * setter of reflection
     * @param kR reflection of material
     * @return Material object
     */
    public Material setKr(Double3 kR) {
        this.kR = kR;
        return this;
    }
}

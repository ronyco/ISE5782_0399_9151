package primitives;

/**
 * class Material represents a Material for geometric bodies
 */
public class Material {
    /**
     * attenuation factor of Diffuse
     */
    public Double3 kD = Double3.ZERO;
    /**
     * attenuation factor of specular
     */
    public Double3 kS = Double3.ZERO;

    /**
     * Attenuation factor of transparency
     */
    public Double3 kT = Double3.ZERO;

    /**
     * Attenuation factor of reflection
     */
    public Double3 kR = Double3.ZERO;

    /**
     * shininess of Material
     */
    public int nShininess = 0;

    /**
     * factor of glossiness
     */
     public double kG = 0;
    /**
     * factor of blurriness
     */
    public double kB = 0;

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
    public Material setKd(double kD) {
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
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /**
     * setter of transparency
     *
     * @param kT transparency of material
     * @return Material object
     */
    public Material setKt(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * setter of reflection
     *
     * @param kR reflection of material
     * @return Material object
     */
    public Material setKr(Double3 kR) {
        this.kR = kR;
        return this;
    }


    /**
     * Setter of reflection
     *
     * @param kR reflection of material
     * @return Material object
     */
    public Material setKr(double kR) {
        this.kR = new Double3(kR);
        return this;
    }

    /**
     * Setter of transparency
     *
     * @param kT transparency of material
     * @return material object
     */
    public Material setKt(double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    /**
     * setter of glossiness
     * @param kG for glossiness of material
     * @return material object
     */
    public Material setkG(double kG) {
        this.kG = kG;
        return this;
    }
    /**
     * setter of blurriness
     * @param kB for blurriness of material
     * @return material object
     */
    public Material setkB(double kB) {
        this.kB = kB;
        return this;
    }
}

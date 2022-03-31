package scene;

import elements.AmbientLight;
import geometries.Geometries;
import primitives.Color;

/**
 * Scene class: Holder for all the objects of the 3d world to render
 */
public class Scene {

    public String name;
    public Color background= Color.BLACK;
    public AmbientLight ambientLight = new AmbientLight();
    public Geometries geometries = new Geometries(); //Composite for all geometric object


    /**
     * Constructor with mandatory name parameter
     * @param name of the scene
     */
    public Scene(String name) {
        this.name = name;
    }


    /**
     * Setter of scene name
     * @param name of scene
     * @return scene object
     */
    public Scene setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Setter of background scene
     * @param background of scene
     * @return Scene object
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Setter of ambient light
     * @param ambientLight of scne
     * @return  Scene object
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Setter of geometries present in scene
     * @param geometries in scene
     * @return Scene object
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}

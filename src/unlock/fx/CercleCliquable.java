package unlock.fx;

public class CercleCliquable {
    
    private static final Integer RADIUS=11;
    
    private final int numero;
    private final int x_center_location;
    private final int y_center_location;
    
    public CercleCliquable(int numero, int x_center_location, int y_center_location) {
        this.numero=numero;
        this.x_center_location=x_center_location;
        this.y_center_location=y_center_location;
    }
    
    public Integer getNumero() {
        return numero;
    }
    
    public boolean isInside(double x,double y) {
        
       return Math.sqrt(Math.pow((x-x_center_location), 2)+
                        Math.pow((y-y_center_location), 2))
                        <= RADIUS;
    } 
}
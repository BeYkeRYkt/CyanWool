package net.CyanWool.api.entity.component.basics;

import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.component.Component;
import net.CyanWool.api.utils.Vector;

public class MovementComponent extends Component {

    private Vector vector;
    private float speed;
    private boolean needUpdate;
    
    private boolean jumping;
    private boolean sneak;
    private boolean sprint;
    
    public MovementComponent(Entity entity) {
        super(entity);
    }

    public Vector getVector() {
        return vector;
    }

    public void setVector(Vector vector) {
        this.vector = vector;
    }
    
    @Override
    public void initialization(){
        this.vector = new Vector();
    }
    
    public double getVectorX(){
        return this.vector.getX();
    }
    
    public double getVectorY(){
        return this.vector.getY();
    }
    
    public double getVectorZ(){
        return this.vector.getZ();
    }
    
    public void setVectorX(double x){
        this.vector.setX(x);
    }
    
    public void setVectorY(double y){
        this.vector.setY(y);
    }
    
    public void setVectorZ(double z){
        this.vector.setZ(z);
    }
    
    public void move(double motionX, double motionY, double motionZ){
        Vector vector = new Vector(motionX, motionY, motionZ);
        vector.multiply(getSpeed());
        setVector(vector);
        needUpdate = true;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
    @Override
    public void update() {
        if(needUpdate){
            getEntity().getLocation().add(getVector());
            needUpdate = false;
        }
    }

    @Override
    public boolean autoUpdate() {
        return true;
    }
    
    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean flag) {
        this.jumping = flag;
    }

    public void setSneaking(boolean flag) {
        this.sneak = flag;
    }

    public void setSprinting(boolean flag) {
        this.sprint = flag;
    }

    public boolean isSneaking() {
        return sneak;
    }

    public boolean isSprinting() {
        return sprint;
    }

    @Override
    public String getID() {
        return "movement";
    }
}
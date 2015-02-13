package net.CyanWool.api.utils;

import java.util.Random;

import net.CyanWool.api.world.Location;
import net.CyanWool.api.world.World;

public class Vector{

    private static Random random = new Random();
    private static double epsilon = 0.000001;

    private double x;
    private double y;
    private double z;

    public Vector() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vector(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector add(Vector vec) {
        x += vec.x;
        y += vec.y;
        z += vec.z;
        return this;
    }

    public Vector subtract(Vector vec) {
        x -= vec.x;
        y -= vec.y;
        z -= vec.z;
        return this;
    }

    public Vector multiply(Vector vec) {
        x *= vec.x;
        y *= vec.y;
        z *= vec.z;
        return this;
    }

    public Vector divide(Vector vec) {
        x /= vec.x;
        y /= vec.y;
        z /= vec.z;
        return this;
    }

    public Vector copy(Vector vec) {
        x = vec.x;
        y = vec.y;
        z = vec.z;
        return this;
    }

    public double length() {
        return Math.sqrt(NumberConversions.square(x) + NumberConversions.square(y) + NumberConversions.square(z));
    }

    public double lengthSquared() {
        return NumberConversions.square(x) + NumberConversions.square(y) + NumberConversions.square(z);
    }

    public double distance(Vector o) {
        return Math.sqrt(NumberConversions.square(x - o.x) + NumberConversions.square(y - o.y) + NumberConversions.square(z - o.z));
    }


    public double distanceSquared(Vector o) {
        return NumberConversions.square(x - o.x) + NumberConversions.square(y - o.y) + NumberConversions.square(z - o.z);
    }

    public float angle(Vector other) {
        double dot = dot(other) / (length() * other.length());

        return (float) Math.acos(dot);
    }

    public Vector midpoint(Vector other) {
        x = (x + other.x) / 2;
        y = (y + other.y) / 2;
        z = (z + other.z) / 2;
        return this;
    }

    public Vector getMidpoint(Vector other) {
        double x = (this.x + other.x) / 2;
        double y = (this.y + other.y) / 2;
        double z = (this.z + other.z) / 2;
        return new Vector(x, y, z);
    }

    public Vector multiply(int m) {
        x *= m;
        y *= m;
        z *= m;
        return this;
    }

    public Vector multiply(double m) {
        x *= m;
        y *= m;
        z *= m;
        return this;
    }

    public Vector multiply(float m) {
        x *= m;
        y *= m;
        z *= m;
        return this;
    }

    public double dot(Vector other) {
        return x * other.x + y * other.y + z * other.z;
    }

    public Vector crossProduct(Vector o) {
        double newX = y * o.z - o.y * z;
        double newY = z * o.x - o.z * x;
        double newZ = x * o.y - o.x * y;

        x = newX;
        y = newY;
        z = newZ;
        return this;
    }

    public Vector normalize() {
        double length = length();

        x /= length;
        y /= length;
        z /= length;

        return this;
    }

    public Vector zero() {
        x = 0;
        y = 0;
        z = 0;
        return this;
    }

    public boolean isInAABB(Vector min, Vector max) {
        return x >= min.x && x <= max.x && y >= min.y && y <= max.y && z >= min.z && z <= max.z;
    }

    
    public boolean isInSphere(Vector origin, double radius) {
        return (NumberConversions.square(origin.x - x) + NumberConversions.square(origin.y - y) + NumberConversions.square(origin.z - z)) <= NumberConversions.square(radius);
    }

    public double getX() {
        return x;
    }

    public int getBlockX() {
        return NumberConversions.floor(x);
    }

    public double getY() {
        return y;
    }

    public int getBlockY() {
        return NumberConversions.floor(y);
    }

    public double getZ() {
        return z;
    }

    public int getBlockZ() {
        return NumberConversions.floor(z);
    }

    public Vector setX(int x) {
        this.x = x;
        return this;
    }

    public Vector setX(double x) {
        this.x = x;
        return this;
    }

    public Vector setX(float x) {
        this.x = x;
        return this;
    }
    
    public Vector setY(int y) {
        this.y = y;
        return this;
    }

    public Vector setY(double y) {
        this.y = y;
        return this;
    }


    public Vector setY(float y) {
        this.y = y;
        return this;
    }

    public Vector setZ(int z) {
        this.z = z;
        return this;
    }

    public Vector setZ(double z) {
        this.z = z;
        return this;
    }

    public Vector setZ(float z) {
        this.z = z;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector)) {
            return false;
        }

        Vector other = (Vector) obj;

        return Math.abs(x - other.x) < epsilon && Math.abs(y - other.y) < epsilon && Math.abs(z - other.z) < epsilon && (this.getClass().equals(obj.getClass()));
    }

    public Location toLocation(World world) {
        return new Location(world, x, y, z);
    }

    public Location toLocation(World world, float yaw, float pitch) {
        return new Location(world, x, y, z, yaw, pitch);
    }

    public static double getEpsilon() {
        return epsilon;
    }

    public static Vector getMinimum(Vector v1, Vector v2) {
        return new Vector(Math.min(v1.x, v2.x), Math.min(v1.y, v2.y), Math.min(v1.z, v2.z));
    }

    public static Vector getMaximum(Vector v1, Vector v2) {
        return new Vector(Math.max(v1.x, v2.x), Math.max(v1.y, v2.y), Math.max(v1.z, v2.z));
    }

    public static Vector getRandom() {
        return new Vector(random.nextDouble(), random.nextDouble(), random.nextDouble());
    }
}

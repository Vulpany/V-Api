package com.pany.mods.vapi.Math;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class ThreeDimSpace {
    /**

     Get Position At Angle

     */
    public static double[] GetPositionAtAngle(double AngleInDegrees) {
        double Angle = DegreesToPI(AngleInDegrees);
        double X = Math.sin(Angle);
        double Z = Math.cos(Angle);
        return new double[]{X,Z};
    }

    public static double[] GetPositionAtAngle(double AngleInDegrees,double Radius) {
        double Angle = DegreesToPI(AngleInDegrees);
        double X = Math.sin(Angle);
        double Z = Math.cos(Angle);
        return new double[]{X*Radius,Z*Radius};
    }
    /**

     Lazy PI Helping

     */
    public static double PIToDegrees(double Input) {
        return (Input/Math.PI) * 360;
    }

    public static double DegreesToPI(double Input) {
        return (Input/360) * Math.PI;
    }
    /**

     Misc

     */
    // Get Distance
    public static double GetDistance(double StartX,double StartY,double StartZ,double EndX,double EndY,double EndZ) {
        return Math.pow(Math.pow(EndX-StartX,2)+Math.pow(EndY-StartY,2)+Math.pow(EndZ-StartZ,2),0.5);
    }
    public static double GetDistance(BlockPos Start,BlockPos End) {
        return GetDistance(Start.getX(),Start.getY(),Start.getZ(),End.getX(),End.getY(),End.getZ());
    }
    public static double GetDistance(Vec3d Start,Vec3d End) {
        return GetDistance(Start.getX(),Start.getY(),Start.getZ(),End.getX(),End.getY(),End.getZ());
    }
    // Lerp
    public static double[] Lerp(double StartX,double StartY,double StartZ,double EndX,double EndY,double EndZ,double Percentage) {
        double ResultX = StartX + (EndX-StartX)*Percentage;
        double ResultY = StartY + (EndY-StartY)*Percentage;
        double ResultZ = StartZ + (EndZ-StartZ)*Percentage;
        return new double[]{ResultX,ResultY,ResultZ};
    }
    public static BlockPos Lerp(BlockPos Start, BlockPos End, double Percentage) {
        double[] Position = Lerp(Start.getX(),Start.getY(),Start.getZ(),End.getX(),End.getY(),End.getZ(),Percentage);
        return new BlockPos((int)Position[0],(int)Position[1],(int)Position[2]);
    }
    public static Vec3d Lerp(Vec3d Start, Vec3d End, double Percentage) {
        double[] Position = Lerp(Start.getX(),Start.getY(),Start.getZ(),End.getX(),End.getY(),End.getZ(),Percentage);
        return new Vec3d(Position[0],Position[1],Position[2]);
    }
    // Dot
    public static double[] Dot(double X,double Y,double Z) {
        double Distance = GetDistance(0,0,0,X,Y,Z);
        return new double[]{X / Distance,Y / Distance,Z / Distance};
    }
    public static BlockPos Dot(BlockPos Pos) {
        double Distance = GetDistance(0,0,0,Pos.getX(),Pos.getY(),Pos.getZ());
        return new BlockPos( (int)(Pos.getX()/Distance),(int)(Pos.getY()/Distance),(int)(Pos.getZ()/Distance) );
    }
    public static Vec3d Dot(Vec3d Pos) {
        double Distance = GetDistance(0,0,0,Pos.getX(),Pos.getY(),Pos.getZ());
        return new Vec3d( (Pos.getX()/Distance),(Pos.getY()/Distance),(Pos.getZ()/Distance) );
    }

}

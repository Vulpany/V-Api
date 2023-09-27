package com.pany.mods.vapi.Shapes;

import java.util.ArrayList;
import java.util.List;

import static com.pany.mods.vapi.Math.ThreeDimSpace.GetPositionAtAngle;
import static com.pany.mods.vapi.Math.ThreeDimSpace.Lerp;

public class ShapeDrawer {
    /**

     Ring

     */
    public static List<double[]> DrawRing(double Radius,double Points,boolean Randomize) {
        List<double[]> PointList = new ArrayList<>();
        // Randomizing Step
        double Randomizer = 0;
        if (Randomize) {
            Randomizer = Math.random()*360d;
        }
        // Creating Points
        double PointRadius = 360d / Points;
        for (int i = 0; i < Points; i++ ) {
            PointList.add( GetPositionAtAngle(PointRadius*i + Randomizer,Radius) );
        }
        return PointList;
    }
    /**

     Circle

     */
    public static List<double[]> DrawCircle(double Radius,double Points) {
        List<double[]> PointList = new ArrayList<>();
        // Creating Points
        for (int i = 0; i < Points; i++ ) {
            PointList.add( GetPositionAtAngle(Math.random() * 360,Radius*Math.random()) );
        }
        return PointList;
    }
    /**

     Draw Line

     */
    public static List<double[]> DrawLine(double StartX,double StartY,double StartZ,double EndX,double EndY,double EndZ,double Points,boolean Randomize) {
        List<double[]> PointList = new ArrayList<>();
        // Randomizing Step
        double Randomizer = 0;
        if (Randomize) {
            Randomizer = Math.random();
        }
        // Creating Points
        double PointLength = 1d / Points;
        for (int i = 0; i <= Points; i++ ) {
            PointList.add( Lerp(StartX,StartY,StartZ,EndX,EndY,EndZ, (Randomizer + PointLength * i )%1 ) );
        }
        return PointList;
    }


}

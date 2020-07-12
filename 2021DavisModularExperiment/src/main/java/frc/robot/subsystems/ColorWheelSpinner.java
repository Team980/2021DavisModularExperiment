/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.util.DavisDealWithThis.*;
import frc.robot.util.DavisDealWithThis;

public class ColorWheelSpinner extends SubsystemBase {    
    private WheelColor lookingAt = WheelColor.UNKNOWN;
    private WheelColor targetColor = WheelColor.UNKNOWN;

    private WheelColor lastSeen = WheelColor.UNKNOWN;

    private final ColorSensorV3 colorSensor = new ColorSensorV3(COLOR_SENSOR_PORT);
    private ColorMatch colorMatcher;

    private final WPI_TalonSRX motor = new WPI_TalonSRX(SPINNER_TALON_CHANNEL);

    public ColorWheelSpinner() {
        colorMatcher = new ColorMatch();
        colorMatcher.addColorMatch(RED);
        colorMatcher.addColorMatch(GREEN);
        colorMatcher.addColorMatch(BLUE);
        colorMatcher.addColorMatch(YELLOW);

    }


    public void runPositionControl() {
        double output = 0;

        if (lookingAt != WheelColor.UNKNOWN && targetColor != WheelColor.UNKNOWN) {
            output = getTargetDirection(lookingAt, targetColor);
        } 

        motor.set(output);
    }

    public boolean rotationControlColorTransition() {
        boolean result = lastSeen != lookingAt;
        lastSeen = lookingAt;

        return result;
    }

    private void tryUpdate() {
        // target color 
        if (targetColor.equals(WheelColor.UNKNOWN)) {
            String gameSpecificMessage = DriverStation.getInstance().getGameSpecificMessage();
            targetColor = WheelColor.fromString(gameSpecificMessage);
        } 

        // looking at 
        ColorMatchResult colorMatchResult = colorMatcher.matchClosestColor(colorSensor.getColor());
        if (colorMatchResult.confidence > COLOR_MATCHER_CONFIDENCE_THRESHOLD) {
            lookingAt = WheelColor.fromColor(colorMatchResult.color);
        }
    }

    
    public boolean isAtPositionTarget() {
        return lookingAt == targetColor && targetColor != WheelColor.UNKNOWN;
    }


    public void stopMotors() {
        motor.stopMotor();
    }


    @Override
    public void periodic() {
        // report info and stuff
        tryUpdate();

        SmartDashboard.putString("spinner target color", targetColor.toString());
        SmartDashboard.putString("looking at color", lookingAt.toString());
    }

    
    private static double getTargetDirection(WheelColor start, WheelColor end) {
        int clockwise = (end.ordinal()-start.ordinal()) % 4;
        int counterClockwise = (-clockwise) % 4;

        if (start.equals(end)) {
            return 0;
        } else if (clockwise < counterClockwise) {
            return -1;
        } else {
            return 1;
        }
    }

    enum WheelColor {
        RED, GREEN, BLUE, YELLOW, UNKNOWN;

        WheelColor() {}

        public static WheelColor fromColor(Color color) {
            if (color.equals(DavisDealWithThis.RED)) {
                return WheelColor.RED;
            } else if (color.equals(DavisDealWithThis.GREEN)) {
                return WheelColor.GREEN;
            } else if (color.equals(DavisDealWithThis.BLUE)) {
                return WheelColor.BLUE;
            } else if (color.equals(DavisDealWithThis.YELLOW)) {
                return WheelColor.YELLOW;
            } else {
                return WheelColor.UNKNOWN;
            }
        }

        public static WheelColor fromString(String color) {
            if (color.length() > 0) {
                switch (color.charAt(0)) {
                    case 'R': return WheelColor.RED; 
                    case 'G': return WheelColor.GREEN; 
                    case 'B': return WheelColor.BLUE; 
                    case 'Y': return WheelColor.YELLOW; 
                    default: return WheelColor.UNKNOWN; 
                }
            }
            return WheelColor.UNKNOWN;
        }
    }


    // private static int getColorIndex(Color color) {
    //     if (color.equals(RED)) {
    //         return 0;
    //     } else if (color.equals(GREEN)) {
    //         return 1;
    //     } else if (color.equals(BLUE)) {
    //         return 2;
    //     } else if (color.equals(YELLOW)) {
    //         return 3;
    //     } else {
    //         return -1;
    //     }
    // }
    // private static double lookup(Color currentColor, Color targetColor) {
    //     for (Response response : responses) {
    //         if (response.matches(currentColor, targetColor)) {
    //             return response.responseSpeed;
    //         }
    //     }
    // }
    // private static final Response[] responses = {
    //     new Response(RED,    RED,    0, 0), 
    //     new Response(RED,    GREEN,  1, 3),
    //     new Response(RED,    BLUE,   2, 2),
    //     new Response(RED,    YELLOW, 3, 1),
    //     new Response(GREEN,  RED,    3, 1),
    //     new Response(GREEN,  GREEN,  0, 0),
    //     new Response(GREEN,  BLUE,   1, 3),
    //     new Response(GREEN,  YELLOW, 2, 2),
    //     new Response(BLUE,   RED,    2, 2),
    //     new Response(BLUE,   GREEN,  3, 1),
    //     new Response(BLUE,   BLUE,   0, 0),
    //     new Response(BLUE,   YELLOW, 1, 3),
    //     new Response(YELLOW, RED,    1, 3),
    //     new Response(YELLOW, GREEN,  2, 2),
    //     new Response(YELLOW, BLUE,   3, 1),
    //     new Response(YELLOW, YELLOW, 0, 0)
    // };  
        // private class Response {
    //     private Color currentColor;
    //     private Color targetColor;
    //     // these refer to the actual field element's rotation, our motor spins opposite
    //     private int clockwiseDistance; 
    //     private int counterclockwiseDistance;

    //     Response(Color currentColor, Color targetColor, int clockwiseDistance, int counterclockwiseDistance) {
    //         this.currentColor = currentColor;
    //         this.targetColor = targetColor;
    //         this.clockwiseDistance = clockwiseDistance;
    //         this.counterclockwiseDistance = counterclockwiseDistance;
    //     }

    //     private double getTurnSpeed() {
    //         if (clockwiseDistance < counterclockwiseDistance) {

    //         }
    //     }

    //     private boolean matches(Color currentColor, Color targetColor) {
    //         return this.currentColor.equals(currentColor) 
    //             && this.targetColor.equals(targetColor);
    //     }
    // }

}

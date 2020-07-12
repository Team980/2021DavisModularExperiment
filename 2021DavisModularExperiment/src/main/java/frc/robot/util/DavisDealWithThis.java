/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class DavisDealWithThis {
    //Drivetrain
    public static final int LEFT_FRONT_CAN_ID = 1;
    public static final int LEFT_BACK_CAN_ID = 2;
    public static final int LEFT_TOP_CAN_ID = 3;

    public static final int RIGHT_FRONT_CAN_ID = 4;
    public static final int RIGHT_BACK_CAN_ID = 5;
    public static final int RIGHT_TOP_CAN_ID = 6;

    public static final int LEFT_DRIVE_ENCODER_CHANNEL_A = 0;
    public static final int LEFT_DRIVE_ENCODER_CHANNEL_B = 1;

    public static final int RIGHT_DRIVE_ENCODER_CHANNEL_A = 2;
    public static final int RIGHT_DRIVE_ENCODER_CHANNEL_B = 3;

    public static final double WHEEL_RADIUS_FEET = 2.0 / 12.0; // 2 inches

    //low gear PID constants
    public static final double LOW_PID_DRIVE_P = 1.0;//TODO: tune constants for low gear
	public static final double LOW_PID_DRIVE_I = 0.0;
	public static final double LOW_PID_DRIVE_D = 0.0;
    public static final double LOW_PID_DRIVE_F_KS = 1.0;//power necessary to barely start the wheel rotating from 0-1
    public static final double LOW_PID_DRIVE_F_KV = 1.0;//power to maintain a speed, calc based on 1 to maintain max speed

    //high gear PID constants
    public static final double HIGH_PID_DRIVE_P = 1.0;//TODO: tune constants for high gear
	public static final double HIGH_PID_DRIVE_I = 0.0;
	public static final double HIGH_PID_DRIVE_D = 0.0;
    public static final double HIGH_PID_DRIVE_F_KS = 1.0; //power necessary to barely start the wheel rotating from 0-1
    public static final double HIGH_PID_DRIVE_F_kSPowerSecondsPerFoot = 1.0; //power to maintain a speed, calc based on 1 to maintain max speed

    public static final double MAX_DRIVE_SPEED_FPS_HIGH = 16.0;// TODO: find max high gear speed
    public static final double MAX_DRIVE_SPEED_FPS_LOW = 4.0;// TODO: find max low gear speed

    public static final Boolean HIGH_GEAR = true;

    public static final double TURN_DEADBAND = 0.1;
    public static final double MOVE_DEADBAND = 0.1;

    public static final boolean DRIVE_PID_ENABLED_DEFAULT = false;

    
    //shifter
    public static final int SHIFTER_SOLENOID_HIGH_CHANNEL = 0;//todo: let davis deal with this
    public static final int SHIFTER_SOLENOID_LOW_CHANNEL = 1;
    public static final double SHIFT_POINT_HIGH = 4.5; // feet per second
    public static final double SHIFT_POINT_LOW = 4;
    public static final double SHIFTER_RADIUS_OF_CONVERGENCE = 0.5;

    public static final int SHOOTER_GATEKEEPER_SOLENOID_CLOSED_CHANNEL = 4;
    public static final int SHOOTER_GATEKEEPER_SOLENOID_OPENED_CHANNEL = 5;
    public static final int SHOOTER_TALON_CHANNEL = 10;
    public static final int SHOOTER_ENCODER_CHANNEL_A = 4; 
    public static final int SHOOTER_ENCODER_CHANNEL_B = 5;

    public static final double SHOOTER_RPS_MAX = 3000.0 / 60.0;
    
    public static final double SHOOTER_P = .1;
    public static final double SHOOTER_I = 0;
    public static final double SHOOTER_D = .0005;
    public static final double SHOOTER_FEEDFORWARD_KS = .05;//volts necessary to barely start the wheel rotating
    public static final double SHOOTER_FEEDFORWARD_KV = 12.0 / SHOOTER_RPS_MAX; //volts to maintain a speed, calc based on 12V to maintain max speed


    public static final int WHEEL_ROTATIONS = 3;
    public static final int COLORS_PER_ROTATION = 8;
    public static final int INITIAL_TRANSITION_COUNT = 0;

    public static final double POWER_PORT_TARGET_LOWEST_POINT_FEET = 6.8125;
    public static final double LIMELIGHT_MOUNTING_HEIGHT_FEET = 3.7;
    public static final double LIMELIGHT_MOUNTING_ELEVATION_RADIANS = Util.degreesToRadians(45); 
    public static final double LIMELIGHT_HORIZONTAL_FOV_PIXELS = 320;
    public static final double LIMELIGHT_VERTICAL_FOV_PIXELS = 240;
    public static final double LIMELIGHT_VERTICAL_FOV_RADIANS = Util.degreesToRadians(41);
    public static final double LIMELIGHT_HORIZONTAL_FOV_RADIANS = Util.degreesToRadians(54);

    public static final int PICKUP_ROLLER_DEPLOYED_SOLENOID_CHANNEL = 2; // todo: find
    public static final int PICKUP_ROLLER_RETRACT_SOLENOID_CHANNEL = 3; // todo: find

    public static final int BELT_TALON_CHANNEL = 11;// TODO: elec match
    public static final double BELT_SPEED = 0.8;

    public static final int INTAKE_TALON_LEFT_CHANNEL = 12; // TODO: elec match
    public static final int INTAKE_TALON_RIGHT_CHANNEL = 13; // TODO: elec match
    public static final double INTAKE_SPEED = 0.8;

    public static final int SPINNER_TALON_CHANNEL = 15; // TODO: elec match

    public static final double COLOR_MATCHER_CONFIDENCE_THRESHOLD = 0.6;
    public static final I2C.Port COLOR_SENSOR_PORT = I2C.Port.kOnboard;
    public static final Color RED = ColorMatch.makeColor(0.47, 0.37, 0.15);
    public static final Color GREEN = ColorMatch.makeColor(0.19, 0.55, 0.25);
    public static final Color BLUE = ColorMatch.makeColor(0.15, 0.44, 0.40);
    public static final Color YELLOW = ColorMatch.makeColor(0.31, 0.55, 0.13);

    public static final int SHOOTER_RATE = 5;//Rotations per second


    // commands
    public static final double DRIVE_BACK_AUTO_FEET = 1;
    public static final double DRIVE_BACK_POWER = 0.8;

}

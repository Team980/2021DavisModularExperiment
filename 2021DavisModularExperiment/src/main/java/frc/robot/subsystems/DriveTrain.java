/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.SpeedControllerPIDWrapper;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import static frc.robot.util.DavisDealWithThis.*;

public class DriveTrain extends SubsystemBase {
	private DifferentialDrive differentialDrive;
	private SpeedControllerPIDWrapper leftDrive;
	private SpeedControllerPIDWrapper rightDrive;

	private Encoder leftDriveEncoder;
	private Encoder rightDriveEncoder;
	
  	public DriveTrain() {
		var leftFront = new WPI_TalonSRX(LEFT_FRONT_CAN_ID);
		var leftBack = new WPI_TalonSRX(LEFT_BACK_CAN_ID);
		var leftTop = new WPI_TalonSRX(LEFT_TOP_CAN_ID);
		leftTop.setInverted(true);

		leftDriveEncoder = new Encoder(LEFT_DRIVE_ENCODER_CHANNEL_A, LEFT_DRIVE_ENCODER_CHANNEL_B, false, EncodingType.k4X);
		leftDriveEncoder.setDistancePerPulse(Math.PI * 2 * WHEEL_RADIUS_FEET / 2048.0);
		leftDrive = new SpeedControllerPIDWrapper(new SpeedControllerGroup(leftFront, leftBack, leftTop), leftDriveEncoder);

		var rightFront = new WPI_TalonSRX(RIGHT_FRONT_CAN_ID);
		var rightBack = new WPI_TalonSRX(RIGHT_BACK_CAN_ID);
		var rightTop = new WPI_TalonSRX(RIGHT_TOP_CAN_ID);
		//rightTop.setInverted(true); //TODO: fix electrical reversing of this motor

		rightDriveEncoder = new Encoder(RIGHT_DRIVE_ENCODER_CHANNEL_A, RIGHT_DRIVE_ENCODER_CHANNEL_B, false, EncodingType.k4X);
		rightDriveEncoder.setDistancePerPulse(Math.PI * 2 * WHEEL_RADIUS_FEET / 2048.0);
		rightDrive = new SpeedControllerPIDWrapper(new SpeedControllerGroup(rightFront, rightBack, rightTop), rightDriveEncoder);
		
		differentialDrive = new DifferentialDrive(leftDrive, rightDrive);
	}
	  
	public void setDrivePidEnabled(boolean pidEnabled) {
		leftDrive.setPidEnabled(pidEnabled);
		rightDrive.setPidEnabled(pidEnabled);
	}

	@Override
	public void periodic() {
		SmartDashboard.putNumber("Left Encoder Velocity", leftDriveEncoder.getRate());
		SmartDashboard.putNumber("Right Encoder Velocity", rightDriveEncoder.getRate());
		SmartDashboard.putNumber("Left Encoder Distance", leftDriveEncoder.getDistance());
		SmartDashboard.putNumber("Right Encoder Distance", rightDriveEncoder.getDistance());
		
		// This method will be called once per scheduler run
	}

  	public void arcadeDrive(double move, double turn) {
		SmartDashboard.putNumber("Throttle", move);//to help with finding the feed forward Ks coefficient
		SmartDashboard.putNumber("Steering", turn);
		differentialDrive.arcadeDrive(move, turn);
	}

	public double getLeftEncoderDistance() {
		return leftDriveEncoder.getDistance();
	}

	public double getLeftRate(){
		return leftDriveEncoder.getRate();
	}

	public double getRightRate(){
		return rightDriveEncoder.getRate();
	}

	public double getRightEncoderDistance() {
		return rightDriveEncoder.getDistance();
	}


}

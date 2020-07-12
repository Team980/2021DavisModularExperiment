/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.util.Util;

import static frc.robot.util.DavisDealWithThis.*;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Shooter extends PIDSubsystem {
  private WPI_TalonSRX motor;
  private Encoder shootEncoder;
  private SimpleMotorFeedforward shootFF;
   
  public Shooter() {
    super(
      new PIDController(SHOOTER_P, SHOOTER_I, SHOOTER_D));
    getController().setTolerance(1);//rotations per second

    motor = new WPI_TalonSRX(SHOOTER_TALON_CHANNEL);

    shootEncoder = new Encoder(SHOOTER_ENCODER_CHANNEL_A, SHOOTER_ENCODER_CHANNEL_B, true, EncodingType.k4X);
    shootEncoder.setDistancePerPulse(1.0 / 2048.0);

    shootFF = new SimpleMotorFeedforward(SHOOTER_FEEDFORWARD_KS, SHOOTER_FEEDFORWARD_KV);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    motor.setVoltage(output + shootFF.calculate(setpoint));
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return shootEncoder.getRate();
  }

  public void manual(double throttle){
    displayEncoderRate();
    motor.set(-Util.applyDeadband(throttle, .1));
  }

  public void fire(double targetRPS){
    displayEncoderRate();
    setSetpoint(targetRPS);
  }

  public Encoder getEncoder(){
    return shootEncoder;
  }

  public void displayEncoderRate(){
    SmartDashboard.putNumber("Shooter RPM", shootEncoder.getRate() * 60.0);
  }
}
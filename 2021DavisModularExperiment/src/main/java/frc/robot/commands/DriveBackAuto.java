/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import static frc.robot.util.DavisDealWithThis.*;

public class DriveBackAuto extends CommandBase {
  private DriveTrain driveTrain;

  private double initialLeftDistance;

  private double desiredDistance;
  // why should we care about both sides? A: because i like symmetry

  public DriveBackAuto(DriveTrain driveTrain, double desiredDistance) {
    this.driveTrain = driveTrain;
    this.desiredDistance = desiredDistance;
    addRequirements(driveTrain);
  }

  @Override
  public void initialize() {
    initialLeftDistance = driveTrain.getLeftEncoderDistance();
  }

  @Override
  public void execute() {
    driveTrain.arcadeDrive(DRIVE_BACK_POWER, 0);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return Math.abs(driveTrain.getLeftEncoderDistance()-initialLeftDistance) > desiredDistance || Math.abs(driveTrain.getRightEncoderDistance() - initialLeftDistance) > desiredDistance;
  }
}

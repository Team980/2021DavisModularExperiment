/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ConstantRateShooter extends CommandBase {
  /**
   * Creates a new ConstantRateShooter.
   */
  private Shooter shooter;
  private double targetRate;
  public ConstantRateShooter(Shooter shooter) {
    this.shooter = shooter;
    this.targetRate = 2500;//RPM
    addRequirements(shooter);
  }

  
  @Override
  public void initialize() {
    shooter.enable();
  }

  
  @Override
  public void execute() {
   shooter.fire(targetRate / 60.0);//convert to RPS
  }

  
  @Override
  public void end(boolean interrupted) {
    shooter.fire(0);
    shooter.disable();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

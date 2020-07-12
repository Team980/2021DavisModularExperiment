/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShifterSubsystem;

public class SetGear extends CommandBase {
 
  private boolean isSet;
  private ShifterSubsystem shifter;

  public SetGear(ShifterSubsystem shifter, boolean isSet) {
    this.shifter = shifter;
    this.isSet = isSet;
    addRequirements(shifter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   shifter.setGear(isSet);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

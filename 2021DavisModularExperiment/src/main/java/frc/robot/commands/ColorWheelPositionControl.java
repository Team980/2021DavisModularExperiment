/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorWheelSpinner;

public class ColorWheelPositionControl extends CommandBase {
    private ColorWheelSpinner spinner;

    //takes in the color spinner
    public ColorWheelPositionControl(ColorWheelSpinner spinner) {
        this.spinner = spinner;
        addRequirements(spinner);
    }

    @Override
    public void initialize() {
    }

    //move the spinner
    @Override
    public void execute() {
        spinner.runPositionControl();
    }

    //this complex shiz right here stops the motors
    @Override
    public void end(boolean interrupted) {
        spinner.stopMotors();
    }

    //stops if the spinner is at the desired position
    @Override
    public boolean isFinished() {
        return spinner.isAtPositionTarget();
    }
}

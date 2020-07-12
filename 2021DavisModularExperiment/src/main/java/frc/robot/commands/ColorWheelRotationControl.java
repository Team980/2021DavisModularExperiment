/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorWheelSpinner;
import static frc.robot.util.DavisDealWithThis.*;

public class ColorWheelRotationControl extends CommandBase {
    private ColorWheelSpinner spinner;

    private int transitionsSeen;

    public ColorWheelRotationControl(ColorWheelSpinner spinner) {
        this.spinner = spinner;
        addRequirements(spinner);
    }

    @Override
    public void initialize() {
        transitionsSeen = 0;
    }

    @Override
    public void execute() {
        if (spinner.rotationControlColorTransition()) {
            transitionsSeen++;
        }
    }

    @Override
    public void end(boolean interrupted) {
        spinner.stopMotors();
    }

    @Override
    public boolean isFinished() {
        return transitionsSeen > WHEEL_ROTATIONS * COLORS_PER_ROTATION;
    }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.commands.AutoShift;
import frc.robot.commands.ConstantRateShooter;
import frc.robot.commands.DriveBackAuto;
import frc.robot.commands.RunBelt;
import frc.robot.commands.SetGear;
import frc.robot.commands.ToggleDeployRoller;
//import frc.robot.commands.VelocityControlledShooter;
import frc.robot.subsystems.Belt;
//import frc.robot.commands.ColorWheelPositionControl;
//import frc.robot.commands.ColorWheelRotationControl;

//import frc.robot.subsystems.ColorWheelSpinner;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.ShifterSubsystem;
//import frc.robot.subsystems.RunBelt;
import frc.robot.subsystems.Shooter;
//import frc.robot.subsystems.ShooterPID;

import static frc.robot.util.DavisDealWithThis.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
	  private static final PilotControllers throttle = new PilotControllers("flight stick" , 0);
    private static final PilotControllers wheel = new PilotControllers("steering wheel" , 1);
    private static final PilotControllers xBox = new PilotControllers("xbox" , 2);
    private static final Joystick prajBox = new Joystick(3);

    private static final DriveTrain driveTrain = new DriveTrain();
    private static final Intake intake = new Intake();
    private static final Shooter shooter = new Shooter();
    private static final Belt belt = new Belt(shooter);
    private static final ShifterSubsystem shifter = new ShifterSubsystem(driveTrain);
    //private static final ColorWheelSpinner colorWheelSpinner = new ColorWheelSpinner();


    //private static final Command colorWheelPositionControl = new ColorWheelPositionControl(colorWheelSpinner);
    //private static final Command colorWheelRotationControl = new ColorWheelRotationControl(colorWheelSpinner);
    //private static final Command toggleRollerDeployed = new ToggleDeployRoller(intake);
    //private static final Command shooterCommand = new ConstantRateShooter;(shooter, 2000);
    private static final Command driveBackAuto = new DriveBackAuto(driveTrain, DRIVE_BACK_AUTO_FEET);
    
    public RobotContainer() {        
        driveTrain.setDefaultCommand(new RunCommand(
            () -> driveTrain.arcadeDrive(-throttle.getAnalogStick("up/down"), wheel.getAnalogStick("left/right")), 
            driveTrain
        ));

        shifter.setDefaultCommand(new AutoShift(shifter));
        // shifter.setDefaultCommand(new RunCommand(
        //         () -> shifter.setGear(true),
        //         shifter
        //     ));


        intake.setDefaultCommand(new RunCommand(
            () -> intake.run(xBox.getAnalogStick("right trigger") - xBox.getAnalogStick("left trigger")),
            intake
        ));

        shooter.setDefaultCommand(new RunCommand(
            () -> shooter.manual(xBox.getAnalogStick("right stick y")),
            shooter
        ));

        // belt.setDefaultCommand(new RunCommand(
        //     () -> belt.run(xBox.getTriggerAxis(Hand.kRight) - xBox.getTriggerAxis(Hand.kLeft)),
        //     belt
        // ));//shoulder trigger analog control for belts
 
        configureButtonBindings();
    }
    
    private void configureButtonBindings() {

        /*new JoystickButton(xBox, XboxController.Button.kA.value)
            .whenPressed(() -> shooter.setGatekeeperOpen(true));

        new JoystickButton(xBox, XboxController.Button.kA.value)
            .whenReleased(() -> shooter.setGatekeeperOpen(false));*/

        new JoystickButton(xBox, 
        )
            .whenPressed(new ConstantRateShooter(shooter));
        
        new JoystickButton(xBox, Button.kX.value)
            .whenPressed(new RunCommand(() -> shooter.manual(xBox.getY(Hand.kRight)),
            shooter
            ));

        new JoystickButton(prajBox, 2) //the one will change to the port on the praj box
            .whenHeld(new SetGear(shifter, true)); //set to high gear

        new JoystickButton(prajBox, 3)
            .whenHeld(new SetGear(shifter, false)); //set to low gear
        
         new JoystickButton(xBox, XboxController.Button.kBumperLeft.value)
             .whenHeld(new RunBelt(belt, 0.8));

         new JoystickButton(xBox, XboxController.Button.kBumperRight.value)
             .whenHeld(new RunBelt(belt, -0.8));

        new JoystickButton(xBox, XboxController.Button.kStart.value)
            .whenPressed(new ToggleDeployRoller(intake, true));

        new JoystickButton(xBox, XboxController.Button.kBack.value)
            .whenPressed(new ToggleDeployRoller(intake, false));


        // new JoystickButton(xBox, XboxController.Button.kBumperLeft.value)
        //     .whenPressed(colorWheelRotationControl);

        // new JoystickButton(xBox, XboxController.Button.kBumperRight.value)
        //     .whenPressed(colorWheelPositionControl);
    }

    public Command getAutoCommand() {
        return driveBackAuto;
    }
}

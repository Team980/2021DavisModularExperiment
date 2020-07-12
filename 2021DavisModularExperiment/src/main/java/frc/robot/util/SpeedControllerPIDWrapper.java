/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;

import static frc.robot.util.DavisDealWithThis.*;
/**
 * Add your docs here.
 */
public class SpeedControllerPIDWrapper implements SpeedController {
    private PIDController pidController;
    private SimpleMotorFeedforward feedFowardController;

    private SpeedController speedController;
    private Encoder encoder;
    private double setSpeed;

    private boolean pidEnabled;
    
    public SpeedControllerPIDWrapper(SpeedController speedController, Encoder encoder) {
        this.feedFowardController = new SimpleMotorFeedforward(LOW_PID_DRIVE_F_KS, LOW_PID_DRIVE_F_KV);
        this.pidController = new PIDController(LOW_PID_DRIVE_P, LOW_PID_DRIVE_I, LOW_PID_DRIVE_D);

        this.speedController = speedController;
        this.encoder = encoder;
        this.setSpeed = 0;

        this.pidEnabled = DRIVE_PID_ENABLED_DEFAULT;
    }

    public void set(double setSpeed) {
        this.setSpeed = setSpeed;

        if (pidEnabled) {
            double pidOutput = pidController.calculate(encoder.getRate()/MAX_DRIVE_SPEED_FPS_LOW, setSpeed);
            double feedFowardOutput = feedFowardController.calculate(setSpeed);
            speedController.set(pidOutput + feedFowardOutput);
        } else {
            speedController.set(setSpeed);
        }
    }

    public void setPidEnabled(boolean pidEnabled) {
        this.pidEnabled = pidEnabled;
    }

    public double get() {
        return setSpeed;
    }

    public void setInverted(boolean isInverted) {
        speedController.setInverted(isInverted);
    }

    public boolean getInverted() {
        return speedController.getInverted();
    }

    public void disable() {
        speedController.disable();
    }

    public void stopMotor() {
        set(0);
        speedController.stopMotor();
    }

    public void pidWrite(double setSpeed) {
        set(setSpeed);
    }
}

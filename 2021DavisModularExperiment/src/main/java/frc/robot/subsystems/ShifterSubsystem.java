/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.util.DavisDealWithThis.*;

public class ShifterSubsystem extends SubsystemBase {
    private DoubleSolenoid shifter;
    private DriveTrain driveTrain;

    public ShifterSubsystem(DriveTrain driveTrain) {
        shifter = new DoubleSolenoid(SHIFTER_SOLENOID_HIGH_CHANNEL, SHIFTER_SOLENOID_LOW_CHANNEL);
        this.driveTrain = driveTrain;
    }

    public boolean isHighGear() {
        return shifter.get() == Value.kForward; 
    }

    public void setGear(boolean isHigh) {
        if (isHigh) {
            shifter.set(Value.kForward);
        } else {
            shifter.set(Value.kReverse);
        }
    } 

    public void autoShift(){
        if (!isHighGear() && shouldShiftUp()){
            setGear(true);
        } else if (isHighGear() && shouldShiftDown()) {
            setGear(false);
        }
    }
    
	public boolean shouldShiftUp()  {
		return Math.abs(driveTrain.getLeftRate()) > SHIFT_POINT_HIGH
			|| Math.abs(driveTrain.getRightRate()) > SHIFT_POINT_HIGH;
    }

    public boolean shouldShiftDown() {
		return Math.abs(driveTrain.getLeftRate()) < SHIFT_POINT_LOW
			&& Math.abs(driveTrain.getRightRate()) < SHIFT_POINT_LOW;
    }
    
    @Override
    public void periodic() {
        SmartDashboard.putBoolean("IS HIGH GEAR", isHighGear());

    }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.util.DavisDealWithThis.*;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Belt extends SubsystemBase {
  private WPI_TalonSRX motor;
  private Shooter shooter;

  public Belt(Shooter shooter) {
    motor = new WPI_TalonSRX(BELT_TALON_CHANNEL);
    this.shooter = shooter;
  }

  public void run(double speed) {
    motor.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

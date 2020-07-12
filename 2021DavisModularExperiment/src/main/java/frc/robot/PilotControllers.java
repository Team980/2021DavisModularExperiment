/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController.Button;

/**
 * Add your docs here.
 */
public class PilotControllers implements PilotInterface{
    private Joystick notXbox;
    private XboxController xbox;
    private int specificType; //0=flight stick, 1=steering wheel, 2=xbox controller, 3=Praj Box, 4 and on open for new options

    public PilotControllers(String type , int port){
        if (type.equals("flight stick")){
            specificType = 0;
            notXbox = new Joystick(port);
        }
        else if (type.equals("steering wheel")){
            specificType = 1;
            notXbox = new Joystick(port);
        }
        else if (type.equals("xbox")){
            specificType = 2;
            xbox = new XboxController(port);
        }
        else if (type.equals("praj box")){
            specificType = 3;
            notXbox = new Joystick(port);
        }
        else{
            specificType = -1;
            notXbox = new Joystick(port);
        }

    }//end constructor

    public double getAnalogStick(String axis){
        if (axis.equals("up/down") && (specificType == 0 || specificType == 2)){
            if (specificType == 0){
                return notXbox.getY();
            }
            else{
                return xbox.getY(Hand.kLeft);
            }
        }
        else if (axis.equals("left/right") && (specificType == 0 || specificType == 1 || specificType == 2)){
            if (specificType < 2){
                return notXbox.getX();
            }
            else{
                return xbox.getX(Hand.kLeft);
            }
        }
        else if (axis.equals("left stick y") && (specificType == 0 || specificType == 2)){
            if (specificType == 0){
                return notXbox.getY();
            }
            else{
                return xbox.getY(Hand.kLeft);
            }
        }
        else if (axis.equals("right stick y") && (specificType == 0 || specificType == 2)){
            if (specificType == 0){
                return notXbox.getY();
            }
            else{
                return xbox.getY(Hand.kRight);
            }
        }
        else if (axis.equals("left stick x") && (specificType == 0 || specificType == 1 || specificType == 2)){
            if (specificType < 2){
                return notXbox.getX();
            }
            else{
                return xbox.getX(Hand.kLeft);
            }
        }
        else if (axis.equals("right stick x") && (specificType == 0 || specificType == 1 || specificType == 2)){
            if (specificType < 2){
                return notXbox.getX();
            }
            else{
                return xbox.getX(Hand.kRight);
            }
        }
        else if (axis.equals("left trigger") && specificType == 2){
            return xbox.getTriggerAxis(Hand.kLeft);
        }
        else if (axis.equals("right trigger") && specificType == 2){
            return xbox.getTriggerAxis(Hand.kRight);
        }
        else{
            return 0;
        }

    }//end getAnalog

    public int getDigitalButton(String button){
        if (specificType < 2){
            return Integer.parseInt(button);
        }
        else if(specificType == 2){
            if (button.equals("a")){
                return Button.kA.value;
            }
            else if(button.equals("b")){
                return Button.kB.value;
            }
            else if(button.equals("x")){
                return Button.kX.value;
            }
            else if(button.equals("y")){
                return Button.kY.value;
            }
            else if(button.equals("lb")){
                return Button.kBumperLeft.value;
            }
            else if(button.equals("lr")){
                return Button.kBumperRight.value;
            }
            else if(button.equals("back")){
                return Button.kBack.value;
            }
            else if(button.equals("start")){
                return Button.kStart.value;
            }
        }
        return -1;
    }//end getDigital
}

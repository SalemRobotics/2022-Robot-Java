package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class Climber extends SubsystemBase
{
    TalonSRX motorA, motorB;
    public DoubleSolenoid solenoid;

    public Climber()
    {
        motorA = new TalonSRX(ClimberConstants.kMotorAPort);
        motorB = new TalonSRX(ClimberConstants.kMotorBPort);
        solenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, ClimberConstants.kForwardChannel, ClimberConstants.kReverseChannel);

        motorB.follow(motorA);
        motorB.setInverted(true);

        solenoid.set(Value.kForward);
    }

    public void climb(double percent) 
    {
        motorA.set(ControlMode.PercentOutput, percent);
    }
}

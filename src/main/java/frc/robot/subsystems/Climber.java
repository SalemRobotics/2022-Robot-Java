package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import frc.robot.Constants.ClimberConstants;

public class Climber extends SubsystemBase {
    WPI_TalonSRX motorA, motorB;
    DoubleSolenoid solenoid;
    DigitalInput limitSwitch;

    public Climber() {
        motorA = new WPI_TalonSRX(ClimberConstants.kMotorAPort);
        motorB = new WPI_TalonSRX(ClimberConstants.kMotorBPort);
        motorB.follow(motorA);
        motorB.setInverted(InvertType.OpposeMaster);

        solenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, ClimberConstants.kForwardChannel, ClimberConstants.kReverseChannel);

        limitSwitch = new DigitalInput(ClimberConstants.limitSwitchPort);
    }

    /**
     * Sets TalonSRX current limit and motor speeds for the climber
     * @param speed Percent of output speed
     * @param currentLimit Whether currentLimit is enabled or disabled
     */
    public void climb(double speed, boolean currentLimit) {
        motorA.configPeakCurrentLimit(20, 500);
        motorB.configPeakCurrentLimit(20, 500);
        motorA.enableCurrentLimit(currentLimit);
        motorB.enableCurrentLimit(currentLimit);
        if (limitSwitch.get()) {
            halt();
            return;
        }
        motorA.set(TalonSRXControlMode.PercentOutput, speed);
    }

    /** Toggles pneumatic brake for climber. */
    public void brake() {
        solenoid.set(Value.kReverse);
    }

    /** Stops all motors. */
    public void halt() {
        motorA.set(TalonSRXControlMode.PercentOutput, 0.0);
    }


    public void releaseBrake() {
        solenoid.set(Value.kForward);
    }
}

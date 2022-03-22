package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import frc.robot.Constants.ClimberConstants;

public class Climber extends SubsystemBase {
    WPI_TalonSRX motorA, motorB;
    DoubleSolenoid solenoid;

    public Climber() {
        motorA = new WPI_TalonSRX(ClimberConstants.kMotorAPort);
        motorB = new WPI_TalonSRX(ClimberConstants.kMotorBPort);
        solenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, ClimberConstants.kForwardChannel, ClimberConstants.kReverseChannel);

        motorB.follow(motorA);
    }

    /** Sets TalonSRX motor speed.
     * @param speed speed motor will run at */
    public void climb(double speed) {
        motorA.set(TalonSRXControlMode.PercentOutput, speed);
    }

    // Toggles pneumatic brake for climber.
    public void brake() {
        solenoid.toggle();
    }

    // Stops all motors.
    public void halt() {
        motorA.set(TalonSRXControlMode.PercentOutput, 0.0);
    }
}

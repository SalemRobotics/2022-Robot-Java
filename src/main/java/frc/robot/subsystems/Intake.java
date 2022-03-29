package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import frc.robot.Constants.IntakeConstants;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/** Intake class that contains 2 rev CAN SparkMaxes. */
public class Intake extends SubsystemBase {

  CANSparkMax leftNeo, rightNeo;
  DoubleSolenoid leftSolenoid, rightSolenoid;

  public Intake() {
    leftNeo = new CANSparkMax(IntakeConstants.leftSparkMaxID, MotorType.kBrushless);
    rightNeo = new CANSparkMax(IntakeConstants.rightSparkMaxID, MotorType.kBrushless);

    leftSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, IntakeConstants.leftSolenoidChannelA, IntakeConstants.leftSolenoidChannelB);
    rightSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, IntakeConstants.rightSolenoidChannelA, IntakeConstants.rightSolenoidChannelB);
    leftSolenoid.set(Value.kReverse);
    rightSolenoid.set(Value.kReverse);
  }

/**
 * spins left and right neo the same direction
 * @param speed this is speed in a value from 0 to 1
 */
  public void collect(double speed) {
    leftNeo.set(speed);
    rightNeo.set(-speed);
  }

  public void extend() {
    leftSolenoid.set(Value.kForward);
    rightSolenoid.set(Value.kForward);
  }

  public void retract() {
    leftSolenoid.set(Value.kReverse);
    rightSolenoid.set(Value.kReverse);
  }

  public void halt() {
    leftNeo.set(0.0);
    rightNeo.set(0.0);
  }
}
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import frc.robot.Constants.IntakeConstants;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/** Intake class that contains 2 rev CAN SparkMaxes. */
public class Intake extends SubsystemBase {

  CANSparkMax leftNeo, rightNeo;
  Solenoid leftSolenoid, rightSolenoid;

  public Intake() {
    leftNeo = new CANSparkMax(IntakeConstants.leftSparkMaxID, MotorType.kBrushless);
    rightNeo = new CANSparkMax(IntakeConstants.rightSparkMaxID, MotorType.kBrushless);

    leftSolenoid = new Solenoid(PneumaticsModuleType.REVPH, IntakeConstants.leftSolenoidChannel);
    rightSolenoid = new Solenoid(PneumaticsModuleType.REVPH, IntakeConstants.rightSolenoidChannel);
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
    leftSolenoid.set(true);
    rightSolenoid.set(true);
  }

  public void retract() {
    leftSolenoid.set(false);
    rightSolenoid.set(false);
  }

  public void halt() {
    leftNeo.set(0.0);
    rightNeo.set(0.0);
  }
}
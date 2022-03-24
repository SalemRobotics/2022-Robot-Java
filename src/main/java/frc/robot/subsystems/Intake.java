package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import frc.robot.Constants.IntakeConstants;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/** Intake class that contains 2 rev CAN SparkMaxes. */
public class Intake extends SubsystemBase {

  CANSparkMax leftNeo, rightNeo;

  public Intake() {
    leftNeo = new CANSparkMax(IntakeConstants.leftSparkMaxID, MotorType.kBrushless);
    rightNeo = new CANSparkMax(IntakeConstants.rightSparkMaxID, MotorType.kBrushless);

  }

/**
 * spins left and right neo the same direction
 * @param speed this is speed in a value from 0 to 1
 */
  public void autobotsRollout(double speed){
    leftNeo.set(speed);
    rightNeo.set(-speed);
  }
}
package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;

/** Command to set arcade drive */
public class DefaultDrive extends CommandBase {
  private final Drivetrain drive;
  private final DoubleSupplier forward;
  private final DoubleSupplier rotation;

  public DefaultDrive(Drivetrain subsystem, DoubleSupplier fwd, DoubleSupplier rot) {
    drive = subsystem;
    forward = fwd;
    rotation = rot;
    addRequirements(drive);
  }

  @Override
  public void execute() {
    drive.arcadeDrive(forward.getAsDouble(), rotation.getAsDouble());
  }
}

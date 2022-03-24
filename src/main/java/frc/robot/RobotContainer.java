package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.XBConstants;

import frc.robot.commands.DefaultDrive;
import frc.robot.commands.IntakeIn;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class RobotContainer {

  private final Drivetrain robotDrive = new Drivetrain();
  private final Intake intake = new Intake();

  XboxController driverController = new XboxController(XBConstants.drivePort);
  XboxController operatorController = new XboxController(XBConstants.opPort);

  public RobotContainer() {
    configureButtonBindings();

    // Sets default command for drivetrain
    robotDrive.setDefaultCommand(
      new DefaultDrive(robotDrive, driverController::getLeftY, driverController::getRightX)
    );
  }

  private void configureButtonBindings() {
    new JoystickButton(operatorController, Button.kRightBumper.value).whenHeld(new IntakeIn(intake));
  }

}

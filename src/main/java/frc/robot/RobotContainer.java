package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.XBConstants;

import frc.robot.commands.DefaultDrive;
import frc.robot.commands.DriveAuton;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {

  private final Drivetrain robotDrive = new Drivetrain();
  private final DriveAuton driveAuton = new DriveAuton();

  XboxController driverController = new XboxController(XBConstants.drivePort);
  XboxController operatorController = new XboxController(XBConstants.opPort);

  public RobotContainer() {
    configureButtonBindings();

    // Sets default command for drivetrain
    robotDrive.setDefaultCommand(
      new DefaultDrive(robotDrive, driverController::getLeftY, driverController::getRightX)
    );
  }

  public Command getAutonomousCommand() {
    return driveAuton.getCommand(robotDrive);
  }

  private void configureButtonBindings() {

  }

}
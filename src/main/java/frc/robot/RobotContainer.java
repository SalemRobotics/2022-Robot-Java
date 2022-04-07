package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.XBConstants;
import frc.robot.command_groups.ClimbBrakeGroup;
import frc.robot.command_groups.ClimbDownGroup;
import frc.robot.command_groups.ClimbUpGroup;
import frc.robot.command_groups.IntakeInGroup;
import frc.robot.command_groups.IntakeOutGroup;
import frc.robot.command_groups.ShootAutoGroup;
import frc.robot.command_groups.ShootIndexGroup;
import frc.robot.command_groups.ShootIntakeAuto;
import frc.robot.command_groups.StopIntakeGroup;
import frc.robot.commands.Auto;
import frc.robot.commands.DefaultDrive;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Indexer;

public class RobotContainer {

  private final Drivetrain robotDrive = new Drivetrain();
  private final Intake intake = new Intake();
  
  private final Shooter shooter = new Shooter();
  private final Climber climber = new Climber();
  private final Indexer indexer = new Indexer();

  private final SendableChooser<Command> aChooser = new SendableChooser<>();

  private final XboxController driverController = new XboxController(XBConstants.drivePort);
  private final XboxController operatorController = new XboxController(XBConstants.opPort);

  public RobotContainer() {
    configureButtonBindings();

    // Sets default command for drivetrain
    robotDrive.setDefaultCommand(
      new DefaultDrive(robotDrive, driverController::getLeftY, driverController::getRightX, false)
    );

    aChooser.setDefaultOption("Shoot and Taxi", new ShootAutoGroup(shooter, robotDrive, indexer));
    aChooser.addOption("Shoot-taxi and intake", new ShootIntakeAuto(shooter, robotDrive, indexer, intake));
    aChooser.addOption("Taxi", new Auto(robotDrive, 0.3));
    SmartDashboard.putData(aChooser);
  }

  public Command getAutonomousCommand() {
    return aChooser.getSelected();
  }

  private void configureButtonBindings() {
    // Switch drive direction
    new JoystickButton(driverController, Button.kA.value).toggleWhenPressed(
      new DefaultDrive(robotDrive, driverController::getLeftY, driverController::getRightX, true));

    // Intake in
    new JoystickButton(operatorController, Button.kRightBumper.value).whenHeld(new IntakeInGroup(intake, indexer, shooter));
    new JoystickButton(operatorController, Button.kRightBumper.value).whenReleased(new StopIntakeGroup(intake, indexer));

    //Intake out 
    new JoystickButton(operatorController, Button.kLeftBumper.value).whenHeld(new IntakeOutGroup(intake, indexer, shooter));

    // Shooter button
    new JoystickButton(operatorController, Button.kX.value).whenHeld(new ShootIndexGroup(shooter, indexer));
    
    // Climber button configs TODO: please, let's make this differently i hate this
    new JoystickButton(operatorController, Button.kA.value).whenPressed(new ClimbUpGroup(climber));
    new JoystickButton(operatorController, Button.kA.value).whenReleased(new ClimbBrakeGroup(climber));
    new JoystickButton(operatorController, Button.kB.value).whenPressed(new ClimbDownGroup(climber));
    new JoystickButton(operatorController, Button.kB.value).whenReleased(new ClimbBrakeGroup(climber));
  }
}

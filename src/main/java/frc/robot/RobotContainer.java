package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.XBConstants;
import frc.robot.command_groups.ClimbBrakeGroup;
import frc.robot.command_groups.ClimbDownGroup;
import frc.robot.command_groups.ClimbUpGroup;
import frc.robot.command_groups.ShootIndexGroup;
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.IntakeIn;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Indexer;

public class RobotContainer {

  private final Drivetrain robotDrive = new Drivetrain();
  private final Intake intake = new Intake();
  
  private final Shooter shooter = new Shooter();
  public final Climber climber = new Climber();
  private final Indexer indexer = new Indexer();

  private final XboxController driverController = new XboxController(XBConstants.drivePort);
  private final XboxController operatorController = new XboxController(XBConstants.opPort);

  public RobotContainer() {
    configureButtonBindings();

    // Sets default command for drivetrain
    robotDrive.setDefaultCommand(
      new DefaultDrive(robotDrive, driverController::getLeftY, driverController::getRightX)
    );
  }

  private void configureButtonBindings() {
    new JoystickButton(operatorController, Button.kRightBumper.value).whenHeld(new IntakeIn(intake));
    // Shooter button
    new JoystickButton(operatorController, Button.kX.value).whenHeld(new ShootIndexGroup(shooter, indexer));
    
    // Climber button configs TODO: please, let's make this differently i hate this
    new JoystickButton(operatorController, Button.kA.value).whenPressed(new ClimbUpGroup(climber));
    new JoystickButton(operatorController, Button.kA.value).whenReleased(new ClimbBrakeGroup(climber));
    new JoystickButton(operatorController, Button.kB.value).whenPressed(new ClimbDownGroup(climber));
    new JoystickButton(operatorController, Button.kB.value).whenReleased(new ClimbBrakeGroup(climber));
  }
}

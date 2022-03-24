package frc.robot.command_groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.ClimberConstants;
import frc.robot.commands.ClimberBrake;
import frc.robot.commands.ClimberMotorHalt;
import frc.robot.subsystems.Climber;

public class ClimbBrakeGroup extends SequentialCommandGroup {

    public ClimbBrakeGroup(Climber subsystem) {
        addCommands(
            new ClimberMotorHalt(subsystem),
    
            new WaitCommand(ClimberConstants.brakeTime),
            
            new ClimberBrake(subsystem)
        );
    }
}

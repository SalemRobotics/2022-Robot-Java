package frc.robot.command_groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ClimberBrake;
import frc.robot.commands.ClimberMotorHalt;
import frc.robot.subsystems.Climber;

public class ClimbBrakeGroup extends SequentialCommandGroup {

    public ClimbBrakeGroup(Climber subsystem) {
        addCommands(
            new ClimberMotorHalt(subsystem),
    
            new WaitCommand(0.5),
            
            new ClimberBrake(subsystem)
        );
    }
}

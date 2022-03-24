package frc.robot.command_groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ClimbDown;
import frc.robot.commands.ClimberBrakeRelease;
import frc.robot.subsystems.Climber;

public class ClimbDownGroup extends SequentialCommandGroup {
    
    public ClimbDownGroup(Climber subsytem) {
        addCommands(
            new ClimberBrakeRelease(subsytem),

            new WaitCommand(0.5),

            new ClimbDown(subsytem)
        );
    }
}

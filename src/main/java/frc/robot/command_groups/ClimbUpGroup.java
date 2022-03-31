package frc.robot.command_groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.ClimberConstants;
import frc.robot.commands.ClimbUp;
import frc.robot.commands.ClimberBrakeRelease;
import frc.robot.subsystems.Climber;

public class ClimbUpGroup extends SequentialCommandGroup {

    public ClimbUpGroup(Climber subsystem) {
        addCommands(
            new ClimberBrakeRelease(subsystem),
    
            new WaitCommand(ClimberConstants.brakeTime),
    
            new ClimbUp(subsystem)
        );      
    }
    
}
package frc.robot.command_groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ClimbUp;
import frc.robot.commands.ClimberBrakeRelease;
import frc.robot.subsystems.Climber;

public class ClimbUpGroup extends SequentialCommandGroup {

    public ClimbUpGroup(Climber subsystem) {
        addCommands(
            // Drive forward the specified distance
            new ClimberBrakeRelease(
                subsystem),
    
            // Release the hatch
            new WaitCommand(0.5),
    
            // Drive backward the specified distance
            new ClimbUp(
                subsystem));
      
    }
    
}
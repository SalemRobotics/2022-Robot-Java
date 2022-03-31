package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ClimberMotorHalt extends CommandBase {
    
    private final Climber climber;

    public ClimberMotorHalt(Climber subsystem) {
        climber = subsystem;
        addRequirements(subsystem);
    }

    // Sets climber motors to run at full speed.
    @Override
    public void execute() {
        climber.halt();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
  
}

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ClimberSpool extends CommandBase
{
    final Climber climber;

    public ClimberSpool(Climber subsystem)
    {
        climber = subsystem;
        addRequirements(climber);
    }

    @Override
    public void execute() 
    {
        climber.climb(0.5);
    }

    @Override
    public boolean isFinished() 
    {
        return false;
    }

    @Override
    public void end(boolean interrupted) 
    {
        climber.climb(0.0);
    }
}

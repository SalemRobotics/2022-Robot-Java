package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeIn extends CommandBase{
    private final Intake intake;
    private final double speed;

    public IntakeIn(Intake subsystem) {
        intake = subsystem;
        this.speed = 1.0;

        addRequirements(intake);
    }

    public IntakeIn(Intake subsystem, double speed) {
        intake = subsystem;
        this.speed = speed;

        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.extend();
        intake.collect(speed);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        intake.retract();
        intake.halt();
    }
}

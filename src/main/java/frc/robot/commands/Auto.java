package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class Auto extends CommandBase {
    private final Drivetrain drivetrain;
    private final Timer timer;
    public Auto(Drivetrain subsystem) {
        drivetrain = subsystem;
        timer = new Timer();
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        drivetrain.tankDriveVolts(0.3);
    }

    @Override
    public boolean isFinished() {
        if (timer.hasElapsed(2.0)) return true;
        return false;
    }
}

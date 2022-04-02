package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class Auto extends CommandBase {
    private final Drivetrain drivetrain;
    private final Timer timer;
    private final double speed;
    private final double time;
    public Auto(Drivetrain subsystem, double speed) {
        drivetrain = subsystem;
        timer = new Timer();
        this.speed = speed;
        this.time = 3.0;
        addRequirements(drivetrain);
    }

    public Auto(Drivetrain subsystem, double speed, double time) {
        drivetrain = subsystem;
        timer = new Timer();
        this.speed = speed;
        this.time = time;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        drivetrain.tankDriveVolts(speed);
    }

    @Override
    public boolean isFinished() {
        if (timer.hasElapsed(time)) return true;
        return false;
    }
}
